package winchester.library.service;

import winchester.library.data.model.users.Employee;

public class UsernameValidator {

    public UsernameValidator() {

    }

    public boolean usernameExists(String username) {
        for (Employee employee : DataPersistenceManager.getInstance().getEmployees()) {
            if (employee.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

}
