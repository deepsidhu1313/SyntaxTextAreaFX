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
    private Pattern PATTERN;
    private CodeArea codeArea;
    private ExecutorService executor;
    private String filePath = "", externalThemePath = "";
    public Scene scene;

    public static enum FILE_TYPES {
        as, adb, ads, ansforth94, asp, automake, awk,
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
        t2t, tcl, thrift, vala, vbnet, verilog, vhdl, xml, yacc, yaml
    };

    private static FILE_TYPES CodingStyle = FILE_TYPES.java;

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
    private static boolean contains(String fileExtension) {

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
        executor = Executors.newSingleThreadExecutor();
        codeArea = new CodeArea();

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
        }
        if (file.trim().length() > 1 && file.contains(".") && SyntaxTextAreaFX.contains(fileExtension)) {
            // setCodingStyle();
            setCodingStyle(FILE_TYPES.valueOf(fileExtension));

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
     * @param v sets Font of SyntaxTextAreaFX
     */
    public void setFont(Font v) {
        codeArea.setFont(v);
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
    public FILE_TYPES getCodingStyle() {
        return CodingStyle;
    }

    /**
     * *
     *
     * @param CodingStyle sets the coding style of the SyntaxTextAreaFX
     */
    public void setCodingStyle(FILE_TYPES CodingStyle) {
        SyntaxTextAreaFX.CodingStyle = CodingStyle;
        if (externalThemePath.trim().length() == 0) {
            codeArea.getStylesheets().add(SyntaxTextAreaFX.class.getResource("res/css/" + getTheme() + "/" + getCodingStyle() + ".css").toExternalForm());
        } else {
            codeArea.getStylesheets().add(readFile(externalThemePath + "/" + getCodingStyle() + ".css"));
        }
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
     * @param text computes Hihlighting on provided text
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

    /**
     * *
     * @param
     *
     * @return s
     */
    private String getStyleClass(Matcher matcher) {
        String styleClass = "";
        switch (getCodingStyle()) {
            case as:
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
            case adb:
                styleClass = matcher.group("PREPROCESSOR") != null ? "preprocessor"
                        : matcher.group("FUNCTION") != null ? "function"
                        : matcher.group("KEYWORDS") != null ? "keywords"
                        : matcher.group("STORAGECLASS") != null ? "storageclass"
                        : matcher.group("TYPE") != null ? "type"
                        : matcher.group("BOOLEAN") != null ? "boolean"
                        : matcher.group("PAREN") != null ? "paren"
                        : matcher.group("BRACE") != null ? "brace"
                        : matcher.group("BRACKET") != null ? "bracket"
                        : matcher.group("SEMICOLON") != null ? "semicolon"
                        : matcher.group("STRING") != null ? "string"
                        : matcher.group("COMMENT") != null ? "comment"
                        : null;
                break;
            case ads:
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

        }

        return styleClass;
    }

    void generatePattern() {
        String DECLARATIONS[];
        String PRIMITIVES[];
        String EXTERNALS[];
        String STORAGECLASS[];
        String SCOPEDECLARATIONS[];
        String FLOW[];
        String MEMORY[];
        String FUTURE[];
        String NULL[];
        String BOOLEAN[];
        String PREPROCESSOR[];
        String FUNCTION[];
        String KEYWORDS[];
        String TYPE[];
        String DECLARATIONS_PATTERN;
        String PRIMITIVES_PATTERN;
        String EXTERNALS_PATTERN;
        String STORAGECLASS_PATTERN;
        String SCOPEDECLARATIONS_PATTERN;
        String FLOW_PATTERN;
        String MEMORY_PATTERN;
        String FUTURE_PATTERN;
        String NULL_PATTERN;
        String BOOLEAN_PATTERN;
        String PREPROCESSOR_PATTERN;
        String FUNCTION_PATTERN;
        String KEYWORDS_PATTERN;
        String TYPE_PATTERN;
        String PAREN_PATTERN;
        String BRACE_PATTERN;
        String BRACKET_PATTERN;
        String SEMICOLON_PATTERN;
        String STRING_PATTERN;
        String COMMENT_PATTERN;

        switch (getCodingStyle()) {
            case as:
                DECLARATIONS = new String[]{"class", "extends",
                    "function", "implements", "instanceof", "interface",
                    "is", "namespace", "throws", "var", "const"};
                PRIMITIVES = new String[]{
                    "arguments", "Array",
                    "Boolean", "Class",
                    "Bitmap", "BitmapData",
                    "BitmapDataChannel", "ByteArray",
                    "Camera", "Capabilities",
                    "CapsStyle", "ColorTransform",
                    "ContextMenu", "Dictionary",
                    "DisplayObject", "DisplayObjectContainer",
                    "Endian", "Error",
                    "Event", "EventDispatcher",
                    "ExternalInterface", "FileFilter",
                    "FileReference", "FileReferenceList",
                    "Function", "Graphics",
                    "int", "IBitmapDrawable",
                    "IEventDispatcher", "IOError",
                    "Keyboard", "KeyboardEvent",
                    "KeyLocation", "Loader",
                    "LocalConnection", "Math",
                    "Matrix", "Microphone",
                    "Mouse", "MovieClip",
                    "Namespace", "NetConnection",
                    "NetStream", "Number",
                    "Object", "Point",
                    "PrintJob", "Proxy",
                    "QName", "Rectangle",
                    "RegExp", "Responder",
                    "Scene", "Security",
                    "Shape", "SharedObject",
                    "Socket", "Sound",
                    "SoundChannel", "SoundTransform",
                    "Sprite", "Stage",
                    "String", "StyleSheet",
                    "SWFVersion", "System",
                    "TextField", "TextFormat",
                    "Timer", "uint",
                    "Video", "XML",
                    "XMLDocument", "XMLList",
                    "XMLNode", "XMLNodeType",
                    "XMLSocket"};
                EXTERNALS = new String[]{
                    "import", "include", "package"};
                STORAGECLASS = new String[]{
                    "dynamic", "internal",
                    "final", "static"};
                SCOPEDECLARATIONS = new String[]{
                    "flash_proxy", "internal",
                    "override", "private",
                    "protected", "public",
                    "set", "get"};
                FLOW = new String[]{
                    "break", "case",
                    "catch", "continue",
                    "default", "do",
                    "else", "for",
                    "if", "is",
                    "return", "throw",
                    "switch", "try",
                    "while"};
                MEMORY = new String[]{
                    "new", "super",
                    "this", "void"};
                FUTURE = new String[]{
                    "goto"};
                NULL = new String[]{
                    "null"};
                BOOLEAN = new String[]{"true", "false"};

                DECLARATIONS_PATTERN = "\\b(" + String.join("|", DECLARATIONS) + ")\\b";
                PRIMITIVES_PATTERN = "\\b(" + String.join("|", PRIMITIVES) + ")\\b";
                EXTERNALS_PATTERN = "\\b(" + String.join("|", EXTERNALS) + ")\\b";
                STORAGECLASS_PATTERN = "\\b(" + String.join("|", STORAGECLASS) + ")\\b";
                SCOPEDECLARATIONS_PATTERN = "\\b(" + String.join("|", SCOPEDECLARATIONS) + ")\\b";
                FLOW_PATTERN = "\\b(" + String.join("|", FLOW) + ")\\b";
                MEMORY_PATTERN = "\\b(" + String.join("|", MEMORY) + ")\\b";
                FUTURE_PATTERN = "\\b(" + String.join("|", FUTURE) + ")\\b";
                NULL_PATTERN = "\\b(" + String.join("|", NULL) + ")\\b";
                BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
                PAREN_PATTERN = "\\(|\\)";
                BRACE_PATTERN = "\\{|\\}";
                BRACKET_PATTERN = "\\[|\\]";
                SEMICOLON_PATTERN = "\\;";
                STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
                COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

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
            case adb:
                PREPROCESSOR = new String[]{"package",
                    "pragma", "use", "with"};
                FUNCTION = new String[]{"function",
                    "procedure", "return"};
                KEYWORDS = new String[]{
                    "abort", "abs", "accept", "all",
                    "and", "begin", "body", "case",
                    "declare", "delay", "do", "else",
                    "elsif", "end", "entry", "exception",
                    "exit", "for", "generic", "goto",
                    "if", "in", "is", "loop",
                    "mod", "new", "not", "null",
                    "or", "others", "out", "protected",
                    "raise", "record", "rem", "renames",
                    "requeue", "reverse", "select", "separate",
                    "subtype", "task", "terminate", "then",
                    "type", "until", "when", "while",
                    "xor"};

                STORAGECLASS = new String[]{
                    "abstract", "access", "aliased", "array",
                    "at", "constant", "delta", "digits",
                    "interface", "limited", "of", "private",
                    "range", "tagged", "synchronized"};
                TYPE = new String[]{
                    "boolean", "character",
                    "count", "duration",
                    "float", "integer",
                    "long_float", "long_integer",
                    "priority", "short_float",
                    "short_integer", "string"};

                BOOLEAN = new String[]{"true", "false"};

                PREPROCESSOR_PATTERN = "\\b(" + String.join("|", PREPROCESSOR) + ")\\b";
                FUNCTION_PATTERN = "\\b(" + String.join("|", FUNCTION) + ")\\b";
                KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
                STORAGECLASS_PATTERN = "\\b(" + String.join("|", STORAGECLASS) + ")\\b";
                TYPE_PATTERN = "\\b(" + String.join("|", TYPE) + ")\\b";
                BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
                PAREN_PATTERN = "\\(|\\)";
                BRACE_PATTERN = "\\{|\\}";
                BRACKET_PATTERN = "\\[|\\]";
                SEMICOLON_PATTERN = "\\;";
                STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
                COMMENT_PATTERN = "--[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

                PATTERN = Pattern.compile(
                        "(?<PREPROCESSOR>" + PREPROCESSOR_PATTERN + ")"
                        + "|(?<FUNCTION>" + FUNCTION_PATTERN + ")"
                        + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                        + "|(?<STORAGECLASS>" + STORAGECLASS_PATTERN + ")"
                        + "|(?<TYPE>" + TYPE_PATTERN + ")"
                        + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
                        + "|(?<PAREN>" + PAREN_PATTERN + ")"
                        + "|(?<BRACE>" + BRACE_PATTERN + ")"
                        + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                        + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                        + "|(?<STRING>" + STRING_PATTERN + ")"
                        + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                );

                break;
            case ads:
                PREPROCESSOR = new String[]{"package",
                    "pragma", "use", "with"};
                FUNCTION = new String[]{"function",
                    "procedure", "return"};
                KEYWORDS = new String[]{
                    "abort", "abs", "accept", "all",
                    "and", "begin", "body", "case",
                    "declare", "delay", "do", "else",
                    "elsif", "end", "entry", "exception",
                    "exit", "for", "generic", "goto",
                    "if", "in", "is", "loop",
                    "mod", "new", "not", "null",
                    "or", "others", "out", "protected",
                    "raise", "record", "rem", "renames",
                    "requeue", "reverse", "select", "separate",
                    "subtype", "task", "terminate", "then",
                    "type", "until", "when", "while",
                    "xor"};

                STORAGECLASS = new String[]{
                    "abstract", "access", "aliased", "array",
                    "at", "constant", "delta", "digits",
                    "interface", "limited", "of", "private",
                    "range", "tagged", "synchronized"};
                TYPE = new String[]{
                    "boolean", "character",
                    "count", "duration",
                    "float", "integer",
                    "long_float", "long_integer",
                    "priority", "short_float",
                    "short_integer", "string"};

                BOOLEAN = new String[]{"true", "false"};

                PREPROCESSOR_PATTERN = "\\b(" + String.join("|", PREPROCESSOR) + ")\\b";
                FUNCTION_PATTERN = "\\b(" + String.join("|", FUNCTION) + ")\\b";
                KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
                STORAGECLASS_PATTERN = "\\b(" + String.join("|", STORAGECLASS) + ")\\b";
                TYPE_PATTERN = "\\b(" + String.join("|", TYPE) + ")\\b";
                BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
                PAREN_PATTERN = "\\(|\\)";
                BRACE_PATTERN = "\\{|\\}";
                BRACKET_PATTERN = "\\[|\\]";
                SEMICOLON_PATTERN = "\\;";
                STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
                COMMENT_PATTERN = "--[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

                PATTERN = Pattern.compile(
                        "(?<PREPROCESSOR>" + PREPROCESSOR_PATTERN + ")"
                        + "|(?<FUNCTION>" + FUNCTION_PATTERN + ")"
                        + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                        + "|(?<STORAGECLASS>" + STORAGECLASS_PATTERN + ")"
                        + "|(?<TYPE>" + TYPE_PATTERN + ")"
                        + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
                        + "|(?<PAREN>" + PAREN_PATTERN + ")"
                        + "|(?<BRACE>" + BRACE_PATTERN + ")"
                        + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                        + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                        + "|(?<STRING>" + STRING_PATTERN + ")"
                        + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                );

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
            default: //JAVA case is default

                DECLARATIONS = new String[]{"class", "enum",
                    "extends", "implements",
                    "instanceof", "interface",
                    "native", "throws"};
                PRIMITIVES = new String[]{
                    "boolean", "byte", "char", "double",
                    "float", "int", "long", "short",
                    "void"};
                EXTERNALS = new String[]{
                    "import", "package"};
                STORAGECLASS = new String[]{
                    "abstract", "final", "static", "strictfp",
                    "synchronized", "transient", "volatile"};
                SCOPEDECLARATIONS = new String[]{
                    "private", "protected", "public"};
                FLOW = new String[]{
                    "assert", "break", "case", "catch", "continue", "default", "do", "else",
                    "finally", "for", "if", "return", "throw", "switch", "try", "while"};
                MEMORY = new String[]{
                    "new", "super", "this"};
                FUTURE = new String[]{
                    "const", "goto"};
                NULL = new String[]{
                    "null"
                };
                BOOLEAN = new String[]{"true", "false"};

                DECLARATIONS_PATTERN = "\\b(" + String.join("|", DECLARATIONS) + ")\\b";
                PRIMITIVES_PATTERN = "\\b(" + String.join("|", PRIMITIVES) + ")\\b";
                EXTERNALS_PATTERN = "\\b(" + String.join("|", EXTERNALS) + ")\\b";
                STORAGECLASS_PATTERN = "\\b(" + String.join("|", STORAGECLASS) + ")\\b";
                SCOPEDECLARATIONS_PATTERN = "\\b(" + String.join("|", SCOPEDECLARATIONS) + ")\\b";
                FLOW_PATTERN = "\\b(" + String.join("|", FLOW) + ")\\b";
                MEMORY_PATTERN = "\\b(" + String.join("|", MEMORY) + ")\\b";
                FUTURE_PATTERN = "\\b(" + String.join("|", FUTURE) + ")\\b";
                NULL_PATTERN = "\\b(" + String.join("|", NULL) + ")\\b";
                BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
                PAREN_PATTERN = "\\(|\\)";
                BRACE_PATTERN = "\\{|\\}";
                BRACKET_PATTERN = "\\[|\\]";
                SEMICOLON_PATTERN = "\\;";
                STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
                COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

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
        }

    }

}
