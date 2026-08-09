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
public class Lua implements Language {

    String KEYWORDS[] = new String[]{"and", "break", "do", "else", "elseif", "end", "for", "function", "goto", "if", "in", "local", "not", "or", "repeat", "return", "then", "until", "while"};
    String FUNCTIONS[] = new String[]{"assert", "bit32\\.arshift", "bit32\\.band", "bit32\\.bnot", "bit32\\.bor", "bit32\\.btest", "bit32\\.bxor", "bit32\\.extract", "bit32\\.lrotate", "bit32\\.lshift", "bit32\\.replace", "bit32\\.rrotate", "bit32\\.rshift", "collectgarbage", "coroutine\\.create", "coroutine\\.resume", "coroutine\\.running", "coroutine\\.status", "coroutine\\.wrap", "coroutine\\.yield", "debug\\.debug", "debug\\.gethook", "debug\\.getinfo", "debug\\.getlocal", "debug\\.getmetatable", "debug\\.getregistry", "debug\\.getupvalue", "debug\\.getuservalue", "debug\\.sethook", "debug\\.setlocal", "debug\\.setmetatable", "debug\\.setupvalue", "debug\\.setuservalue", "debug\\.traceback", "debug\\.upvalueid", "debug\\.upvaluejoin", "dofile", "error", "getmetatable", "io\\.close", "io\\.flush", "io\\.input", "io\\.lines", "io\\.open", "io\\.output", "io\\.popen", "io\\.read", "io\\.tmpfile", "io\\.type", "io\\.write", "ipairs", "load", "loadfile", "loadstring", "math\\.abs", "math\\.acos", "math\\.asin", "math\\.atan", "math\\.atan2", "math\\.ceil", "math\\.cos", "math\\.cosh", "math\\.deg", "math\\.exp", "math\\.floor", "math\\.fmod", "math\\.frexp", "math\\.ldexp", "math\\.log", "math\\.log10", "math\\.max", "math\\.min", "math\\.modf", "math\\.pow", "math\\.rad", "math\\.random", "math\\.randomseed", "math\\.sin", "math\\.sinh", "math\\.sqrt", "math\\.tan", "math\\.tanh", "module", "next", "os\\.clock", "os\\.date", "os\\.difftime", "os\\.execute", "os\\.exit", "os\\.getenv", "os\\.remove", "os\\.rename", "os\\.setlocale", "os\\.time", "os\\.tmpname", "package\\.loadlib", "package\\.searchpath", "package\\.seeall", "pairs", "pcall", "print", "rawequal", "rawget", "rawlen", "rawset", "require", "select", "setmetatable", "string\\.byte", "string\\.char", "string\\.dump", "string\\.find", "string\\.format", "string\\.gmatch", "string\\.gsub", "string\\.len", "string\\.lower", "string\\.match", "string\\.rep", "string\\.reverse", "string\\.sub", "string\\.upper", "table\\.concat", "table\\.insert", "table\\.maxn", "table\\.pack", "table\\.remove", "table\\.sort", "table\\.unpack", "tonumber", "tostring", "type", "unpack", "xpcall", "getfenv", "gcinfo", "loadlib", "setfenv", "__mode", "__index", "__newindex", "__mode", "__call", "__metatable", "__tostring", "__len", "__gc", "__unm", "__add", "__sub", "__mul", "__div", "__mod", "__pow", "__concat", "__eq", "__lt", "__le"};
    String LUA_RESERVED[] = new String[]{"_[A-Z][A-Za-z0-9_]*"};
    String NIL_VALUE[] = new String[]{"nil"};
    String BOOLEAN[] = new String[]{"false", "true"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q--\\E[^\\n]*|\\Q--[[\\E[\\s\\S]*?\\Q]]\\E";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String FUNCTIONS_PATTERN = "\\b(" + String.join("|", FUNCTIONS) + ")\\b";
        String LUA_RESERVED_PATTERN = "\\b(" + String.join("|", LUA_RESERVED) + ")\\b";
        String NIL_VALUE_PATTERN = "\\b(" + String.join("|", NIL_VALUE) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<FUNCTIONS>" + FUNCTIONS_PATTERN + ")"
                + "|(?<LUARESERVED>" + LUA_RESERVED_PATTERN + ")"
                + "|(?<NILVALUE>" + NIL_VALUE_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("FUNCTIONS") != null ? "functions"
                : matcher.group("LUARESERVED") != null ? "lua-reserved"
                : matcher.group("NILVALUE") != null ? "nil-value"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(FUNCTIONS));
        keywordList.addAll(Arrays.asList(LUA_RESERVED));
        keywordList.addAll(Arrays.asList(NIL_VALUE));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        Collections.sort(keywordList);
        return keywordList;
    }

}
