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
public class Matlab implements Language {

    String BUILTIN[] = new String[]{"abs", "acos", "asin", "atan2", "atan", "ceil", "conv", "cosh", "cos", "cumprod", "dims", "error", "fclose", "floor", "fopen", "fprintf", "fread", "fsolve", "imag", "isempty", "isinf", "islogical", "ismatrix", "isnan", "isna", "isnumeric", "isscalar", "isstr", "isvector", "length", "linspace", "log10", "log2", "log", "max", "min", "printf", "prod", "real", "rem", "repmat", "reshape", "round", "setstr", "sinh", "sin", "size", "sort", "sprintf", "sqrt", "strcat", "strcmp", "sum", "system", "tanh", "tan", "unlink", "warning"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q%\\E[^\\n]*|\\Q%{\\E[\\s\\S]*?\\Q%}\\E";
        String BUILTIN_PATTERN = "\\b(" + String.join("|", BUILTIN) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<BUILTIN>" + BUILTIN_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("BUILTIN") != null ? "builtin"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(BUILTIN));
        Collections.sort(keywordList);
        return keywordList;
    }

}
