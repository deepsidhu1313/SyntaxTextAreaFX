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
public class Scala implements Language {

    String EXTERNAL[] = new String[]{"import", "package"};
    String DECLARATION[] = new String[]{"case class", "class", "def", "extends", "forSome", "mixin", "object", "trait", "type", "val", "var", "with"};
    String PRIMITIVE_TYPE[] = new String[]{"Boolean", "Byte", "Char", "Double", "Float", "Int", "Long", "Short", "Unit"};
    String LIBRARY_TYPE[] = new String[]{"Iterator", "Array", "String", "IterableProxy", "SeqProxy", "TraversableProxy", "Cell", "Option", "Some", "None", "Either", "Left", "Right", "Nil", "null", "Nothing", "Class", "Any", "AnyVal", "AnyRef", "Pair", "Triple", "BigDecimal", "BigInt", "BitSet", "DefaultMap", "HashMap", "HashSet", "IndexedSeq", "IntMap", "Iterable", "LinearSeq", "List", "ListMap", "ListSet", "LongMap", "Map", "MapLike", "MapProxy", "Nil", "NumericRange", "RangeUtils", "PagedSeq", "Queue", "Range", "RedBlack", "Seq", "Set", "SetProxy", "SortedMap", "SortedSet", "Stack", "Stream", "StreamIterator", "StreamView", "StreamViewLike", "StringLike", "StringOps", "Traversable", "TreeMap", "TreeSet", "Vector", "VectorBuilder", "VectorIterator", "WrappedString", "AddingBuilder", "ArrayBuffer", "ArrayBuilder", "ArrayLike", "ArrayOps", "ArraySeq", "ArrayStack", "BitSet", "Buffer", "BufferLike", "BufferProxy", "Builder", "Cloneable", "ConcurrentMap", "DefaultEntry", "DefaultMapModel", "DoubleLinkedList", "DoubleLinkedListLike", "FlatHashTable", "GrowingBuilder", "HashEntry", "HashMap", "HashSet", "HashTable", "History", "ImmutableMapAdaptor", "ImmutableSetAdaptor", "IndexedSeq", "IndexedSeqLike", "IndexedSeqOptimized", "IndexedSeqView", "Iterable", "LazyBuilder", "LinearSeq", "LinkedEntry", "LinkedHashMap", "LinkedHashSet", "LinkedList", "LinkedListLike", "ListBuffer", "ListMap", "Map", "MapBuilder", "MapLike", "MapProxy", "MultiMap", "MutableList", "ObservableBuffer", "ObservableMap", "ObservableSet", "OpenHashMap", "PriorityQueue", "PriorityQueueProxy", "Publisher", "Queue", "QueueProxy", "ResizableArray", "RevertibleHistory", "Seq", "SeqLike", "Set", "SetBuilder", "SetLike", "SetProxy", "Stack", "StackProxy", "StringBuilder", "Subscriber", "SynchronizedBuffer", "SynchronizedMap", "SynchronizedPriorityQueue", "SynchronizedQueue", "SynchronizedSet", "SynchronizedStack", "Traversable", "Undoable", "UnrolledBuffer", "WeakHashMap", "WrappedArray", "WrappedArrayBuilder"};
    String MODIFIER[] = new String[]{"abstract", "final", "implicit", "lazy", "override", "private", "protected", "sealed"};
    String FLOW[] = new String[]{"case", "catch", "do", "else", "finally", "for", "if", "match", "requires", "return", "throw", "try", "while", "yield"};
    String MEMORY[] = new String[]{"new", "super", "this"};
    String SPECIAL[] = new String[]{"true", "false", "null"};

    @Override
    public Pattern generatePattern() {
        String STRING_PATTERN = "\"[^\"\\\\]*+(?:\\\\.[^\"\\\\]*+)*+\"|'[^'\\\\]*+(?:\\\\.[^'\\\\]*+)*+'";
        String COMMENT_PATTERN = "\\Q//\\E[^\\n]*|\\Q/*\\E[\\s\\S]*?\\Q*/\\E";
        String EXTERNAL_PATTERN = "\\b(" + String.join("|", EXTERNAL) + ")\\b";
        String DECLARATION_PATTERN = "\\b(" + String.join("|", DECLARATION) + ")\\b";
        String PRIMITIVE_TYPE_PATTERN = "\\b(" + String.join("|", PRIMITIVE_TYPE) + ")\\b";
        String LIBRARY_TYPE_PATTERN = "\\b(" + String.join("|", LIBRARY_TYPE) + ")\\b";
        String MODIFIER_PATTERN = "\\b(" + String.join("|", MODIFIER) + ")\\b";
        String FLOW_PATTERN = "\\b(" + String.join("|", FLOW) + ")\\b";
        String MEMORY_PATTERN = "\\b(" + String.join("|", MEMORY) + ")\\b";
        String SPECIAL_PATTERN = "\\b(" + String.join("|", SPECIAL) + ")\\b";

        Pattern pattern = Pattern.compile(
                "(?<STRING>" + STRING_PATTERN + ")"
                + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                + "|(?<EXTERNAL>" + EXTERNAL_PATTERN + ")"
                + "|(?<DECLARATION>" + DECLARATION_PATTERN + ")"
                + "|(?<PRIMITIVETYPE>" + PRIMITIVE_TYPE_PATTERN + ")"
                + "|(?<LIBRARYTYPE>" + LIBRARY_TYPE_PATTERN + ")"
                + "|(?<MODIFIER>" + MODIFIER_PATTERN + ")"
                + "|(?<FLOW>" + FLOW_PATTERN + ")"
                + "|(?<MEMORY>" + MEMORY_PATTERN + ")"
                + "|(?<SPECIAL>" + SPECIAL_PATTERN + ")"
        );
        return pattern;
    }

    @Override
    public String getStyleClass(Matcher matcher) {
        return matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("EXTERNAL") != null ? "external"
                : matcher.group("DECLARATION") != null ? "declaration"
                : matcher.group("PRIMITIVETYPE") != null ? "primitive-type"
                : matcher.group("LIBRARYTYPE") != null ? "library-type"
                : matcher.group("MODIFIER") != null ? "modifier"
                : matcher.group("FLOW") != null ? "flow"
                : matcher.group("MEMORY") != null ? "memory"
                : matcher.group("SPECIAL") != null ? "special"
                : null;
    }

    @Override
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywordList = new ArrayList<>();
        keywordList.addAll(Arrays.asList(EXTERNAL));
        keywordList.addAll(Arrays.asList(DECLARATION));
        keywordList.addAll(Arrays.asList(PRIMITIVE_TYPE));
        keywordList.addAll(Arrays.asList(LIBRARY_TYPE));
        keywordList.addAll(Arrays.asList(MODIFIER));
        keywordList.addAll(Arrays.asList(FLOW));
        keywordList.addAll(Arrays.asList(MEMORY));
        keywordList.addAll(Arrays.asList(SPECIAL));
        Collections.sort(keywordList);
        return keywordList;
    }

}
