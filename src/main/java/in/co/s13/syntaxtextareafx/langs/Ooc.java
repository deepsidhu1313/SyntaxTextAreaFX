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
public class Ooc implements Language {

    String EXTERNALS[] = new String[]{"import", "include", "use"};
    String DECLARATIONS[] = new String[]{"class", "cover", "func", "implement", "interface", "operator"};
    String PRIMITIVE_TYPES[] = new String[]{"Int", "Int8", "Int16", "Int32", "Int64", "Int80", "Int128", "UInt", "UInt8", "UInt16", "UInt32", "UInt64", "UInt80", "UInt128", "Octet", "Short", "UShort", "Long", "ULong", "LLong", "ULLong", "Float", "Double", "LDouble", "Float32", "Float64", "Float128", "Char", "UChar", "SChar", "WChar", "String", "Void", "Pointer", "Bool", "SizeT", "This"};
    String STORAGE_CLASS[] = new String[]{"abstract", "static", "final", "extern", "const", "proto"};
    String SCOPE_DECLARATIONS[] = new String[]{"private", "protected", "public", "internal"};
    String FLOW[] = new String[]{"as", "break", "continue", "else", "fallthrough", "finally", "for", "if", "match", "return", "while"};
    String MEMORY[] = new String[]{"new", "this"};
    String FUTURE_RESERVED_WORDS[] = new String[]{"catch", "const", "goto", "finally", "throw", "try", "with", "scope"};
    String NULL_VALUE[] = new String[]{"null"};
    String BOOLEAN[] = new String[]{"false", "true"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q//\\E[^\\n]*|\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String EXTERNALS_PATTERN = "\\b(" + String.join("|", EXTERNALS) + ")\\b";
        String DECLARATIONS_PATTERN = "\\b(" + String.join("|", DECLARATIONS) + ")\\b";
        String PRIMITIVE_TYPES_PATTERN = "\\b(" + String.join("|", PRIMITIVE_TYPES) + ")\\b";
        String STORAGE_CLASS_PATTERN = "\\b(" + String.join("|", STORAGE_CLASS) + ")\\b";
        String SCOPE_DECLARATIONS_PATTERN = "\\b(" + String.join("|", SCOPE_DECLARATIONS) + ")\\b";
        String FLOW_PATTERN = "\\b(" + String.join("|", FLOW) + ")\\b";
        String MEMORY_PATTERN = "\\b(" + String.join("|", MEMORY) + ")\\b";
        String FUTURE_RESERVED_WORDS_PATTERN = "\\b(" + String.join("|", FUTURE_RESERVED_WORDS) + ")\\b";
        String NULL_VALUE_PATTERN = "\\b(" + String.join("|", NULL_VALUE) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<EXTERNALS>" + EXTERNALS_PATTERN + ")"
                + "|(?<DECLARATIONS>" + DECLARATIONS_PATTERN + ")"
                + "|(?<PRIMITIVETYPES>" + PRIMITIVE_TYPES_PATTERN + ")"
                + "|(?<STORAGECLASS>" + STORAGE_CLASS_PATTERN + ")"
                + "|(?<SCOPEDECLARATIONS>" + SCOPE_DECLARATIONS_PATTERN + ")"
                + "|(?<FLOW>" + FLOW_PATTERN + ")"
                + "|(?<MEMORY>" + MEMORY_PATTERN + ")"
                + "|(?<FUTURERESERVEDWORDS>" + FUTURE_RESERVED_WORDS_PATTERN + ")"
                + "|(?<NULLVALUE>" + NULL_VALUE_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("EXTERNALS") != null ? "externals"
                : matcher.group("DECLARATIONS") != null ? "declarations"
                : matcher.group("PRIMITIVETYPES") != null ? "primitive-types"
                : matcher.group("STORAGECLASS") != null ? "storage-class"
                : matcher.group("SCOPEDECLARATIONS") != null ? "scope-declarations"
                : matcher.group("FLOW") != null ? "flow"
                : matcher.group("MEMORY") != null ? "memory"
                : matcher.group("FUTURERESERVEDWORDS") != null ? "future-reserved-words"
                : matcher.group("NULLVALUE") != null ? "null-value"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(EXTERNALS));
        keywordList.addAll(Arrays.asList(DECLARATIONS));
        keywordList.addAll(Arrays.asList(PRIMITIVE_TYPES));
        keywordList.addAll(Arrays.asList(STORAGE_CLASS));
        keywordList.addAll(Arrays.asList(SCOPE_DECLARATIONS));
        keywordList.addAll(Arrays.asList(FLOW));
        keywordList.addAll(Arrays.asList(MEMORY));
        keywordList.addAll(Arrays.asList(FUTURE_RESERVED_WORDS));
        keywordList.addAll(Arrays.asList(NULL_VALUE));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        Collections.sort(keywordList);
        return keywordList;
    }

}
