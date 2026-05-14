import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class JavaLanguageShowcase {

    public static void main(String[] args) {
        System.out.println("Java Essentials: language feature showcase");
        // 1) Variable declaration and local type inference.
        variablesAndTypeInference();
        // 2) Data modeling with classes/records.
        oopWithRecord();
        // 3) Null-safety approach using Optional.
        nullSafetyWithOptional();
        // 4) Functional operations over collections.
        functionalCollections();
        // 5) Type checks with pattern matching style.
        patternMatchingWithInstanceof();
        // 6) Built-in async with CompletableFuture.
        asyncProgramming();
        // 7) Checked exception flow.
        errorHandling();
        // 8) Mutable vs immutable collection handling.
        collectionsMutability();
        // 9) Interface-based behavior abstraction.
        extensionPointWithInterface();
    }

    private static void variablesAndTypeInference() {
        // Java uses explicit immutability with final.
        final String immutableByConvention = "Java supports local type inference using var";
        // var keeps strong static typing while reducing verbosity.
        var words = List.of("Java", "Scala", "Kotlin");
        System.out.println("\n1) Variables and type inference");
        System.out.println("Immutable text: " + immutableByConvention);
        System.out.println("Words: " + words);
    }

    private static void oopWithRecord() {
        // Record is a concise data carrier with generated boilerplate.
        UserRecord user = new UserRecord("Ava", 31);
        System.out.println("\n2) OOP model with concise data holder (record)");
        System.out.println("User: " + user);
    }

    private static void nullSafetyWithOptional() {
        // Optional helps model absence instead of using null directly.
        Optional<String> email = Optional.of("ava@example.com");
        String normalized = email.map(String::toLowerCase).orElse("unknown@example.com");
        System.out.println("\n3) Null handling via Optional");
        System.out.println("Normalized email: " + normalized);
    }

    private static void functionalCollections() {
        List<Integer> scores = Arrays.asList(7, 1, 5, 10, 9);
        // Stream pipelines provide map/filter/reduce style transforms.
        List<Integer> transformed = scores.stream()
            .filter(n -> n >= 5)
            .sorted(Comparator.reverseOrder())
            .map(n -> n * 10)
            .collect(Collectors.toList());

        // Collectors convert stream output into target structures.
        Map<String, Integer> lengths = List.of("java", "scala").stream()
            .collect(Collectors.toMap(word -> word, String::length));

        System.out.println("\n4) Functional style with streams");
        System.out.println("Transformed scores: " + transformed);
        System.out.println("Word lengths: " + lengths);
    }

    private static void patternMatchingWithInstanceof() {
        Object input = 42;
        String description = describeValue(input);
        System.out.println("\n5) Pattern matching style checks");
        System.out.println("Description: " + description);
    }

    private static String describeValue(Object value) {
        // Pattern variables simplify cast + type-check in one step.
        if (value instanceof Integer number && number > 0) {
            return "positive integer: " + number;
        }
        if (value instanceof String text) {
            return "string of size " + text.length();
        }
        return "unknown";
    }

    private static void asyncProgramming() {
        // CompletableFuture composes asynchronous steps.
        CompletableFuture<String> greetingFuture = CompletableFuture
            .supplyAsync(() -> "hello")
            .thenApply(text -> text + " from CompletableFuture");
        System.out.println("\n6) Async programming");
        System.out.println("Async result: " + greetingFuture.join());
    }

    private static void errorHandling() {
        System.out.println("\n7) Checked exception model");
        try {
            parsePositiveInt("123");
            parsePositiveInt("-7");
        } catch (InvalidValueException ex) {
            System.out.println("Handled checked exception: " + ex.getMessage());
        }
    }

    private static int parsePositiveInt(String input) throws InvalidValueException {
        // Java signatures encode checked exception contracts.
        int value = Integer.parseInt(input);
        if (value <= 0) {
            throw new InvalidValueException("Expected a positive number but got " + value);
        }
        return value;
    }

    private static void collectionsMutability() {
        // Java collections are often mutable unless wrapped/copied.
        var mutable = new java.util.ArrayList<>(List.of("A", "B"));
        mutable.add("C");
        // List.copyOf creates an immutable snapshot.
        var immutable = List.copyOf(mutable);
        System.out.println("\n8) Mutable vs immutable collections");
        System.out.println("Mutable list: " + mutable);
        System.out.println("Immutable copy: " + immutable);
    }

    private static void extensionPointWithInterface() {
        // Functional interfaces allow lambda-based behavior injection.
        Greeter greeter = name -> "Hello, " + name + "!";
        System.out.println("\n9) Behavior abstraction with interfaces");
        System.out.println(greeter.greet("Scala developer"));
    }
}

record UserRecord(String name, int age) {}

@FunctionalInterface
interface Greeter {
    String greet(String name);
}

class InvalidValueException extends Exception {
    InvalidValueException(String message) {
        super(message);
    }
}
