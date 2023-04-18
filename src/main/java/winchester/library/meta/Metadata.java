package winchester.library.meta;

public final class Metadata {

    private static Metadata instance;
    private final String programName = "Winchester Library Services Technical Preview";
    private final String programVersion = "::meta::Metadata->programVersion";

    private Metadata() { }

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
