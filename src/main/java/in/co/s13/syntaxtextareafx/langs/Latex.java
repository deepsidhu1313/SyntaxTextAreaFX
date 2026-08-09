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
public class Latex implements Language {

    String COMMON_COMMANDS[] = new String[]{"Alpha", "Beta", "Chi", "Delta", "Epsilon", "Eta", "Gamma", "Iota", "Kappa", "Lambda", "Leftarrow", "Leftrightarrow", "Mu", "Nu", "Omega", "Phi", "Pi", "Psi", "Rho", "Rightarrow", "Sigma", "Tau", "Zeta", "alpha", "appendix", "begin", "beta", "bigcap", "bigcup", "cap", "cdot", "chapter", "chi", "cite", "cup", "delta", "documentclass", "end", "enumi", "enumii", "enumiii", "enumiv", "epsilon", "equation", "eta", "exists", "figure", "footnote", "footnotemark", "footnotetext", "forall", "gamma", "geq", "in", "int", "iota", "kappa", "label", "lambda", "ldots", "leftarrow", "leq", "mpfootnote", "mu", "neq", "newcommand", "newenvironment", "newfont", "newtheorem", "not", "notin", "nu", "omega", "onecolumn", "page", "pageref", "paragraph", "part", "phi", "pi", "prod", "psi", "qquad", "quad", "ref", "rho", "rightarrow", "section", "setminus", "sigma", "subparagraph", "subsection", "subset", "subseteq", "subsetneq", "subsubsection", "subsubsubsection", "sum", "supset", "supseteq", "supsetneq", "table", "tau", "times", "twocolumn", "varepsilon", "varphi", "zeta"};
    String INCLUDE[] = new String[]{"input", "include", "includeonly", "usepackage"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q%\\E[^\\n]*";
        String COMMON_COMMANDS_PATTERN = "\\b(" + String.join("|", COMMON_COMMANDS) + ")\\b";
        String INCLUDE_PATTERN = "\\b(" + String.join("|", INCLUDE) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<COMMONCOMMANDS>" + COMMON_COMMANDS_PATTERN + ")"
                + "|(?<INCLUDE>" + INCLUDE_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("COMMONCOMMANDS") != null ? "common-commands"
                : matcher.group("INCLUDE") != null ? "include"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(COMMON_COMMANDS));
        keywordList.addAll(Arrays.asList(INCLUDE));
        Collections.sort(keywordList);
        return keywordList;
    }

}
