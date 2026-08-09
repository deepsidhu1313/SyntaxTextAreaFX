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
public class Makefile implements Language {

    String DIRECTIVES[] = new String[]{"define", "else", "endef", "endif", "if", "ifdef", "ifeq", "ifndef", "ifneq", "include", "override", "unexport"};
    String FUNCTIONS[] = new String[]{"addprefix", "addsuffix", "basename", "call", "dir", "error", "filter", "filter-out", "findstring", "firstword", "foreach", "join", "notdir", "origin", "patsubst", "shell", "sort", "strip", "subst", "suffix", "warning", "wildcard", "word", "words"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q#\\E[^\\n]*";
        String DIRECTIVES_PATTERN = "\\b(" + String.join("|", DIRECTIVES) + ")\\b";
        String FUNCTIONS_PATTERN = "\\b(" + String.join("|", FUNCTIONS) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<DIRECTIVES>" + DIRECTIVES_PATTERN + ")"
                + "|(?<FUNCTIONS>" + FUNCTIONS_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("DIRECTIVES") != null ? "directives"
                : matcher.group("FUNCTIONS") != null ? "functions"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(DIRECTIVES));
        keywordList.addAll(Arrays.asList(FUNCTIONS));
        Collections.sort(keywordList);
        return keywordList;
    }

}
