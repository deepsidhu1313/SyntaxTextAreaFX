/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.s13;

import in.co.s13.meta.Generator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.PlainTextChange;
import org.fxmisc.richtext.StyleSpans;
import org.fxmisc.richtext.StyleSpansBuilder;
import org.reactfx.EventStream;
import org.reactfx.util.Try;

/**
 *
 * @author Nika
 */
public class SyntaxTextAreaFX {

//    private static final String[] KEYWORDS = new String[]{
//        "abstract", "assert", "boolean", "break", "byte",
//        "case", "catch", "char", "class", "const",
//        "continue", "default", "do", "double", "else",
//        "enum", "extends", "final", "finally", "float",
//        "for", "goto", "if", "implements", "import",
//        "instanceof", "int", "interface", "long", "native",
//        "new", "package", "private", "protected", "public",
//        "return", "short", "static", "strictfp", "super",
//        "switch", "synchronized", "this", "throw", "throws",
//        "transient", "try", "void", "volatile", "while"
//    };
//
//    private static String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
//    private static String PAREN_PATTERN = "\\(|\\)";
//    private static String BRACE_PATTERN = "\\{|\\}";
//    private static String BRACKET_PATTERN = "\\[|\\]";
//    private static String SEMICOLON_PATTERN = "\\;";
//    private static String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
//    private static String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
//    
//    private static Pattern PATTERN = Pattern.compile(
//            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
//            + "|(?<PAREN>" + PAREN_PATTERN + ")"
//            + "|(?<BRACE>" + BRACE_PATTERN + ")"
//            + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
//            + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
//            + "|(?<STRING>" + STRING_PATTERN + ")"
//            + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
//    );
    private static String Theme = "default";
    private  Pattern PATTERN;
    private CodeArea codeArea;
    private ExecutorService executor;
    private String filePath = "";
    public Scene scene;

    public static enum FILE_TYPES {
        actionscript, ada, ansforth94, asp, automake, awk, bennugd, bibtex, bluespec, boo, c, cg, changelog, cmake, cobol, cpp, cpphdr, csharp, css, cuda, d, def, desktop, diff, docbook, dosbatch, dot, dpatch, dtd, eiffel, erlang, fcl, fortran, fsharp, gap, gdblog, genie, glsl, gtkdoc, gtkrc, haddock, haskell, haskellliterate, html, idlexelis, imagej, ini, j, jade, java, javascript, json, julia, latex, lex, libtool, llvm, m4, makefile, mallard, markdown, matlab, mediawiki, modelica, mxml, nemerle, nemo_action, netrexx, nsis, objj, ocaml, ocl, octave, ooc, opal, pascal, perl, php, pig, pkgconfig, po, protobuf, puppet, python, python3, R, rpmspec, ruby, rust, scala, scheme, scilab, sh, sparql, sql, sweave, systemverilog, t2t, tcl, thrift, vala, vbnet, verilog, vhdl, xml, yacc, yaml
    };

    private static FILE_TYPES CodingStyle;

    public SyntaxTextAreaFX() {
        this("");
    }

    public static String getTheme() {
        return Theme;
    }

    public static void setTheme(String Theme) {
        SyntaxTextAreaFX.Theme = Theme;
    }

    public static void setThemeDefault() {
        SyntaxTextAreaFX.Theme = "default";
    }

    public SyntaxTextAreaFX(String file) {
        filePath = file;
        executor = Executors.newSingleThreadExecutor();
        codeArea = new CodeArea();

        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        EventStream<PlainTextChange> textChanges = codeArea.plainTextChanges();
        textChanges
                .successionEnds(Duration.ofMillis(500))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(textChanges)
                .map(Try::get)
                .subscribe(this::applyHighlighting);
        String fileExtension = "";
        if (file.trim().length() > 1 && file.contains(".")) {
            fileExtension = file.substring(file.lastIndexOf(".")).trim().toLowerCase();
        }
        if (file.trim().length() > 1 && file.contains(".") && Arrays.asList(FILE_TYPES.values()).contains(fileExtension)) {
            // setCodingStyle();
           // setCodingStyle(FILE_TYPES.valueOf(fileExtension));
            codeArea.getStylesheets().add(SyntaxTextAreaFX.class.getResource("res/css/" + getTheme() + "/" + getCodingStyle() + ".css").toExternalForm());

        } else {
            codeArea.getStylesheets().add(SyntaxTextAreaFX.class.getResource("res/css/default/java.css").toExternalForm());

        }
       // this.setText(this.readFile(filePath));
    }

