package winchester.library.service;

import winchester.library.data.model.users.Employee;

/**
 * A class that check if a username is valid.
 */
public final class UsernameValidator {

    /**
     * The default constructor.
     */
    public UsernameValidator() { }

    /**
     * A method that checks if a username exists in the database.
     * @param username the username to be checked.
     * @return true if the username exists.
     */
    public boolean usernameExists(String username) {
        for (Employee employee : DataPersistenceManager.getInstance().getEmployees()) {
            if (employee.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

}
