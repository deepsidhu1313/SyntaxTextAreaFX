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
public class Ruby implements Language {

    String ATTRIBUTE_DEFINITIONS[] = new String[]{"attr", "attr_writer", "attr_reader", "attr_accessor"};
    String DEFINITIONS[] = new String[]{"alias", "class", "module", "def", "undef"};
    String MODULE_HANDLERS[] = new String[]{"require", "include", "load"};
    String KEYWORDS[] = new String[]{"BEGIN", "END", "and", "begin", "break", "case", "catch", "defined?", "do", "else", "elsif", "end", "ensure", "for", "if", "in", "next", "not", "or", "private", "protected", "public", "redo", "rescue", "retry", "return", "then", "throw", "unless", "until", "when", "while", "yield"};
    String BUILTINS[] = new String[]{"ARGF", "ARGV", "Abbrev", "ArgumentError", "Array", "Base64", "Benchmark::Tms", "Benchmark", "Bignum", "Binding", "CGI::Cookie", "CGI::HtmlExtension", "CGI::QueryExtension", "CGI::Session::FileStore", "CGI::Session::MemoryStore", "CGI::Session", "CGI", "Class", "Comparable", "Complex", "ConditionVariable", "Continuation", "DATA", "Data", "Date", "DateTime", "Delegator", "Dir", "ENV", "EOFError", "ERB::Util", "ERB", "Enumerable::Enumerator", "Enumerable", "Errno", "Exception", "FalseClass", "File::Constants", "File::Stat", "File", "FileTest", "FileUtils::DryRun", "FileUtils::NoWrite", "FileUtils::StreamUtils", "FileUtils::Verbose", "FileUtils", "Find", "Fixnum", "Float", "FloatDomainError", "Forwardable", "GC", "Generator", "Hash", "IO", "IOError", "Iconv::BrokenLibrary", "Iconv::Failure", "Iconv::IllegalSequence", "Iconv::InvalidCharacter", "Iconv::InvalidEncoding", "Iconv::OutOfRange", "Iconv", "IndexError", "Integer", "Interrupt", "Kernel", "LoadError", "LocalJumpError", "Logger::Application", "Logger::Error", "Logger::Formatter", "Logger::LogDevice::LogDeviceMutex", "Logger::LogDevice", "Logger::Severity", "Logger::ShiftingError", "Logger", "Marshal", "MatchData", "Math", "Matrix", "Method", "Module", "Mutex", "NameError::message", "NameError", "NilClass", "NoMemoryError", "NoMethodError", "NotImplementedError", "Numeric", "Object", "ObjectSpace", "Observable", "PStore::Error", "PStore", "Pathname", "Precision", "Proc", "Process::GID", "Process::Status", "Process::Sys", "Process::UID", "Process", "Queue", "RUBY_PLATFORM", "RUBY_RELEASE_DATE", "RUBY_VERSION", "Range", "RangeError", "Rational", "Regexp", "RegexpError", "RuntimeError", "STDERR", "STDIN", "STDOUT", "ScriptError", "SecurityError", "Set", "Shellwords", "Signal", "SignalException", "SimpleDelegator", "SingleForwardable", "Singleton", "SingletonClassMethods", "SizedQueue", "SortedSet", "StandardError", "String", "StringIO", "StringScanner::Error", "StringScanner", "Struct::Tms", "Struct", "Symbol", "SyncEnumerator", "SyntaxError", "SystemCallError", "SystemExit", "SystemStackError", "Tempfile", "Test::Unit", "Test", "Thread", "ThreadError", "ThreadGroup", "ThreadsWait", "Time", "Timeout::Error", "Timeout", "TrueClass", "TypeError", "URI::BadURIError", "URI::Error", "URI::Escape", "URI::FTP", "URI::Generic", "URI::HTTP", "URI::HTTPS", "URI::InvalidComponentError", "URI::InvalidURIError", "URI::LDAP", "URI::MailTo", "URI::REGEXP::PATTERN", "URI::REGEXP", "URI", "UnboundMethod", "Vector", "YAML", "ZeroDivisionError", "Zlib::BufError", "Zlib::DataError", "Zlib::Deflate", "Zlib::Error", "Zlib::GzipFile::CRCError", "Zlib::GzipFile::Error", "Zlib::GzipFile::LengthError", "Zlib::GzipFile::NoFooter", "Zlib::GzipFile", "Zlib::GzipReader", "Zlib::GzipWriter", "Zlib::Inflate", "Zlib::MemError", "Zlib::NeedDict", "Zlib::StreamEnd", "Zlib::StreamError", "Zlib::VersionError", "Zlib::ZStream", "Zlib", "fatal"};
    String SPECIAL_VARIABLES[] = new String[]{"self", "super", "__FILE__", "__LINE__"};
    String NIL_VALUE[] = new String[]{"NIL", "nil"};
    String BOOLEAN[] = new String[]{"false", "true", "false", "true"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q#\\E[^\\n]*";
        String ATTRIBUTE_DEFINITIONS_PATTERN = "\\b(" + String.join("|", ATTRIBUTE_DEFINITIONS) + ")\\b";
        String DEFINITIONS_PATTERN = "\\b(" + String.join("|", DEFINITIONS) + ")\\b";
        String MODULE_HANDLERS_PATTERN = "\\b(" + String.join("|", MODULE_HANDLERS) + ")\\b";
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        String BUILTINS_PATTERN = "\\b(" + String.join("|", BUILTINS) + ")\\b";
        String SPECIAL_VARIABLES_PATTERN = "\\b(" + String.join("|", SPECIAL_VARIABLES) + ")\\b";
        String NIL_VALUE_PATTERN = "\\b(" + String.join("|", NIL_VALUE) + ")\\b";
        String BOOLEAN_PATTERN = "\\b(" + String.join("|", BOOLEAN) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<ATTRIBUTEDEFINITIONS>" + ATTRIBUTE_DEFINITIONS_PATTERN + ")"
                + "|(?<DEFINITIONS>" + DEFINITIONS_PATTERN + ")"
                + "|(?<MODULEHANDLERS>" + MODULE_HANDLERS_PATTERN + ")"
                + "|(?<KEYWORDS>" + KEYWORDS_PATTERN + ")"
                + "|(?<BUILTINS>" + BUILTINS_PATTERN + ")"
                + "|(?<SPECIALVARIABLES>" + SPECIAL_VARIABLES_PATTERN + ")"
                + "|(?<NILVALUE>" + NIL_VALUE_PATTERN + ")"
                + "|(?<BOOLEAN>" + BOOLEAN_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("ATTRIBUTEDEFINITIONS") != null ? "attribute-definitions"
                : matcher.group("DEFINITIONS") != null ? "definitions"
                : matcher.group("MODULEHANDLERS") != null ? "module-handlers"
                : matcher.group("KEYWORDS") != null ? "keywords"
                : matcher.group("BUILTINS") != null ? "builtins"
                : matcher.group("SPECIALVARIABLES") != null ? "special-variables"
                : matcher.group("NILVALUE") != null ? "nil-value"
                : matcher.group("BOOLEAN") != null ? "boolean"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(ATTRIBUTE_DEFINITIONS));
        keywordList.addAll(Arrays.asList(DEFINITIONS));
        keywordList.addAll(Arrays.asList(MODULE_HANDLERS));
        keywordList.addAll(Arrays.asList(KEYWORDS));
        keywordList.addAll(Arrays.asList(BUILTINS));
        keywordList.addAll(Arrays.asList(SPECIAL_VARIABLES));
        keywordList.addAll(Arrays.asList(NIL_VALUE));
        keywordList.addAll(Arrays.asList(BOOLEAN));
        Collections.sort(keywordList);
        return keywordList;
    }

}