    public void setText(String text) {
        codeArea.replaceText(0, 0, text);
        codeArea.getUndoManager().forgetHistory();
        codeArea.getUndoManager().mark();

    }

    public String getText() {
        return codeArea.getText();

    }

    public void appendText(String text) {
        codeArea.appendText(text);

    }

    public void setFont(Font v) {
        codeArea.setFont(v);
    }

    public CodeArea getNode() {
        return codeArea;
    }

    public void save() {
        if (filePath.trim().length() > 1) {
            try {
                write(new File(filePath), getText());
            } catch (IOException ex) {
                Logger.getLogger(SyntaxTextAreaFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("File Path is Empty !!!");
        }
    }

    public void saveAs(String filePath) {
        this.filePath = filePath;
        try {
            write(new File(filePath), getText());
        } catch (IOException ex) {
            Logger.getLogger(SyntaxTextAreaFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FILE_TYPES getCodingStyle() {
        return CodingStyle;
    }

    public void setCodingStyle(FILE_TYPES CodingStyle) {
        SyntaxTextAreaFX.CodingStyle = CodingStyle;
        generatePattern();
    }

    private void write(File f, String text) throws IOException {
        f.mkdirs();
        try (FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw)) {
            pw.print(text);
            pw.close();
            fw.close();
        }

    }

    private String readFile(String path) {
        String str = "";
        try {
            Charset encoding = Charset.defaultCharset();
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            str = new String(encoded, encoding);
        } catch (IOException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }

    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        String text = codeArea.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() throws Exception {
                return computeHighlighting(text);
            }
        };
        executor.execute(task);
        return task;
    }

    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        codeArea.setStyleSpans(0, highlighting);
    }

    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        generatePattern();
        while (matcher.find()) {
            String styleClass
                    = getStyleClass(matcher);
            /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    private String getStyleClass(Matcher matcher) {
        String styleClass = matcher.group("DECLARATIONS") != null ? "declarations"
                : matcher.group("PRIMITIVES") != null ? "primitives"
                : matcher.group("EXTERNALS") != null ? "externals"
                : matcher.group("STORAGECLASS") != null ? "storageclass"
                : matcher.group("SCOPEDECLARATIONS") != null ? "scopedeclarations"
                : matcher.group("FLOW") != null ? "flow"
                : matcher.group("MEMORY") != null ? "memory"
                : matcher.group("FUTURE") != null ? "future"
                : matcher.group("NULL") != null ? "nullvalue"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : matcher.group("PAREN") != null ? "paren"
                : matcher.group("BRACE") != null ? "brace"
                : matcher.group("BRACKET") != null ? "bracket"
                : matcher.group("SEMICOLON") != null ? "semicolon"
                : matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : null;
        switch (getCodingStyle()) {
            case actionscript:
                break;
            case ada:
                break;
            case ansforth94:
                break;
            case asp:
                break;
            case automake:
                break;
            case awk:
                break;
            case bennugd:
                break;
            case bibtex:
                break;
            case bluespec:
                break;
            case boo:
                break;
            case c:
                break;
            case cg:
                break;
            case changelog:
                break;
            case cmake:
                break;
            case cobol:
                break;
            case cpp:
                break;
            case cpphdr:
                break;
            case csharp:
                break;
            case css:
                break;
            case cuda:
                break;
            case d:
                break;
            case def:
                break;
            case desktop:
                break;
            case diff:
                break;
            case docbook:
                break;
            case dosbatch:
                break;
            case dot:
                break;
            case dpatch:
                break;
            case dtd:
                break;
            case eiffel:
                break;
            case erlang:
                break;
            case fcl:
                break;
            case fortran:
                break;
            case fsharp:
                break;
            case gap:
                break;
            case gdblog:
                break;
            case genie:
                break;
            case glsl:
                break;
            case gtkdoc:
                break;
            case gtkrc:
                break;
            case haddock:
                break;
            case haskell:
                break;
            case haskellliterate:
                break;
            case html:
                break;
            case idlexelis:
                break;
            case imagej:
                break;
            case ini:
                break;
            case j:
                break;
            case jade:
                break;
            case java:
                styleClass = matcher.group("DECLARATIONS") != null ? "declarations"
                        : matcher.group("PRIMITIVES") != null ? "primitives"
                        : matcher.group("EXTERNALS") != null ? "externals"
                        : matcher.group("STORAGECLASS") != null ? "storageclass"
                        : matcher.group("SCOPEDECLARATIONS") != null ? "scopedeclarations"
                        : matcher.group("FLOW") != null ? "flow"
                        : matcher.group("MEMORY") != null ? "memory"
                        : matcher.group("FUTURE") != null ? "future"
                        : matcher.group("NULL") != null ? "nullvalue"
                        : matcher.group("BOOLEAN") != null ? "boolean"
                        : matcher.group("PAREN") != null ? "paren"
                        : matcher.group("BRACE") != null ? "brace"
                        : matcher.group("BRACKET") != null ? "bracket"
                        : matcher.group("SEMICOLON") != null ? "semicolon"
                        : matcher.group("STRING") != null ? "string"
                        : matcher.group("COMMENT") != null ? "comment"
                        : null;
                break;
            case javascript:
                break;
            case json:
                break;
            case julia:
                break;
            case latex:
                break;
            case lex:
                break;
            case libtool:
                break;
            case llvm:
                break;
            case m4:
                break;
            case makefile:
                break;
            case mallard:
                break;
            case markdown:
                break;
            case matlab:
                break;
            case mediawiki:
                break;
            case modelica:
                break;
            case mxml:
                break;
            case nemerle:
                break;
            case nemo_action:
                break;
            case netrexx:
                break;
            case nsis:
                break;
            case objj:
                break;
            case ocaml:
                break;
            case ocl:
                break;
            case octave:
                break;
            case ooc:
                break;
            case opal:
                break;
            case pascal:
                break;
            case perl:
                break;
            case php:
                break;
            case pig:
                break;
            case pkgconfig:
                break;
            case po:
                break;
            case protobuf:
                break;
            case puppet:
                break;
            case python:
                break;
            case python3:
                break;
            case R:
                break;
            case rpmspec:
                break;
            case ruby:
                break;
            case rust:
                break;
            case scala:
                break;
            case scheme:
                break;
            case scilab:
                break;
            case sh:
                break;
            case sparql:
                break;
            case sql:
                break;
            case sweave:
                break;
            case systemverilog:
                break;
            case t2t:
                break;
            case tcl:
                break;
            case thrift:
                break;
            case vala:
                break;
            case vbnet:
                break;
            case verilog:
                break;
            case vhdl:
                break;
            case xml:
                break;
            case yacc:
                break;
            case yaml:
                break;
        }

        return styleClass;
    }

    private void generatePattern() {
        switch (getCodingStyle()) {
            case actionscript:
                break;
            case ada:
                break;
            case ansforth94:
                break;
            case asp:
                break;
            case automake:
                break;
            case awk:
                break;
            case bennugd:
                break;
            case bibtex:
                break;
            case bluespec:
                break;
            case boo:
                break;
            case c:
                break;
            case cg:
                break;
            case changelog:
                break;
            case cmake:
                break;
            case cobol:
                break;
            case cpp:
                break;
            case cpphdr:
                break;
            case csharp:
                break;
            case css:
                break;
            case cuda:
                break;
            case d:
                break;
            case def:
                break;
            case desktop:
                break;
            case diff:
                break;
            case docbook:
                break;
            case dosbatch:
                break;
            case dot:
                break;
            case dpatch:
                break;
            case dtd:
                break;
            case eiffel:
                break;
            case erlang:
                break;
            case fcl:
                break;
            case fortran:
                break;
            case fsharp:
                break;
            case gap:
                break;
            case gdblog:
                break;
            case genie:
                break;
            case glsl:
                break;
            case gtkdoc:
                break;
            case gtkrc:
                break;
            case haddock:
                break;
            case haskell:
                break;
            case haskellliterate:
                break;
            case html:
                break;
            case idlexelis:
                break;
            case imagej:
                break;
            case ini:
                break;
            case j:
                break;
            case jade:
                break;
            case java:
                String DECLARATIONS[] = new String[]{"class", "enum",
                    "extends", "implements",
                    "instanceof", "interface",
                    "native", "throws"};
                String PRIMITIVES[] = new String[]{
                    "boolean", "byte", "char", "double",
                    "float", "int", "long", "short",
                    "void"};
                String EXTERNALS[] = new String[]{
                    "import", "package"};
                String STORAGECLASS[] = new String[]{
                    "abstract", "final", "static", "strictfp",
                    "synchronized", "transient", "volatile"};
                String SCOPEDECLARATIONS[] = new String[]{
                    "private", "protected", "public"};
                String FLOW[] = new String[]{
                    "assert", "break", "case", "catch", "continue", "default", "do", "else",
                    "finally", "for", "if", "return", "throw", "switch", "try", "while"};
                String MEMORY[] = new String[]{
                    "new", "super", "this"};
                String FUTURE[] = new String[]{
                    "const", "goto"};
                String NULL[] = new String[]{
                    "null"};
                String BOOLEAN[] = new String[]{"true", "false"};

                String DECLARATIONS_PATTERN = "\\b(" + String.join("|", DECLARATIONS) + ")\\b";
                String PRIMITIVES_PATTERN = "\\b(" + String.join("|", PRIMITIVES) + ")\\b";
                String EXTERNALS_PATTERN = "\\b(" + String.join("|", EXTERNALS) + ")\\b";
                String STORAGECLASS_PATTERN = "\\b(" + String.join("|", STORAGECLASS) + ")\\b";
                String SCOPEDECLARATIONS_PATTERN = "\\b(" + String.join("|", SCOPEDECLARATIONS) + ")\\b";
                String FLOW_PATTERN = "\\b(" + String.join("|", FLOW) + ")\\b";
                String MEMORY_PATTERN = "\\b(" + String.join("|", MEMORY) + ")\\b";
                String FUTURE_PATTERN = "\\b(" + String.join("|", FUTURE) + ")\\b";
                String NULL_PATTERN = "\\b(" + String.join("|", NULL) + ")\\b";
                String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
                String PAREN_PATTERN = "\\(|\\)";
                String BRACE_PATTERN = "\\{|\\}";
                String BRACKET_PATTERN = "\\[|\\]";
                String SEMICOLON_PATTERN = "\\;";
                String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
                String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

                PATTERN = Pattern.compile(
                        "(?<DECLARATIONS>" + DECLARATIONS_PATTERN + ")"
                        + "|(?<PRIMITIVES>" + PRIMITIVES_PATTERN + ")"
                        + "|(?<EXTERNALS>" + EXTERNALS_PATTERN + ")"
                        + "|(?<STORAGECLASS>" + STORAGECLASS_PATTERN + ")"
                        + "|(?<SCOPEDECLARATIONS>" + SCOPEDECLARATIONS_PATTERN + ")"
                        + "|(?<FLOW>" + FLOW_PATTERN + ")"
                        + "|(?<MEMORY>" + MEMORY_PATTERN + ")"
                        + "|(?<FUTURE>" + FUTURE_PATTERN + ")"
                        + "|(?<NULL>" + NULL_PATTERN + ")"
                        + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
                        + "|(?<PAREN>" + PAREN_PATTERN + ")"
                        + "|(?<BRACE>" + BRACE_PATTERN + ")"
                        + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                        + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                        + "|(?<STRING>" + STRING_PATTERN + ")"
                        + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                );

                break;
            case javascript:
                break;
            case json:
                break;
            case julia:
                break;
            case latex:
                break;
            case lex:
                break;
            case libtool:
                break;
            case llvm:
                break;
            case m4:
                break;
            case makefile:
                break;
            case mallard:
                break;
            case markdown:
                break;
            case matlab:
                break;
            case mediawiki:
                break;
            case modelica:
                break;
            case mxml:
                break;
            case nemerle:
                break;
            case nemo_action:
                break;
            case netrexx:
                break;
            case nsis:
                break;
            case objj:
                break;
            case ocaml:
                break;
            case ocl:
                break;
            case octave:
                break;
            case ooc:
                break;
            case opal:
                break;
            case pascal:
                break;
            case perl:
                break;
            case php:
                break;
            case pig:
                break;
            case pkgconfig:
                break;
            case po:
                break;
            case protobuf:
                break;
            case puppet:
                break;
            case python:
                break;
            case python3:
                break;
            case R:
                break;
            case rpmspec:
                break;
            case ruby:
                break;
            case rust:
                break;
            case scala:
                break;
            case scheme:
                break;
            case scilab:
                break;
            case sh:
                break;
            case sparql:
                break;
            case sql:
                break;
            case sweave:
                break;
            case systemverilog:
                break;
            case t2t:
                break;
            case tcl:
                break;
            case thrift:
                break;
            case vala:
                break;
            case vbnet:
                break;
            case verilog:
                break;
            case vhdl:
                break;
            case xml:
                break;
            case yacc:
                break;
            case yaml:
                break;
        }

    }

}
