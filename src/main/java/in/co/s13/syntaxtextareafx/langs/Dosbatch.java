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
public class Dosbatch implements Language {

    String KEYWORDS[] = new String[]{"do", "else", "exist", "exit", "for", "goto", "if", "not", "return", "say", "select", "then", "when"};
    String COMMANDS[] = new String[]{"ansi", "append", "assign", "assoc", "attrib", "at", "autofail", "backup", "basedev", "boot", "break", "buffers", "cache", "call", "cacls", "cd", "chcp", "chdir", "chkdsk", "chkntfs", "cls", "cmd", "codepage", "color", "command", "compact", "comp", "convert", "copy", "date", "del", "dir", "diskcomp", "diskcopy", "doskey", "echo", "endlocal", "erase", "fc", "find", "findstr", "format", "ftype", "graftabl", "help", "keyb", "label", "md", "mkdir", "mode", "more", "move", "path", "pause", "popd", "print", "prompt", "pushd", "rd", "recover", "rename", "ren", "replace", "restore", "rmdir", "run", "set", "setboot", "setlocal", "shift", "sort", "start", "subst", "time", "title", "trapdump", "tree", "type", "undelete", "unpack", "use", "verify", "ver", "vol", "xcopy"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Qrem\\E[^\\n]*";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String COMMANDS_PATTERN = "\\b(" + String.join("|", COMMANDS) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<COMMANDS>" + COMMANDS_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("COMMANDS") != null ? "commands"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(COMMANDS));
        Collections.sort(keywordList);
        return keywordList;
    }

}
