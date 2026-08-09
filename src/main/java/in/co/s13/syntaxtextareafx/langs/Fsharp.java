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
public class Fsharp implements Language {

    String BOOLEAN_CONSTANT[] = new String[]{"true", "false"};
    String KEYSYMBOL[] = new String[]{"\\.\\.", "::", "=", "@", "~", "->", "|", ":?", ":?>", "^", "<-", "&&", "&"};
    String KEYWORDS[] = new String[]{"abstract", "and", "as", "assert", "asr", "begin", "class", "default", "delegate", "do", "done", "downcast", "downto", "else", "end", "enum", "exception", "false", "finaly", "for", "fun", "function", "if", "in", "iherit", "interface", "land", "lazy", "let", "lor", "lsl", "lsr", "lxor", "match", "member", "mod", "module", "mutable", "namespace", "new", "null", "of", "open", "or", "override", "sig", "static", "struct", "then", "to", "true", "try", "type", "val", "when", "inline", "upcast", "while", "with", "async", "atomic", "break", "checked", "component", "const", "constructor", "continue", "eager", "event", "external", "fixed", "functor", "include", "method", "mixin", "process", "property", "protected", "public", "pure", "readonly", "return", "sealed", "switch", "virtual", "void", "volatile", "yield", "where"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q//\\E[^\\n]*|\\Q(*\\E[\\s\\S]*?\\Q*)\\E";
        String BOOLEAN_CONSTANT_PATTERN = "\\b(" + String.join("|", BOOLEAN_CONSTANT) + ")\\b";
        String KEYSYMBOL_PATTERN = "\\b(" + String.join("|", KEYSYMBOL) + ")\\b";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<BOOLEANCONSTANT>" + BOOLEAN_CONSTANT_PATTERN + ")"
                + "|(?<KEYSYMBOL>" + KEYSYMBOL_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("BOOLEANCONSTANT") != null ? "boolean-constant"
                : matcher.group("KEYSYMBOL") != null ? "keysymbol"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(BOOLEAN_CONSTANT));
        keywordList.addAll(Arrays.asList(KEYSYMBOL));
        keywordList.addAll(Arrays.asList(KEYWORDS));
        Collections.sort(keywordList);
        return keywordList;
    }

}
