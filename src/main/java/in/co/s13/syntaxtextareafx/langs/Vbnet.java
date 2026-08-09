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
public class Vbnet implements Language {

    String KEYWORDS[] = new String[]{"addhandler", "addressof", "alias", "and", "andalso", "ansi", "as", "assembly", "async", "auto", "await", "binary", "byref", "byval", "call", "case", "catch", "cbool", "cbyte", "cchar", "cdate", "cdec", "cdbl", "cint", "class", "clng", "cobj", "compare", "const", "cshort", "csng", "cstr", "ctype", "declare", "default", "delegate", "dim", "do", "each", "else", "elseif", "end", "enum", "erase", "error", "event", "exit", "explicit", "finally", "for", "friend", "function", "get", "goto", "handles", "if", "implements", "imports", "in", "inherits", "integer", "interface", "is", "let", "lib", "like", "loop", "me", "mod", "module", "mustinherit", "mustoverride", "mybase", "myclass", "nameof", "namespace", "new", "next", "not", "notinheritable", "notoverridable", "off", "on", "option", "optional", "or", "orelse", "overloads", "overridable", "overrides", "param_array", "preserve", "private", "property", "protected", "public", "raiseevent", "readonly", "redim", "rem", "removehandler", "resume", "return", "select", "set", "shadows", "shared", "sizeof", "static", "step", "stop", "strict", "structure", "sub", "synclock", "text", "then", "throw", "to", "try", "typeof", "unicode", "until", "variant", "when", "while", "with", "withevents", "writeonly", "xor", "yield"};
    String TYPES[] = new String[]{"boolean", "byte", "char", "date", "decimal", "double", "long", "object", "short", "single", "string"};
    String SPECIAL_CONSTANTS[] = new String[]{"nothing", "null"};
    String BOOLEAN[] = new String[]{"false", "true"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q'\\E[^\\n]*";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String TYPES_PATTERN = "\\b(" + String.join("|", TYPES) + ")\\b";
        String SPECIAL_CONSTANTS_PATTERN = "\\b(" + String.join("|", SPECIAL_CONSTANTS) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<TYPES>" + TYPES_PATTERN + ")"
                + "|(?<SPECIALCONSTANTS>" + SPECIAL_CONSTANTS_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("TYPES") != null ? "types"
                : matcher.group("SPECIALCONSTANTS") != null ? "special-constants"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(TYPES));
        keywordList.addAll(Arrays.asList(SPECIAL_CONSTANTS));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        Collections.sort(keywordList);
        return keywordList;
    }

}
