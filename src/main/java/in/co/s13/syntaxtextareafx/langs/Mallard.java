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
public class Mallard implements Language {

    String PAGE_ELEMENT[] = new String[]{"page"};
    String SECTION_ELEMENT[] = new String[]{"section"};
    String BLOCK_ELEMENTS[] = new String[]{"code", "p", "example", "screen", "media", "comment", "figure", "listing", "note", "quote", "synopsis", "list", "steps", "terms", "tree", "table", "col", "colgroup", "tr", "td", "item"};
    String INLINE_ELEMENTS[] = new String[]{"app", "cmd", "code", "em", "file", "gui", "guiseq", "input", "key", "keyseq", "link", "media", "output", "span", "sys", "var", "info", "credit", "name", "email", "copyright", "year", "license", "desc", "link", "revision", "title", "subtitle", "desc", "cite"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q<!--\\E[\\s\\S]*?\\Q-->\\E";
        String PAGE_ELEMENT_PATTERN = "\\b(" + String.join("|", PAGE_ELEMENT) + ")\\b";
        String SECTION_ELEMENT_PATTERN = "\\b(" + String.join("|", SECTION_ELEMENT) + ")\\b";
        String BLOCK_ELEMENTS_PATTERN = "\\b(" + String.join("|", BLOCK_ELEMENTS) + ")\\b";
        String INLINE_ELEMENTS_PATTERN = "\\b(" + String.join("|", INLINE_ELEMENTS) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<PAGEELEMENT>" + PAGE_ELEMENT_PATTERN + ")"
                + "|(?<SECTIONELEMENT>" + SECTION_ELEMENT_PATTERN + ")"
                + "|(?<BLOCKELEMENTS>" + BLOCK_ELEMENTS_PATTERN + ")"
                + "|(?<INLINEELEMENTS>" + INLINE_ELEMENTS_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("PAGEELEMENT") != null ? "page-element"
                : matcher.group("SECTIONELEMENT") != null ? "section-element"
                : matcher.group("BLOCKELEMENTS") != null ? "block-elements"
                : matcher.group("INLINEELEMENTS") != null ? "inline-elements"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(PAGE_ELEMENT));
        keywordList.addAll(Arrays.asList(SECTION_ELEMENT));
        keywordList.addAll(Arrays.asList(BLOCK_ELEMENTS));
        keywordList.addAll(Arrays.asList(INLINE_ELEMENTS));
        Collections.sort(keywordList);
        return keywordList;
    }

}
