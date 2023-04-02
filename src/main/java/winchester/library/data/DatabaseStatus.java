package winchester.library.data;

public enum DatabaseStatus {
    CONNECTED(1, "Database connected"),
    NOT_CONNECTED(2, "Database is not connected");

    private final int identifier;
    private final String value;

    DatabaseStatus(int identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
