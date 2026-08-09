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
public class Verilog implements Language {

    String COMPILER_DIRECTIVE[] = new String[]{"celldefine", "default_nettype", "define", "else", "elsif", "endcelldefine", "endif", "ifdef", "ifndef", "include", "line", "nounconnected_drive", "resetall", "timescale", "unconnected_drive", "undef"};
    String IEEE_SYSTEM_TASK[] = new String[]{"acos", "acosh", "asin", "asinh", "atan", "atan2", "atanh", "async\\$and\\$array", "async\\$and\\$plane", "async\\$nand\\$array", "async\\$nand\\$plane", "async\\$or\\$array", "async\\$or\\$plane", "async\\$nor\\$array", "async\\$nor\\$plane", "bitstoreal", "ceil", "cos", "cosh", "clog2", "display", "displayb", "displayh", "displayo", "dist_chi_square", "dist_erlang", "dist_exponential", "dist_normal", "dist_poisson", "dist_t", "dist_uniform", "dummpall", "dumpfile", "dumpflush", "dumplimit", "dumpoff", "dumpon", "dumpvars", "exp", "fclose", "fdisplay", "fdisplayb", "fdisplayh", "fdisplayo", "feof", "ferror", "fflush", "fgetc", "fgets", "finish", "floor", "fmonitor", "fmonitorb", "fmonitorh", "fmonitoro", "fopen", "fread", "fscanf", "fseek", "fstrobe", "fstrobeb", "fstrobeh", "fstrobeo", "ftell", "fwrite", "fwriteb", "fwriteh", "fwriteo", "hold", "hypot", "itor", "ln", "log10", "monitor", "monitorb", "monitorh", "monitoro", "monitoroff", "monitoron", "nochange", "period", "pow", "printtimescale", "q_add", "q_exam", "q_full", "q_initialize", "q_remove", "random", "readmemb", "readmemh", "realtime", "realtobits", "recovery", "rewind", "rtoi", "sdf_annotate", "setup", "setuphold", "sformat", "signed", "sin", "sinh", "skew", "sqrt", "sscanf", "stime", "stop", "strobe", "strobeb", "strobeh", "strobeo", "swrite", "swriteb", "swriteh", "swriteo", "sync\\$and\\$array", "sync\\$and\\$plane", "sync\\$nand\\$array", "sync\\$nand\\$plane", "sync\\$or\\$array", "sync\\$or\\$plane", "sync\\$nor\\$array", "sync\\$nor\\$plane", "tan", "tanh", "test\\$plusargs", "time", "timeformat", "ungetc", "unsigned", "value\\$plusargs", "width", "write", "writeb", "writeh", "writeo"};
    String LRM_ADDITIONAL_SYSTEM_TASK[] = new String[]{"countdrivers", "getpattern", "incsave", "key", "list", "log", "nokey", "nolog", "reset", "reset_count", "reset_value", "restart", "save", "scale", "scope", "showscopes", "showvars", "sreadmemb", "sreadmemh"};
    String KEYWORDS[] = new String[]{"always", "assign", "attribute", "begin", "case", "casex", "casez", "deassign", "default", "defparam", "disable", "edge", "else", "end", "endattribute", "endcase", "endfunction", "endgenerate", "endmodule", "endprimitive", "endspecify", "endtable", "endtask", "for", "force", "forever", "fork", "function", "generate", "highz0", "highz1", "if", "ifnone", "initial", "join", "large", "macromodule", "medium", "module", "negedge", "posedge", "primitive", "pull0", "pull1", "release", "repeat", "signed", "small", "specify", "specparam", "strength", "strong0", "strong1", "table", "task", "unsigned", "wait", "weak0", "weak1", "while"};
    String GATES[] = new String[]{"and", "buf", "bufif0", "bufif1", "cmos", "nand", "nmos", "nor", "not", "notif0", "notif1", "or", "pmos", "pullup", "pulldown", "rcmos", "rnmos", "rpmos", "rtran", "rtranif0", "rtranif1", "tran", "tranif0", "tranif1", "xnor", "xor"};
    String TYPES[] = new String[]{"event", "genvar", "inout", "input", "integer", "output", "parameter", "real", "reg", "realtime", "scalared", "supply0", "supply1", "time", "tri", "tri0", "tri1", "triand", "trior", "trireg", "vectored", "wand", "wire", "wor"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q//\\E[^\\n]*|\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String COMPILER_DIRECTIVE_PATTERN = "\\b(" + String.join("|", COMPILER_DIRECTIVE) + ")\\b";
        String IEEE_SYSTEM_TASK_PATTERN = "\\b(" + String.join("|", IEEE_SYSTEM_TASK) + ")\\b";
        String LRM_ADDITIONAL_SYSTEM_TASK_PATTERN = "\\b(" + String.join("|", LRM_ADDITIONAL_SYSTEM_TASK) + ")\\b";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String GATES_PATTERN = "\\b(" + String.join("|", GATES) + ")\\b";
        String TYPES_PATTERN = "\\b(" + String.join("|", TYPES) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<COMPILERDIRECTIVE>" + COMPILER_DIRECTIVE_PATTERN + ")"
                + "|(?<IEEESYSTEMTASK>" + IEEE_SYSTEM_TASK_PATTERN + ")"
                + "|(?<LRMADDITIONALSYSTEMTASK>" + LRM_ADDITIONAL_SYSTEM_TASK_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<GATES>" + GATES_PATTERN + ")"
                + "|(?<TYPES>" + TYPES_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("COMPILERDIRECTIVE") != null ? "compiler-directive"
                : matcher.group("IEEESYSTEMTASK") != null ? "ieee-system-task"
                : matcher.group("LRMADDITIONALSYSTEMTASK") != null ? "lrm-additional-system-task"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("GATES") != null ? "gates"
                : matcher.group("TYPES") != null ? "types"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(COMPILER_DIRECTIVE));
        keywordList.addAll(Arrays.asList(IEEE_SYSTEM_TASK));
        keywordList.addAll(Arrays.asList(LRM_ADDITIONAL_SYSTEM_TASK));
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(GATES));
        keywordList.addAll(Arrays.asList(TYPES));
        Collections.sort(keywordList);
        return keywordList;
    }

}
