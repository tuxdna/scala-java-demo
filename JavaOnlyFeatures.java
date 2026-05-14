import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class JavaOnlyFeatures {

    public static void main(String[] args) {
        System.out.println("Java-only feature highlights");
        checkedExceptions();
        tryWithResources();
    }

    private static void checkedExceptions() {
        // 1) Checked exceptions are part of method signatures in Java.
        try {
            validatePositive(42);
            validatePositive(-1);
        } catch (InvalidNumberException ex) {
            System.out.println("Checked exception handled: " + ex.getMessage());
        }
    }

    private static void tryWithResources() {
        // 2) try-with-resources provides structured resource management.
        try (BufferedReader reader = new BufferedReader(new StringReader("Java\nScala"))) {
            String firstLine = reader.readLine();
            System.out.println("First line from reader: " + firstLine);
        } catch (IOException ex) {
            System.out.println("I/O problem: " + ex.getMessage());
        }
    }

    private static void validatePositive(int value) throws InvalidNumberException {
        if (value <= 0) {
            throw new InvalidNumberException("Expected a positive value, got " + value);
        }
    }
}

class InvalidNumberException extends Exception {
    InvalidNumberException(String message) {
        super(message);
    }
}
