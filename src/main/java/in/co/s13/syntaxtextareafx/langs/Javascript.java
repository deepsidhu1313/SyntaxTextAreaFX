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
package in.co.s13.syntaxtextareafx.langs;

import in.co.s13.syntaxtextareafx.meta.Language;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JavaScript, through ES2022.
 *
 * <p>Requested in issue #6, where selecting {@code LANGS.javascript} silently
 * did nothing because no rules existed for it.
 *
 * <p>Three things differ from the Java rules this is modelled on:
 * template literals are a third kind of string, {@code //} comments must not
 * swallow the {@code //} inside a URL in a string, and the keyword set has no
 * primitive types — {@code typeof} names are ordinary identifiers.
 *
 * <p>Patterns avoid alternation inside a quantifier throughout. Java's regex
 * engine recurses per repetition of a group, which is what made long comments
 * throw {@code StackOverflowError} in issue #5.
 */
public class Javascript implements Language {

    String DECLARATIONS[] = new String[]{
        "class", "extends", "function", "get", "set", "static"};
    String STORAGECLASS[] = new String[]{
        "const", "let", "var"};
    String EXTERNALS[] = new String[]{
        "as", "export", "from", "import", "require"};
    String FLOW[] = new String[]{
        "await", "break", "case", "catch", "continue", "default", "do", "else",
        "finally", "for", "if", "return", "switch", "throw", "try", "while",
        "yield"};
    String MEMORY[] = new String[]{
        "delete", "in", "instanceof", "new", "of", "super", "this", "typeof",
        "void", "with"};
    String FUTURE[] = new String[]{
        "async", "debugger", "enum", "implements", "interface", "package",
        "private", "protected", "public"};
    String NULL[] = new String[]{"null", "undefined", "NaN"};
    String BOOLEAN[] = new String[]{"true", "false"};

    @Override
    public Pattern generatePattern() {
        String DECLARATIONS_PATTERN = "\\b(" + String.join("|", DECLARATIONS) + ")\\b";
        String STORAGECLASS_PATTERN = "\\b(" + String.join("|", STORAGECLASS) + ")\\b";
        String EXTERNALS_PATTERN = "\\b(" + String.join("|", EXTERNALS) + ")\\b";
        String FLOW_PATTERN = "\\b(" + String.join("|", FLOW) + ")\\b";
        String MEMORY_PATTERN = "\\b(" + String.join("|", MEMORY) + ")\\b";
        String FUTURE_PATTERN = "\\b(" + String.join("|", FUTURE) + ")\\b";
        String NULL_PATTERN = "\\b(" + String.join("|", NULL) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
        String PAREN_PATTERN = "\\(|\\)";
        String BRACE_PATTERN = "\\{|\\}";
        String BRACKET_PATTERN = "\\[|\\]";
        String SEMICOLON_PATTERN = "\\;";

        // Unrolled with possessive quantifiers: no nested quantifier, so no
        // recursion however long the literal is. Single, double and template
        // quoting are separate alternatives rather than one group repeated.
        String STRING_PATTERN
                = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\""
                + "|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'"
                + "|`[^`\\\\]*+(?:\\\\.[^`\\\\]*+)*+`";

        // [\s\S] rather than (.|\R): a character class, so it cannot recurse.
        String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*[\\s\\S]*?\\*/";

        // Strings come before comments so that "https://example.com" inside a
        // literal is not treated as the start of a line comment.
        return Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<DECLARATIONS>" + DECLARATIONS_PATTERN + ")"
                + "|(?<STORAGECLASS>" + STORAGECLASS_PATTERN + ")"
                + "|(?<EXTERNALS>" + EXTERNALS_PATTERN + ")"
                + "|(?<FLOW>" + FLOW_PATTERN + ")"
                + "|(?<MEMORY>" + MEMORY_PATTERN + ")"
                + "|(?<FUTURE>" + FUTURE_PATTERN + ")"
                + "|(?<NULL>" + NULL_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
                + "|(?<PAREN>" + PAREN_PATTERN + ")"
                + "|(?<BRACE>" + BRACE_PATTERN + ")"
                + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
        );
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("DECLARATIONS") != null ? "declarations"
                : matcher.group("STORAGECLASS") != null ? "storageclass"
                : matcher.group("EXTERNALS") != null ? "externals"
                : matcher.group("FLOW") != null ? "flow"
                : matcher.group("MEMORY") != null ? "memory"
                : matcher.group("FUTURE") != null ? "future"
                : matcher.group("NULL") != null ? "nullvalue"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : matcher.group("PAREN") != null ? "paren"
                : matcher.group("BRACE") != null ? "brace"
                : matcher.group("BRACKET") != null ? "bracket"
                : matcher.group("SEMICOLON") != null ? "semicolon"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywords = new ArrayList<>();
        keywords.addAll(Arrays.asList(DECLARATIONS));
        keywords.addAll(Arrays.asList(STORAGECLASS));
        keywords.addAll(Arrays.asList(EXTERNALS));
        keywords.addAll(Arrays.asList(FLOW));
        keywords.addAll(Arrays.asList(MEMORY));
        keywords.addAll(Arrays.asList(FUTURE));
        keywords.addAll(Arrays.asList(NULL));
        keywords.addAll(Arrays.asList(BOOLEAN));
        Collections.sort(keywords);
        return keywords;
    }
}
