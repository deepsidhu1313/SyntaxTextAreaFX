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
public class GdbLog implements Language {

    String OPTIMIZED_OUT[] = new String[]{"value optimized out"};
    String INCOMPLETE_SEQUENCE[] = new String[]{"incomplete sequence"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String OPTIMIZED_OUT_PATTERN = "\\b(" + String.join("|", OPTIMIZED_OUT) + ")\\b";
        String INCOMPLETE_SEQUENCE_PATTERN = "\\b(" + String.join("|", INCOMPLETE_SEQUENCE) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<OPTIMIZEDOUT>" + OPTIMIZED_OUT_PATTERN + ")"
                + "|(?<INCOMPLETESEQUENCE>" + INCOMPLETE_SEQUENCE_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("OPTIMIZEDOUT") != null ? "optimized-out"
                : matcher.group("INCOMPLETESEQUENCE") != null ? "incomplete-sequence"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(OPTIMIZED_OUT));
        keywordList.addAll(Arrays.asList(INCOMPLETE_SEQUENCE));
        Collections.sort(keywordList);
        return keywordList;
    }

}
