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
public class Nemerle implements Language {

    String KEYWORDS[] = new String[]{"_", "abstract", "and", "array", "as", "base", "catch", "class", "def", "do", "else", "extends", "extern", "finally", "foreach", "for", "fun", "if", "implements", "in", "interface", "internal", "lock", "macro", "match", "module", "mutable", "namespace", "new", "out", "override", "params", "private", "protected", "public", "ref", "repeat", "sealed", "static", "struct", "syntax", "this", "throw", "try", "type", "typeof", "unless", "until", "using", "variant", "virtual", "when", "where", "while"};
    String TYPES[] = new String[]{"bool", "byte", "char", "decimal", "double", "float", "int", "list", "long", "object", "sbyte", "short", "string", "uint", "ulong", "ushort", "void"};
    String NULL_VALUE[] = new String[]{"null"};
    String BOOLEAN[] = new String[]{"false", "true"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q//\\E[^\\n]*|\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String TYPES_PATTERN = "\\b(" + String.join("|", TYPES) + ")\\b";
        String NULL_VALUE_PATTERN = "\\b(" + String.join("|", NULL_VALUE) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<TYPES>" + TYPES_PATTERN + ")"
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
                : matcher.group("TYPES") != null ? "types"
                : matcher.group("NULLVALUE") != null ? "null-value"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(TYPES));
        keywordList.addAll(Arrays.asList(NULL_VALUE));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        Collections.sort(keywordList);
        return keywordList;
    }

}
