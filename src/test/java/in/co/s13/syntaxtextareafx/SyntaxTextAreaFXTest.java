package in.co.s13.syntaxtextareafx;

import in.co.s13.syntaxtextareafx.SyntaxTextAreaFX.CONSTANTS.LANGS;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Language selection and shared-state guarantees.
 *
 * <p>Deliberately exercises only the static surface. {@code SyntaxTextAreaFX}
 * extends {@code CodeArea}, so constructing one starts the JavaFX toolkit and
 * needs a display; keeping these tests headless means they run in CI on every
 * platform.
 */
class SyntaxTextAreaFXTest {

    @ParameterizedTest
    @CsvSource({
        "Main.java,      java",
        "script.python,  python",
        "notes.txt,      text",
        "Build.cmake,    cmake",
        "sheet.css,      css",
        "page.html,      html"
    })
    void picksTheLanguageFromTheFileExtension(String fileName, String expected) {
        assertEquals(expected, SyntaxTextAreaFX.languageForFile(fileName).name());
    }

    @Test
    void isNotConfusedByDirectoriesInThePath() {
        assertEquals(LANGS.java,
                SyntaxTextAreaFX.languageForFile("/home/user/my.python.project/Main.java"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"README", "noextension"})
    void filesWithoutAnExtensionOpenAsPlainText(String fileName) {
        assertEquals(LANGS.text, SyntaxTextAreaFX.languageForFile(fileName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Makefile", "makefile", "GNUmakefile", "gnumakefile"})
    void makefilesAreRecognisedByNameSinceTheyHaveNoExtension(String fileName) {
        assertEquals(LANGS.makefile, SyntaxTextAreaFX.languageForFile(fileName));
        assertEquals(LANGS.makefile, SyntaxTextAreaFX.languageForFile("/project/" + fileName));
    }

    @Test
    void anUnknownExtensionOpensAsPlainTextRatherThanFailing() {
        // An editor must be openable on any file, known language or not.
        assertEquals(LANGS.text, SyntaxTextAreaFX.languageForFile("archive.zzz"));
    }

    @ParameterizedTest
    @CsvSource({
        // The extension SyntaxTextAreaFX 1.2.0's consolidated extension
        // mapping carried forward from the FILE_TYPES mechanism it replaced.
        "script.py,   python",
        "lib.rb,      ruby",
        "app.js,      javascript",
        "notes.md,    markdown",
        "values.yml,  yaml",
        "Header.h,    chdr",
        "impl.cxx,    cpp",
        "impl.cc,     cpp",
        "Sprite.m,    objc",
        "spec.ads,    ada",
        "body.adb,    ada",
        "changes.patch, diff",
        "code.4th,    forth"
    })
    void commonExtensionsThatDontMatchALanguageNameResolveViaTheAliasTable(String fileName, String expected) {
        assertEquals(expected, SyntaxTextAreaFX.languageForFile(fileName).name());
    }

    @Test
    void aTrailingDotIsNotAnExtension() {
        assertEquals(LANGS.text, SyntaxTextAreaFX.languageForFile("weird."));
    }

    @Test
    void nullAndBlankNamesAreTolerated() {
        assertEquals(LANGS.text, SyntaxTextAreaFX.languageForFile(null));
        assertEquals(LANGS.text, SyntaxTextAreaFX.languageForFile("   "));
    }

    @Test
    void extensionMatchingIsCaseInsensitive() {
        assertEquals(LANGS.java, SyntaxTextAreaFX.languageForFile("Main.JAVA"));
        assertEquals(LANGS.java, SyntaxTextAreaFX.languageForFile("Main.Java"));
    }

    @Test
    void supportsReportsKnownExtensions() {
        assertTrue(SyntaxTextAreaFX.supports("java"));
        assertTrue(SyntaxTextAreaFX.supports("py"), "py is an alias, not a LANGS name, but still a known extension");
        assertFalse(SyntaxTextAreaFX.supports("definitely-not-a-language"));
    }

    @Test
    void aUsefulNumberOfLanguagesIsAvailable() {
        assertTrue(LANGS.values().length > 20,
                "only " + LANGS.values().length + " languages; the set looks truncated");
        assertTrue(java.util.Arrays.stream(LANGS.values()).anyMatch(l -> l == LANGS.text),
                "a plain-text fallback must exist");
    }

    /**
     * The bug this guards: the language was a static field assigned from an
     * instance setter, so two editors open on different files highlighted as
     * whichever was configured last — exactly how a multi-tab IDE uses this.
     */
    @Test
    void theLanguageIsPerEditorNotSharedAcrossAllOfThem() throws NoSuchFieldException {
        Field field = SyntaxTextAreaFX.class.getDeclaredField("CodingStyle");
        assertFalse(Modifier.isStatic(field.getModifiers()),
                "CodingStyle must not be static: two open editors would share one language");
    }

    /**
     * Anything an instance mutates must not be static, for the same reason.
     */
    @Test
    void noPerDocumentStateIsHeldStatically() {
        for (String name : new String[]{"CodingStyle", "filePath", "externalThemePath"}) {
            try {
                Field field = SyntaxTextAreaFX.class.getDeclaredField(name);
                assertFalse(Modifier.isStatic(field.getModifiers()),
                        name + " varies per document and must not be static");
            } catch (NoSuchFieldException expected) {
                // Field renamed or removed; nothing to guard.
            }
        }
    }

    // ---- which languages actually have rules (issue #6) ----

    @Test
    void reportsWhichLanguagesActuallyHaveRules() {
        // The enum lists far more languages than have highlighting written.
        // Callers need to be able to ask, rather than select one and get
        // silence.
        assertTrue(SyntaxTextAreaFX.isLanguageImplemented(LANGS.java));
        assertTrue(SyntaxTextAreaFX.isLanguageImplemented(LANGS.c));
        assertTrue(SyntaxTextAreaFX.isLanguageImplemented(LANGS.text));
        assertTrue(SyntaxTextAreaFX.isLanguageImplemented(LANGS.javascript));
        assertFalse(SyntaxTextAreaFX.isLanguageImplemented(LANGS.j),
                "j's keyword definitions embed gtksourceview template syntax "
                + "(\"%{valid-name}\") that isn't valid regex on its own, so no "
                + "rules were generated for it");
    }

    @Test
    void farMoreLanguagesAreDeclaredThanImplemented() {
        long implemented = java.util.Arrays.stream(LANGS.values())
                .filter(SyntaxTextAreaFX::isLanguageImplemented)
                .count();
        assertTrue(implemented > 0, "at least some languages must work");
        assertTrue(implemented < LANGS.values().length,
                "if this ever fails, every declared language has rules and the "
                + "isLanguageImplemented caveat can be dropped");
    }
}

