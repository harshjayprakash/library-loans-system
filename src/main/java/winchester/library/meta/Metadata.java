package winchester.library.meta;

/**
 * A singleton class that stores program metadata information.
 */
public final class Metadata {

    private static Metadata instance = null;
    private final String programName;
    private final String programVersion;

    /**
     * A private constructor that initialises the attributes.
     */
    private Metadata() {
        this.programName = "Winchester Library Services Technical Preview";
        this.programVersion = "Version 1.0.2";
    }

    /**
     * A static method to retrieve the single instance of the Metadata class.
     * @return the instance of the class.
     */
    public static Metadata getInstance() {
        if (Metadata.instance == null) {
            Metadata.instance = new Metadata();
        }
        return Metadata.instance;
    }

    /**
     * An accessor to retrieve the program name.
     * @return the program name.
     */
    public String getProgramName() {
        return this.programName;
    }

    /**
     * An accessor to retrieve the program version number.
     * @return the program version number.
     */
    public String getProgramVersionNumber() {
        return this.programVersion;
    }

}
