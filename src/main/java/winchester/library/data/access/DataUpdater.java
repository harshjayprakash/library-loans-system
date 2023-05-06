package winchester.library.data.access;

import java.util.Optional;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.EmployeeStatus;

/**
 * A class that abstracts the method of updating a record within the database.
 */
public class DataUpdater {

    private final DatabaseCredentials credentials;

    /**
     * The default constructor.
     */
    public DataUpdater() {
        this.credentials = DatabaseCredentials.getInstance();
    }

    public DatabaseConstant updateEmployeeStatus(Employee employee, EmployeeStatus status) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> updateResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.UPDATE_AND_FILTER)
                        .update("library.employees")
                        .set(String.format("status_id = %d", status.getIdentifier()))
                        .where(String.format("user_id = %d", employee.getIdentifier())));
        if (updateResult.isEmpty()) {
            return DatabaseConstant.UPDATE_ERROR;
        }
        connection.close();
        return DatabaseConstant.UPDATE_SUCCESSFUL;
    }

}
