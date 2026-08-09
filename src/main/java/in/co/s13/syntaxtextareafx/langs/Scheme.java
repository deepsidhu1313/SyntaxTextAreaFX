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
public class Scheme implements Language {

    String KEYWORD[] = new String[]{"and", "begin", "case", "cond-expand", "cond", "define-accessor", "define-class", "defined\\?", "define-generic", "define", "define\\*", "define-macro", "define-method", "define-module", "define-private", "define-public", "define\\*-public", "define-reader-ctor", "define-syntax", "define-syntax-macro", "defmacro", "defmacro\\*", "defmacro\\*-public", "delay", "do", "else", "fluid-let", "if", "lambda", "let", "let\\*", "letrec", "letrec-syntax", "let-syntax", "or", "quasiquote", "quote", "set\\!", "syntax-rules", "unquote"};
    String FUNCTION[] = new String[]{"abs", "acos", "angle", "append", "apply", "asin", "assoc", "assq", "assv", "atan", "boolean\\?", "caaar", "caadr", "caar", "cadar", "caddr", "cadr", "call/cc", "call-with-current-continuation", "call-with-input-file", "call-with-output-file", "call-with-values", "car", "catch", "cdaar", "cdadr", "cdar", "cddar", "cdddr", "cddr", "cdr", "ceiling", "char-alphabetic\\?", "char-ci>=\\?", "char-ci>\\?", "char-ci\\=\\?", "char-ci<=\\?", "char-ci<\\?", "char-downcase", "char->integer", "char>=\\?", "char>\\?", "char=\\?", "char\\?", "char-lower-case\\?", "char<=\\?", "char<\\?", "char-numeric\\?", "char-ready\\?", "char-upcase", "char-upper-case\\?", "char-whitespace\\?", "close-input-port", "close-output-port", "complex\\?", "cons", "cos", "current-input-port", "current-output-port", "delete-file", "display", "dynamic-wind", "eof-object\\?", "eq\\?", "equal\\?", "eqv\\?", "eval", "even\\?", "exact->inexact", "exact\\?", "exit", "exp", "expt", "file-exists\\?", "file-or-directory-modify-seconds", "floor", "force", "for-each", "gcd", "gensym", "getenv", "get-output-string", "imag-part", "inexact\\?", "input-port\\?", "integer->char", "integer\\?", "lcm", "length", "list->string", "list->vector", "list", "list\\?", "list-ref", "list-tail", "load", "log", "magnitude", "make-polar", "make-rectangular", "make-string", "make-vector", "map", "max", "member", "memq", "memv", "min", "modulo", "negative\\?", "newline", "nil", "not", "null\\?", "number->string", "number\\?", "odd\\?", "open-input-file", "open-input-string", "open-output-file", "open-output-string", "output-port\\?", "pair\\?", "peek-char", "port\\?", "positive\\?", "procedure\\?", "quotient", "rational\\?", "read-char", "read", "read-line", "real\\?", "real-part", "remainder", "reverse", "reverse\\!", "round\\", "set-car\\!", "set-cdr\\!", "sin", "sqrt", "string-append", "string-ci>=\\?", "string-ci>\\?", "string-ci=\\?", "string-ci<=\\?", "string-ci<\\?", "string-copy", "string-fill\\!", "string>=\\?", "string>\\?", "string->list", "string->number", "string->symbol", "string", "string=\\?", "string\\?", "string-length", "string<=\\?", "string<\\?", "string-ref", "string-set\\!", "substring", "symbol->string", "symbol\\?", "system", "tan", "truncate", "values", "vector-fill\\!", "vector->list", "vector", "vector\\?", "vector-length", "vector-ref", "vector-set\\!", "with-input-from-file", "with-output-to-file", "write-char", "write", "zero\\?"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q;\\E[^\\n]*";
        String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORD) + ")\\b";
        String FUNCTION_PATTERN = "\\b(" + String.join("|", FUNCTION) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                + "|(?<FUNCTION>" + FUNCTION_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("KEYWORD") != null ? "keyword"
                : matcher.group("FUNCTION") != null ? "function"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(KEYWORD));
        keywordList.addAll(Arrays.asList(FUNCTION));
        Collections.sort(keywordList);
        return keywordList;
    }

}
