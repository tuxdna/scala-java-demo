# Java vs Scala Language Showcase

This project contains two side-by-side examples that compare essential Java and Scala language features.

## Files

- `JavaLanguageShowcase.java`
- `ScalaLanguageShowcase.scala`
- `JavaOnlyFeatures.java`
- `ScalaOnlyFeatures.scala`

Both programs demonstrate the same comparison topics (type inference, null safety, pattern matching, async, error handling, and more).
The additional `JavaOnlyFeatures.java` and `ScalaOnlyFeatures.scala` files show language-specific features.

## Prerequisites

- Java JDK (for `javac` and `java`)
- Scala (for `scalac` and `scala`)

You can verify tools are installed:

```bash
javac -version
scala -version
```

## Run the Java example

From the project root:

```bash
javac JavaLanguageShowcase.java
java JavaLanguageShowcase
```

## Run the Scala example

From the project root:

```bash
scalac ScalaLanguageShowcase.scala
scala ScalaLanguageShowcase
```

## Run language-specific examples

From the project root:

```bash
javac JavaOnlyFeatures.java
java JavaOnlyFeatures

scalac ScalaOnlyFeatures.scala
scala ScalaOnlyFeatures
```

## Optional cleanup

These commands remove compiled artifacts if you want a clean directory:

```bash
rm -f *.class
rm -f *.tasty
```

## Makefile shortcuts

Use the included `Makefile` to compile, run, and clean quickly:

```bash
make help
make java
make scala
make java-only
make scala-only
make run-all
make clean
```
