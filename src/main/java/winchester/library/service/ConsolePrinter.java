package winchester.library.service;

public class ConsolePrinter {

    private static ConsolePrinter instance = null;
    private boolean enabled;

    private ConsolePrinter() {
        this.enabled = true;
    }

    public static ConsolePrinter getInstance() {
        if (ConsolePrinter.instance == null) {
            ConsolePrinter.instance = new ConsolePrinter();
        }
        return ConsolePrinter.instance;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void WriteLine(String message) {
        System.out.println(message);
    }

    public void WriteLineError(String message, String javaMessage) {
        System.err.printf("%s: %s%n", message, javaMessage);
    }

    public void WriteLineError(String message) {
        System.err.println(message);
    }

}
