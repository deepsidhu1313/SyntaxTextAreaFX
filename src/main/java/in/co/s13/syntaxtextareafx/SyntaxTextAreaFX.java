/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.s13.syntaxtextareafx;

import static in.co.s13.syntaxtextareafx.SyntaxTextAreaFX.CONSTANTS.ALT_FILE_TYPES;
import in.co.s13.syntaxtextareafx.SyntaxTextAreaFX.CONSTANTS.FILE_TYPES;
import in.co.s13.syntaxtextareafx.SyntaxTextAreaFX.CONSTANTS.LANGS;
import in.co.s13.syntaxtextareafx.langs.ActionScript;
import in.co.s13.syntaxtextareafx.langs.Ada;
import in.co.s13.syntaxtextareafx.langs.Ansforth94;
import in.co.s13.syntaxtextareafx.langs.Asp;
import in.co.s13.syntaxtextareafx.langs.Automake;
import in.co.s13.syntaxtextareafx.langs.Awk;
import in.co.s13.syntaxtextareafx.langs.Bennugd;
import in.co.s13.syntaxtextareafx.langs.Bibtex;
import in.co.s13.syntaxtextareafx.langs.Bluespec;
import in.co.s13.syntaxtextareafx.langs.Boo;
import in.co.s13.syntaxtextareafx.langs.C;
import in.co.s13.syntaxtextareafx.langs.Cg;
import in.co.s13.syntaxtextareafx.langs.Changelog;
import in.co.s13.syntaxtextareafx.langs.Cmake;
import in.co.s13.syntaxtextareafx.langs.Cobol;
import in.co.s13.syntaxtextareafx.langs.Cpp;
import in.co.s13.syntaxtextareafx.langs.CppHdr;
import in.co.s13.syntaxtextareafx.langs.Csharp;
import in.co.s13.syntaxtextareafx.langs.Css;
import in.co.s13.syntaxtextareafx.langs.Csv;
import in.co.s13.syntaxtextareafx.langs.Cuda;
import in.co.s13.syntaxtextareafx.langs.D;
import in.co.s13.syntaxtextareafx.langs.Def;
import in.co.s13.syntaxtextareafx.langs.Desktop;
import in.co.s13.syntaxtextareafx.langs.Diff;
import in.co.s13.syntaxtextareafx.langs.Docbook;
import in.co.s13.syntaxtextareafx.langs.Dosbatch;
import in.co.s13.syntaxtextareafx.langs.Dot;
import in.co.s13.syntaxtextareafx.langs.Dpatch;
import in.co.s13.syntaxtextareafx.langs.Dtd;
import in.co.s13.syntaxtextareafx.langs.Eiffel;
import in.co.s13.syntaxtextareafx.langs.Erlang;
import in.co.s13.syntaxtextareafx.langs.Fcl;
import in.co.s13.syntaxtextareafx.langs.Forth;
import in.co.s13.syntaxtextareafx.langs.Fortran;
import in.co.s13.syntaxtextareafx.langs.Fsharp;
import in.co.s13.syntaxtextareafx.langs.Gap;
import in.co.s13.syntaxtextareafx.langs.GdbLog;
import in.co.s13.syntaxtextareafx.langs.Genie;
import in.co.s13.syntaxtextareafx.langs.Glsl;
import in.co.s13.syntaxtextareafx.langs.Go;
import in.co.s13.syntaxtextareafx.langs.GtkDoc;
import in.co.s13.syntaxtextareafx.langs.Gtkrc;
import in.co.s13.syntaxtextareafx.langs.Haddock;
import in.co.s13.syntaxtextareafx.langs.Haskell;
import in.co.s13.syntaxtextareafx.langs.HaskellLiterate;
import in.co.s13.syntaxtextareafx.langs.Html;
import in.co.s13.syntaxtextareafx.langs.Idl;
import in.co.s13.syntaxtextareafx.langs.IdlExelis;
import in.co.s13.syntaxtextareafx.langs.Imagej;
import in.co.s13.syntaxtextareafx.langs.Ini;
import in.co.s13.syntaxtextareafx.langs.Jade;
import in.co.s13.syntaxtextareafx.langs.Java;
import in.co.s13.syntaxtextareafx.langs.Javascript;
import in.co.s13.syntaxtextareafx.langs.Json;
import in.co.s13.syntaxtextareafx.langs.Julia;
import in.co.s13.syntaxtextareafx.langs.Latex;
import in.co.s13.syntaxtextareafx.langs.Lex;
import in.co.s13.syntaxtextareafx.langs.Libtool;
import in.co.s13.syntaxtextareafx.langs.Llvm;
import in.co.s13.syntaxtextareafx.langs.Lua;
import in.co.s13.syntaxtextareafx.langs.M4;
import in.co.s13.syntaxtextareafx.langs.Makefile;
import in.co.s13.syntaxtextareafx.langs.Mallard;
import in.co.s13.syntaxtextareafx.langs.Markdown;
import in.co.s13.syntaxtextareafx.langs.Matlab;
import in.co.s13.syntaxtextareafx.langs.Mediawiki;
import in.co.s13.syntaxtextareafx.langs.Meson;
import in.co.s13.syntaxtextareafx.langs.Modelica;
import in.co.s13.syntaxtextareafx.langs.Mxml;
import in.co.s13.syntaxtextareafx.langs.Nemerle;
import in.co.s13.syntaxtextareafx.langs.NemoAction;
import in.co.s13.syntaxtextareafx.langs.Netrexx;
import in.co.s13.syntaxtextareafx.langs.Nsis;
import in.co.s13.syntaxtextareafx.langs.Objc;
import in.co.s13.syntaxtextareafx.langs.Objj;
import in.co.s13.syntaxtextareafx.langs.Ocaml;
import in.co.s13.syntaxtextareafx.langs.Ocl;
import in.co.s13.syntaxtextareafx.langs.Octave;
import in.co.s13.syntaxtextareafx.langs.Ooc;
import in.co.s13.syntaxtextareafx.langs.Opal;
import in.co.s13.syntaxtextareafx.langs.Opencl;
import in.co.s13.syntaxtextareafx.langs.Pascal;
import in.co.s13.syntaxtextareafx.langs.Perl;
import in.co.s13.syntaxtextareafx.langs.Php;
import in.co.s13.syntaxtextareafx.langs.Pig;
import in.co.s13.syntaxtextareafx.langs.Pkgconfig;
import in.co.s13.syntaxtextareafx.langs.Po;
import in.co.s13.syntaxtextareafx.langs.Prolog;
import in.co.s13.syntaxtextareafx.langs.Protobuf;
import in.co.s13.syntaxtextareafx.langs.Puppet;
import in.co.s13.syntaxtextareafx.langs.Python;
import in.co.s13.syntaxtextareafx.langs.Python3;
import in.co.s13.syntaxtextareafx.langs.R;
import in.co.s13.syntaxtextareafx.langs.Rpmspec;
import in.co.s13.syntaxtextareafx.langs.Rst;
import in.co.s13.syntaxtextareafx.langs.Ruby;
import in.co.s13.syntaxtextareafx.langs.Rust;
import in.co.s13.syntaxtextareafx.langs.Scala;
import in.co.s13.syntaxtextareafx.langs.Scheme;
import in.co.s13.syntaxtextareafx.langs.Scilab;
import in.co.s13.syntaxtextareafx.langs.Sh;
import in.co.s13.syntaxtextareafx.langs.Sml;
import in.co.s13.syntaxtextareafx.langs.Sparql;
import in.co.s13.syntaxtextareafx.langs.Sql;
import in.co.s13.syntaxtextareafx.langs.Sweave;
import in.co.s13.syntaxtextareafx.langs.Systemverilog;
import in.co.s13.syntaxtextareafx.langs.T2t;
import in.co.s13.syntaxtextareafx.langs.Tcl;
import in.co.s13.syntaxtextareafx.langs.Texinfo;
import in.co.s13.syntaxtextareafx.langs.Text;
import in.co.s13.syntaxtextareafx.langs.Thrift;
import in.co.s13.syntaxtextareafx.langs.Vala;
import in.co.s13.syntaxtextareafx.langs.Vbnet;
import in.co.s13.syntaxtextareafx.langs.Verilog;
import in.co.s13.syntaxtextareafx.langs.Vhdl;
import in.co.s13.syntaxtextareafx.langs.Xml;
import in.co.s13.syntaxtextareafx.langs.Xslt;
import in.co.s13.syntaxtextareafx.langs.Yacc;
import in.co.s13.syntaxtextareafx.langs.Yaml;
import in.co.s13.syntaxtextareafx.meta.Generator;
import in.co.s13.syntaxtextareafx.meta.Syntax;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

