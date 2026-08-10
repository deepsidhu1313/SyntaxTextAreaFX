package in.co.s13.syntaxtextareafx;

import in.co.s13.syntaxtextareafx.langs.Java;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Highlighting patterns, and the stack overflow they used to cause.
 *
 * <p>Issue #5: highlighting a Java file threw {@code StackOverflowError} from
 * deep inside {@code java.util.regex}. The cause was an alternation inside a
 * quantifier — the block comment rule read {@code /\*(.|\R)*?\*}{@code /}, and
 * Java's regex engine allocates a stack frame per repetition of a group, so a
 * long comment simply ran out of stack. A character class matches exactly the
 * same text at constant stack depth.
 */
class HighlightingPatternTest {

    private static Pattern javaPattern() {
        return new Java().generatePattern();
    }

    /**
     * The reported crash. A few thousand lines inside one block comment was
     * enough; this uses more, and completes in milliseconds once the pattern is
     * not recursive.
     */
    @Test
    @Timeout(20)
    void aLongBlockCommentDoesNotOverflowTheStack() {
        String source = "/*\n" + "a comment line that goes on\n".repeat(20000) + "*/\nclass A {}";

        assertDoesNotThrow(() -> {
            Matcher matcher = javaPattern().matcher(source);
            while (matcher.find()) {
                // Walking every match is what the highlighter does.
            }
        });
    }

    @Test
    @Timeout(20)
    void aLongStringLiteralDoesNotOverflowTheStack() {
        // The string rule had the same alternation-in-a-quantifier shape.
        String source = "class A { String s = \"" + "x".repeat(200000) + "\"; }";

        assertDoesNotThrow(() -> {
            Matcher matcher = javaPattern().matcher(source);
            while (matcher.find()) {
                // as above
            }
        });
    }

    @Test
    @Timeout(20)
    void manyEscapesInAStringDoNotBacktrackExponentially() {
        String source = "class A { String s = \"" + "\\\\".repeat(20000) + "\"; }";

        assertDoesNotThrow(() -> {
            Matcher matcher = javaPattern().matcher(source);
            while (matcher.find()) {
                // as above
            }
        });
    }

    @Test
    void stillHighlightsBlockCommentsCorrectly() {
        Matcher matcher = javaPattern().matcher("/* hello\nworld */");

        assertTrue(matcher.find(), "the comment should match");
        assertEquals("/* hello\nworld */", matcher.group("COMMENT"));
    }

    @Test
    void stillHighlightsLineComments() {
        Matcher matcher = javaPattern().matcher("// just a line\n");

        assertTrue(matcher.find());
        assertEquals("// just a line", matcher.group("COMMENT"));
    }

    @Test
    void stillHighlightsStringsIncludingEscapedQuotes() {
        Matcher matcher = javaPattern().matcher("String s = \"a \\\" b\";");

        assertTrue(matcher.find(), "expected a match");
        while (matcher.group("STRING") == null && matcher.find()) {
            // skip keyword matches before the literal
        }
        assertEquals("\"a \\\" b\"", matcher.group("STRING"));
    }

    @Test
    void stillHighlightsKeywords() {
        // Java splits keywords into categories rather than one KEYWORD group;
        // "class" is a declaration.
        Matcher matcher = javaPattern().matcher("class A {}");

        assertTrue(matcher.find());
        assertEquals("class", matcher.group("DECLARATIONS"));
    }

    @Test
    void anUnterminatedBlockCommentDoesNotHang() {
        // A comment the user is still typing has no closing delimiter.
        String source = "/* still typing\n" + "more\n".repeat(20000);

        assertDoesNotThrow(() -> {
            Matcher matcher = javaPattern().matcher(source);
            while (matcher.find()) {
                // as above
            }
        });
    }
}
