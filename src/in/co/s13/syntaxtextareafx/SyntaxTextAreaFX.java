/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.s13.syntaxtextareafx;

import in.co.s13.syntaxtextareafx.langs.ActionScript;
import in.co.s13.syntaxtextareafx.langs.Ada;
import in.co.s13.syntaxtextareafx.langs.Ansforth94;
import in.co.s13.syntaxtextareafx.langs.Asp;
import in.co.s13.syntaxtextareafx.langs.Automake;
import in.co.s13.syntaxtextareafx.langs.Text;
import in.co.s13.syntaxtextareafx.meta.Generator;
import in.co.s13.syntaxtextareafx.meta.Syntax;
import java.awt.event.ActionEvent;
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
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javax.swing.AbstractAction;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleSpans;
import org.fxmisc.richtext.StyleSpansBuilder;
import org.reactfx.Subscription;

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
    private Pattern PATTERN;
    private CodeArea codeArea;
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

    public static enum SPECIAL_FILENAMES {
        Makefile,GNUmakefile
    };

    public static enum FILE_TYPES {
        as, adb, ads, forth, asp, am, awk,
        bennugd, bibtex, bluespec, boo, c, cg, changelog,
        cmake, cobol, cpp, cpphdr, csharp, css, cuda, d,
        def, desktop, diff, docbook, dosbatch, dot, dpatch,
        dtd, eiffel, erlang, fcl, fortran, fsharp, gap, gdblog,
        genie, glsl, gtkdoc, gtkrc, haddock, haskell, haskellliterate,
        html, idlexelis, imagej, ini, j, jade, java, javascript, json,
        julia, latex, lex, libtool, llvm, m4, makefile, mallard, markdown,
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
        cpphdr, csharp, css, csv,
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

    public static String[] ALT_FILE_TYPES = new String[]{"4th", ""};

    private static LANGS CodingStyle = LANGS.text;

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
     * *
     *
     * @param fileExtension a string file extension
     * @return boolean value true/false if fileExtension is supported
     */
    private static boolean supports(String fileExtension) {

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

        codeArea = new CodeArea();
        codeArea.setOnKeyPressed((event) -> {
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

        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
//        EventStream<PlainTextChange> textChanges = codeArea.plainTextChanges();
//        textChanges
//                .successionEnds(Duration.ofMillis(500))
//                .supplyTask(this::computeHighlightingAsync)
//                .awaitLatest(textChanges)
//                .map(Try::get)
//                .subscribe(this::applyHighlighting);
        String fileExtension = "";
        if (file.trim().length() > 1 && file.contains(".")) {
            fileExtension = file.substring(file.lastIndexOf(".") + 1).trim().toLowerCase();
        } else if(file.trim().length()>1 && !file.contains(".")) {
            setCodingStyle(getCodingStyleFromFileType(getFileTypeFromSpecialFileName(new File(file).getName())));
        }
        if (file.trim().length() > 1 && file.contains(".") && SyntaxTextAreaFX.supports(fileExtension)) {
            // setCodingStyle();
            setCodingStyle(getCodingStyleFromFileType(getFileTypeFromFileExtension(fileExtension)));

        } else {
            generatePattern();
            codeArea.getStylesheets().add(SyntaxTextAreaFX.class.getResource("res/css/default/java.css").toExternalForm());

        }
        codeArea.richChanges()
                .filter(ch -> !ch.getInserted().equals(ch.getRemoved())) // XXX
                .successionEnds(Duration.ofMillis(500))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(codeArea.richChanges())
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

        Subscription s = codeArea.plainTextChanges().subscribe(tc -> {
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
        codeArea.replaceText(0, 0, text);
        codeArea.getUndoManager().forgetHistory();
        codeArea.getUndoManager().mark();

    }

    /**
     *
     * @return Text as String from SyntaxTextAreaFX
     */
    public String getText() {
        return codeArea.getText();

    }

    /**
     * *
     *
     * @param text appends text to SyntaxTextAreaFX
     */
    public void appendText(String text) {
        codeArea.appendText(text);

    }

    /**
     * **
     *
     * @return the Graphical Node, which can be added to Layout
     */
    public CodeArea getNode() {
        return codeArea;
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
        SyntaxTextAreaFX.CodingStyle = CodingStyle;
        clearStyleSheets();
        if (externalThemePath.trim().length() == 0) {
            codeArea.getStylesheets().add(SyntaxTextAreaFX.class.getResource("res/css/" + getTheme() + "/" + getCodingStyle() + ".css").toExternalForm());
        } else {
            codeArea.getStylesheets().add(readFile(externalThemePath + "/" + getCodingStyle() + ".css"));
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
        codeArea.getStylesheets().add(CSScode);
    }

    /**
     * **
     * Remove all CSS coding sheets
     */
    public void clearStyleSheets() {
        codeArea.getStylesheets().clear();
    }

    /**
     * *
     *
     * @return all CSS file as a List of Strings
     */
    public ObservableList<String> getAllStyleSheets() {
        return codeArea.getStylesheets();
    }

    /**
     * *
     *
     * @param file File to be saved
     * @param text Content to write
     * @throws IOException if unable to find the file path
     */
    private void write(File file, String text) throws IOException {
        file.getParentFile().mkdirs();
        try (FileWriter fw = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fw)) {
            pw.print(text);
            pw.close();
            fw.close();
        }

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
                Charset encoding = Charset.defaultCharset();
                byte[] encoded = Files.readAllBytes(Paths.get(path));
                str = new String(encoded, encoding);
            } catch (IOException ex) {
                Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * *
     *
     * @param highlighting Apply Highlighting style to SyntaxTextAreaFX
     */
    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        codeArea.setStyleSpans(0, highlighting);
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
        SPECIAL_FILENAMES sFileName = SPECIAL_FILENAMES.valueOf(FileName);
        switch (sFileName) {
            case Makefile:
            case GNUmakefile:
                fileType = FILE_TYPES.am;
                break;
        }
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
                language=LANGS.automake;
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

    private void loadLanguage() {
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
                syntax= new Syntax(new Automake());
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
            case forth:
                break;
            case fortran:
                break;
            case fsharp:
                break;
            case gap:
                break;
            case gdb_log:
                break;
            case genie:
                break;
            case glsl:
                break;
            case gtk_doc:
                break;
            case gtkrc:
                break;
            case haddock:
                break;
            case haskell:
                break;
            case haskell_literate:
                break;
            case html:
                break;
            case idl_exelis:
                break;
            case imagej:
                break;
            case ini:
                break;
            case j:
                break;
            case jade:
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
            default:
                syntax = new Syntax(new Text());
                break;

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
    private class CompletionTask implements Runnable {

        String completion;
        int position;

        CompletionTask(String completion, int position) {
            this.completion = completion;
            this.position = position;
        }

        public void run() {
            codeArea.insertText(position, completion);
            codeArea.positionCaret(position + completion.length());
            codeArea.moveTo(position);
            mode = Mode.COMPLETION;
        }
    }

    private class CommitAction extends AbstractAction {

        public void actionPerformed(ActionEvent ev) {
            if (mode == Mode.COMPLETION) {
                int pos = codeArea.getSelection().getEnd();
                codeArea.insertText(pos, " ");
                codeArea.positionCaret(pos + 1);
                mode = Mode.INSERT;
            } else {
                codeArea.replaceSelection("\n");
            }
        }
    }

    private class SyntaxDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent ev) {
            if (ev.getLength() != 1) {
                return;
            }

            int pos = ev.getOffset();
            String content = null;
            content = codeArea.getText(0, pos + 1);

            // Find where the word starts
            int w;
            for (w = pos; w >= 0; w--) {
                if (!Character.isLetter(content.charAt(w))) {
                    break;
                }
            }
            if (pos - w < 2) {
                // Too few chars
                return;
            }

            String prefix = content.substring(w + 1).toLowerCase();
            int n = Collections.binarySearch(suggestions, prefix);
            if (n < 0 && -n <= suggestions.size()) {
                String match = suggestions.get(-n - 1);
                if (match.startsWith(prefix)) {
                    // A completion is found
                    String completion = match.substring(pos - w);
                    // We cannot modify Document from within notification,
                    // so we submit a task that does the change later
                    Platform.runLater(
                            new CompletionTask(completion, pos + 1));
                }
            } else {
                // Nothing found
                mode = Mode.INSERT;
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
