package winchester.library.service;

import java.util.ArrayList;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;

public class IdentifierGenerator {

    public IdentifierGenerator() {

    }

    public int generateForUser() {
        ArrayList<Employee> employees = DataPersistenceManager.getInstance().getEmployees();
        ArrayList<Customer> customers = DataPersistenceManager.getInstance().getCustomers();
        Employee lastEmployee = employees.get(employees.size() - 1);
        Customer lastCustomer = customers.get(customers.size() - 1);
        return Math.max(lastCustomer.getIdentifier(), lastEmployee.getIdentifier()) + 1;
    }

}
