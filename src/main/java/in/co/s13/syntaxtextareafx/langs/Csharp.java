/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.s13.syntaxtextareafx.langs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import in.co.s13.syntaxtextareafx.meta.Language;
import java.util.Collections;

/**
 *
 * @author nika
 */
public class Csharp implements Language {

    String KEYWORDS[] = new String[]{"async", "await", "class", "delegate", "enum", "event", "interface", "namespace", "struct", "using", "abstract", "const", "explicit", "extern", "fixed", "implicit", "internal", "lock", "out", "override", "params", "partial", "private", "protected", "public", "ref", "sealed", "static", "readonly", "unsafe", "virtual", "volatile", "add", "as", "assembly", "base", "break", "case", "catch", "checked", "continue", "default", "do", "else", "finally", "for", "foreach", "get", "goto", "if", "in", "is", "nameof", "new", "remove", "return", "set", "sizeof", "stackalloc", "super", "switch", "this", "throw", "try", "typeof", "unchecked", "value", "var", "void", "while", "yield"};
    String PRIMITIVES[] = new String[]{"bool", "byte", "char", "decimal", "double", "dynamic", "float", "int", "long", "object", "operator", "sbyte", "short", "string", "uint", "ulong", "ushort"};
    String NULL_VALUE[] = new String[]{"null"};
    String BOOLEAN[] = new String[]{"false", "true"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q//\\E[^\\n]*|\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String PRIMITIVES_PATTERN = "\\b(" + String.join("|", PRIMITIVES) + ")\\b";
        String NULL_VALUE_PATTERN = "\\b(" + String.join("|", NULL_VALUE) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<PRIMITIVES>" + PRIMITIVES_PATTERN + ")"
                + "|(?<NULLVALUE>" + NULL_VALUE_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("PRIMITIVES") != null ? "primitives"
                : matcher.group("NULLVALUE") != null ? "null-value"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(PRIMITIVES));
        keywordList.addAll(Arrays.asList(NULL_VALUE));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        Collections.sort(keywordList);
        return keywordList;
    }

}
