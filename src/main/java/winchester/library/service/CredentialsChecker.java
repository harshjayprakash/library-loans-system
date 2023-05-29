package winchester.library.service;

import java.util.Optional;
import winchester.library.data.model.users.Employee;

/**
 * A class to check login credentials.
 */
public final class CredentialsChecker {

    /**
     * The default constructor.
     */
    public CredentialsChecker() { }

    /**
     * Checks through all credentials to see whether it is correct.
     * @param username the username to be checked.
     * @param hashedPassword the password to be checked.
     * @return an optional version of employee. Returns empty if no credentials match.
     */
    public Optional<Employee> AreCredentialsCorrect(String username, String hashedPassword) {
        for (Employee employee : DataPersistenceManager.getInstance().getEmployees()) {
            if (employee.getUsername().equals(username) && employee.getHashedPassword().equals(hashedPassword)) {
                return Optional.of(employee);
            }
        }
        return Optional.empty();
    }

}
