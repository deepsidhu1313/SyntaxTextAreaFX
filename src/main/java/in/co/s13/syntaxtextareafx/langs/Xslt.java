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
public class Xslt implements Language {

    String ELEMENTS[] = new String[]{"analyze-string", "apply-imports", "apply-templates", "attribute-set", "attribute", "call-template", "character-map", "choose", "comment", "copy-of", "copy", "decimal-format", "document", "element", "fallback", "for-each-group", "for-each", "function", "if", "import-schema", "import", "include", "key", "matching-substring", "message", "namespace-alias", "namespace", "number", "next-match", "non-matching-substring", "otherwise", "output-character", "output", "param", "perform-sort", "preserve-space", "processing-instruction", "result-document", "script", "sequence", "sort", "strip-space", "stylesheet", "template", "text", "transform", "value-of", "variable", "when", "with-param"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q<!--\\E[\\s\\S]*?\\Q-->\\E";
        String ELEMENTS_PATTERN = "\\b(" + String.join("|", ELEMENTS) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<ELEMENTS>" + ELEMENTS_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("ELEMENTS") != null ? "elements"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(ELEMENTS));
        Collections.sort(keywordList);
        return keywordList;
    }

}
