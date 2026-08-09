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
public class Sh implements Language {

    String REDIRECTION[] = new String[]{">&+[0-9]", "\\b[0-9]+>&", "\\b[0-9]+>&+[0-9]", "\\b[0-9]+>", ">>", "\\b[0-9]+>>", "<&[0-9]", "\\b[0-9]+<&", "\\b[0-9]+<&[0-9]", "\\b[0-9]+<", "<<+[0-9]", "\\b[0-9]+<<"};
    String OPERATOR[] = new String[]{"\\-a\\b", "\\-b\\b", "\\-c\\b", "\\-d\\b", "\\-e\\b", "\\-f\\b", "\\-g\\b", "\\-h\\b", "\\-k\\b", "\\-p\\b", "\\-r\\b", "\\-s\\b", "\\-t\\b", "\\-u\\b", "\\-w\\b", "\\-x\\b", "\\-O\\b", "\\-G\\b", "\\-L\\b", "\\-S\\b", "\\-N\\b", "\\-nt\\b", "\\-ot\\b", "\\-ef\\b", "\\-o\\b", "\\-z\\b", "\\-n\\b", "<", ">", "\\!=", "\\-eq\\b", "\\-ne\\b", "\\-lt\\b", "\\-le\\b", "\\-gt\\b", "\\-ge\\b"};
    String BUILT_IN_COMMAND_1[] = new String[]{"\\!", "\\{", "\\}", "\\:", "(?<=\\s)\\.(?=\\s)"};
    String BUILT_IN_COMMAND_2[] = new String[]{"do", "done", "elif", "else", "fi", "for", "function", "if", "in", "select", "then", "until", "while", "alias", "bg", "bind", "break", "builtin", "cd", "command", "compgen", "complete", "continue", "declare", "dirs", "disown", "echo", "enable", "eval", "exec", "exit", "export", "fc", "fg", "getopts", "hash", "help", "history", "jobs", "let", "local", "logout", "popd", "printf", "pushd", "read", "readonly", "return", "set", "shift", "shopt", "source", "suspend", "test", "times", "trap", "type", "typeset", "ulimit", "umask", "unalias", "unset", "wait"};
    String COMMON_COMMAND[] = new String[]{"ar", "awk", "basename", "bash", "beep", "bunzip2", "bzcat", "bzcmp", "bzdiff", "bzegrep", "bzfgrep", "bzgrep", "bzip2recover", "bzip2", "bzless", "bzmore", "cc", "cat", "chattr", "chgrp", "chmod", "chown", "chroot", "clear", "cmp", "cpp", "cp", "cut", "date", "dd", "df", "dialog", "diff3", "diff", "dirname", "dir", "du", "egrep", "eject", "env", "expr", "false", "fgrep", "file", "find", "fmt", "free", "ftp", "funzip", "fuser", "gawk", "gcc", "getent", "getopt", "grep", "groups", "gunzip", "gzip", "head", "hostname", "id", "ifconfig", "info", "insmod", "install", "join", "killall", "kill", "lastb", "last", "ld", "less", "ln", "locate", "lockfile", "login", "logname", "lp", "lpr", "lsattr", "ls", "lsmod", "make", "man", "mkdir", "mkfifo", "mknod", "mktemp", "modprobe", "more", "mount", "mv", "namei", "nawk", "nice", "nl", "passwd", "patch", "perl", "pgawk", "pidof", "ping", "pkg-config", "pr", "ps", "pwd", "readlink", "renice", "rmdir", "rm", "scp", "sed", "seq", "sh", "sleep", "sort", "split", "ssh-add", "ssh-agent", "ssh-keygen", "ssh-keyscan", "ssh", "stat", "sudo", "sum", "su", "sync", "tac", "tail", "tar", "tee", "tempfile", "touch", "true", "tr", "umount", "uname", "uniq", "unlink", "unzip", "uptime", "wall", "wc", "wget", "whereis", "which", "whoami", "who", "write", "w", "xargs", "xdialog", "zcat", "zcmp", "zdiff", "zegrep", "zenity", "zfgrep", "zforce", "zgrep", "zip", "zless", "zmore", "znew"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q#\\E[^\\n]*";
        String REDIRECTION_PATTERN = "\\b(" + String.join("|", REDIRECTION) + ")\\b";
        String OPERATOR_PATTERN = "\\b(" + String.join("|", OPERATOR) + ")\\b";
        String BUILT_IN_COMMAND_1_PATTERN = "\\b(" + String.join("|", BUILT_IN_COMMAND_1) + ")\\b";
        String BUILT_IN_COMMAND_2_PATTERN = "\\b(" + String.join("|", BUILT_IN_COMMAND_2) + ")\\b";
        String COMMON_COMMAND_PATTERN = "\\b(" + String.join("|", COMMON_COMMAND) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<REDIRECTION>" + REDIRECTION_PATTERN + ")"
                + "|(?<OPERATOR>" + OPERATOR_PATTERN + ")"
                + "|(?<BUILTINCOMMAND1>" + BUILT_IN_COMMAND_1_PATTERN + ")"
                + "|(?<BUILTINCOMMAND2>" + BUILT_IN_COMMAND_2_PATTERN + ")"
                + "|(?<COMMONCOMMAND>" + COMMON_COMMAND_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("REDIRECTION") != null ? "redirection"
                : matcher.group("OPERATOR") != null ? "operator"
                : matcher.group("BUILTINCOMMAND1") != null ? "built-in-command-1"
                : matcher.group("BUILTINCOMMAND2") != null ? "built-in-command-2"
                : matcher.group("COMMONCOMMAND") != null ? "common-command"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(REDIRECTION));
        keywordList.addAll(Arrays.asList(OPERATOR));
        keywordList.addAll(Arrays.asList(BUILT_IN_COMMAND_1));
        keywordList.addAll(Arrays.asList(BUILT_IN_COMMAND_2));
        keywordList.addAll(Arrays.asList(COMMON_COMMAND));
        Collections.sort(keywordList);
        return keywordList;
    }

}
