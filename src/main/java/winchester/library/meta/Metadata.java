package winchester.library.meta;

public final class Metadata {

    private static final Metadata instance = new Metadata();
    private final String programName = "Winchester Library Services Technical Preview";
    private final String programVersion = "1.2.x.x";

    private Metadata() {

    }

    public static Metadata getInstance() {
        return Metadata.instance;
    }

    public String getProgramName() {
        return this.programName;
    }

    public String getProgramVersionNumber() {
        return this.programVersion;
    }

}
