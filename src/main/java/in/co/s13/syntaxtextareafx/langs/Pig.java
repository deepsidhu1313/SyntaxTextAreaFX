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
public class Pig implements Language {

    String DATATYPE[] = new String[]{"int", "long", "float", "double", "boolean", "datetime", "chararray", "bytearray", "biginteger", "bigdecimal"};
    String BOOLEAN_LITERAL[] = new String[]{"false", "true"};
    String NULL[] = new String[]{"null"};
    String RESERVED_WORDS[] = new String[]{"and", "any", "all", "arrange", "assert", "asc", "as", "bag", "by", "cache", "case", "cat", "cd", "cogroup", "copyfromlocal", "copytolocal", "cp", "cross", "cube", "%declare", "%default", "define", "describe", "desc", "dense", "diff", "distinct", "dump", "du", "eval", "exec", "explain", "filter", "flatten", "foreach", "full", "generate", "group", "help", "if", "illustrate", "import", "inner", "input", "into", "is", "join", "kill", "left", "limit", "load", "ls", "map", "matches", "mkdir", "mv", "not", "onschema", "order", "or", "outer", "output", "parallel", "pig", "pwd", "quit", "rank", "register", "returns", "right", "rmf", "rm", "rollup", "run", "sample", "set", "ship", "split", "stderr", "stdin", "stdout", "store", "stream", "through", "tuple", "union", "using", "void"};
    String BUILTIN_FUNCTIONS[] = new String[]{"ABS", "ACOS", "AccumuloStorage", "AddDuration", "ASIN", "ATAN", "AVG", "AvroStorage", "BagToString", "BinStorage", "CBRT", "CEIL", "CONCAT", "COSH", "COS", "COUNT_STAR", "COUNT", "CurrentTime", "DaysBetween", "DIFF", "ENDSWITH", "EqualsIgnoreCase", "EXP", "FLOOR", "GetDay", "GetHour", "GetMilliSecond", "GetMinute", "GetMonth", "GetSecond", "GetWeek", "GetWeekYear", "GetYear", "HBaseStorage", "HiveUDAF", "HiveUDF", "HiveUDTF", "HoursBetween", "INDEXOF", "IsEmpty", "JsonLoader", "JsonStorage", "LAST_INDEX_OF", "LCFIRST", "LOG10", "LOG", "LOWER", "LTRIM", "MAX", "MilliSecondsBetween", "MIN", "MinutesBetween", "MonthsBetween", "OrcStorage", "PigDump", "PigStorage", "PluckTuple", "RANDOM", "REGEX_EXTRACT_ALL", "REGEX_EXTRACT", "REPLACE", "ROUND_TO", "ROUND", "RTRIM", "SecondsBetween", "SINH", "SIN", "SIZE", "SPRINTF", "SQRT", "STARTSWITH", "STRSPLITTOBAG", "STRSPLIT", "SUBSTRING", "SubtractDuration", "SUBTRACT", "SUM", "TANH", "TAN", "Terms", "TextLoader", "TOBAG", "ToDate", "TOKENIZE", "TOMAP", "ToMilliSeconds", "TOP", "ToString", "TOTUPLE", "ToUnixTime", "TRIM", "TrevniStrorage", "UCFIRST", "UniqueID", "UPPER", "Usage", "WeeksBetween", "YearsBetween"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q--\\E[^\\n]*|\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String DATATYPE_PATTERN = "\\b(" + String.join("|", DATATYPE) + ")\\b";
        String BOOLEAN_LITERAL_PATTERN = "\\b(" + String.join("|", BOOLEAN_LITERAL) + ")\\b";
        String NULL_PATTERN = "\\b(" + String.join("|", NULL) + ")\\b";
        String RESERVED_WORDS_PATTERN = "\\b(" + String.join("|", RESERVED_WORDS) + ")\\b";
        String BUILTIN_FUNCTIONS_PATTERN = "\\b(" + String.join("|", BUILTIN_FUNCTIONS) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<DATATYPE>" + DATATYPE_PATTERN + ")"
                + "|(?<BOOLEANLITERAL>" + BOOLEAN_LITERAL_PATTERN + ")"
                + "|(?<NULL>" + NULL_PATTERN + ")"
                + "|(?<RESERVEDWORDS>" + RESERVED_WORDS_PATTERN + ")"
                + "|(?<BUILTINFUNCTIONS>" + BUILTIN_FUNCTIONS_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("DATATYPE") != null ? "datatype"
                : matcher.group("BOOLEANLITERAL") != null ? "boolean-literal"
                : matcher.group("NULL") != null ? "null"
                : matcher.group("RESERVEDWORDS") != null ? "reserved-words"
                : matcher.group("BUILTINFUNCTIONS") != null ? "builtin-functions"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(DATATYPE));
        keywordList.addAll(Arrays.asList(BOOLEAN_LITERAL));
        keywordList.addAll(Arrays.asList(NULL));
        keywordList.addAll(Arrays.asList(RESERVED_WORDS));
        keywordList.addAll(Arrays.asList(BUILTIN_FUNCTIONS));
        Collections.sort(keywordList);
        return keywordList;
    }

}
