package winchester.library.data.model.users;

import java.util.Optional;
import winchester.library.data.access.DatabaseEntity;

/**
 * An enumeration to represent the types of users modelled within the system.
 */
@DatabaseEntity(table = "user_types")
public enum UserType {
    DEMO(0, "Demo"),
    CUSTOMER(1, "Customer"),
    STANDARD(2, "Standard"),
    ADMINISTRATOR(3, "Administrator");

    private final int identifier;
    private final String text;

    UserType(int identifier, String text) {
        this.identifier = identifier;
        this.text = text;
    }

    public static Optional<UserType> fromIdentifier(int identifier) {
        for (UserType type : UserType.values()) {
            if (type.identifier == identifier) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }

    public int getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
