package winchester.library.data.model.users;

import java.util.Optional;
import winchester.library.data.access.DatabaseEntity;

/**
 * An enumeration to represent the integer values of employee status.
 */
@DatabaseEntity(table = "employee_status")
public enum EmployeeStatus {
    DISABLED(-1, "Disabled"),
    NOT_APPROVED(0, "Not Approved"),
    ACTIVE(1, "Active");

    private final int identifier;
    private final String text;

    /**
     * A constructor to provide additional information to each constant such as a string value and identifier.
     * @param identifier an identifier that can be referred to.
     * @param text a string value corresponding to the constant.
     */
    EmployeeStatus(int identifier, String text) {
        this.identifier = identifier;
        this.text = text;
    }

    /**
     * A method to find the EmployeeStatus constant based on the identifier.
     * @param identifier the identifier of the EmployeeStatus to be found.
     * @return an optional of EmployeeStatus if it corresponds to a constant or empty if not.
     */
    public static Optional<EmployeeStatus> fromIdentifier(int identifier) {
        for (EmployeeStatus status : EmployeeStatus.values()) {
            if (status.identifier == identifier) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }

    /**
     * An accessor to retrieve the identifier corresponding to the constant.
     * @return the identifier corresponding to the constant.
     */
    public int getIdentifier() {
        return this.identifier;
    }

    /**
     * An access to retrieve the string value corresponding to the constant.
     * @return the string value corresponding to the constant.
     */
    @Override
    public String toString() {
        return this.text;
    }
}
