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
public class Sml implements Language {

    String BOOLEAN[] = new String[]{"true", "false"};
    String KEYWORDS[] = new String[]{"and", "abstype", "as", "case", "datatype", "else", "end", "eqtype", "exception", "do", "fn", "fun", "functor", "funsig", "handle", "if", "in", "include", "infix", "infixr", "lazy", "let", "local", "nonfix", "of", "op", "open", "overload", "raise", "rec", "sharing", "sig", "signature", "struct", "structure", "then", "type", "val", "where", "while", "with", "withtype", "orelse", "andalso"};
    String TOP_LEVEL_TYPES[] = new String[]{"unit", "int", "word", "real", "char", "string", "substring", "exn", "array", "vector", "ref", "bool", "option", "order", "list"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q(*\\E[\\s\\S]*?\\Q*)\\E";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String TOP_LEVEL_TYPES_PATTERN = "\\b(" + String.join("|", TOP_LEVEL_TYPES) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<TOPLEVELTYPES>" + TOP_LEVEL_TYPES_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("TOPLEVELTYPES") != null ? "top-level-types"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(BOOLEAN));
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(TOP_LEVEL_TYPES));
        Collections.sort(keywordList);
        return keywordList;
    }

}
