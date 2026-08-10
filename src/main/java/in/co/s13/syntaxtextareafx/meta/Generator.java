/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.s13.syntaxtextareafx.meta;

import in.co.s13.syntaxtextareafx.SyntaxTextAreaFX;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * Offline dev tool that turns gtksourceview language definitions into
 * {@link Language} implementations.
 *
 * <p>Run {@link #generateJavaFiles()} from the repository root with the
 * gtksourceview-derived JSON definitions in {@code jsons/} (one file per
 * language, e.g. {@code jsons/python.json}); it writes one {@code .java}
 * file per definition to {@code java/}. That output still needs a human
 * pass: {@code java/} is scratch space, not a source root, so review each
 * file and copy the ones worth keeping into {@code langs/} by hand, then
 * wire the new language into {@code SyntaxTextAreaFX#loadLanguage()} and its
 * {@code IMPLEMENTED} set. Not every definition is worth generating —
 * languages whose keywords aren't a flat list in the JSON (most of the
 * C family, for example) produce a class with no keyword highlighting at
 * all, and a few definitions embed gtksourceview template syntax
 * (like {@code %{valid-name}}) that isn't valid Java regex on its own.
 *
 * <p>Only {@link #generateJavaFiles()} is part of that workflow.
 * {@link #generateJSONsAndCSS()} and {@link #convertXMLsToJSONs()} are
 * earlier, unmaintained experiments kept for reference.
 */
public class Generator {

    static String colors[] = {"#001F3F", "#0074D9", "#7FDBFF", "#39CCCC",
        "#3D9970", "#2ECC40", "#01FF70", "#FFDC00",
        "#FF851B", "#FF4136", "#F012BE", "#B10DC9",
        "#85144B", "#FFFFFF", "#AAAAAA", "#111111"};
    static int colorCounter = 0;

    public static void main(String[] args) {
        // System.out.println("" + (( "R".equalsIgnoreCase(""+SyntaxTextAreaFX.FILE_TYPES.R))));
        //      System.out.println("" + (SyntaxTextAreaFX.FILE_TYPES.valueOf("java")));
        generateJavaFiles();

//generateJSONsAndCSS();
    }

    public static void generateJavaFiles() {
        File f = new File("jsons/");
        File files[] = f.listFiles((File dir, String name) -> name.toLowerCase().endsWith(".json"));
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String baseName = file.getName().substring(0, file.getName().lastIndexOf("."));
            String className = toClassName(baseName);

            JSONObject rules = new JSONObject(readFile(file.getAbsolutePath()));
            JSONObject language = rules.getJSONObject("language");
            JSONObject metadata = language.optJSONObject("metadata");
            if (metadata == null) {
                metadata = new JSONObject();
            }

            ArrayList<JSONObject> contexts = new ArrayList<>();
            Object arr2 = language.getJSONObject("definitions").get("context");
            if (arr2 instanceof JSONArray) {
                JSONArray arr = (JSONArray) arr2;
                for (int j = 0; j < arr.length(); j++) {
                    contexts.add(arr.getJSONObject(j));
                }
            } else if (arr2 instanceof JSONObject) {
                contexts.add((JSONObject) arr2);
            }

            StringBuilder arrayDecs = new StringBuilder();
            StringBuilder patternDecs = new StringBuilder();
            StringBuilder addVartoArrayLists = new StringBuilder();
            ArrayList<String> groupClauses = new ArrayList<>();
            ArrayList<String> matcherClauses = new ArrayList<>();

            for (JSONObject arg : contexts) {
                if (!arg.has("keyword") || !arg.has("id")) {
                    continue;
                }
                String keywordContent = keywordArrayLiteral(arg.get("keyword"));
                if (keywordContent.isEmpty()) {
                    continue;
                }
                String arrayName = toFieldName(arg.get("id").toString());
                String groupName = arrayName.replaceAll("_", "");

                arrayDecs.append("    String ").append(arrayName).append("[] = new String[]{")
                        .append(keywordContent).append("};\n");
                patternDecs.append("        String ").append(arrayName)
                        .append("_PATTERN = \"\\\\b(\" + String.join(\"|\", ").append(arrayName)
                        .append(") + \")\\\\b\";\n");
                groupClauses.add("(?<" + groupName + ">\" + " + arrayName + "_PATTERN + \")");
                matcherClauses.add("matcher.group(\"" + groupName + "\") != null ? \""
                        + arrayName.toLowerCase().replaceAll("_", "-") + "\"");
                addVartoArrayLists.append("        keywordList.addAll(Arrays.asList(").append(arrayName).append("));\n");
            }

            String literalDecs = literalRules(metadata);
            boolean hasComment = literalDecs.contains("COMMENT_PATTERN");

            ArrayList<String> allGroupClauses = new ArrayList<>();
            allGroupClauses.add("(?<STRING>\" + STRING_PATTERN + \")");
            if (hasComment) {
                allGroupClauses.add("(?<COMMENT>\" + COMMENT_PATTERN + \")");
            }
            allGroupClauses.addAll(groupClauses);

            ArrayList<String> allMatcherClauses = new ArrayList<>();
            allMatcherClauses.add("matcher.group(\"STRING\") != null ? \"string\"");
            if (hasComment) {
                allMatcherClauses.add("matcher.group(\"COMMENT\") != null ? \"comment\"");
            }
            allMatcherClauses.addAll(matcherClauses);

            StringBuilder patternCompiles = new StringBuilder();
            for (int j = 0; j < allGroupClauses.size(); j++) {
                patternCompiles.append(j == 0 ? "                \"" : "                + \"|")
                        .append(allGroupClauses.get(j)).append("\"\n");
            }

            StringBuilder styleClassBody = new StringBuilder();
            for (int j = 0; j < allMatcherClauses.size(); j++) {
                styleClassBody.append(j == 0 ? "        return " : "                : ")
                        .append(allMatcherClauses.get(j)).append("\n");
            }
            styleClassBody.append("                : null;\n");

            String javaContent = "/*\n"
                    + " * To change this license header, choose License Headers in Project Properties.\n"
                    + " * To change this template file, choose Tools | Templates\n"
                    + " * and open the template in the editor.\n"
                    + " */\n"
                    + "package in.co.s13.syntaxtextareafx.langs;\n"
                    + "\n"
                    + "import java.util.ArrayList;\n"
                    + "import java.util.Arrays;\n"
                    + "import java.util.regex.Matcher;\n"
                    + "import java.util.regex.Pattern;\n"
                    + "import in.co.s13.syntaxtextareafx.meta.Language;\n"
                    + "import java.util.Collections;\n"
                    + "\n"
                    + "/**\n"
                    + " *\n"
                    + " * @author nika\n"
                    + " */\n"
                    + "public class " + className + " implements Language {\n"
                    + "\n"
                    + arrayDecs.toString()
                    + "\n"
                    + "    @Override\n"
                    + "    public Pattern generatePattern() {\n"
                    + literalDecs
                    + patternDecs.toString()
                    + "\n"
                    + "        Pattern pattern = Pattern.compile(\n"
                    + patternCompiles.toString()
                    + "        );\n"
                    + "        return pattern;\n"
                    + "    }\n"
                    + "\n"
                    + "    @Override\n"
                    + "    public String getStyleClass(Matcher matcher) {\n"
                    + styleClassBody.toString()
                    + "    }\n"
                    + "\n"
                    + "    @Override\n"
                    + "    public ArrayList<String> getKeywords() {\n"
                    + "        ArrayList<String> keywordList = new ArrayList<>();\n"
                    + addVartoArrayLists.toString()
                    + "        Collections.sort(keywordList);\n"
                    + "        return keywordList;\n"
                    + "    }\n"
                    + "\n"
                    + "}\n";
            try {
                write(new File("java/" + className + ".java"), javaContent);
            } catch (IOException ex) {
                Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Turns a definition id such as {@code 2x-only-keywords} into a valid,
     * collision-resistant field name — uppercased, non-identifier characters
     * folded to underscores, and prefixed if it would otherwise start with a
     * digit.
     */
    static String toFieldName(String id) {
        String name = id.toUpperCase().replaceAll("[^A-Z0-9]+", "_").replaceAll("^_+|_+$", "");
        if (name.isEmpty() || Character.isDigit(name.charAt(0))) {
            name = "K_" + name;
        }
        return name;
    }

    /**
     * Turns a JSON basename such as {@code haskell-literate} into a
     * PascalCase Java class name.
     */
    static String toClassName(String baseName) {
        StringBuilder out = new StringBuilder();
        for (String part : baseName.split("[-_]+")) {
            if (part.isEmpty()) {
                continue;
            }
            out.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return out.length() == 0 ? "Lang" : out.toString();
    }

    /**
     * Renders a definition's {@code keyword} value — normally a JSON array of
     * strings, but gtksourceview's XML-to-JSON conversion turns bare
     * {@code True}/{@code False} keyword text into JSON booleans — as the
     * comma-separated, double-quoted contents of a Java {@code String[]}
     * initializer.
     */
    static String keywordArrayLiteral(Object keyword) {
        ArrayList<Object> items = new ArrayList<>();
        if (keyword instanceof JSONArray) {
            JSONArray arr = (JSONArray) keyword;
            for (int i = 0; i < arr.length(); i++) {
                items.add(arr.get(i));
            }
        } else {
            items.add(keyword);
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if (i > 0) {
                out.append(", ");
            }
            out.append('"').append(escape(String.valueOf(items.get(i)))).append('"');
        }
        return out.toString();
    }

    public static void generateJSONsAndCSS() {
        File f = new File("jsons/");
        File files[] = f.listFiles((File dir, String name) -> name.toLowerCase().endsWith(".json"));
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String content = "{" + file.getName() + ":";
            String cssContent = "";
            JSONObject obj = new JSONObject(readFile(file.getAbsolutePath()));
            Iterator<String> it = obj.getJSONObject("language").getJSONObject("definitions").keys();
            while (it.hasNext()) {

                System.out.println(it.next());
            }
            Object arr2 = obj.getJSONObject("language").getJSONObject("definitions").get("context");
            if (arr2 instanceof JSONArray) {
                JSONArray arr = (JSONArray) (arr2);
                for (int j = 0; j < arr.length(); j++) {
                    JSONObject arg = arr.getJSONObject(j);
                    cssContent += assignCSScolor(arg);
                }

                content += arr.toString(4);
            } else {
                content += "" + ((JSONObject) arr2);
                cssContent += assignCSScolor((JSONObject) arr2);
            }
            System.out.println(file.getName() + "  " + content);
            try {
                write(new File("json2/" + file.getName().replace("-", "")), new JSONObject(content + "}").toString(4));
                write(new File("css/" + file.getName().substring(0, file.getName().lastIndexOf(".")) + ".css".replace("-", "")), cssContent);

            } catch (IOException ex) {
                Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void convertXMLsToJSONs() {
        ArrayList<File> list = Generator.getPFiles("/usr/share/gtksourceview-3.0/language-specs/");
        for (int i = 0; i < list.size(); i++) {
            File get = list.get(i);
            try {
                String str = readFile(get.getAbsolutePath());
                JSONObject xmlJSONObj = XML.toJSONObject(str);
                String jsonPrettyPrintString = xmlJSONObj.toString(4);
                write(new File(get.getName().substring(0, get.getName().lastIndexOf(".")) + ".json"), jsonPrettyPrintString);
            } catch (JSONException je) {
                System.out.println(je.toString());
            } catch (IOException ex) {
                Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static String assignCSScolor(JSONObject obj) {
        StringBuilder sb = new StringBuilder("");

        if (colorCounter == colors.length) {
            colorCounter = 0;
        }
        if (obj.has("id")) {
            if (obj.get("id") instanceof String) {
                sb.append(".").append(obj.getString("id"));
                sb.append(" {\n" + "    -fx-fill:").append(colors[colorCounter]).append(";\n")
                        .append("    -fx-font-weight: bold;\n")
                        .append("}\n");
                colorCounter++;
            }
        }

        return sb.toString();
    }

    public static ArrayList<File> getPFiles(String dir) {
        ArrayList<File> list = new ArrayList<>();
        File directory = new File(dir);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {

                list.addAll(getPFiles(file.getAbsolutePath()));
            } else if (file.getName().endsWith(".lang")) {
                list.add(file);
            }
        }

        return list;
    }

    public static void write(File f, String text) throws IOException {
        try (FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw)) {
            pw.print(text);
            pw.close();
            fw.close();
        }

    }

    static String readFile(String path) {
        String str = null;
        try {
            Charset encoding = Charset.defaultCharset();
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            str = new String(encoded, encoding);
        } catch (IOException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }


    /**
     * Builds the STRING and COMMENT alternatives for a language.
     *
     * <p>Generated classes previously carried keywords only, so a generated
     * language highlighted keywords and left strings and comments plain —
     * visibly worse than the hand-written ones. The definitions have always
     * carried line-comment-start, block-comment-start and block-comment-end;
     * the generator simply ignored them.
     *
     * <p>Patterns avoid alternation inside a quantifier. Java's regex engine
     * recurses per repetition of a group, which is what made long comments
     * throw StackOverflowError (issue #5).
     */
    static String literalRules(JSONObject metadata) {
        String lineStart = property(metadata, "line-comment-start");
        String blockStart = property(metadata, "block-comment-start");
        String blockEnd = property(metadata, "block-comment-end");

        StringBuilder comment = new StringBuilder();
        if (!lineStart.isEmpty()) {
            comment.append(java.util.regex.Pattern.quote(lineStart)).append("[^\\n]*");
        }
        if (!blockStart.isEmpty() && !blockEnd.isEmpty()) {
            if (comment.length() > 0) {
                comment.append("|");
            }
            // [\s\S] is a character class, so it cannot recurse.
            comment.append(java.util.regex.Pattern.quote(blockStart))
                   .append("[\\s\\S]*?")
                   .append(java.util.regex.Pattern.quote(blockEnd));
        }

        // Double and single quoting, unrolled with possessive quantifiers so a
        // long literal cannot build a recursion chain.
        String string = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\""
                + "|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";

        StringBuilder out = new StringBuilder();
        out.append("        String STRING_PATTERN = \"").append(escape(string)).append("\";\n");
        if (comment.length() > 0) {
            out.append("        String COMMENT_PATTERN = \"").append(escape(comment.toString())).append("\";\n");
        }
        return out.toString();
    }

    /** Reads a named property from a language's metadata block. */
    static String property(JSONObject metadata, String name) {
        try {
            org.json.JSONArray props = metadata.getJSONArray("property");
            for (int i = 0; i < props.length(); i++) {
                JSONObject prop = props.getJSONObject(i);
                if (name.equals(prop.optString("name"))) {
                    return prop.optString("content", "");
                }
            }
        } catch (RuntimeException absent) {
            // Not every definition carries every property.
        }
        return "";
    }

    /** Escapes a regex for embedding in generated Java source. */
    static String escape(String regex) {
        return regex.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
