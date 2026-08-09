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
public class M4 implements Language {

    String M4_MACROS_NOARGS[] = new String[]{"divnum", "init", "location", "sysval"};
    String M4_MACROS_OPTARGS[] = new String[]{"change(com|quote)", "debug(file|mode)", "divert_pop", "newline", "trace(on|off)"};
    String AC_MACROS_NOARGS[] = new String[]{"ARG_PROGRAM", "AUTOCONF_VERSION", "C_BACKSLASH_A", "CACHE_(LOAD|SAVE)", "CANONICAL_(BUILD|HOST|TARGET)", "C_CHAR_UNSIGNED", "C_CONST", "C_FLEXIBLE_ARRAY_MEMBER", "C_INLINE", "C_PROTOTYPES", "C_RESTRICT", "C_STRINGIZE", "C_TYPEOF", "C_VARARRAYS", "C_VOLATILE", "DISABLE_OPTION_CHECKING", "ERLANG_SUBST_(((INSTALL_)?LIB|ROOT)_DIR|ERTS_VER)", "F77_MAIN", "F77_WRAPPERS", "FC_MAIN", "FC_WRAPPERS", "FUNC_ALLOCA", "FUNC_CHOWN", "FUNC_CLOSEDIR_VOID", "FUNC_ERROR_AT_LINE", "FUNC_FNMATCH(_GNU)?", "FUNC_FORK", "FUNC_FSEEKO", "FUNC_GETGROUPS", "FUNC_GETLOADAVG", "FUNC_GETMNTENT", "FUNC_GETPGRP", "FUNC_LSTAT_FOLLOWS_SLASHED_SYMLINK", "FUNC_LSTAT", "FUNC_MALLOC", "FUNC_MBRTOWC", "FUNC_MEMCMP", "FUNC_MKTIME", "FUNC_MMAP", "FUNC_OBSTACK", "FUNC_REALLOC", "FUNC_SELECT_ARGTYPES", "FUNC_SETPGRP", "FUNC_STAT", "FUNC_STRCOLL", "FUNC_STRERROR_R", "FUNC_STRFTIME", "FUNC_STRNLEN", "FUNC_STRTOD", "FUNC_STRTOLD", "FUNC_UTIME_NULL", "FUNC_VPRINTF", "HEADER_ASSERT", "HEADER_DIRENT", "HEADER_MAJOR", "HEADER_RESOLV", "HEADER_STAT", "HEADER_STDBOOL", "HEADER_STDC", "HEADER_SYS_WAIT", "HEADER_TIME", "HEADER_TIOCGWINSZ", "LANG_DEFINES_PROVIDED", "LANG_WERROR", "OUTPUT", "PACKAGE_BUGREPORT", "PACKAGE_NAME", "PACKAGE_STRING", "PACKAGE_TARNAME", "PACKAGE_URL", "PACKAGE_VERSION", "PATH_X", "PATH_XTRA", "PRESERVE_HELP_ORDER", "PROG_AWK", "PROG_CC(_C(89|99|_O))", "PROG_CC_STDC", "PROG_CPP(_WERROR)?", "PROG_CXX(CPP|_C_O)?", "PROG_EGREP", "PROG_F77_C_O", "PROG_FC_C_O", "PROG_FGREP", "PROG_GCC_TRADITIONAL", "PROG_GREP", "PROG_INSTALL", "PROG_LEX", "PROG_LN_S", "PROG_MAKE_SET", "PROG_MKDIR_P", "PROG_OBJ(CXX)?CPP", "PROG_RANLIB", "PROG_SED", "PROG_YACC", "REPLACE_FNMATCH", "REQUIRE_CPP", "STRUCT_DIRENT_D_(INO|TYPE)", "STRUCT_ST_BLOCKS", "STRUCT_TIMEZONE", "STRUCT_TM", "SYS_INTERPRETER", "SYS_LARGEFILE", "SYS_LONG_FILE_NAMES", "SYS_POSIX_TERMIOS", "TYPE_GETGROUPS", "TYPE_INT(16_T|32_T|64_T|8_T)", "TYPE_INTMAX_T", "TYPE_INTPTR_T", "TYPE_LONG_DOUBLE(_WIDER)?", "TYPE_LONG_LONG_INT", "TYPE_MBSTATE_T", "TYPE_MODE_T", "TYPE_OFF_T", "TYPE_PID_T", "TYPE_SIGNAL", "TYPE_SIZE_T", "TYPE_SSIZE_T", "TYPE_UID_T", "TYPE_UINT(16_T|32_T|64_T|8_T)", "TYPE_UINTMAX_T", "TYPE_UINTPTR_T", "TYPE_UNSIGNED_LONG_LONG_INT", "USE_SYSTEM_EXTENSIONS"};
    String AC_MACROS_NOARGS_2[] = new String[]{"AH_HEADER", "AS_BOURNE_COMPATIBLE", "AS_INIT", "AS_LINENO_PREPARE", "AS_ME_PREPARE", "AS_MESSAGE_FD", "AS_MESSAGE_LOG_FD", "AS_ORIGINAL_STDIN_FD", "AS_SHELL_SANITIZE", "AT_CLEANUP", "AT_COLOR_TESTS"};
    String AC_MACROS_OPTARGS[] = new String[]{"AC_C_BIGENDIAN", "AC_ERLANG_(PATH|NEED)_ERLC?", "AC_F(77|C)_DUMMY_MAIN", "AC_FC_((FIXED|FREE)FORM|LINE_LENGTH)", "AC_INCLUDES_DEFAULT", "AC_LANG_POP", "AC_PROG_(CC|CXX|OBJC(XX)?|F(77|C))", "AS_EXIT", "AT_INIT"};
    String AC_MACROS_OBSOLETE[] = new String[]{"AIX", "ALLOCA", "ARG_ARRAY", "CANONICAL_SYSTEM", "C_CROSS", "CHAR_UNSIGNED", "CHECKING", "C_LONG_DOUBLE", "COMPILE_CHECK", "CONFIG_HEADER", "CONST", "CROSS_CHECK", "CYGWIN", "DECL_SYS_SIGLIST", "DECL_YYTEXT", "DIR_HEADER", "DISABLE_FAST_INSTALL", "DISABLE_SHARED", "DISABLE_STATIC", "DYNIX_SEQ", "ENABLE(_(SHARED|STATIC))?", "EMXOS2", "ERROR", "EXEEXT", "FIND_X", "FIND_XTRA", "FOREACH", "FUNC_CHECK", "FUNC_SETVBUF_REVERSED", "FUNC_WAIT3", "GCC_TRADITIONAL", "GETGROUPS_T", "GETLOADAVG", "GNU_SOURCE", "HAVE_FUNCS", "HAVE_HEADERS", "HAVE_LIBRARY", "HAVE_POUNDBANG", "HEADER_CHECK", "HEADER_EGREP", "HELP_STRING", "INLINE", "INT_16_BITS", "IRIX_SUN", "LANG_C", "LANG_CPLUSPLUS", "LANG_FORTRAN77", "LANG_RESTORE", "LANG_SAVE", "LIBTOOL_DLOPEN", "LIBTOOL_WIN32_DLL", "LINK_FILES", "LN_S", "LONG_64_BITS", "LONG_DOUBLE", "LONG_FILE_NAMES", "MAJOR_HEADER", "MEMORY_H", "MINGW32", "MINIX", "MINUS_C_MINUS_O", "MMAP", "MODE_T", "OBJEXT", "OBSOLETE", "OFF_T", "OUTPUT_COMMANDS", "PID_T", "PREFIX", "PROG_(INTL|LIB)TOOL", "PROGRAM_CHECK", "PROGRAM_EGREP", "PROGRAM_PATH", "PROGRAMS_CHECK", "PROGRAMS_PATH", "REMOTE_TAPE", "RESTARTABLE_SYSCALLS", "RETSIGTYPE", "RSH", "SCO_INTL", "SET_MAKE", "SETVBUF_REVERSED", "SIZEOF_TYPE", "SIZE_T", "STAT_MACROS_BROKEN", "ST_BLKSIZE", "ST_BLOCKS", "STDC_HEADERS", "STRCOLL", "ST_RDEV", "SYS_RESTARTABLE_SYSCALLS", "SYS_SIGLIST_DECLARED", "TEST_CPP", "TEST_PROGRAM", "TIME_WITH_SYS_TIME", "TIMEZONE", "TRY_COMPILE", "TRY_CPP", "TRY_LINK_FUNC", "TRY_LINK", "TRY_RUN", "UID_T", "UNISTD_H", "USG", "UTIME_NULL", "VALIDATE_CACHED_SYSTEM_TUPLE", "VERBOSE", "VFORK", "VPRINTF", "WAIT3", "WARN", "WITH", "WORDS_BIGENDIAN", "XENIX_DIR", "YYTEXT_POINTER"};
    String AM_MACROS_NOARGS[] = new String[]{"ENABLE_MULTILIB", "GCONF_SOURCE_2", "GLIB_GNU_GETTEXT", "GNU_GETTEXT_INTL_SUBDIR", "PATH_LISPDIR", "PO_SUBDIRS", "PROG_AS", "PROG_CC_C_O", "PROG_GCJ", "PROG_LEX", "SILENT_RULES", "WITH_DMALLOC", "WITH_REGEX"};
    String AM_MACROS_OPTARGS[] = new String[]{"INIT_AUTOMAKE", "MAINTAINER_MODE", "PATH_PYTHON", "PROG_(UPC|VALAC)"};
    String AM_MACROS_PRIVATE[] = new String[]{"DEP_TRACK", "MAKE_INCLUDE", "OUTPUT_DEPENDENCY_COMMANDS", "PROG_INSTALL_STRIP", "SANITY_CHECK", "SET_DEPDIR"};
    String AM_MACROS_OBSOLETE[] = new String[]{"C_PROTOTYPES", "CONFIG_HEADER", "HEADER_TIOCGWINSZ_NEEDS_SYS_IOCTL", "PATH_CHECK", "PROG_(LIBTOOL|MKDIR_P)", "SYS_POSIX_TERMIOS", "(DIS|EN)ABLE_(STATIC|SHARED)"};
    String MISC_MACROS_NOARGS[] = new String[]{"__(file|o?line)__", "LT_CMD_MAX_LEN", "LT_FUNC_DLSYM_USCORE", "LT_LIB_(M|DLLOAD)", "LT_OUTPUT", "LT_PATH_(LD|NM)", "LT_SYS_DLOPEN_(SELF|DEPLIBS)", "LT_SYS_MODULE_(EXT|PATH)", "LT_SYS_(DLSEARCH_PATH|SYMBOL_USCORE)"};
    String MISC_MACROS_OPTARGS[] = new String[]{"LT_INIT", "PKG_PROG_PKG_CONFIG"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q#\\E[^\\n]*";
        String M4_MACROS_NOARGS_PATTERN = "\\b(" + String.join("|", M4_MACROS_NOARGS) + ")\\b";
        String M4_MACROS_OPTARGS_PATTERN = "\\b(" + String.join("|", M4_MACROS_OPTARGS) + ")\\b";
        String AC_MACROS_NOARGS_PATTERN = "\\b(" + String.join("|", AC_MACROS_NOARGS) + ")\\b";
        String AC_MACROS_NOARGS_2_PATTERN = "\\b(" + String.join("|", AC_MACROS_NOARGS_2) + ")\\b";
        String AC_MACROS_OPTARGS_PATTERN = "\\b(" + String.join("|", AC_MACROS_OPTARGS) + ")\\b";
        String AC_MACROS_OBSOLETE_PATTERN = "\\b(" + String.join("|", AC_MACROS_OBSOLETE) + ")\\b";
        String AM_MACROS_NOARGS_PATTERN = "\\b(" + String.join("|", AM_MACROS_NOARGS) + ")\\b";
        String AM_MACROS_OPTARGS_PATTERN = "\\b(" + String.join("|", AM_MACROS_OPTARGS) + ")\\b";
        String AM_MACROS_PRIVATE_PATTERN = "\\b(" + String.join("|", AM_MACROS_PRIVATE) + ")\\b";
        String AM_MACROS_OBSOLETE_PATTERN = "\\b(" + String.join("|", AM_MACROS_OBSOLETE) + ")\\b";
        String MISC_MACROS_NOARGS_PATTERN = "\\b(" + String.join("|", MISC_MACROS_NOARGS) + ")\\b";
        String MISC_MACROS_OPTARGS_PATTERN = "\\b(" + String.join("|", MISC_MACROS_OPTARGS) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<M4MACROSNOARGS>" + M4_MACROS_NOARGS_PATTERN + ")"
                + "|(?<M4MACROSOPTARGS>" + M4_MACROS_OPTARGS_PATTERN + ")"
                + "|(?<ACMACROSNOARGS>" + AC_MACROS_NOARGS_PATTERN + ")"
                + "|(?<ACMACROSNOARGS2>" + AC_MACROS_NOARGS_2_PATTERN + ")"
                + "|(?<ACMACROSOPTARGS>" + AC_MACROS_OPTARGS_PATTERN + ")"
                + "|(?<ACMACROSOBSOLETE>" + AC_MACROS_OBSOLETE_PATTERN + ")"
                + "|(?<AMMACROSNOARGS>" + AM_MACROS_NOARGS_PATTERN + ")"
                + "|(?<AMMACROSOPTARGS>" + AM_MACROS_OPTARGS_PATTERN + ")"
                + "|(?<AMMACROSPRIVATE>" + AM_MACROS_PRIVATE_PATTERN + ")"
                + "|(?<AMMACROSOBSOLETE>" + AM_MACROS_OBSOLETE_PATTERN + ")"
                + "|(?<MISCMACROSNOARGS>" + MISC_MACROS_NOARGS_PATTERN + ")"
                + "|(?<MISCMACROSOPTARGS>" + MISC_MACROS_OPTARGS_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("M4MACROSNOARGS") != null ? "m4-macros-noargs"
                : matcher.group("M4MACROSOPTARGS") != null ? "m4-macros-optargs"
                : matcher.group("ACMACROSNOARGS") != null ? "ac-macros-noargs"
                : matcher.group("ACMACROSNOARGS2") != null ? "ac-macros-noargs-2"
                : matcher.group("ACMACROSOPTARGS") != null ? "ac-macros-optargs"
                : matcher.group("ACMACROSOBSOLETE") != null ? "ac-macros-obsolete"
                : matcher.group("AMMACROSNOARGS") != null ? "am-macros-noargs"
                : matcher.group("AMMACROSOPTARGS") != null ? "am-macros-optargs"
                : matcher.group("AMMACROSPRIVATE") != null ? "am-macros-private"
                : matcher.group("AMMACROSOBSOLETE") != null ? "am-macros-obsolete"
                : matcher.group("MISCMACROSNOARGS") != null ? "misc-macros-noargs"
                : matcher.group("MISCMACROSOPTARGS") != null ? "misc-macros-optargs"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(M4_MACROS_NOARGS));
        keywordList.addAll(Arrays.asList(M4_MACROS_OPTARGS));
        keywordList.addAll(Arrays.asList(AC_MACROS_NOARGS));
        keywordList.addAll(Arrays.asList(AC_MACROS_NOARGS_2));
        keywordList.addAll(Arrays.asList(AC_MACROS_OPTARGS));
        keywordList.addAll(Arrays.asList(AC_MACROS_OBSOLETE));
        keywordList.addAll(Arrays.asList(AM_MACROS_NOARGS));
        keywordList.addAll(Arrays.asList(AM_MACROS_OPTARGS));
        keywordList.addAll(Arrays.asList(AM_MACROS_PRIVATE));
        keywordList.addAll(Arrays.asList(AM_MACROS_OBSOLETE));
        keywordList.addAll(Arrays.asList(MISC_MACROS_NOARGS));
        keywordList.addAll(Arrays.asList(MISC_MACROS_OPTARGS));
        Collections.sort(keywordList);
        return keywordList;
    }

}
