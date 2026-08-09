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
public class Vala implements Language {

    String KEYWORDS[] = new String[]{"class", "delegate", "enum", "errordomain", "interface", "namespace", "signal", "struct", "using", "abstract", "async", "const", "dynamic", "extern", "inline", "internal", "out", "override", "owned", "private", "protected", "public", "ref", "static", "unowned", "virtual", "volatile", "weak", "as", "base", "break", "case", "catch", "construct", "continue", "default", "delete", "do", "else", "ensures", "finally", "for", "foreach", "get", "if", "in", "is", "lock", "new", "params", "requires", "return", "set", "sizeof", "switch", "this", "throw", "throws", "try", "typeof", "value", "var", "while", "yield", "yields"};
    String PRIMITIVES[] = new String[]{"bool", "char", "double", "float", "int", "int8", "int16", "int32", "int64", "long", "short", "size_t", "ssize_t", "string", "uchar", "uint", "uint8", "uint16", "uint32", "uint64", "ulong", "unichar", "ushort", "void"};
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
