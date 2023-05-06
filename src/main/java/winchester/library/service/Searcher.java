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

    /**
     * The default constructor.
     */
    public Searcher() { }

    /**
     * A method to search for an Item from the identifier.
     * @param identifier the identifier of the item.
     * @return the instance of the item based on the given identifier.
     */
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

    /**
     * A method to search for a customer based on full name.
     * @param name the name to search.
     * @return an array list of customers that fit the criteria of the search.
     */
    public ArrayList<Customer> searchCustomers(String name) {
        ArrayList<Customer> customers = new ArrayList<>();
        DataPersistenceManager.getInstance().getCustomers()
                .stream()
                .filter(customer -> customer.getFullName().toLowerCase().contains(name.toLowerCase()))
                .forEach(customers::add);
        return customers;
    }

    /**
     * A method to search for an employee based on full name.
     * @param name the name to search.
     * @return an array list of employees that fit the criteria of the search.
     */
    public ArrayList<Employee> searchEmployees(String name) {
        ArrayList<Employee> employees = new ArrayList<>();
        DataPersistenceManager.getInstance().getEmployees()
                .stream()
                .filter(employee -> employee.getFullName().toLowerCase().contains(name.toLowerCase()))
                .forEach(employees::add);
        return employees;
    }

}
