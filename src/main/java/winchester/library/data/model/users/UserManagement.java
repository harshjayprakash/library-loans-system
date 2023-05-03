package winchester.library.data.model.users;

/**
 * An interface to allow user management within the system.
 */
public interface UserManagement {
    void approveUser(Employee employee);
    void disableUser(Employee employee);
}
