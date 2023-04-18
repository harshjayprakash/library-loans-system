package winchester.library.meta;

public final class Metadata {

    private static Metadata instance;
    private final String programName;
    private final String programVersion;

    private Metadata() {
        this.programName = "Winchester Library Services Technical Preview";
        this.programVersion = "::meta::Metadata->programVersion";
    }

    public static Metadata getInstance() {
        if (Metadata.instance == null) {
            Metadata.instance = new Metadata();
        }
        return Metadata.instance;
    }

    public String getProgramName() {
        return this.programName;
    }

    public String getProgramVersionNumber() {
        return this.programVersion;
    }

}