/**
 *
 * @author Nika
 */
public class SyntaxTextAreaFX extends CodeArea {

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
    private Pattern PATTERN;
    //private CodeArea this;
    private ExecutorService executor;
    private String filePath = "", externalThemePath = "";
    public Scene scene;
    private Syntax syntax;

    public static enum Mode {
        INSERT, COMPLETION
    };

    private Mode mode = Mode.INSERT;
    ArrayList<String> suggestions;
    private static final String COMMIT_ACTION = "commit";

    public static class CONSTANTS {

        public static enum FILE_TYPES {
            as, adb, ads, forth, asp, am, awk,
            prg, bib, bsv, boo, c, cg, changelog,
            cmake, cobol, cpp, cxx, cc, C, h, csharp, css, cuda, d,
            def, desktop, diff, patch, rej, docbook, dosbatch, dot, dpatch,
            dtd, eiffel, erlang, fcl, fortran, fsharp, gap, gdblog,
            genie, glsl, gtkdoc, gtkrc, haddock, haskell, haskellliterate,
            html, idlexelis, imagej, ini, j, jade, java, javascript, json,
            julia, latex, lex, libtool, llvm, m, m4, makefile, Makefile, GNUmakefile, mallard, markdown,
            matlab, mediawiki, modelica, mxml, nemerle, nemo_action, netrexx,
            nsis, objj, ocaml, ocl, octave, ooc, opal, pascal, perl, php, pig,
            pkgconfig, po, protobuf, puppet, python, python3, r, rpmspec, ruby,
            rust, scala, scheme, scilab, sh, sparql, sql, sweave, systemverilog,
            text, txt, t2t, tcl, thrift, vala, vbnet, verilog, vhdl, xml, yacc, yaml
        };

