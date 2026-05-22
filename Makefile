JAVA_SHOWCASE = JavaLanguageShowcase
JAVA_ONLY = JavaOnlyFeatures
SCALA_SHOWCASE = ScalaLanguageShowcase
SCALA_ONLY = ScalaOnlyFeatures
SCALA_CAVEATS_DIR = src/scala/caveats
SCALA_CAVEAT_REPRO = ScalaAsyncInitRepro
SCALA_CAVEAT_SAFE = ScalaAsyncInitSafe

.PHONY: all java scala java-only scala-only scala-caveat-repro scala-caveat-safe run-all compile clean help

all: run-all

help:
	@echo "Available targets:"
	@echo "  make java         - Compile and run JavaLanguageShowcase"
	@echo "  make scala        - Compile and run ScalaLanguageShowcase"
	@echo "  make java-only    - Compile and run JavaOnlyFeatures"
	@echo "  make scala-only   - Compile and run ScalaOnlyFeatures"
	@echo "  make scala-caveat-repro - Compile and run failing async init caveat example"
	@echo "  make scala-caveat-safe  - Compile and run safe async init example"
	@echo "  make run-all      - Run all examples"
	@echo "  make compile      - Compile all examples"
	@echo "  make clean        - Remove compiled class/tasty files"

java:
	javac $(JAVA_SHOWCASE).java
	java $(JAVA_SHOWCASE)

scala:
	scalac $(SCALA_SHOWCASE).scala
	scala $(SCALA_SHOWCASE)

java-only:
	javac $(JAVA_ONLY).java
	java $(JAVA_ONLY)

scala-only:
	scalac $(SCALA_ONLY).scala
	scala $(SCALA_ONLY)

scala-caveat-repro:
	scalac $(SCALA_CAVEATS_DIR)/$(SCALA_CAVEAT_REPRO).scala
	scala $(SCALA_CAVEAT_REPRO)

scala-caveat-safe:
	scalac $(SCALA_CAVEATS_DIR)/$(SCALA_CAVEAT_SAFE).scala
	scala $(SCALA_CAVEAT_SAFE)

sum:
	cc -o sum sum.c
	@echo "Running sum program with"
	./sum

compile:
	javac $(JAVA_SHOWCASE).java $(JAVA_ONLY).java
	scalac $(SCALA_SHOWCASE).scala $(SCALA_ONLY).scala

run-all: java scala java-only scala-only sum

clean:
	rm -f *.class *.tasty sum
