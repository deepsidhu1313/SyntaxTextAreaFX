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
public class Erlang implements Language {

    String KEYWORDS[] = new String[]{"after", "begin", "case", "catch", "cond", "end", "fun", "if", "let", "of", "query", "receive", "when"};
    String OPERATORS[] = new String[]{"and", "band", "bnot", "bor", "bsl", "bsr", "bxor", "div", "not", "or", "rem", "xor"};
    String BUILTINS[] = new String[]{"abs", "alive", "apply", "atom_to_list", "atom", "binary_to_list", "binary_to_ter", "binary", "check_process_code", "concat_binary", "constant", "date", "delete_module", "disconnect_node", "element", "erase", "exit", "float", "float_to_list", "function", "get_cookie", "get_keys", "get", "group_leader", "halt", "hash", "hd", "integer_to_list", "integer", "is_alive", "length", "link", "list_to_atom", "list_to_binary", "list_to_float", "list_to_integer", "list_to_pid", "list_to_tuple", "list", "load_module", "make_ref", "math", "module_loaded", "monitor_node", "node", "nodes", "now", "number", "open_port", "pid_to_list", "pid", "ports", "port_close", "port_info", "preloaded", "processes", "process_flag", "process_info", "process", "purge_module", "put", "record", "reference", "registered", "register", "round", "self", "setelement", "set_cookie", "set_node", "size", "spawn_link", "spawn", "split_binary", "statistics", "term_to_binary", "throw", "time", "tl", "trunc", "tuple_to_list", "unlink", "unregister", "whereis"};
    String COMPILER_DIRECTIVES[] = new String[]{"author", "compile", "copyright", "define", "doc", "else", "endif", "export", "file", "ifdef", "ifndef", "import", "include_lib", "include", "module", "record", "undef"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q%\\E[^\\n]*";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String OPERATORS_PATTERN = "\\b(" + String.join("|", OPERATORS) + ")\\b";
        String BUILTINS_PATTERN = "\\b(" + String.join("|", BUILTINS) + ")\\b";
        String COMPILER_DIRECTIVES_PATTERN = "\\b(" + String.join("|", COMPILER_DIRECTIVES) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<OPERATORS>" + OPERATORS_PATTERN + ")"
                + "|(?<BUILTINS>" + BUILTINS_PATTERN + ")"
                + "|(?<COMPILERDIRECTIVES>" + COMPILER_DIRECTIVES_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("OPERATORS") != null ? "operators"
                : matcher.group("BUILTINS") != null ? "builtins"
                : matcher.group("COMPILERDIRECTIVES") != null ? "compiler-directives"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(OPERATORS));
        keywordList.addAll(Arrays.asList(BUILTINS));
        keywordList.addAll(Arrays.asList(COMPILER_DIRECTIVES));
        Collections.sort(keywordList);
        return keywordList;
    }

}
