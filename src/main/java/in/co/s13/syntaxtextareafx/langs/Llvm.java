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
public class Llvm implements Language {

    String KEYWORDS[] = new String[]{"private", "linker_private", "linker_private_weak", "linker_private_weak_def_auto", "internal", "available_externally", "linkonce", "common", "weak", "appending", "extern_weak", "linkonce_odr", "weak_odr", "external", "dllimport", "dllexport", "ccc", "fastcc", "coldcc", "default", "hidden", "protected", "type", "thread_local", "constant", "unnamed_addr", "addrspace", "define", "alias", "declare", "zeroext", "signext", "inreg", "byval", "sret", "noalias", "nocapture", "nest", "gc", "address_safety", "alignstack", "alwaysinline", "nonlazybind", "inlinehint", "naked", "noimplicitfloat", "noinline", "noredzone", "noreturn", "nounwind", "optsize", "readnone", "readonly", "returns_twice", "ssp", "sspreq", "uwtable", "align", "module", "asm", "target", "datalayout", "triple", "unordered", "monotonic", "acquire", "release", "acq_rel", "seq_cst", "singlethread", "nuw", "nsw", "exact", "volatile", "atomic", "to", "personality", "cleanup", "catch", "filter", "eq", "ne", "ugt", "uge", "ult", "ule", "sgt", "slt", "sle", "oeq", "ogt", "oge", "olt", "ole", "one", "ord", "ueq", "ugt", "uge", "ult", "ule", "une", "uno", "inbounds"};
    String FUNCTIONS[] = new String[]{"ret", "br", "switch", "indirectbr", "invoke", "resume", "unreachable", "add", "fadd", "sub", "fsub", "mul", "fmul", "udiv", "sdiv", "fdiv", "urem", "srem", "frem", "shl", "lshr", "ashr", "and", "or", "xor", "extractelement", "insertelement", "shufflevector", "extractvalue", "insertvalue", "alloca", "load", "store", "fence", "cmpxchg", "atomicrmw", "getelementptr", "trunc", "zext", "sext", "fptrunc", "fpext", "fptoui", "fptosi", "uitofp", "sitofp", "ptrtoint", "inttoptr", "bitcast", "icmp", "fcmp", "phi", "select", "call", "va_arg", "landingpad"};
    String CONSTANT[] = new String[]{"null", "zeroinitializer", "undef"};
    String BOOLEAN[] = new String[]{"true", "false"};
    String TYPES[] = new String[]{"half", "float", "double", "x86_fp80", "fp128", "ppc_fp128", "x86mmx", "void", "label", "metadata", "opaque"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q;\\E[^\\n]*";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String FUNCTIONS_PATTERN = "\\b(" + String.join("|", FUNCTIONS) + ")\\b";
        String CONSTANT_PATTERN = "\\b(" + String.join("|", CONSTANT) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";
        String TYPES_PATTERN = "\\b(" + String.join("|", TYPES) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<FUNCTIONS>" + FUNCTIONS_PATTERN + ")"
                + "|(?<CONSTANT>" + CONSTANT_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
                + "|(?<TYPES>" + TYPES_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("FUNCTIONS") != null ? "functions"
                : matcher.group("CONSTANT") != null ? "constant"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : matcher.group("TYPES") != null ? "types"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(FUNCTIONS));
        keywordList.addAll(Arrays.asList(CONSTANT));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        keywordList.addAll(Arrays.asList(TYPES));
        Collections.sort(keywordList);
        return keywordList;
    }

}
