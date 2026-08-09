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
public class Python implements Language {

    String SPECIAL_VARIABLES[] = new String[]{"self", "__name__", "__debug__"};
    String BOOLEAN[] = new String[]{"false", "true"};
    String MODULE_HANDLER[] = new String[]{"import", "from", "as"};
    String KEYWORDS[] = new String[]{"and", "assert", "break", "class", "continue", "def", "del", "elif", "else", "except", "finally", "for", "global", "if", "in", "is", "lambda", "not", "or", "pass", "raise", "return", "try", "while", "with", "yield"};
    String K_2X_ONLY_KEYWORDS[] = new String[]{"exec", "print"};
    String BUILTIN_CONSTANTS[] = new String[]{"Ellipsis", "None", "NotImplemented"};
    String BUILTIN_OBJECTS[] = new String[]{"ArithmeticError", "AssertionError", "AttributeError", "EnvironmentError", "EOFError", "Exception", "FloatingPointError", "ImportError", "IndentationError", "IndexError", "IOError", "KeyboardInterrupt", "KeyError", "LookupError", "MemoryError", "NameError", "NotImplementedError", "OSError", "OverflowError", "ReferenceError", "RuntimeError", "StandardError", "StopIteration", "SyntaxError", "SystemError", "SystemExit", "TabError", "TypeError", "UnboundLocalError", "UnicodeDecodeError", "UnicodeEncodeError", "UnicodeError", "UnicodeTranslateError", "ValueError", "WindowsError", "ZeroDivisionError", "Warning", "UserWarning", "DeprecationWarning", "PendingDeprecationWarning", "SyntaxWarning", "OverflowWarning", "RuntimeWarning", "FutureWarning"};
    String BUILTIN_FUNCTIONS[] = new String[]{"__import__", "abs", "all", "any", "bin", "bool", "callable", "chr", "classmethod", "compile", "complex", "delattr", "dict", "dir", "divmod", "enumerate", "eval", "filter", "float", "format", "frozenset", "getattr", "globals", "hasattr", "hash", "hex", "id", "input", "int", "isinstance", "issubclass", "iter", "len", "list", "locals", "map", "max", "min", "object", "oct", "open", "ord", "pow", "property", "range", "repr", "reversed", "round", "setattr", "set", "slice", "sorted", "staticmethod", "str", "sum", "super", "tuple", "type", "vars", "zip"};
    String K_2X_ONLY_BUILTIN_FUNCTIONS[] = new String[]{"apply", "basestring", "buffer", "cmp", "coerce", "execfile", "file", "intern", "long", "raw_input", "reduce", "reload", "unichr", "unicode", "xrange"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q#\\E[^\\n]*";
        String SPECIAL_VARIABLES_PATTERN = "\\b(" + String.join("|", SPECIAL_VARIABLES) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
        String MODULE_HANDLER_PATTERN = "\\b(" + String.join("|", MODULE_HANDLER) + ")\\b";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String K_2X_ONLY_KEYWORDS_PATTERN = "\\b(" + String.join("|", K_2X_ONLY_KEYWORDS) + ")\\b";
        String BUILTIN_CONSTANTS_PATTERN = "\\b(" + String.join("|", BUILTIN_CONSTANTS) + ")\\b";
        String BUILTIN_OBJECTS_PATTERN = "\\b(" + String.join("|", BUILTIN_OBJECTS) + ")\\b";
        String BUILTIN_FUNCTIONS_PATTERN = "\\b(" + String.join("|", BUILTIN_FUNCTIONS) + ")\\b";
        String K_2X_ONLY_BUILTIN_FUNCTIONS_PATTERN = "\\b(" + String.join("|", K_2X_ONLY_BUILTIN_FUNCTIONS) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<SPECIALVARIABLES>" + SPECIAL_VARIABLES_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
                + "|(?<MODULEHANDLER>" + MODULE_HANDLER_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<K2XONLYKEYWORDS>" + K_2X_ONLY_KEYWORDS_PATTERN + ")"
                + "|(?<BUILTINCONSTANTS>" + BUILTIN_CONSTANTS_PATTERN + ")"
                + "|(?<BUILTINOBJECTS>" + BUILTIN_OBJECTS_PATTERN + ")"
                + "|(?<BUILTINFUNCTIONS>" + BUILTIN_FUNCTIONS_PATTERN + ")"
                + "|(?<K2XONLYBUILTINFUNCTIONS>" + K_2X_ONLY_BUILTIN_FUNCTIONS_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("SPECIALVARIABLES") != null ? "special-variables"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : matcher.group("MODULEHANDLER") != null ? "module-handler"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("K2XONLYKEYWORDS") != null ? "k-2x-only-keywords"
                : matcher.group("BUILTINCONSTANTS") != null ? "builtin-constants"
                : matcher.group("BUILTINOBJECTS") != null ? "builtin-objects"
                : matcher.group("BUILTINFUNCTIONS") != null ? "builtin-functions"
                : matcher.group("K2XONLYBUILTINFUNCTIONS") != null ? "k-2x-only-builtin-functions"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(SPECIAL_VARIABLES));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        keywordList.addAll(Arrays.asList(MODULE_HANDLER));
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(K_2X_ONLY_KEYWORDS));
        keywordList.addAll(Arrays.asList(BUILTIN_CONSTANTS));
        keywordList.addAll(Arrays.asList(BUILTIN_OBJECTS));
        keywordList.addAll(Arrays.asList(BUILTIN_FUNCTIONS));
        keywordList.addAll(Arrays.asList(K_2X_ONLY_BUILTIN_FUNCTIONS));
        Collections.sort(keywordList);
        return keywordList;
    }

}
