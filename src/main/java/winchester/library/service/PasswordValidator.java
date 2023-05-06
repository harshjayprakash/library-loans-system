package winchester.library.service;

/**
 * A class that checks password strength.
 */
public final class PasswordValidator {

    public final String passwordRequirements;
    private final int passwordLength;

    /**
     * The default constructor that internally sets the password length and requirements text.
     */
    public PasswordValidator() {
        this.passwordLength = 8;
        this.passwordRequirements = String.format(
                """
                Your password must:
                - be at least %d characters long
                - contain a number (0-9)
                - contain a letter (A-Z, a-z)
                - contain a special character (. , : @ # £ $ & * ! ? < > SPACE)
                """, this.passwordLength);
    }

    /**
     * An accessor that gets the multiline string of the password requirements.
     * @return the password requirements.
     */
    public String getPasswordRequirements() {
        return this.passwordRequirements;
    }

    /**
     * A method to check if all password requirements have been met.
     * @param password the password to be checked.
     * @return true if all password requirements have been met.
     */
    public boolean meetsAllRequirements(String password) {
        return meetsLengthRequirement(password) && meetsNumberRequirement(password)
                && meetsLetterRequirement(password) && meetsSpecialCharacterRequirements(password);
    }

    /**
     * A method to check if the password meets the length requirement.
     * @param password the password to be checked.
     * @return true if the password meets the requirement.
     */
    public boolean meetsLengthRequirement(String password) {
        return password.length() >= this.passwordLength;
    }

    /**
     * A method to check if the password meets the requirement of including a number.
     * @param password the password to be checked.
     * @return true if the password includes a number.
     */
    public boolean meetsNumberRequirement(String password) {
        for (int i = 0; i < 10; i++) {
            if (password.contains(String.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * A method to check if the password meets the requirement of including a letter.
     * @param password the password to be checked.
     * @return true if the password includes a letter.
     */
    public boolean meetsLetterRequirement(String password) {
        for (char character : password.toCharArray()) {
            if (Character.isLetter(character)) {
                return true;
            }
        }
        return false;
    }

    /**
     * A method to check if the password meets the requirement of including a special character.
     * @param password the password to be checked.
     * @return true if the password includes a special character.
     */
    public boolean meetsSpecialCharacterRequirements(String password) {
        for (char character : password.toCharArray()) {
            if (switch (character) {
                case '.', ',', ':', '@', '#', '£', '$', '&', '*', '!', '?', '<', '>', ' ' -> true;
                default -> false;
            }) {
                return true;
            }
        }
        return false;
    }
}
