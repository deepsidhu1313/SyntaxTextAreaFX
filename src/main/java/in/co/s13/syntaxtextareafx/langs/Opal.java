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
public class Opal implements Language {

    String MODULE_HANDLER[] = new String[]{"IMPORT", "COMPLETELY", "ONLY"};
    String KEYWORDS[] = new String[]{"ALL", "AND", "ANDIF", "ANY", "AS", "ASSERT", "AXM", "DATA", "DEF", "DERIVE", "DFD", "DESCRIMINATORS", "ELSE", "EX", "EXTERNAL", "FI", "FIX", "FUN", "IF", "IMPLEMENTATION", "IMPLIES", "IN", "INHERIT", "INJECTIONS", "INTERFACE", "INTERNAL", "LAW", "LAZY", "LEFTASSOC", "LET", "MODULE", "NOT", "NOR", "OR", "ORIF", "OTHERWISE", "POST", "PRE", "PRED", "PRIORITY", "PROPERTIES", "REALIZES", "REQUIRE", "RIGHTASSOC", "SELECTORS", "SIGNATURE", "SORT", "SPC", "SPEC", "SPECIFICATION", "STRUCTURE", "THE", "THEN", "THEORY", "THM", "TYPE", "UNIQ", "WHERE"};
    String KEYSYMBOL[] = new String[]{"::", "==", "->"};
    String TYPES[] = new String[]{"aEntry", "agent", "align", "anchor", "ans", "arg", "arg1", "arg2", "array", "arrowWhere", "bag", "bitmap", "bool", "bstree", "byte", "callback", "canvasEditor", "capStyle", "channel", "char", "childstat", "codom", "codomFrom", "codomTo", "color", "colorModel", "com", "composeOp", "config", "configCom", "cursor", "dArray", "data", "data1", "data11", "data2", "data21", "data3", "data31", "data4", "data41", "dataFrom", "dataTo", "defaultPrio", "denotation", "device", "dist", "distOut", "dom", "domFrom", "domTo", "drawing", "dyn", "emitter", "env", "event", "eventInfo", "file", "filemode", "filestat", "filetype", "first", "first1", "first2", "first3", "fission", "fmt", "font", "from", "from1", "from2", "funct", "group", "groupid", "heap", "iconfig", "image", "in", "inData", "index", "inode", "input", "int", "inter", "interdom", "interpreter", "iseq", "items", "joinStyle", "justifyHow", "long", "manager", "managerRequest", "map", "mapEntry", "mark", "mid", "modifier", "nat", "natMap", "OBJECT", "option", "orient", "out", "outData", "output", "packOp", "pair", "parser", "permission", "point", "positionRequest", "process", "procstat", "quad", "range", "real", "regulator", "rel", "relief", "res", "res1", "res2", "result", "role", "sap", "script", "scroller", "scrollView", "scrollWindow", "searchOpt", "second", "seekMode", "selector", "semaphor", "seq", "seqEntry", "set", "setEntry", "short", "sigaction", "sighandler", "sigmask", "signal", "size", "sizeRequest", "some", "sreal", "state", "stateId", "stateRequest", "string", "subrel", "tag", "textEditor", "time", "to", "tree", "triple", "union", "user", "userid", "version", "view", "void", "wconfig", "wconfigCom", "wday", "widget", "window", "wrapStyle"};
    String BOOLEAN[] = new String[]{"true", "false"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q--\\E[^\\n]*|\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String MODULE_HANDLER_PATTERN = "\\b(" + String.join("|", MODULE_HANDLER) + ")\\b";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String KEYSYMBOL_PATTERN = "\\b(" + String.join("|", KEYSYMBOL) + ")\\b";
        String TYPES_PATTERN = "\\b(" + String.join("|", TYPES) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<MODULEHANDLER>" + MODULE_HANDLER_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<KEYSYMBOL>" + KEYSYMBOL_PATTERN + ")"
                + "|(?<TYPES>" + TYPES_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("MODULEHANDLER") != null ? "module-handler"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("KEYSYMBOL") != null ? "keysymbol"
                : matcher.group("TYPES") != null ? "types"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(MODULE_HANDLER));
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(KEYSYMBOL));
        keywordList.addAll(Arrays.asList(TYPES));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        Collections.sort(keywordList);
        return keywordList;
    }

}
