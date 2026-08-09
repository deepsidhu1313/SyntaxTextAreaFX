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
public class Mediawiki implements Language {

    String MAGIC_WORD_1[] = new String[]{"NOTOC", "FORCETOC", "TOC", "NOEDITSECTION", "NEWSECTIONLINK", "NONEWSECTIONLINK", "NOGALLERY", "HIDDENCAT", "NOCONTENTCONVERT", "NOCC", "NOTITLECONVERT", "NOTC", "START", "END", "INDEX", "NOINDEX", "STATICREDIRECT"};
    String MAGIC_WORD_2[] = new String[]{"CURRENTYEAR", "CURRENTMONTH", "CURRENTMONTHNAME", "CURRENTMONTHNAMEGEN", "CURRENTMONTHABBREV", "CURRENTDAY", "CURRENTDAY2", "CURRENTDOW", "CURRENTDAYNAME", "CURRENTTIME", "CURRENTHOUR", "CURRENTWEEK", "CURRENTTIMESTAMP", "LOCALYEAR", "LOCALMONTH", "LOCALMONTHNAME", "LOCALMONTHNAMEGEN", "LOCALMONTHABBREV", "LOCALDAY", "LOCALDAY2", "LOCALDOW", "LOCALDAYNAME", "LOCALTIME", "LOCALHOUR", "LOCALWEEK", "LOCALTIMESTAMP", "SITENAME", "SERVER", "SERVERNAME", "DIRMARK", "DIRECTIONMARK", "SCRIPTPATH", "STYLEPATH", "CURRENTVERSION", "CONTENTLANGUAGE", "CONTENTLANG", "REVISIONID", "REVISIONDAY", "REVISIONDAY2", "REVISIONMONTH", "REVISIONMONTH1", "REVISIONYEAR", "REVISIONTIMESTAMP", "REVISIONUSER", "NUMBEROFPAGES", "NUMBEROFARTICLES", "NUMBEROFFILES", "NUMBEROFEDITS", "NUMBEROFVIEWS", "NUMBEROFUSERS", "NUMBEROFADMINS", "NUMBEROFACTIVEUSERS", "FULLPAGENAME", "PAGENAME", "BASEPAGENAME", "SUBPAGENAME", "SUBJECTPAGENAME", "TALKPAGENAME", "FULLPAGENAMEE", "PAGENAMEE", "BASEPAGENAMEE", "SUBPAGENAMEE", "SUBJECTPAGENAMEE", "TALKPAGENAMEE", "NAMESPACE", "SUBJECTSPACE", "ARTICLESPACE", "TALKSPACE", "NAMESPACEE", "SUBJECTSPACEE", "TALKSPACEE"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q<!--\\E[\\s\\S]*?\\Q-->\\E";
        String MAGIC_WORD_1_PATTERN = "\\b(" + String.join("|", MAGIC_WORD_1) + ")\\b";
        String MAGIC_WORD_2_PATTERN = "\\b(" + String.join("|", MAGIC_WORD_2) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<MAGICWORD1>" + MAGIC_WORD_1_PATTERN + ")"
                + "|(?<MAGICWORD2>" + MAGIC_WORD_2_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("MAGICWORD1") != null ? "magic-word-1"
                : matcher.group("MAGICWORD2") != null ? "magic-word-2"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(MAGIC_WORD_1));
        keywordList.addAll(Arrays.asList(MAGIC_WORD_2));
        Collections.sort(keywordList);
        return keywordList;
    }

}
