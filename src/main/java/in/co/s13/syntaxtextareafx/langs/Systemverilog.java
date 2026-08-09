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
public class Systemverilog implements Language {

    String COMPILER_DIRECTIVE[] = new String[]{"begin_keywords", "default_decay_time", "default_trireg_strength", "delay_mode_distributed", "delay_mode_path", "delay_mode_unit", "delay_mode_zero", "end_keywords", "pragma"};
    String SYSTEM_TASK[] = new String[]{"assertkill", "assertoff", "asserton", "bits", "bitstoshortreal", "cast", "comment", "countones", "dimensions", "error", "exit", "fatal", "fell", "fullskew", "get_coverage", "high", "history", "increment", "info", "isunbounded", "isunknown", "left", "load_coverage_db", "low", "onehot", "onehot0", "past", "recrem", "removal", "right", "root", "rose", "sampled", "set_coverage_db_name", "shortrealtobits", "showvariables", "size", "stable", "timescale", "timeskew", "typename", "typeof", "urandom", "unit", "unpacked_dimensions", "upscope", "urandom", "urandom_range", "var", "vcdclose", "version", "warning"};
    String KEYWORD[] = new String[]{"accept_on", "alias", "always_comb", "always_ff", "always_latch", "assert", "assume", "automatic", "before", "bind", "bins", "binsof", "break", "cell", "checker", "class", "clocking", "config", "const", "constraint", "context", "continue", "cover", "covergroup", "coverpoint", "cross", "design", "dist", "do", "endchecker", "endclass", "endclocking", "endconfig", "endgroup", "endinterface", "endpackage", "endprogram", "endproperty", "endproperty", "endsequence", "endspecify", "enum", "expect", "export", "extends", "extern", "final", "first_match", "foreach", "forever", "forkjoin", "global", "iff", "ignore_bins", "illegal_bins", "implies", "import", "incdir", "inside", "instance", "interface", "intersect", "join_any", "join_none", "liblist", "library", "local", "matches", "modport", "new", "nexttime", "noshoowcancelled", "null", "package", "packed", "priority", "program", "property", "protected", "pulsestyle_onevent", "pulsestyle_ondetect", "pure", "rand", "randc", "randcase", "randsequence", "ref", "reject_on", "restrict", "return", "s_always", "s_eventually", "s_nexttime", "s_until", "s_until_with", "sequence", "showcancelled", "solve", "static", "struct", "super", "sync_accept_on", "sync_reject_on", "tagged", "this", "throughout", "timeprecision", "timeunit", "type", "typedef", "union", "unique", "unique0", "until", "until_with", "untyped", "use", "var", "virtual", "void", "wait_order", "wildcard", "with", "within"};
    String TYPE[] = new String[]{"bit", "byte", "chandle", "genvar", "int", "localparam", "logic", "longint", "shortint", "shortreal", "string", "uwire"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q//\\E[^\\n]*|\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String COMPILER_DIRECTIVE_PATTERN = "\\b(" + String.join("|", COMPILER_DIRECTIVE) + ")\\b";
        String SYSTEM_TASK_PATTERN = "\\b(" + String.join("|", SYSTEM_TASK) + ")\\b";
        String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORD) + ")\\b";
        String TYPE_PATTERN = "\\b(" + String.join("|", TYPE) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<COMPILERDIRECTIVE>" + COMPILER_DIRECTIVE_PATTERN + ")"
                + "|(?<SYSTEMTASK>" + SYSTEM_TASK_PATTERN + ")"
                + "|(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                + "|(?<TYPE>" + TYPE_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("COMPILERDIRECTIVE") != null ? "compiler-directive"
                : matcher.group("SYSTEMTASK") != null ? "system-task"
                : matcher.group("KEYWORD") != null ? "keyword"
                : matcher.group("TYPE") != null ? "type"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(COMPILER_DIRECTIVE));
        keywordList.addAll(Arrays.asList(SYSTEM_TASK));
        keywordList.addAll(Arrays.asList(KEYWORD));
        keywordList.addAll(Arrays.asList(TYPE));
        Collections.sort(keywordList);
        return keywordList;
    }

}
