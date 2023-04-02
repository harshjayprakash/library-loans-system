package winchester.library.impl.users;

public enum EmployeeStatus {
    DISABLED(-1, "Disabled"),
    NOT_APPROVED(0, "Not Approved"),
    ACTIVE(1, "Active");

    private final int identifier;
    private final String text;

    EmployeeStatus(int identifier, String text) {
        this.identifier = identifier;
        this.text = text;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
