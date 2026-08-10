# SyntaxTextAreaFX

A JavaFX code editor with syntax highlighting, built on
[RichTextFX](https://github.com/FXMisc/RichTextFX). Used by the SIPS
workbench, and usable on its own.

## Features

* Syntax highlighting for **120 of the 121 languages** `LANGS` lists —
  keywords, strings and comments, at minimum; a handful of hand-written
  languages (Java, C/C++, JavaScript, ...) get finer-grained rules on top.
  See [Supported Languages](https://github.com/deepsidhu1313/SyntaxTextAreaFX/wiki/Supported-Languages#supported-languages)
  for the full list, or call `SyntaxTextAreaFX.implementedLanguages()` at
  runtime.
* Language selection either by name (`setCodingStyle(LANGS.python)`) or by
  guessing from a file name (`languageForFile("script.py")`).
* Theming via CSS: a built-in default theme, plus `addStyleSheet(String)` to
  layer your own style sheet on top.
* Keyword autocomplete: typing two or more letters of a keyword pops up
  matching completions for the current language, sourced from that
  language's `getKeywords()`.

## Requirements

* Java 21+
* JavaFX 21 (pulled in transitively as a Maven dependency — no separate SDK
  install needed)

## Usage

```java
SyntaxTextAreaFX editor = new SyntaxTextAreaFX();
editor.setCodingStyle(SyntaxTextAreaFX.CONSTANTS.LANGS.java);
someContainer.getChildren().add(editor.getNode());
```

Not every language in `LANGS` has highlighting rules written for it yet —
check first if you're populating a language picker:

```java
if (SyntaxTextAreaFX.isLanguageImplemented(LANGS.rust)) {
    editor.setCodingStyle(LANGS.rust);
}
```

An unimplemented language falls back to plain text rather than failing.

## Building from source

```bash
mvn package   # runs the test suite and produces target/SyntaxTextAreaFX-<version>.jar
mvn install   # same, plus installs the jar to your local Maven repository
```

## Adding a language

Highlighting rules live in `src/main/java/.../langs/`, one class per
language, implementing the `Language` interface (`meta/Language.java`).
Most were generated from gtksourceview's language definitions
(`jsons/*.json`) by the dev tool at `meta/Generator.java` — see its class
Javadoc for the generate-review-wire workflow. A new language needs an
import, a `case` in `SyntaxTextAreaFX#loadLanguage()`, and an entry in its
`IMPLEMENTED` set.

## TO DO

* Autocomplete is keyword-list-based only — it doesn't know it's inside a
  string or comment, or what's actually in scope. Real context-aware
  completion needs an actual parser per language, not the regex-pattern
  architecture this project has; a much larger undertaking than the
  keyword popup.
* `LANGS.j` has no highlighting rules — its keyword definitions embed
  gtksourceview template syntax that isn't valid regex on its own
