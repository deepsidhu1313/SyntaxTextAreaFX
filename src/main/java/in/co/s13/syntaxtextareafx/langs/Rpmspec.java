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
public class Rpmspec implements Language {

    String HEADER[] = new String[]{"Auto[A-Za-z]+", "[A-Za-z]+Arch", "[A-Za-z]+Req", "Build[A-Za-z]+", "Conflicts", "Epoch", "Group", "Icon", "License", "Name", "Obsoletes", "Packager", "Patch[0-9]*", "Provides", "Recommends", "Release", "Requires\\(?[a-z]*\\)?", "Source[0-9]*", "Suggests", "Summary", "Supplements", "Url", "URL", "Vendor", "Version"};
    String SECTION[] = new String[]{"global", "description", "package", "prep", "build", "install", "clean", "check", "pre[a-z]*", "post[a-z]*", "trigger[a-z]*", "files"};
    String SPEC_MACRO[] = new String[]{"defattr", "docdir", "doc", "license", "setup", "autosetup", "config", "configure", "make", "makeinstall", "make_install", "make_build", "dir", "ghost", "patch[0-9]+", "find_lang", "exclude"};
    String FLOW[] = new String[]{"ifarch", "if", "else", "elif", "endif"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q#\\E[^\\n]*";
        String HEADER_PATTERN = "\\b(" + String.join("|", HEADER) + ")\\b";
        String SECTION_PATTERN = "\\b(" + String.join("|", SECTION) + ")\\b";
        String SPEC_MACRO_PATTERN = "\\b(" + String.join("|", SPEC_MACRO) + ")\\b";
        String FLOW_PATTERN = "\\b(" + String.join("|", FLOW) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<HEADER>" + HEADER_PATTERN + ")"
                + "|(?<SECTION>" + SECTION_PATTERN + ")"
                + "|(?<SPECMACRO>" + SPEC_MACRO_PATTERN + ")"
                + "|(?<FLOW>" + FLOW_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("HEADER") != null ? "header"
                : matcher.group("SECTION") != null ? "section"
                : matcher.group("SPECMACRO") != null ? "spec-macro"
                : matcher.group("FLOW") != null ? "flow"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(HEADER));
        keywordList.addAll(Arrays.asList(SECTION));
        keywordList.addAll(Arrays.asList(SPEC_MACRO));
        keywordList.addAll(Arrays.asList(FLOW));
        Collections.sort(keywordList);
        return keywordList;
    }

}
