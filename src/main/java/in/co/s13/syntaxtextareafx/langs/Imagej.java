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
public class Imagej implements Language {

    String STORAGE_TYPE[] = new String[]{"var"};
    String BOOLEAN[] = new String[]{"false", "true"};
    String KEYWORD[] = new String[]{"do", "else", "for", "function", "if", "macro", "return", "while"};
    String COMMAND[] = new String[]{"16-bit", "3-3-2 RGB", "32-bit", "3D Project\\.\\.\\.", "8-bit", "8-bit Color", "AND\\.\\.\\.", "AVI\\.\\.\\.", "AVI\\.\\.\\.", "About ImageJ\\.\\.\\.", "About This Submenu\\.\\.\\.", "Abs", "Add Image\\.\\.\\.", "Add Noise", "Add Selection\\.\\.\\.", "Add Slice", "Add Specified Noise\\.\\.\\.", "Add to Manager", "Add\\.\\.\\.", "Analyze Line Graph", "Analyze Particles\\.\\.\\.", "Animation Options\\.\\.\\.", "Appearance\\.\\.\\.", "Apply LUT", "Arrow Tool\\.\\.\\.", "AuPbSn 40 \\(56K\\)", "BMP\\.\\.\\.", "Bandpass Filter\\.\\.\\.", "Bat Cochlea Renderings \\(449K\\)", "Bat Cochlea Volume \\(19K\\)", "Benchmark", "Blobs \\(25K\\)", "Blue", "Boats \\(356K\\)", "Bridge \\(174K\\)", "Brightness/Contrast\\.\\.\\.", "CT \\(420K, 16-bit DICOM\\)", "Calibrate\\.\\.\\.", "Calibration Bar\\.\\.\\.", "Canvas Size\\.\\.\\.", "Capture Image", "Capture Screen", "Cardio \\(768K, RGB DICOM\\)", "Cascade", "Cell Colony \\(31K\\)", "Channels Tool\\.\\.\\.", "Channels Tool\\.\\.\\.", "Clear", "Clear Outside", "Clear Results", "Close", "Close All", "Close-", "Clown \\(14K\\)", "Color Balance\\.\\.\\.", "Color Picker\\.\\.\\.", "Color Threshold\\.\\.\\.", "Colors\\.\\.\\.", "Combine\\.\\.\\.", "Compile and Run\\.\\.\\.", "Compiler\\.\\.\\.", "Concatenate\\.\\.\\.", "Confocal Series \\(2\\.2MB\\)", "Control Panel\\.\\.\\.", "Conversions\\.\\.\\.", "Convert to Mask", "Convert\\.\\.\\.", "Convex Hull", "Convolve\\.\\.\\.", "Copy", "Copy to System", "Create Mask", "Create Selection", "Create Shortcut\\.\\.\\.", "Crop", "Curve Fitting\\.\\.\\.", "Custom Filter\\.\\.\\.", "Cut", "Cyan", "DICOM\\.\\.\\.", "Delete Slice", "Despeckle", "Dev\\. Resources\\.\\.\\.", "Dilate", "Distance Map", "Distribution\\.\\.\\.", "Divide\\.\\.\\.", "Documentation\\.\\.\\.", "Dot Blot \\(7K\\)", "Draw", "Duplicate\\.\\.\\.", "East", "Edit LUT\\.\\.\\.", "Edit\\.\\.\\.", "Embryos \\(42K\\)", "Enhance Contrast", "Enlarge\\.\\.\\.", "Erode", "Exp", "FD Math\\.\\.\\.", "FFT", "FFT Options\\.\\.\\.", "FITS\\.\\.\\.", "Fill", "Fill Holes", "Find Commands\\.\\.\\.", "Find Edges", "Find Maxima\\.\\.\\.", "Fire", "Fit Ellipse", "Fit Spline", "Flatten", "Flip Horizontally", "Flip Vertically", "Flip Z", "Flood Fill Tool Options\\.\\.\\.", "Fluorescent Cells \\(400K\\)", "Fly Brain \\(1MB\\)", "Fonts\\.\\.\\.", "Fractal Box Count\\.\\.\\.", "From ROI Manager", "Gamma\\.\\.\\.", "Gaussian Blur\\.\\.\\.", "Gel \\(105K\\)", "Gel Analyzer Options\\.\\.\\.", "Gif\\.\\.\\.", "Grays", "Green", "HSB Stack", "HeLa Cells \\(1\\.3M, 48-bit RGB\\)", "Hide Overlay", "Histogram", "Hyperstack to Stack", "Hyperstack\\.\\.\\.", "Ice", "Image Calculator\\.\\.\\.", "Image Sequence\\.\\.\\.", "Image Sequence\\.\\.\\.", "Image\\.\\.\\.", "ImageJ News\\.\\.\\.", "ImageJ Properties\\.\\.\\.", "ImageJ Website\\.\\.\\.", "Images to Stack", "In", "Input/Output\\.\\.\\.", "Insert\\.\\.\\.", "Install Plugin\\.\\.\\.", "Install\\.\\.\\.", "Installation\\.\\.\\.", "Internal Clipboard", "Inverse FFT", "Invert", "Invert LUT", "JavaScript", "Jpeg\\.\\.\\.", "LUT\\.\\.\\.", "LUT\\.\\.\\.", "Label", "Label Peaks", "Label\\.\\.\\.", "Leaf \\(36K\\)", "Lena \\(68K\\)", "Line Graph \\(21K\\)", "Line Width\\.\\.\\.", "Line Width\\.\\.\\.", "List Archives\\.\\.\\.", "List Shortcuts\\.\\.\\.", "Log", "M51 Galaxy \\(177K, 16-bits\\)", "MRI Stack \\(528K\\)", "Macro", "Macro Functions\\.\\.\\.", "Macro\\.\\.\\.", "Macro\\.\\.\\.", "Macros\\.\\.\\.", "Magenta", "Make Band\\.\\.\\.", "Make Binary", "Make Composite", "Make Inverse", "Make Montage\\.\\.\\.", "Max\\.\\.\\.", "Maximum\\.\\.\\.", "Mean\\.\\.\\.", "Measure", "Measure\\.\\.\\.", "Median\\.\\.\\.", "Memory & Threads\\.\\.\\.", "Merge Channels\\.\\.\\.", "Min\\.\\.\\.", "Minimum\\.\\.\\.", "Misc\\.\\.\\.", "Mitosis \\(26MB, 5D stack\\)", "Monitor Memory\\.\\.\\.", "Montage to Stack\\.\\.\\.", "Multiply\\.\\.\\.", "NaN Background", "Neuron \\(1\\.6M, 5 channels\\)", "New Hyperstack\\.\\.\\.", "Next Slice \\[>\\]", "Nile Bend \\(1\\.9M\\)", "North", "Northeast", "Northwest", "OR\\.\\.\\.", "Open", "Open Next", "Open\\.\\.\\.", "Options\\.\\.\\.", "Organ of Corti \\(2\\.8M, 4D stack\\)", "Original Scale", "Orthogonal Views", "Out", "Outline", "PGM\\.\\.\\.", "PNG\\.\\.\\.", "Page Setup\\.\\.\\.", "Paintbrush Tool Options\\.\\.\\.", "Particles \\(75K\\)", "Paste", "Paste Control\\.\\.\\.", "Pencil Tool Options\\.\\.\\.", "Plot Lanes", "Plot Profile", "Plot Z-axis Profile", "Plugin", "Plugin Filter", "Plugin Frame", "Plugins\\.\\.\\.", "Point Tool\\.\\.\\.", "Previous Slice \\[<\\]", "Print\\.\\.\\.", "Profile Plot Options\\.\\.\\.", "Properties\\.\\.\\.", "Properties\\.\\.\\.", "Proxy Settings\\.\\.\\.", "Put Behind \\[tab]", "Quit", "RGB Color", "RGB Stack", "ROI Manager\\.\\.\\.", "Raw Data\\.\\.\\.", "Raw\\.\\.\\.", "Re-plot Lanes", "Reciprocal", "Record\\.\\.\\.", "Red", "Red/Green", "Redisplay Power Spectrum", "Reduce Dimensionality\\.\\.\\.", "Reduce\\.\\.\\.", "Refresh Menus", "Remove Outliers\\.\\.\\.", "Remove Overlay", "Remove\\.\\.\\.", "Rename\\.\\.\\.", "Repeat Command", "Reset", "Reset\\.\\.\\.", "Reslice \\[/\\]\\.\\.\\.", "Restore Selection", "Results\\.\\.\\.", "Results\\.\\.\\.", "Reverse", "Revert", "Rotate 90 Degrees Left", "Rotate 90 Degrees Right", "Rotate\\.\\.\\.", "Rotate\\.\\.\\.", "Run\\.\\.\\.", "Salt and Pepper", "Save", "Save XY Coordinates\\.\\.\\.", "Scale Bar\\.\\.\\.", "Scale\\.\\.\\.", "Search Website\\.\\.\\.", "Search\\.\\.\\.", "Select All", "Select First Lane", "Select Next Lane", "Select None", "Selection\\.\\.\\.", "Set Measurements\\.\\.\\.", "Set Scale\\.\\.\\.", "Set Slice\\.\\.\\.", "Set\\.\\.\\.", "Set\\.\\.\\.", "Shadows Demo", "Sharpen", "Show All", "Show Circular Masks\\.\\.\\.", "Show Info\\.\\.\\.", "Show LUT", "Show Overlay", "Size\\.\\.\\.", "Skeletonize", "Smooth", "South", "Southeast", "Southwest", "Specify\\.\\.\\.", "Spectrum", "Split Channels", "Square", "Square Root", "Stack From List\\.\\.\\.", "Stack to Hyperstack\\.\\.\\.", "Stack to Images", "Stack to RGB", "Start Animation \\[\\\\\\]", "Startup Macros\\.\\.\\.", "Stop Animation", "Straighten\\.\\.\\.", "Subtract Background\\.\\.\\.", "Subtract\\.\\.\\.", "Summarize", "Surface Plot\\.\\.\\.", "Swap Quadrants", "System Clipboard", "T1 Head \\(2\\.4M, 16-bits\\)", "T1 Head Renderings \\(736K\\)", "TEM Filter \\(112K\\)", "TIFF Virtual Stack\\.\\.\\.", "Table\\.\\.\\.", "Text File\\.\\.\\.", "Text Image\\.\\.\\.", "Text Image\\.\\.\\.", "Text Window", "Text Window\\.\\.\\.", "Text\\.\\.\\.", "Threads\\.\\.\\.", "Threshold\\.\\.\\.", "Tiff\\.\\.\\.", "Tile", "To ROI Manager", "To Selection", "Translate\\.\\.\\.", "Tree Rings \\(48K\\)", "URL\\.\\.\\.", "Ultimate Points", "Undo", "Unsharp Mask\\.\\.\\.", "Update ImageJ\\.\\.\\.", "Variance\\.\\.\\.", "View 100%", "Virtual Stack\\.\\.\\.", "Voronoi", "Wand Tool\\.\\.\\.", "Watershed", "West", "Window/Level\\.\\.\\.", "XOR\\.\\.\\.", "XY Coordinates\\.\\.\\.", "Yellow", "Z Project\\.\\.\\.", "ZIP\\.\\.\\."};
    String BUILTIN[] = new String[]{"Array\\.copy", "Array\\.fill", "Array\\.getStatistics", "Array\\.invert", "Array\\.sort", "Array\\.trim", "Dialog\\.addCheckbox", "Dialog\\.addCheckboxGroup", "Dialog\\.addChoice", "Dialog\\.addHelp", "Dialog\\.addMessage", "Dialog\\.addNumber", "Dialog\\.addString", "Dialog\\.create", "Dialog\\.getCheckbox", "Dialog\\.getChoice", "Dialog\\.getNumber", "Dialog\\.getString", "Dialog\\.show", "Ext\\.(?=[a-zA-Z0-9])", "File\\.append", "File\\.close", "File\\.dateLastModified", "File\\.delete", "File\\.directory", "File\\.exists", "File\\.getName", "File\\.getParent", "File\\.isDirectory", "File\\.lastModified", "File\\.length", "File\\.makeDirectory", "File\\.name", "File\\.nameWithoutExtension", "File\\.open", "File\\.openAsRawString", "File\\.openAsString", "File\\.openDialog", "File\\.openUrlAsString", "File\\.rename", "File\\.saveString", "File\\.separator", "Fit\\.doFit", "Fit\\.f", "Fit\\.getEquation", "Fit\\.logResults", "Fit\\.nEquations", "Fit\\.nParams", "Fit\\.p", "Fit\\.plot", "Fit\\.rSquared", "Fit\\.showDialog", "IJ\\.currentMemory", "IJ\\.deleteRows", "IJ\\.freeMemory", "IJ\\.getToolName", "IJ\\.maxMemory", "IJ\\.redirectErrorMessages", "List\\.clear", "List\\.get", "List\\.getList", "List\\.getValue", "List\\.set", "List\\.setCommands", "List\\.setList", "List\\.size", "Overlay\\.add", "Overlay\\.drawEllipse", "Overlay\\.drawLine", "Overlay\\.drawRect", "Overlay\\.drawString", "Overlay\\.hide", "Overlay\\.lineTo", "Overlay\\.moveTo", "Overlay\\.remove", "Overlay\\.removeSelection", "Overlay\\.show", "Overlay\\.size", "PI", "Plot\\.add", "Plot\\.addText", "Plot\\.create", "Plot\\.drawLine", "Plot\\.getValues", "Plot\\.setColor", "Plot\\.setJustification", "Plot\\.setLimits", "Plot\\.setLineWidth", "Plot\\.show", "Plot\\.update", "Stack\\.getActiveChannels", "Stack\\.getDimensions", "Stack\\.getDisplayMode", "Stack\\.getFrameRate", "Stack\\.getPosition", "Stack\\.getStatistics", "Stack\\.isHyperstack", "Stack\\.setActiveChannels", "Stack\\.setChannel", "Stack\\.setDimensions", "Stack\\.setDisplayMode", "Stack\\.setFrame", "Stack\\.setFrameRate", "Stack\\.setPosition", "Stack\\.setSlice", "Stack\\.setTUnit", "Stack\\.setZUnit", "Stack\\.swap", "String\\.append", "String\\.buffer", "String\\.copy", "String\\.copyResults", "String\\.paste", "String\\.resetBuffer", "abs", "acos", "asin", "atan", "atan2", "autoUpdate", "beep", "bitDepth", "calibrate", "call", "changeValues", "charCodeAt", "close", "cos", "d2s", "doCommand", "doWand", "drawLine", "drawOval", "drawRect", "drawString", "dump", "endsWith", "eval", "exec", "exit", "exp", "fill", "fillOval", "fillRect", "floodFill", "floor", "fromCharCode", "getArgument", "getBoolean", "getBoundingRect", "getCursorLoc", "getDateAndTime", "getDimensions", "getDirectory", "getFileList", "getHeight", "getHistogram", "getImageID", "getImageInfo", "getInfo", "getLine", "getList", "getLocationAndSize", "getLut", "getMetadata", "getMinAndMax", "getNumber", "getPixel", "getPixelSize", "getProfile", "getRawStatistics", "getResult", "getResultLabel", "getSelectionBounds", "getSelectionCoordinates", "getSliceNumber", "getStatistics", "getString", "getStringWidth", "getThreshold", "getTime", "getTitle", "getValue", "getVersion", "getVoxelSize", "getWidth", "getZoom", "imageCalculator", "indexOf", "is", "isActive", "isKeyDown", "isNaN", "isOpen", "lastIndexOf", "lengthOf", "lineTo", "log", "makeLine", "makeOval", "makePoint", "makePolygon", "makeRectangle", "makeSelection", "makeText", "matches", "maxOf", "minOf", "moveTo", "nImages", "nResults", "nSlices", "newArray", "newImage", "newMenu", "open", "parseFloat", "parseInt", "pow", "print", "random", "rename", "replace", "requires", "reset", "resetMinAndMax", "resetThreshold", "restorePreviousTool", "restoreSettings", "roiManager", "round", "run", "runMacro", "save", "saveAs", "saveSettings", "screenHeight", "screenWidth", "selectImage", "selectWindow", "selectionContains", "selectionName", "selectionType", "setAutoThreshold", "setBackgroundColor", "setBatchMode", "setColor", "setFont", "setForegroundColor", "setJustification", "setKeyDown", "setLineWidth", "setLocation", "setLut", "setMetadata", "setMinAndMax", "setOption", "setPasteMode", "setPixel", "setRGBWeights", "setResult", "setSelectionLocation", "setSelectionName", "setSlice", "setThreshold", "setTool", "setVoxelSize", "setZCoordinate", "setupUndo", "showMessage", "showMessageWithCancel", "showProgress", "showStatus", "sin", "snapshot", "split", "sqrt", "startsWith", "substring", "tan", "toBinary", "toHex", "toLowerCase", "toString", "toUpperCase", "toolID", "updateDisplay", "updateResults", "wait", "waitForUser"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q//\\E[^\\n]*|\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String STORAGE_TYPE_PATTERN = "\\b(" + String.join("|", STORAGE_TYPE) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
        String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORD) + ")\\b";
        String COMMAND_PATTERN = "\\b(" + String.join("|", COMMAND) + ")\\b";
        String BUILTIN_PATTERN = "\\b(" + String.join("|", BUILTIN) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<STORAGETYPE>" + STORAGE_TYPE_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
                + "|(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                + "|(?<COMMAND>" + COMMAND_PATTERN + ")"
                + "|(?<BUILTIN>" + BUILTIN_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("STORAGETYPE") != null ? "storage-type"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : matcher.group("KEYWORD") != null ? "keyword"
                : matcher.group("COMMAND") != null ? "command"
                : matcher.group("BUILTIN") != null ? "builtin"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(STORAGE_TYPE));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        keywordList.addAll(Arrays.asList(KEYWORD));
        keywordList.addAll(Arrays.asList(COMMAND));
        keywordList.addAll(Arrays.asList(BUILTIN));
        Collections.sort(keywordList);
        return keywordList;
    }

}
