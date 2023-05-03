package winchester.library.service;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import winchester.library.data.model.items.Item;
import winchester.library.data.model.users.Customer;
import winchester.library.data.model.users.Employee;

/**
 * A class that searches through lists of entities.
 */
public class Searcher {

    public Searcher() {

    }

    public Item searchItemFromIdentifier(String identifier) {
        AtomicReference<Item> searchedItem = new AtomicReference<>();
        DataPersistenceManager.getInstance().getBooks()
                .stream()
                .filter(book -> book.getIsbn().equals(identifier))
                .forEach(searchedItem::set);
        DataPersistenceManager.getInstance().getFilms()
                .stream()
                .filter(film -> film.getIdentifier().equals(identifier))
                .forEach(searchedItem::set);
        return searchedItem.get();
    }

    public ArrayList<Customer> searchCustomers(String name) {
        ArrayList<Customer> customers = new ArrayList<>();
        for (Customer customer : DataPersistenceManager.getInstance().getCustomers()) {
            if (customer.getFullName().contains(name)) {
                customers.add(customer);
            }
        }
        return customers;
    }

    public ArrayList<Employee> searchEmployees(String name) {
        ArrayList<Employee> employees = new ArrayList<>();
        DataPersistenceManager.getInstance().getEmployees()
                .stream()
                .filter(employee -> employee.getFullName().contains(name))
                .forEach(employees::add);
        return employees;
    }

}
