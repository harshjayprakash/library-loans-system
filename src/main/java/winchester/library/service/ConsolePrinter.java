package winchester.library.service;

public class ConsolePrinter {

    private static ConsolePrinter instance = null;
    private boolean enabled;
    private boolean exceptionMessagesEnabled;

    private ConsolePrinter() {
        this.enabled = true;
        this.exceptionMessagesEnabled = false;
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

    public boolean isExceptionMessagesEnabled() {
        return this.exceptionMessagesEnabled;
    }

    public void setExceptionMessagesEnabled(boolean enabled) {
        this.exceptionMessagesEnabled = enabled;
    }

    public void WriteLine(String message) {
        System.out.println(message);
    }

    public void WriteLineError(String message, String javaMessage) {
        if (this.exceptionMessagesEnabled) {
            System.err.printf("%s: %s%n", message, javaMessage);
        }
        else {
            this.WriteLineError(message);
        }
    }

    public void WriteLineError(String message) {
        System.err.println(message);
    }

}
