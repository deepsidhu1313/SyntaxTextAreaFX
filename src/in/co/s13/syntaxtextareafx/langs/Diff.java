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
public class Diff implements Language {

    @Override
    public Pattern generatePattern() {
        Pattern pattern;
        String ADDITION_PATTERN;
        ADDITION_PATTERN = "\\+[^\n]*" + "|" + "\\>[^\n]*";
        String REM_PATTERN;
        REM_PATTERN = "\\-[^\n]*" + "|" + "\\<[^\n]*";
        String DIFF_PATTERN;
        DIFF_PATTERN = "\\%[^\n]*";
        String CHANGE_PATTERN;
        CHANGE_PATTERN = "\\![^\n]*";
        String COMMON_PATTERN="\\@[^\n]*";
        pattern = Pattern.compile(
                "(?<ADD>" + ADDITION_PATTERN + ")"
                + "|(?<REM>" + REM_PATTERN + ")"
                + "|(?<DIFF>" + DIFF_PATTERN + ")"
                + "|(?<CHANGE>" + CHANGE_PATTERN + ")"
                + "|(?<COMMON>" + COMMON_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("ADD") != null ? "add"
                : matcher.group("REM") != null ? "rem"
                : matcher.group("DIFF") != null ? "diff"
                : matcher.group("CHANGE") != null ? "change"
                : matcher.group("COMMON") != null ? "common"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        Collections.sort(keywordList);
        return keywordList;
    }

}
