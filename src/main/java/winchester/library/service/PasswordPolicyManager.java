package winchester.library.service;

public final class PasswordPolicyManager {

    public final String passwordRequirements;

    public PasswordPolicyManager() {
        this.passwordRequirements =
                """
                Your password must:
                - be at least 8 characters long
                - contain a number (0-9)
                - contain a letter (A-Z, a-z)
                - contain a special character (. , : @ # £ $ % & * ! ? < > SPACE)
                """;
    }

    public String getPasswordRequirements() {
        return this.passwordRequirements;
    }

    public boolean checkPassword() {
        return false;
    }

    private boolean checkLength() {
        return false;
    }

    private boolean checkForNumber() {
        return false;
    }

    private boolean checkForSpecialCharacter() {
        return false;
    }
}
