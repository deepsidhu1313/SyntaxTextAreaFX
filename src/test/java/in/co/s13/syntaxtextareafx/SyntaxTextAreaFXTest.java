/*
 * Copyright (C) 2026 Navdeep Singh Sidhu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
    @ValueSource(strings = {"README", "Makefile", "noextension"})
    void filesWithoutAnExtensionOpenAsPlainText(String fileName) {
        assertEquals(LANGS.text, SyntaxTextAreaFX.languageForFile(fileName));
    }

    @Test
    void anUnknownExtensionOpensAsPlainTextRatherThanFailing() {
        // An editor must be openable on any file, known language or not.
        assertEquals(LANGS.text, SyntaxTextAreaFX.languageForFile("archive.zzz"));
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
}
