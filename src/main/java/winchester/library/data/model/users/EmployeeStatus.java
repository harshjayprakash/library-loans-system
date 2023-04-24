package winchester.library.data.model.users;

import java.util.Optional;

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

    public static Optional<EmployeeStatus> fromIdentifier(int identifier) {
        for (EmployeeStatus status : EmployeeStatus.values()) {
            if (status.identifier == identifier) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return this.text;
    }
}
