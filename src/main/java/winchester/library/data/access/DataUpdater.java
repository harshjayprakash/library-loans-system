package winchester.library.data.access;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Employee;
import winchester.library.data.model.users.EmployeeStatus;

/**
 * A class that abstracts the method of updating a record within the database.
 */
public final class DataUpdater {

    private final DatabaseCredentials credentials;

    /**
     * The default constructor.
     */
    public DataUpdater() {
        this.credentials = DatabaseCredentials.getInstance();
    }

    /**
     * A method to update the employee status in the database.
     * @param employee the employee to be updated.
     * @param status the status of the employee.
     * @return a DatabaseConstant of UPDATE_SUCCESSFUL or UPDATE_ERROR.
     */
    public DatabaseConstant updateEmployeeStatus(Employee employee, EmployeeStatus status) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> updateResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.UPDATE_AND_FILTER)
                        .update("library.employees")
                        .set(String.format("status_id = %d", status.getIdentifier()))
                        .where(String.format("user_id = %d", employee.getIdentifier())));
        if (updateResult.isEmpty()) { return DatabaseConstant.UPDATE_ERROR; }
        connection.close();
        return DatabaseConstant.UPDATE_SUCCESSFUL;
    }

    /**
     * A method to mark the loan as returned in the database.
     * @param loan the loan to be updated.
     * @return a DatabaseConstant of UPDATE_SUCCESSFUL or UPDATE_ERROR.
     */
    public DatabaseConstant returnLoan(Loan loan) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> updateResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.UPDATE_AND_FILTER)
                        .update("library.loans")
                        .set("returned = 1")
                        .where(String.format("loan_id = %d", loan.getIdentifier())));
        if (updateResult.isEmpty()) { return DatabaseConstant.UPDATE_ERROR; }
        connection.close();
        return DatabaseConstant.UPDATE_SUCCESSFUL;
    }

    /**
     * A method to update the due date on the loan, syncing it to the database.
     * @param loan the loan to be updated.
     * @return a DatabaseConstant of UPDATE_SUCCESSFUL or UPDATE_ERROR.
     */
    public DatabaseConstant updateDueDateOnLoan(Loan loan) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> updateResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.UPDATE_AND_FILTER)
                        .update("library.loans")
                        .set(String.format("return_date = '%s'",
                                loan.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                        .where(String.format("loan_id = %d", loan.getIdentifier())));
        if (updateResult.isEmpty()) { return DatabaseConstant.UPDATE_ERROR; }
        connection.close();
        return DatabaseConstant.UPDATE_SUCCESSFUL;
    }

    /**
     * A method to update the employee's password, syncing it to the database.
     * @param employee the employee to be updated.
     * @return a DatabaseConstant of UPDATE_SUCCESSFUL or UPDATE_ERROR.
     */
    public DatabaseConstant updateEmployeePassword(Employee employee) {
        DatabaseConnection connection = new DatabaseConnection();
        connection.establish(this.credentials);
        Optional<Integer> updateResult = connection.executeUpdate(
                QueryBuilder.createQuery(QueryType.UPDATE_AND_FILTER)
                        .insertInto("library.employees")
                        .set(String.format("hashed_password = '%s'", employee.getHashedPassword()))
                        .where(String.format("user_id = %d", employee.getIdentifier())));
        if (updateResult.isEmpty()) { return DatabaseConstant.UPDATE_ERROR; }
        connection.close();
        return DatabaseConstant.UPDATE_SUCCESSFUL;
    }

}