        public static enum LANGS {
            actionscript, ada, ansforth94, asp,
            automake, awk, bennugd, bibtex,
            bluespec, boo, c, cg, changelog,
            chdr, cmake, cobol, cpp,
            csharp, css, csv,
            cuda, d, def, desktop,
            diff, docbook, dosbatch, dot,
            dpatch, dtd, eiffel, erlang,
            fcl, forth, fortran, fsharp,
            gap, gdb_log, genie, glsl,
            go, gtk_doc, gtkrc, haddock,
            haskell, haskell_literate, html, idl, idl_exelis,
            imagej, ini, j, jade, java,
            javascript, json, julia,
            latex, lex, libtool, llvm,
            lua, m4, makefile, mallard, markdown,
            matlab, mediawiki, meson, modelica,
            mxml, nemerle, nemo_action, netrexx,
            nsis, objc, objj, ocaml,
            ocl, octave, ooc, opal,
            opencl, pascal, perl, php,
            pig, pkgconfig, po, prolog,
            protobuf, puppet, python, python3,
            R, rpmspec, rst, ruby,
            rust, scala, scheme, scilab,
            sh, sml, sparql, sql,
            sweave, systemverilog, t2t, tcl,
            texinfo, text, thrift, vala, vbnet,
            verilog, vhdl, xml, xslt,
            yacc, yaml
        };

        public static String[] ALT_FILE_TYPES = new String[]{"4th", "", "c++"};

    }

    /**
     * The language this editor highlights.
     *
     * <p>Per instance, deliberately. This used to be static, so opening a Java
     * file and a Python file in two editors made both highlight as whichever
     * was configured last — which is precisely how a multi-tab IDE uses this
     * control.
     */
    private LANGS CodingStyle = LANGS.text;

    /**
     * *
     * Default Constructor
     */
    public SyntaxTextAreaFX() {
        this("");
    }

    /**
     *
     * @return Theme Name as String
     */
    public static String getTheme() {
        return Theme;
    }

    /**
     * *
     *
     * @param Theme Name of theme as String, where Theme is the name of the
     * directory available in res/css/Theme/
     *
     */
    public static void setTheme(String Theme) {
        SyntaxTextAreaFX.Theme = Theme;
    }

    /**
     * *
     * Sets theme to default
     */
    public static void setThemeDefault() {
        SyntaxTextAreaFX.Theme = "default";
    }

    /**
     * The language to highlight a file as, chosen from its name.
     *
     * <p>Callers previously had to map extensions themselves, or ask
     * {@code supports} and then guess. Unknown and extension-less names fall
     * back to plain text rather than throwing, so an editor can always be
     * opened on any file.
     *
     * @param fileName a file name or path
     * @return the matching language, or {@link LANGS#text}
     */
    public static LANGS languageForFile(String fileName) {
        if (fileName == null) {
            return LANGS.text;
        }
        String name = fileName.trim();
        int dot = name.lastIndexOf('.');
        if (dot < 0 || dot == name.length() - 1) {
            return LANGS.text;
        }
        String extension = name.substring(dot + 1).toLowerCase();
        for (LANGS language : LANGS.values()) {
            if (language.name().equalsIgnoreCase(extension)) {
                return language;
            }
        }
        // A recognised file type does not always name a language: ".txt" is a
        // supported type but the language is "text". Fall back rather than
        // letting valueOf throw.
        try {
            return LANGS.valueOf(extension);
        } catch (IllegalArgumentException notALanguageName) {
            return LANGS.text;
        }
    }

    /**
     * *
     *
     * @param fileExtension a string file extension
     * @return boolean value true/false if fileExtension is supported
     */
    public static boolean supports(String fileExtension) {

        for (FILE_TYPES c : FILE_TYPES.values()) {
            if (c.name().equals(fileExtension)) {
                return true;
            }
        }

        return false;
    }

    /**
     * *
     *
     * @param file String Path to file Reads file from given path and set
     * contents to SyntaxTextAreaFX
     */
    public SyntaxTextAreaFX(String file) {
        filePath = file;
        // executor = Executors.newSingleThreadExecutor();
        executor = Executors.newSingleThreadExecutor(runnable -> {
            Thread thread = Executors.defaultThreadFactory().newThread(runnable);
            thread.setDaemon(true);
            return thread;
        });

        //this = new CodeArea();
        this.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
//                if (suggestion != null) {
//                    if (suggestion.insertSelection()) {
//                        event.consume();
//                        final int position = codeArea.getCaretPosition();
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                codeArea.replaceText(position - 1, 1,"");
//                            }
//                        });
//                    }
//                }
            }
        });

        this.setParagraphGraphicFactory(LineNumberFactory.get(this));
