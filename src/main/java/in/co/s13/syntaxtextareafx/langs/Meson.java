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
public class Meson implements Language {

    String BUILTIN_COMMAND[] = new String[]{"add_global_arguments", "build_target", "configuration_data", "configure_file", "custom_target", "declare_dependency", "dependency", "error", "executable", "find_program", "find_library", "files", "generator", "get_option", "gettext", "include_directories", "install_data", "install_headers", "install_man", "install_subdir", "is_subproject", "jar", "message", "pkgconfig_gen", "project", "run_command", "run_target", "set_variable", "shared_library", "static_library", "subdir", "subproject", "test", "vcs_tag"};
    String OPERATOR[] = new String[]{"if", "else", "endif", "foreach", "endforeach"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q#\\E[^\\n]*";
        String BUILTIN_COMMAND_PATTERN = "\\b(" + String.join("|", BUILTIN_COMMAND) + ")\\b";
        String OPERATOR_PATTERN = "\\b(" + String.join("|", OPERATOR) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<BUILTINCOMMAND>" + BUILTIN_COMMAND_PATTERN + ")"
                + "|(?<OPERATOR>" + OPERATOR_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("BUILTINCOMMAND") != null ? "builtin-command"
                : matcher.group("OPERATOR") != null ? "operator"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(BUILTIN_COMMAND));
        keywordList.addAll(Arrays.asList(OPERATOR));
        Collections.sort(keywordList);
        return keywordList;
    }

}
