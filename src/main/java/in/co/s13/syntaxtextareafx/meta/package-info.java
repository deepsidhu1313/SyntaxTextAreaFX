/**
 * The contract each language's highlighting rules implement, and the tools
 * around it.
 *
 * <p>{@link in.co.s13.syntaxtextareafx.meta.Language} is the interface every
 * class in {@link in.co.s13.syntaxtextareafx.langs} implements.
 * {@link in.co.s13.syntaxtextareafx.meta.Syntax} wraps whichever
 * {@code Language} is currently selected. {@link
 * in.co.s13.syntaxtextareafx.meta.Generator} is the offline tool used to
 * generate new {@code Language} implementations from gtksourceview
 * definitions — it is a build-time dev tool, not part of the runtime API.
 */
package in.co.s13.syntaxtextareafx.meta;
