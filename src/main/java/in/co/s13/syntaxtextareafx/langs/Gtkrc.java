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
public class Gtkrc implements Language {

    String KEYWORD[] = new String[]{"style", "engine", "widget_class", "class", "widget"};
    String VARIABLE[] = new String[]{"fg", "bg", "bg_pixmap", "base", "text"};
    String STATE[] = new String[]{"ACTIVE", "SELECTED", "NORMAL", "PRELIGHT", "INSENSITIVE"};
    String INCLUDE_DIRECTIVE[] = new String[]{"include"};
    String BOOLEAN_VALUE[] = new String[]{"true", "false"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q#\\E[^\\n]*";
        String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORD) + ")\\b";
        String VARIABLE_PATTERN = "\\b(" + String.join("|", VARIABLE) + ")\\b";
        String STATE_PATTERN = "\\b(" + String.join("|", STATE) + ")\\b";
        String INCLUDE_DIRECTIVE_PATTERN = "\\b(" + String.join("|", INCLUDE_DIRECTIVE) + ")\\b";
        String BOOLEAN_VALUE_PATTERN = "\\b(" + String.join("|", BOOLEAN_VALUE) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                + "|(?<VARIABLE>" + VARIABLE_PATTERN + ")"
                + "|(?<STATE>" + STATE_PATTERN + ")"
                + "|(?<INCLUDEDIRECTIVE>" + INCLUDE_DIRECTIVE_PATTERN + ")"
                + "|(?<BOOLEANVALUE>" + BOOLEAN_VALUE_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("KEYWORD") != null ? "keyword"
                : matcher.group("VARIABLE") != null ? "variable"
                : matcher.group("STATE") != null ? "state"
                : matcher.group("INCLUDEDIRECTIVE") != null ? "include-directive"
                : matcher.group("BOOLEANVALUE") != null ? "boolean-value"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(KEYWORD));
        keywordList.addAll(Arrays.asList(VARIABLE));
        keywordList.addAll(Arrays.asList(STATE));
        keywordList.addAll(Arrays.asList(INCLUDE_DIRECTIVE));
        keywordList.addAll(Arrays.asList(BOOLEAN_VALUE));
        Collections.sort(keywordList);
        return keywordList;
    }

}
