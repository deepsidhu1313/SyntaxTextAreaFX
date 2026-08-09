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
public class Modelica implements Language {

    String CLASS_TYPE[] = new String[]{"class", "block", "connector", "function", "model", "package", "record", "type"};
    String DATA_TYPE[] = new String[]{"Boolean", "enumeration", "ExternalObject", "Integer", "Real", "StateSelect", "String"};
    String DEFAULT_ATTRIBUTE[] = new String[]{"display", "fixed", "max", "min", "nominal", "quantity", "start", "stateSelect", "unit", "value"};
    String BOOLEAN[] = new String[]{"false", "true"};
    String KEYWORD[] = new String[]{"algorithm", "and", "annotation", "assert", "break", "connect", "constant", "constrainedby", "discrete", "else", "elseif", "elsewhen", "encapsulated", "end", "equation", "expandable", "extends", "external", "false", "final", "flow", "for", "if", "import", "in", "inner", "input", "loop", "not", "or", "outer", "output", "parameter", "partial", "protected", "public", "redeclare", "replaceable", "return", "then", "true", "when", "while", "within"};
    String BUILTIN[] = new String[]{"time", "abs", "ceil", "div", "floor", "integer", "mod", "rem", "sign", "sqrt", "sin", "cos", "tan", "asin", "acos", "atan", "atan2", "sinh", "cosh", "tanh", "exp", "log", "log10", "analysisType", "cardinality", "change", "delay", "der", "direction", "edge", "initial", "isPresent", "noEvent", "pre", "reinit", "sample", "semiLinear", "smooth", "terminal", "terminate", "ndims", "size", "scalar", "vector", "matrix", "array", "zeros", "ones", "fill", "identity", "diagonal", "linspace", "min", "max", "sum", "product", "transpose", "outerProduct", "symmetric", "cross", "skew", "cat"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q//\\E[^\\n]*|\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String CLASS_TYPE_PATTERN = "\\b(" + String.join("|", CLASS_TYPE) + ")\\b";
        String DATA_TYPE_PATTERN = "\\b(" + String.join("|", DATA_TYPE) + ")\\b";
        String DEFAULT_ATTRIBUTE_PATTERN = "\\b(" + String.join("|", DEFAULT_ATTRIBUTE) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
        String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORD) + ")\\b";
        String BUILTIN_PATTERN = "\\b(" + String.join("|", BUILTIN) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<CLASSTYPE>" + CLASS_TYPE_PATTERN + ")"
                + "|(?<DATATYPE>" + DATA_TYPE_PATTERN + ")"
                + "|(?<DEFAULTATTRIBUTE>" + DEFAULT_ATTRIBUTE_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
                + "|(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                + "|(?<BUILTIN>" + BUILTIN_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("CLASSTYPE") != null ? "class-type"
                : matcher.group("DATATYPE") != null ? "data-type"
                : matcher.group("DEFAULTATTRIBUTE") != null ? "default-attribute"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : matcher.group("KEYWORD") != null ? "keyword"
                : matcher.group("BUILTIN") != null ? "builtin"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(CLASS_TYPE));
        keywordList.addAll(Arrays.asList(DATA_TYPE));
        keywordList.addAll(Arrays.asList(DEFAULT_ATTRIBUTE));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        keywordList.addAll(Arrays.asList(KEYWORD));
        keywordList.addAll(Arrays.asList(BUILTIN));
        Collections.sort(keywordList);
        return keywordList;
    }

}