//        EventStream<PlainTextChange> textChanges = codeArea.plainTextChanges();
//        textChanges
//                .successionEnds(Duration.ofMillis(500))
//                .supplyTask(this::computeHighlightingAsync)
//                .awaitLatest(textChanges)
//                .map(Try::get)
//                .subscribe(this::applyHighlighting);
        String fileExtension = "";
        if (file.trim().length() > 1 && file.contains(".")) {
            fileExtension = file.substring(file.lastIndexOf(".") + 1).trim();
        } else if (file.trim().length() > 1 && (!file.contains("."))) {
            fileExtension = new File(file).getName();
// setCodingStyle(getCodingStyleFromFileType(getFileTypeFromSpecialFileName(new File(file).getName())));

        }
        // System.out.println("File Extension is: "+fileExtension+" for "+file);
        if (file.trim().length() > 1 && file.contains(".") && SyntaxTextAreaFX.supports(fileExtension)) {
            // setCodingStyle();
            setCodingStyle(getCodingStyleFromFileType(getFileTypeFromFileExtension(fileExtension)));
//              System.out.println("1");
        } //        else if (file.trim().length() > 1 && !file.contains(".") && SyntaxTextAreaFX.supports(file.substring(file.lastIndexOf("/")))) {
        //            // setCodingStyle();
        //            setCodingStyle(getCodingStyleFromFileType(getFileTypeFromFileExtension(file.substring(file.lastIndexOf("/")))));
        //
        //        }
        else {
            setCodingStyle(getCodingStyleFromFileType(FILE_TYPES.text));
            generatePattern();
//            codeArea.getStylesheets().add(SyntaxTextAreaFX.class.getResource("res/css/default/java.css").toExternalForm());
//               System.out.println("12");

        }
        this.richChanges()
                .filter(ch -> !ch.getInserted().equals(ch.getRemoved())) // XXX
                .successionEnds(Duration.ofMillis(500))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(this.richChanges())
                .filterMap(t -> {
                    if (t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(this::applyHighlighting);

        this.setText(this.readFile(filePath));

        Subscription s = this.plainTextChanges().subscribe(tc -> {
            String removed = tc.getRemoved();
            String inserted = tc.getInserted();

            if (!removed.isEmpty() && inserted.isEmpty()) {
                // deletion
            } else if (!inserted.isEmpty() && removed.isEmpty()) {
                // insertion
            } else {
                // replacement
            }
        });

    }

    /**
     * *
     *
     * @param text sets text to SyntaxTextAreaFX
     */
    public void setText(String text) {

        this.replaceText(0, this.getText().length(), text);
        this.getUndoManager().forgetHistory();
        this.getUndoManager().mark();

    }

    /**
     *
     * @return Text as String from SyntaxTextAreaFX
     */
    /**
     * *
     *
     * @param text appends text to SyntaxTextAreaFX
     */
    public void appendText(String text) {
        this.appendText(text);

    }

    /**
     * **
     *
     * @return the Graphical Node, which can be added to Layout
     */
    public CodeArea getNode() {
        return this;
    }

    /**
     * *
     *
     * @return Path to External Theme
     */
    public String getExternalThemePath() {
        return externalThemePath;
    }

    /**
     * *
     *
     * @param externalThemePath sets external theme path
     */
    public void setExternalThemePath(String externalThemePath) {
        this.externalThemePath = externalThemePath;
    }

    /**
     * *
     * saves the contents of SyntaxTextAreaFX to @FilePath
     */
    public void save() {
        if (filePath.trim().length() > 0) {
            try {
                write(new File(filePath), getText());
            } catch (IOException ex) {
                Logger.getLogger(SyntaxTextAreaFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("File Path is Empty !!!");
        }
    }

    /**
     * *
     *
     * @param filePath saves content to filePath
     */
    public void saveAs(String filePath) {
        this.filePath = filePath;
        try {
            write(new File(filePath), getText());
        } catch (IOException ex) {
            Logger.getLogger(SyntaxTextAreaFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *
     *
     * @return get the Coding Style of SyntaxTextAreaFX
     */
    public LANGS getCodingStyle() {
        return CodingStyle;
    }

    /**
     * *
     *
     * @param CodingStyle sets the coding style of the SyntaxTextAreaFX
     */
    public void setCodingStyle(LANGS CodingStyle) {
        this.CodingStyle = CodingStyle;
        // Replace rather than append: switching language used to leave the
        // previous language's stylesheet applied underneath the new one.
        clearStyleSheets();
        if (externalThemePath.trim().length() == 0) {
            String resource = "res/css/" + getTheme() + "/" + getCodingStyle() + ".css";
            java.net.URL css = SyntaxTextAreaFX.class.getResource(resource);
            if (css == null) {
                // An unstyled editor is better than a crash on an unknown theme.
                Logger.getLogger(SyntaxTextAreaFX.class.getName())
                        .log(Level.WARNING, "No stylesheet {0}; continuing unstyled", resource);
            } else {
                this.getStylesheets().add(css.toExternalForm());
            }
        } else {
            this.getStylesheets().add(readFile(externalThemePath + "/" + getCodingStyle() + ".css"));
        }
        loadLanguage();
        loadKeywordSuggestions();
        generatePattern();
    }

    /**
     * *
     *
     * @param CSScode add CSS code to SyntaxTextAreaFX
     */
    public void addStyleSheet(String CSScode) {
        this.getStylesheets().add(CSScode);
    }

    /**
     * **
     * Remove all CSS coding sheets
     */
    public void clearStyleSheets() {
        this.getStylesheets().clear();
    }

    /**
     * *
     *
     * @return all CSS file as a List of Strings
     */
    public ObservableList<String> getAllStyleSheets() {
        return this.getStylesheets();
    }

    /**
     * *
     *
     * @param file File to be saved
     * @param text Content to write
     * @throws IOException if unable to find the file path
     */
    /**
     * Writes the editor's content as UTF-8.
     *
     * <p>This used a {@code FileWriter}, which encodes with the platform
     * default charset, so saving source containing any non-ASCII character
     * produced a different file on different machines.
     */
    private void write(File file, String text) throws IOException {
        File parent = file.getAbsoluteFile().getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        java.nio.file.Files.writeString(file.toPath(), text,
                java.nio.charset.StandardCharsets.UTF_8);
    }

    /**
     * *
     *
     * @param path path to file
     * @return contents of the file
     */
    private String readFile(String path) {
        String str = "";
        if (path.trim().length() > 1) {
            try {
                // UTF-8 rather than the platform default: source read on one
                // machine and written on another must survive the round trip.
                byte[] encoded = Files.readAllBytes(Paths.get(path));
                str = new String(encoded, java.nio.charset.StandardCharsets.UTF_8);
            } catch (IOException ex) {
                Logger.getLogger(SyntaxTextAreaFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str;
    }

    /**
     * *
     *
     * @return which computes the Highlighting
     */
    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        String text = this.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() throws Exception {
                return computeHighlighting(text);
            }
        };
        executor.execute(task);
        return task;
    }

    /**
     * *
     *
     * @param highlighting Apply Highlighting style to SyntaxTextAreaFX
     */
    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        this.setStyleSpans(0, highlighting);
    }

    /**
     * **
     *
     * @param text computes Highlighting on provided text
     * @return Collection
     */
    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        //generatePattern();
        while (matcher.find()) {
            String styleClass
                    = getStyleClass(matcher);
            System.out.println(styleClass);
            /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    private FILE_TYPES getFileTypeFromFileExtension(String fileExtension) {
        FILE_TYPES fileType = FILE_TYPES.txt;
        if (Arrays.asList(ALT_FILE_TYPES).contains(fileExtension)) {
            int index = Arrays.asList(ALT_FILE_TYPES).indexOf(fileExtension);
            switch (index) {
                case 0:
                    fileType = FILE_TYPES.forth;
                    break;
                case 2:
                    fileType = FILE_TYPES.cpp;
                    break;
//                default:
//                    fileType= FILE_TYPES.txt;
//                    break;

            }
        } else {
            fileType = FILE_TYPES.valueOf(fileExtension);

        }

        return fileType;
    }

    private FILE_TYPES getFileTypeFromSpecialFileName(String FileName) {
        FILE_TYPES fileType = FILE_TYPES.text;
//        if (Arrays.asList(SPECIAL_FILENAMES).contains(FileName)) {
//            return fileType;
//        }
//        SPECIAL_FILENAMES sFileName = SPECIAL_FILENAMES.valueOf(FileName);
//        switch (sFileName) {
//
//        
//        fileType = FILE_TYPES.am;
//                break;
//        }
        return fileType;
    }

    private LANGS getCodingStyleFromFileType(FILE_TYPES filetype) {
        LANGS language = LANGS.java;
        switch (filetype) {
            case as:
                language = LANGS.actionscript;
                break;
            case adb:
            case ads:
                language = LANGS.ada;
                break;

            case asp:
                language = LANGS.asp;
                break;
            case am:
                language = LANGS.automake;
                break;
            case awk:
                language = LANGS.awk;
                break;
            case prg:
                language = LANGS.bennugd;
                break;
            case bib:
                language = LANGS.bibtex;
                break;
            case bsv:
                language = LANGS.bluespec;
                break;
            case boo:
                language = LANGS.boo;
                break;
            case c:
                language = LANGS.c;
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
            case cxx:
            case cc:
            case C:
                language = LANGS.cpp;
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
            case patch:
            case rej:
                language = LANGS.diff;
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
            case forth:
                language = LANGS.ansforth94;
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
            case h:
                language = LANGS.chdr;
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
                language = LANGS.java;
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
            case m:
                language = LANGS.objc;

                break;
            case m4:
                break;
            case makefile:
            case Makefile:
            case GNUmakefile:
                language = LANGS.makefile;
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
            case r:
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
            default:
                language = LANGS.text;
                break;
        }
        return language;
    }

    private void loadKeywordSuggestions() {
        suggestions = syntax.getKeywords();
    }

    /**
     * Selects the highlighting rules for the current language.
     *
     * <p>The {@code LANGS} enum lists 121 languages but only some have rules
     * written for them. An unimplemented one used to fall through an empty
     * {@code case} and leave {@code syntax} unset, so the editor silently did
     * no highlighting at all and gave the caller no clue why. It now falls back
     * to plain text and says so.
     */
    /**
     * Whether highlighting rules exist for a language.
     *
     * <p>{@code LANGS} lists far more languages than have rules written; the
     * rest render as plain text.
     */
    /**
     * Languages that actually have highlighting rules.
     *
     * <p>{@code LANGS} lists 121 languages because the enum was generated from
     * the full definition set in {@code jsons/}; rules have been written for
     * these. The rest render as plain text.
     */
    private static final java.util.EnumSet<LANGS> IMPLEMENTED = java.util.EnumSet.of(
            LANGS.R, LANGS.actionscript, LANGS.ada, LANGS.ansforth94, LANGS.asp,
            LANGS.automake, LANGS.awk, LANGS.bennugd, LANGS.bibtex, LANGS.bluespec,
            LANGS.boo, LANGS.c, LANGS.cg, LANGS.changelog, LANGS.chdr, LANGS.cmake,
            LANGS.cobol, LANGS.cpp, LANGS.csharp, LANGS.css, LANGS.csv, LANGS.cuda,
            LANGS.d, LANGS.def, LANGS.desktop, LANGS.diff, LANGS.docbook, LANGS.dosbatch,
            LANGS.dot, LANGS.dpatch, LANGS.dtd, LANGS.eiffel, LANGS.erlang, LANGS.fcl,
            LANGS.forth, LANGS.fortran, LANGS.fsharp, LANGS.gap, LANGS.gdb_log,
            LANGS.genie, LANGS.glsl, LANGS.go, LANGS.gtk_doc, LANGS.gtkrc, LANGS.haddock,
            LANGS.haskell, LANGS.haskell_literate, LANGS.html, LANGS.idl,
            LANGS.idl_exelis, LANGS.imagej, LANGS.ini, LANGS.jade, LANGS.java,
            LANGS.javascript, LANGS.json, LANGS.julia, LANGS.latex, LANGS.lex,
            LANGS.libtool, LANGS.llvm, LANGS.lua, LANGS.m4, LANGS.makefile,
            LANGS.mallard, LANGS.markdown, LANGS.matlab, LANGS.mediawiki, LANGS.meson,
            LANGS.modelica, LANGS.mxml, LANGS.nemerle, LANGS.nemo_action, LANGS.netrexx,
            LANGS.nsis, LANGS.objc, LANGS.objj, LANGS.ocaml, LANGS.ocl, LANGS.octave,
            LANGS.ooc, LANGS.opal, LANGS.opencl, LANGS.pascal, LANGS.perl, LANGS.php,
            LANGS.pig, LANGS.pkgconfig, LANGS.po, LANGS.prolog, LANGS.protobuf,
            LANGS.puppet, LANGS.python, LANGS.python3, LANGS.rpmspec, LANGS.rst,
            LANGS.ruby, LANGS.rust, LANGS.scala, LANGS.scheme, LANGS.scilab, LANGS.sh,
            LANGS.sml, LANGS.sparql, LANGS.sql, LANGS.sweave, LANGS.systemverilog,
            LANGS.t2t, LANGS.tcl, LANGS.texinfo, LANGS.text, LANGS.thrift, LANGS.vala,
            LANGS.vbnet, LANGS.verilog, LANGS.vhdl, LANGS.xml, LANGS.xslt, LANGS.yacc,
            LANGS.yaml);


    /**
     * Whether highlighting rules exist for a language.
     *
     * <p>Ask before selecting: an unimplemented language renders as plain text.
     */
    public static boolean isLanguageImplemented(LANGS language) {
        return language != null && IMPLEMENTED.contains(language);
    }

    /** The languages that have highlighting rules, for populating a menu. */
    public static java.util.Set<LANGS> implementedLanguages() {
        return java.util.Collections.unmodifiableSet(IMPLEMENTED);
    }

    private void loadLanguage() {
        syntax = null;
        switch (getCodingStyle()) {
            case actionscript:
                syntax = new Syntax(new ActionScript());
                break;
            case ada:
                syntax = new Syntax(new Ada());
                break;
            case ansforth94:
                syntax = new Syntax(new Ansforth94());
                break;
            case asp:
                syntax = new Syntax(new Asp());
                break;
            case automake:
                syntax = new Syntax(new Automake());
                break;
            case awk:
                syntax = new Syntax(new Awk());
                break;
            case bennugd:
                syntax = new Syntax(new Bennugd());
                break;
            case bibtex:
                syntax = new Syntax(new Bibtex());
                break;
            case bluespec:
                syntax = new Syntax(new Bluespec());
                break;
            case boo:
                syntax = new Syntax(new Boo());
                break;
            case c:
                syntax = new Syntax(new C());

                break;
            case cg:
                syntax = new Syntax(new Cg());
                break;
            case changelog:
                syntax = new Syntax(new Changelog());
                break;
            case cmake:
                syntax = new Syntax(new Cmake());
                break;
            case cobol:
                syntax = new Syntax(new Cobol());
                break;
            case cpp:
                syntax = new Syntax(new Cpp());

                break;
            case chdr:
                syntax = new Syntax(new CppHdr());

                break;
            case csharp:
                syntax = new Syntax(new Csharp());
                break;
            case css:
                syntax = new Syntax(new Css());
                break;
            case cuda:
                syntax = new Syntax(new Cuda());
                break;
            case d:
                syntax = new Syntax(new D());
                break;
            case def:
                syntax = new Syntax(new Def());
                break;
            case desktop:
                syntax = new Syntax(new Desktop());
                break;
            case diff:
                syntax = new Syntax(new Diff());
                break;
            case docbook:
                syntax = new Syntax(new Docbook());
                break;
            case dosbatch:
                syntax = new Syntax(new Dosbatch());
                break;
            case dot:
                syntax = new Syntax(new Dot());
                break;
            case dpatch:
                syntax = new Syntax(new Dpatch());
                break;
            case dtd:
                syntax = new Syntax(new Dtd());
                break;
            case eiffel:
                syntax = new Syntax(new Eiffel());
                break;
            case erlang:
                syntax = new Syntax(new Erlang());
                break;
            case fcl:
                syntax = new Syntax(new Fcl());
                break;
            case forth:
                syntax = new Syntax(new Forth());
                break;
            case fortran:
                syntax = new Syntax(new Fortran());
                break;
            case fsharp:
                syntax = new Syntax(new Fsharp());
                break;
            case gap:
                syntax = new Syntax(new Gap());
                break;
            case gdb_log:
                syntax = new Syntax(new GdbLog());
                break;
            case genie:
                syntax = new Syntax(new Genie());
                break;
            case glsl:
                syntax = new Syntax(new Glsl());
                break;
            case gtk_doc:
                syntax = new Syntax(new GtkDoc());
                break;
            case gtkrc:
                syntax = new Syntax(new Gtkrc());
                break;
            case haddock:
                syntax = new Syntax(new Haddock());
                break;
            case haskell:
                syntax = new Syntax(new Haskell());
                break;
            case haskell_literate:
                syntax = new Syntax(new HaskellLiterate());
                break;
            case html:
                syntax = new Syntax(new Html());
                break;
            case idl_exelis:
                syntax = new Syntax(new IdlExelis());
                break;
            case imagej:
                syntax = new Syntax(new Imagej());
                break;
            case ini:
                syntax = new Syntax(new Ini());
                break;
            case j:
                break;
            case jade:
                syntax = new Syntax(new Jade());
                break;
            case java:
                syntax = new Syntax(new Java());
                break;
            case javascript:
                syntax = new Syntax(new Javascript());
                break;
            case json:
                syntax = new Syntax(new Json());
                break;
            case julia:
                syntax = new Syntax(new Julia());
                break;
            case latex:
                syntax = new Syntax(new Latex());
                break;
            case lex:
                syntax = new Syntax(new Lex());
                break;
            case libtool:
                syntax = new Syntax(new Libtool());
                break;
            case llvm:
                syntax = new Syntax(new Llvm());
                break;
            case m4:
                syntax = new Syntax(new M4());
                break;
            case makefile:
                syntax = new Syntax(new Makefile());
                break;
            case mallard:
                syntax = new Syntax(new Mallard());
                break;
            case markdown:
                syntax = new Syntax(new Markdown());
                break;
            case matlab:
                syntax = new Syntax(new Matlab());
                break;
            case mediawiki:
                syntax = new Syntax(new Mediawiki());
                break;
            case modelica:
                syntax = new Syntax(new Modelica());
                break;
            case mxml:
                syntax = new Syntax(new Mxml());
                break;
            case nemerle:
                syntax = new Syntax(new Nemerle());
                break;
            case nemo_action:
                syntax = new Syntax(new NemoAction());
                break;
            case netrexx:
                syntax = new Syntax(new Netrexx());
                break;
            case nsis:
                syntax = new Syntax(new Nsis());
                break;
            case objc:
                syntax = new Syntax(new Objc());

                break;
            case objj:
                syntax = new Syntax(new Objj());
                break;
            case ocaml:
                syntax = new Syntax(new Ocaml());
                break;
            case ocl:
                syntax = new Syntax(new Ocl());
                break;
            case octave:
                syntax = new Syntax(new Octave());
                break;
            case ooc:
                syntax = new Syntax(new Ooc());
                break;
            case opal:
                syntax = new Syntax(new Opal());
                break;
            case pascal:
                syntax = new Syntax(new Pascal());
                break;
            case perl:
                syntax = new Syntax(new Perl());
                break;
            case php:
                syntax = new Syntax(new Php());
                break;
            case pig:
                syntax = new Syntax(new Pig());
                break;
            case pkgconfig:
                syntax = new Syntax(new Pkgconfig());
                break;
            case po:
                syntax = new Syntax(new Po());
                break;
            case protobuf:
                syntax = new Syntax(new Protobuf());
                break;
            case puppet:
                syntax = new Syntax(new Puppet());
                break;
            case python:
                syntax = new Syntax(new Python());
                break;
            case python3:
                syntax = new Syntax(new Python3());
                break;
            case R:
                syntax = new Syntax(new R());
                break;
            case rpmspec:
                syntax = new Syntax(new Rpmspec());
                break;
            case ruby:
                syntax = new Syntax(new Ruby());
                break;
            case rust:
                syntax = new Syntax(new Rust());
                break;
            case scala:
                syntax = new Syntax(new Scala());
                break;
            case scheme:
                syntax = new Syntax(new Scheme());
                break;
            case scilab:
                syntax = new Syntax(new Scilab());
                break;
            case sh:
                syntax = new Syntax(new Sh());
                break;
            case sparql:
                syntax = new Syntax(new Sparql());
                break;
            case sql:
                syntax = new Syntax(new Sql());
                break;
            case sweave:
                syntax = new Syntax(new Sweave());
                break;
            case systemverilog:
                syntax = new Syntax(new Systemverilog());
                break;
            case t2t:
                syntax = new Syntax(new T2t());
                break;
            case tcl:
                syntax = new Syntax(new Tcl());
                break;
            case thrift:
                syntax = new Syntax(new Thrift());
                break;
            case vala:
                syntax = new Syntax(new Vala());
                break;
            case vbnet:
                syntax = new Syntax(new Vbnet());
                break;
            case verilog:
                syntax = new Syntax(new Verilog());
                break;
            case vhdl:
                syntax = new Syntax(new Vhdl());
                break;
            case xml:
                syntax = new Syntax(new Xml());
                break;
            case yacc:
                syntax = new Syntax(new Yacc());
                break;
            case yaml:
                syntax = new Syntax(new Yaml());
                break;
            case csv:
                syntax = new Syntax(new Csv());
                break;
            case go:
                syntax = new Syntax(new Go());
                break;
            case idl:
                syntax = new Syntax(new Idl());
                break;
            case lua:
                syntax = new Syntax(new Lua());
                break;
            case meson:
                syntax = new Syntax(new Meson());
                break;
            case opencl:
                syntax = new Syntax(new Opencl());
                break;
            case prolog:
                syntax = new Syntax(new Prolog());
                break;
            case rst:
                syntax = new Syntax(new Rst());
                break;
            case sml:
                syntax = new Syntax(new Sml());
                break;
            case texinfo:
                syntax = new Syntax(new Texinfo());
                break;
            case xslt:
                syntax = new Syntax(new Xslt());
                break;
            default:
                syntax = new Syntax(new Text());
                break;

        }
    
        if (syntax == null) {
            Logger.getLogger(SyntaxTextAreaFX.class.getName()).log(Level.INFO,
                    "No highlighting rules for {0}; showing it as plain text. "
                    + "See isLanguageImplemented(LANGS).", getCodingStyle());
            syntax = new Syntax(new Text());
        }
    }

    /**
     * *
     * @param
     *
     * @return s
     */
    private String getStyleClass(Matcher matcher) {

        return syntax.getStyleClass(matcher);
    }

    private void generatePattern() {

        PATTERN = syntax.generatePattern();
    }

//    private void insertUpdate(CodeArea ca){
////            if (ca.plainTextChanges().getLength() != 1) {
////                return;
////            }
////       
////            int pos = ev.getOffset();
//            String content = null;
//            content = codeArea.getText(0, pos + 1);
//
//            // Find where the word starts
//            int w;
//            for (w = pos; w >= 0; w--) {
//                if (!Character.isLetter(content.charAt(w))) {
//                    break;
//                }
//            }
//            if (pos - w < 2) {
//                // Too few chars
//                return;
//            }
//
//            String prefix = content.substring(w + 1).toLowerCase();
//            int n = Collections.binarySearch(suggestions, prefix);
//            if (n < 0 && -n <= suggestions.size()) {
//                String match = suggestions.get(-n - 1);
//                if (match.startsWith(prefix)) {
//                    // A completion is found
//                    String completion = match.substring(pos - w);
//                    // We cannot modify Document from within notification,
//                    // so we submit a task that does the change later
//                    Platform.runLater(
//                            new CompletionTask(completion, pos + 1));
//                }
//            } else {
//                // Nothing found
//                mode = Mode.INSERT;
//            }
//    
//    }
//    private class CompletionTask implements Runnable {
//
//        String completion;
//        int position;
//
//        CompletionTask(String completion, int position) {
//            this.completion = completion;
//            this.position = position;
//        }
//
//        public void run() {
//            this.insertText(position, completion);
//            this.positionCaret(position + completion.length());
//            this.moveTo(position);
//            mode = Mode.COMPLETION;
//        }
//    }
//
//    private class CommitAction extends AbstractAction {
//
//        public void actionPerformed(ActionEvent ev) {
//            if (mode == Mode.COMPLETION) {
//                int pos = this.getSelection().getEnd();
//                this.insertText(pos, " ");
//                this.positionCaret(pos + 1);
//                mode = Mode.INSERT;
//            } else {
//                this.replaceSelection("\n");
//            }
//        }
//    }
//
//    private class SyntaxDocumentListener implements DocumentListener {
//
//        @Override
//        public void insertUpdate(DocumentEvent ev) {
//            if (ev.getLength() != 1) {
//                return;
//            }
//
//            int pos = ev.getOffset();
//            String content = null;
//            content = this.getText(0, pos + 1);
//
//            // Find where the word starts
//            int w;
//            for (w = pos; w >= 0; w--) {
//                if (!Character.isLetter(content.charAt(w))) {
//                    break;
//                }
//            }
//            if (pos - w < 2) {
//                // Too few chars
//                return;
//            }
//
//            String prefix = content.substring(w + 1).toLowerCase();
//            int n = Collections.binarySearch(suggestions, prefix);
//            if (n < 0 && -n <= suggestions.size()) {
//                String match = suggestions.get(-n - 1);
//                if (match.startsWith(prefix)) {
//                    // A completion is found
//                    String completion = match.substring(pos - w);
//                    // We cannot modify Document from within notification,
//                    // so we submit a task that does the change later
//                    Platform.runLater(
//                            new CompletionTask(completion, pos + 1));
//                }
//            } else {
//                // Nothing found
//                mode = Mode.INSERT;
//            }
//        }
//
//        @Override
//        public void removeUpdate(DocumentEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void changedUpdate(DocumentEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//    }
}
