package winchester.library.service;

import java.util.ArrayList;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;

/**
 * A class to generate identifiers for entities.
 */
public class IdentifierGenerator {

    /**
     * The default constructor.
     */
    public IdentifierGenerator() { }

    /**
     * A method to generate a new user's identifier for the data source without conflicts.
     * @return the new identifier.
     */
    public int generateForUser() {
        ArrayList<Employee> employees = DataPersistenceManager.getInstance().getEmployees();
        ArrayList<Customer> customers = DataPersistenceManager.getInstance().getCustomers();
        int newIdentifier = 0;
        for (Employee employee : employees) { newIdentifier = Math.max(newIdentifier, employee.getIdentifier()); }
        for (Customer customer: customers) { newIdentifier = Math.max(newIdentifier, customer.getIdentifier()); }
        return ++newIdentifier;
    }

}
