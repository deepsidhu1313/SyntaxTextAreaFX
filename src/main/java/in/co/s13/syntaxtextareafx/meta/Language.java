/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.s13.syntaxtextareafx.meta;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A single language's highlighting rules: what to match, and how to style
 * each match.
 *
 * <p>Implementations are either hand-written (e.g. {@code Java}, {@code C})
 * or generated from a gtksourceview JSON definition by {@link Generator}. A
 * single compiled {@link Pattern} drives both highlighting and keyword
 * lookup, so {@link #getStyleClass} and {@link #getKeywords} must agree with
 * whatever named groups {@link #generatePattern} actually declares.
 */
public interface Language {

    /**
     * Compiles this language's full set of highlighting rules into one
     * {@link Pattern}, with each rule as a named capture group (e.g.
     * {@code (?<STRING>...)}) that {@link #getStyleClass} can look up by
     * name.
     *
     * @return the pattern the editor repeatedly matches against as the user
     * types
     */
    public Pattern generatePattern();

    /**
     * The CSS style class for whatever group in {@link #generatePattern}'s
     * pattern this matcher's current match came from.
     *
     * @param matcher a matcher already positioned on a successful match of
     * this language's pattern
     * @return a style class such as {@code "string"} or {@code "keywords"},
     * or {@code null} if the match doesn't belong to a group this language
     * assigns a style to
     */
    public String getStyleClass(Matcher matcher);

    /**
     * Every keyword this language recognises, for autocomplete suggestions.
     *
     * @return the language's keywords, sorted
     */
    public ArrayList<String> getKeywords();
}
