package winchester.library.service;

/**
 * A class that provides an abstraction to output to the console.
 */
public final class Logger {

    private static Logger instance = null;
    private boolean enabled;
    private boolean whereEnabled;

    /**
     * A private constructor to prevent multiple instances.
     */
    private Logger() {
        this.enabled = true;
        this.whereEnabled = false;
    }

    /**
     * A static accessor to retrieve the single instance of the Logger class.
     * @return the instance of the class.
     */
    public static Logger getInstance() {
        if (Logger.instance == null) {
            Logger.instance = new Logger();
        }
        return Logger.instance;
    }

    /**
     * A static accessor to retrieve whether outputting to the console is enabled.
     * @return if the logger is enabled.
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * A mutator to set whether logger messages should be enabled.
     * @param enabled if the logger is enabled.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * A mutator to set whether logger prints the 'where' part of the statement.
     * This should be disabled at production to prevent exposing the program's architecture.
     * @param enabled if the where part of error messages should be enabled.
     */
    public void setWhereEnabled(boolean enabled) {
        this.whereEnabled = enabled;
    }

    /**
     * A method to print an error message with the format of where (which can be toggled), context, problem and
     * a potential solution.
     * @param where where the exception occurred.
     * @param context what was being executed.
     * @param problem what is the problem.
     * @param solution what is the potential solution.
     */
    public void PrintError(String where, String context, String problem, String solution) {
        if (!this.isEnabled()) { return; }
        System.err.printf(
                """
                %sContext: %s
                Problem: %s
                Solution: %s%n
                """, (this.whereEnabled) ? String.format("Where: %s%n", where) : "", context, problem, solution);
    }

}
