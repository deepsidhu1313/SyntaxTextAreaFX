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
public class Css implements Language {

    String SELECTOR_PSEUDO_ELEMENTS[] = new String[]{"first-line", "first-letter", "before", "after"};
    String SELECTOR_PSEUDO_CLASSES[] = new String[]{"enabled", "disabled", "checked", "indeterminate", "root", "nth-child", "nth-last-child", "nth-of-type", "nth-last-of-type", "first-child", "last-child", "first-of-type", "last-of-type", "only-child", "only-of-type", "empty", "not", "link", "visited", "hover", "active", "focus", "target", "lang"};
    String AT_RULES[] = new String[]{"charset", "font-face", "media", "page", "import"};
    String NAMED_COLOR[] = new String[]{"aqua", "black", "blue", "fuchsia", "gray", "green", "lime", "maroon", "navy", "olive", "orange", "purple", "red", "silver", "teal", "white", "yellow"};
    String PROPERTY_NAMES[] = new String[]{"azimuth", "background-attachment", "background-color", "background-image", "background-position", "background-repeat", "background", "border-bottom-color", "border-bottom-left-radius", "border-bottom-right-radius", "border-bottom-style", "border-bottom-width", "border-bottom", "border-collapse", "border-color", "border-left-color", "border-left-style", "border-left-width", "border-left", "border-radius", "border-right-color", "border-right-style", "border-right-width", "border-right", "border-spacing", "border-style", "border-top-color", "border-top-left-radius", "border-top-right-radius", "border-top-style", "border-top-width", "border-top", "border-width", "border", "bottom", "box-shadow", "caption-side", "clear", "clip", "color", "content", "counter-increment", "counter-reset", "cue-after", "cue-before", "cue", "cursor", "direction", "display", "elevation", "empty-cells", "float", "font-family", "font-size-adjust", "font-size", "font-style", "font-variant", "font-weight", "font", "height", "left", "letter-spacing", "line-height", "list-style-image", "list-style-position", "list-style-type", "list-style", "margin-bottom", "margin-left", "margin-right", "margin-top", "margin", "marker-offset", "marks", "max-height", "max-width", "min-height", "min-width", "orphans", "outline-color", "outline-style", "outline-width", "outline", "overflow", "padding-bottom", "padding-left", "padding-right", "padding-top", "padding", "page-break-after", "page-break-before", "page-break-inside", "page", "pause-after", "pause-before", "pause", "pitch-range", "pitch", "play-during", "position", "quotes", "richness", "right", "size", "speak-header", "speak-numerical", "speak-punctuation", "speak", "speech-rate", "stress", "table-layout", "text-align", "text-decoration", "text-indent", "text-shadow", "text-transform", "top", "unicode-bidi", "vertical-align", "visibility", "voice-family", "volume", "white-space", "widows", "width", "word-spacing", "z-index"};
    String KNOWN_PROPERTY_VALUES[] = new String[]{"above", "absolute", "always", "armenian", "auto", "avoid", "baseline", "behind", "below", "bidi-override", "blink", "block", "bolder", "bold", "both", "bottom", "capitalize", "center-left", "center-right", "center", "circle", "cjk-ideographic", "close-quote", "code", "collapse", "compact", "condensed", "continuous", "crop", "crosshair", "cross", "cue-after", "cue-before", "cursive", "dashed", "decimal", "decimal-leading-zero", "default", "digits", "disc", "dotted", "double", "embed", "e-resize", "expanded", "extra-condensed", "extra-expanded", "fantasy", "far-left", "far-right", "faster", "fast", "fixed", "fixed", "georgian", "groove", "hebrew", "help", "hidden", "hide", "higher", "high", "hiragana-iroha", "hiragana", "inherit", "inline", "inline-table", "inset", "inside", "invert", "italic", "justify", "katakana-iroha", "katakana", "landscape", "large", "larger", "left", "left-side", "leftwards", "level", "lighter", "line-through", "list-item", "loud", "lower-alpha", "lowercase", "lower-greek", "lower-latin", "lower-roman", "lower", "low", "ltr", "marker", "medium", "medium", "middle", "mix", "monospace", "move", "narrower", "ne-resize", "no-close-quote", "none", "no-open-quote", "no-repeat", "normal", "nowrap", "n-resize", "nw-resize", "oblique", "once", "open-quote", "outset", "outside", "overline", "pointer", "portait", "pre", "relative", "repeat-x", "repeat-y", "repeat", "ridge", "right-side", "right", "rightwards", "rtl", "run-in", "sans-serif", "scroll", "scroll", "semi-condensed", "semi-expanded", "separate", "se-resize", "serif", "show", "silent", "slower", "slow", "small-caps", "smaller", "small", "soft", "solid", "spell-out", "square", "s-resize", "static", "sub", "super", "sw-resize", "table-caption", "table-cell", "table-column-group", "table-column", "table-footer-group", "table-header-group", "table-row-group", "table-row", "table", "text-bottom", "text", "text-top", "thick", "thin", "top", "top", "transparent", "ultra-condensed", "ultra-expanded", "underline", "upper-alpha", "uppercase", "upper-latin", "upper-roman", "visible", "wait", "wider", "w-resize", "x-fast", "x-high", "x-large", "x-loud", "x-low", "x-slow", "x-small", "x-soft", "xx-large", "xx-small"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String SELECTOR_PSEUDO_ELEMENTS_PATTERN = "\\b(" + String.join("|", SELECTOR_PSEUDO_ELEMENTS) + ")\\b";
        String SELECTOR_PSEUDO_CLASSES_PATTERN = "\\b(" + String.join("|", SELECTOR_PSEUDO_CLASSES) + ")\\b";
        String AT_RULES_PATTERN = "\\b(" + String.join("|", AT_RULES) + ")\\b";
        String NAMED_COLOR_PATTERN = "\\b(" + String.join("|", NAMED_COLOR) + ")\\b";
        String PROPERTY_NAMES_PATTERN = "\\b(" + String.join("|", PROPERTY_NAMES) + ")\\b";
        String KNOWN_PROPERTY_VALUES_PATTERN = "\\b(" + String.join("|", KNOWN_PROPERTY_VALUES) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<SELECTORPSEUDOELEMENTS>" + SELECTOR_PSEUDO_ELEMENTS_PATTERN + ")"
                + "|(?<SELECTORPSEUDOCLASSES>" + SELECTOR_PSEUDO_CLASSES_PATTERN + ")"
                + "|(?<ATRULES>" + AT_RULES_PATTERN + ")"
                + "|(?<NAMEDCOLOR>" + NAMED_COLOR_PATTERN + ")"
                + "|(?<PROPERTYNAMES>" + PROPERTY_NAMES_PATTERN + ")"
                + "|(?<KNOWNPROPERTYVALUES>" + KNOWN_PROPERTY_VALUES_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("SELECTORPSEUDOELEMENTS") != null ? "selector-pseudo-elements"
                : matcher.group("SELECTORPSEUDOCLASSES") != null ? "selector-pseudo-classes"
                : matcher.group("ATRULES") != null ? "at-rules"
                : matcher.group("NAMEDCOLOR") != null ? "named-color"
                : matcher.group("PROPERTYNAMES") != null ? "property-names"
                : matcher.group("KNOWNPROPERTYVALUES") != null ? "known-property-values"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(SELECTOR_PSEUDO_ELEMENTS));
        keywordList.addAll(Arrays.asList(SELECTOR_PSEUDO_CLASSES));
        keywordList.addAll(Arrays.asList(AT_RULES));
        keywordList.addAll(Arrays.asList(NAMED_COLOR));
        keywordList.addAll(Arrays.asList(PROPERTY_NAMES));
        keywordList.addAll(Arrays.asList(KNOWN_PROPERTY_VALUES));
        Collections.sort(keywordList);
        return keywordList;
    }

}
