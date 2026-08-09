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
public class NemoAction implements Language {

    String BOOLEAN[] = new String[]{"true", "false"};
    String ENCODING[] = new String[]{"UTF\\-8", "Legacy\\-Mixed"};
    String STANDARD_KEY[] = new String[]{"Active", "Name", "Comment", "Exec", "Icon-Name", "Stock-Id", "Selection", "Extensions", "Mimetypes", "Separator", "Quote", "Dependencies", "Conditions", "EscapeSpaces"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q#\\E[^\\n]*";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
        String ENCODING_PATTERN = "\\b(" + String.join("|", ENCODING) + ")\\b";
        String STANDARD_KEY_PATTERN = "\\b(" + String.join("|", STANDARD_KEY) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
                + "|(?<ENCODING>" + ENCODING_PATTERN + ")"
                + "|(?<STANDARDKEY>" + STANDARD_KEY_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : matcher.group("ENCODING") != null ? "encoding"
                : matcher.group("STANDARDKEY") != null ? "standard-key"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(BOOLEAN));
        keywordList.addAll(Arrays.asList(ENCODING));
        keywordList.addAll(Arrays.asList(STANDARD_KEY));
        Collections.sort(keywordList);
        return keywordList;
    }

}
