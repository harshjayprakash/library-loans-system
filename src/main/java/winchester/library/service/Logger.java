package winchester.library.service;

/**
 * A class that provides an abstraction to output to the console.
 */
public final class Logger {

    private static Logger instance = null;
    private boolean enabled;

    private Logger() {
        this.enabled = true;
    }

    public static Logger getInstance() {
        if (Logger.instance == null) {
            Logger.instance = new Logger();
        }
        return Logger.instance;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void PrintError(String context, String problem, String solution) {
        if (!this.isEnabled()) { return; }
        System.err.printf(
                """
                Context: %s
                Problem: %s
                Solution: %s%n
                """, context, problem, solution);
    }

}
