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
public class Docbook implements Language {

    String HEADER_ELEMENTS[] = new String[]{"abstract", "articleinfo", "article", "bookinfo", "authorgroup", "author", "affiliation", "copyright", "date", "email", "firstname", "orgname", "publishername", "publisher", "pubdate", "pubsnumber", "releaseinfo", "surname", "revdescription", "revhistory", "revision", "revnumber"};
    String FORMATTING_ELEMENTS[] = new String[]{"caution", "command", "computeroutput", "filename", "firstterm", "link", "note", "option", "para", "remark", "replaceable", "tip", "ulink", "variablelist", "varlistentry", "warning", "xref"};
    String GUI_ELEMENTS[] = new String[]{"accel", "application", "guibutton", "guiicon", "guilabel", "guimenuitem", "guimenu", "guisubmenu", "interface", "keycap", "keycombo", "keysym", "menuchoice"};
    String STRUCTURAL_ELEMENTS[] = new String[]{"appendix", "book", "chapter", "anchor", "citetitle", "colspec", "emphasis", "entry", "figure", "glossary", "glossdef", "glossentry", "glossterm", "sect1", "sect2", "sect3", "sect4", "section", "highlights", "holder", "imagedata", "imageobject", "indexterm", "informaltable", "inlinemediaobject", "itemizedlist", "literal", "legalnotice", "listitem", "mediaobject", "orderedlist", "phrase", "primary", "programlisting", "row", "screen", "screenshot", "secondary", "see", "shortcut", "table", "tbody", "term", "tertiary", "textobject", "tgroup", "thead", "titleabbrev", "title", "uri", "userinput", "year", "ackno", "acronym", "action", "address", "answer", "appendixinfo", "areaset", "areaspec", "area", "arg", "artpagenums", "attribution", "audiodata", "audioobject", "authorblurb", "authorinitials", "beginpage", "bibliocoverage", "bibliodiv", "biblioentry", "bibliography", "bibliographyinfo", "biblioid", "bibliomisc", "bibliomixed", "bibliomset", "bibliorelation", "biblioset", "bibliosource", "blockinfo", "blockquote", "bridgehead", "calloutlist", "callout", "caption", "chapterinfo", "citation", "citebiblioid", "citerefentry", "city", "classname", "classsynopsis", "classsynopsisinfo", "cmdsynopsis", "code", "colgroup", "collabname", "collab", "colophon", "confdates", "confgroup", "confnum", "confsponsor", "conftitle", "constant", "constraintdef", "constraint", "constructorsynopsis", "contractnum", "contractsponsor", "contrib", "coref", "corpauthor", "corpcredit", "corpname", "country", "database", "dedication", "destructorsynopsis", "edition", "editor", "entrytbl", "envar", "epigraph", "equation", "errorcode", "errorname", "errortext", "errortype", "example", "exceptionname", "fax", "fieldsynopsis", "footnoteref", "footnote", "foreignphrase", "formalpara", "funcdef", "funcparams", "funcprototype", "funcsynopsis", "funcsynopsisinfo", "function", "glossaryinfo", "glossdiv", "glosslist", "glosssee", "glossseealso", "graphicco", "graphic", "hardware", "honorific", "imageobjectco", "important", "indexdiv", "indexentry", "indexinfo", "informalequation", "informalexample", "informalfigure", "initializer", "inlineequation", "inlinegraphic", "interfacename", "invpartnumber", "isbn", "issn", "issuenum", "itermset", "jobtitle", "keycode", "keywordset", "keyword", "lhs", "lineage", "lineannotation", "literallayout", "lot", "lotentry", "manvolnum", "markup", "medialabel", "mediaobjectco", "member", "methodname", "methodparam", "methodsynopsis", "modespec", "modifier", "mousebutton", "msgaud", "msgentry", "msgexplan", "msginfo", "msglevel", "msgmain", "msgorig", "msgrel", "msgset", "msgsub", "msgtext", "msg", "nonterminal", "objectinfo", "olink", "ooclass", "ooexception", "oointerface", "optional", "orgdiv", "otheraddr", "othercredit", "othername", "pagenums", "paramdef", "parameter", "partinfo", "partintro", "part", "personblurb", "personname", "phone", "pob", "postcode", "prefaceinfo", "preface", "primaryie", "printhistory", "procedure", "productionrecap", "productionset", "production", "productname", "productnumber", "programlistingco", "prompt", "property", "qandadiv", "qandaentry", "qandaset", "question", "quote", "refclass", "refdescriptor", "refentryinfo", "refentrytitle", "refentry", "referenceinfo", "reference", "refmeta", "refmiscinfo", "refname", "refnamediv", "refpurpose", "refsect1", "refsect1info", "refsect2", "refsect2info", "refsect3", "refsect3info", "refsection", "refsectioninfo", "refsynopsisdivinfo", "refsynopsisdiv", "returnvalue", "revremark", "rhs", "sbr", "screenco", "screeninfo", "secondaryie", "sect1info", "sect2info", "sect3info", "sect4info", "sect5info", "sect5", "sectioninfo", "seealsoie", "seealso", "seeie", "seglistitem", "segmentedlist", "segtitle", "seg", "seriesvolnums", "setindexinfo", "setindex", "setinfo", "set", "sgmltag", "shortaffil", "sidebar", "sidebarinfo", "simpara", "simplelist", "simplemsgentry", "simplesect", "spanspec", "state", "stepalternatives", "step", "street", "structfield", "structname", "subject", "subjectset", "subjectterm", "subscript", "substeps", "subtitle", "superscript", "symbol", "synopfragment", "synopfragmentref", "synopsis", "systemitem", "taskprerequisites", "taskrelated", "tasksummary", "task", "td", "tertiaryie", "textdata", "tfoot", "tocback", "tocchap", "tocentry", "tocfront", "toclevel", "tocpart", "toc", "token", "trademark", "type", "varargs", "varname", "videodata", "videoobject", "void", "volumenum", "wordasword"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q<!--\\E[\\s\\S]*?\\Q-->\\E";
        String HEADER_ELEMENTS_PATTERN = "\\b(" + String.join("|", HEADER_ELEMENTS) + ")\\b";
        String FORMATTING_ELEMENTS_PATTERN = "\\b(" + String.join("|", FORMATTING_ELEMENTS) + ")\\b";
        String GUI_ELEMENTS_PATTERN = "\\b(" + String.join("|", GUI_ELEMENTS) + ")\\b";
        String STRUCTURAL_ELEMENTS_PATTERN = "\\b(" + String.join("|", STRUCTURAL_ELEMENTS) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<HEADERELEMENTS>" + HEADER_ELEMENTS_PATTERN + ")"
                + "|(?<FORMATTINGELEMENTS>" + FORMATTING_ELEMENTS_PATTERN + ")"
                + "|(?<GUIELEMENTS>" + GUI_ELEMENTS_PATTERN + ")"
                + "|(?<STRUCTURALELEMENTS>" + STRUCTURAL_ELEMENTS_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("HEADERELEMENTS") != null ? "header-elements"
                : matcher.group("FORMATTINGELEMENTS") != null ? "formatting-elements"
                : matcher.group("GUIELEMENTS") != null ? "gui-elements"
                : matcher.group("STRUCTURALELEMENTS") != null ? "structural-elements"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(HEADER_ELEMENTS));
        keywordList.addAll(Arrays.asList(FORMATTING_ELEMENTS));
        keywordList.addAll(Arrays.asList(GUI_ELEMENTS));
        keywordList.addAll(Arrays.asList(STRUCTURAL_ELEMENTS));
        Collections.sort(keywordList);
        return keywordList;
    }

}
