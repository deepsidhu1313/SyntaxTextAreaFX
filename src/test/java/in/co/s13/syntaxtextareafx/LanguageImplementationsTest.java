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

import in.co.s13.syntaxtextareafx.meta.Language;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.DynamicTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * Every {@link Language} implementation, generated or hand-written.
 *
 * <p>Bulk-generating a language's rules from its gtksourceview JSON
 * definition can produce a syntactically invalid regex without any compiler
 * catching it: {@code Pattern.compile} is a runtime call. This is what
 * happened for the "J" language, whose keyword list embeds a gtksourceview
 * template placeholder ({@code %{valid-name}}) that Java's regex engine reads
 * as an illegal repetition — which is why {@code J.java} was never wired into
 * {@code LANGS}. This test compiles every implementation's pattern up front
 * so a similarly broken language fails here instead of when a caller selects
 * it.
 */
class LanguageImplementationsTest {

    private static List<Class<?>> languageImplementations() throws Exception {
        String pkgPath = "in/co/s13/syntaxtextareafx/langs";
        URL dirUrl = LanguageImplementationsTest.class.getClassLoader().getResource(pkgPath);
        List<Class<?>> classes = new ArrayList<>();
        for (File file : new File(dirUrl.toURI()).listFiles((d, name) -> name.endsWith(".class"))) {
            if (file.getName().equals("package-info.class")) {
                continue;
            }
            String className = "in.co.s13.syntaxtextareafx.langs."
                    + file.getName().substring(0, file.getName().length() - ".class".length());
            classes.add(Class.forName(className));
        }
        return classes;
    }

    @TestFactory
    List<DynamicTest> everyLanguagesPatternCompilesAndMatches() throws Exception {
        List<DynamicTest> tests = new ArrayList<>();
        for (Class<?> cls : languageImplementations()) {
            tests.add(dynamicTest(cls.getSimpleName(), () -> {
                Language language = (Language) cls.getDeclaredConstructor().newInstance();
                Pattern pattern = assertDoesNotThrow(language::generatePattern,
                        cls.getSimpleName() + "'s pattern must compile");

                // A grab-bag of tokens likely to trip at least one alternative
                // in most languages' patterns (string, comment, punctuation,
                // a bare word). getStyleClass() must handle every match
                // Matcher.group() is asked for, since not every language
                // declares the same set of named groups.
                Matcher matcher = pattern.matcher(
                        "\"x\" 'y' // c\n/* c */ class if 123 + - ( ) { } ; ,");
                while (matcher.find()) {
                    assertDoesNotThrow(() -> language.getStyleClass(matcher),
                            cls.getSimpleName() + "'s getStyleClass must not reference a "
                            + "capture group that generatePattern did not declare");
                }
                assertDoesNotThrow(language::getKeywords);
            }));
        }
        return tests;
    }
}
