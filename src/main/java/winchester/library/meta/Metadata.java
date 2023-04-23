package winchester.library.meta;

public final class Metadata {

    private static Metadata instance;
    private final String programName;
    private final String programVersion;

    private Metadata() {
        this.programName = "Winchester Library Services [EAP]";
        this.programVersion = "Version 1.0.2";
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
