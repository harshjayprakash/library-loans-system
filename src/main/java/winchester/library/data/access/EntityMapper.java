package winchester.library.data.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import winchester.library.data.model.items.Book;
import winchester.library.data.model.items.Film;
import winchester.library.data.model.items.ItemFormat;
import winchester.library.data.model.items.ItemStock;
import winchester.library.data.model.items.ItemType;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.*;
import winchester.library.service.Logger;

/**
 * A class that has the ability to map the returned ResultSet from the database connection to the correct list of
 * objects.
 */
public class EntityMapper {

    public EntityMapper() { }

    private <T> Optional<ArrayList<T>> mapToList(ResultSet data, Function<ResultSet, T> mapFunction) {
        ArrayList<T> entityList = new ArrayList<>();
        try {
            while (data.next()) {
                T entity = mapFunction.apply(data);
                if (entity != null) {
                    entityList.add(entity);
                }
            }
            return Optional.of(entityList);
        }
        catch (NullPointerException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Mapping Data to an Array",
                    DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(),
                    "Ensure that the Result Set Contains Data");
        }
        catch (SQLException exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Mapping Data to an Array",
                    DatabaseConstant.DATABASE_NOT_ACCESSIBLE.toString(),
                    "Ensure there is an Active Connection to the Database");
        }
        catch (Exception exception) {
            Logger.getInstance().PrintError(
                    this.getClass().getName(),
                    "Mapping Data to an Array",
                    DatabaseConstant.UNKNOWN_ERROR.toString() + ": " + exception.getMessage(),
                    "^ Please find a solution to the above error");
        }
        return Optional.empty();
    }

    public Optional<ArrayList<Book>> mapAsBooks(ResultSet data) {
        return this.mapToList(data, result -> {
            try {
                return new Book(
                        result.getString("isbn"), result.getString("title"), result.getString("author"),
                        result.getInt("publication_year"), result.getString("publisher"),
                        result.getString("image_url"));
            }
            catch (SQLException exception) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Mapping an Entity to Specified Class",
                        DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(),
                        "Ensure that the column specified is valid");
                return null;
            }
        });
    }

    public Optional<ArrayList<Film>> mapAsFilms(ResultSet data) {
        return this.mapToList(data, result -> {
            try {
                return new Film(
                        result.getString("film_id"), result.getString("title"), result.getString("director"),
                        result.getInt("release_year"), result.getString("distributor"),
                        result.getInt("duration_minutes"), result.getString("image_url"));
            }
            catch (SQLException exception) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Mapping an Entity to Specified Class",
                        DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(),
                        "Ensure that the column specified is valid");
                return null;
            }
        });
    }

    public Optional<ArrayList<Customer>> mapAsCustomers(ResultSet data) {
        return this.mapToList(data, result -> {
            try {
                return new Customer(
                        result.getInt("user_id"), result.getString("first_name"),
                        result.getString("last_name"), result.getString("postal_code"));
            }
            catch (SQLException exception) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Mapping an Entity to Specified Class",
                        DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(),
                        "Ensure that the column specified is valid");
                return null;
            }
        });
    }

    public Optional<ArrayList<ItemStock>> mapAsItemStock(ResultSet data, ItemType type) {
        return this.mapToList(data, result -> {
            try {
                return new ItemStock (
                        switch (type) {
                            case BOOK -> result.getString("isbn");
                            case FILM -> result.getString("film_id");
                        },
                        ItemFormat.fromIdentifier(result.getInt("item_subtype_id")).orElse(null),
                        result.getInt("copies_available"),
                        0);
            }
            catch (SQLException exception) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Mapping an Entity to Specified Class",
                        DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(),
                        "Ensure that the column specified is valid");
                return null;
            }
        });
    }

    public Optional<ArrayList<Employee>> mapAsEmployee(ResultSet data) {
        return this.mapToList(data, result -> {
            try {
                return switch (UserType.fromIdentifier(result.getInt("user_type_id")).orElse(UserType.STANDARD)) {
                    default -> new Employee(result.getInt("user_id"),
                            result.getString("first_name"), result.getString("last_name"),
                            result.getString("postal_code"), result.getString("username"), result.getString("password"),
                            EmployeeStatus.fromIdentifier(result.getInt("status_id")).orElse(EmployeeStatus.DISABLED));
                    case ADMINISTRATOR, STANDARD -> new Administrator(result.getInt("user_id"),
                            result.getString("first_name"), result.getString("last_name"),
                            result.getString("postal_code"), result.getString("username"), result.getString("password"),
                            EmployeeStatus.fromIdentifier(result.getInt("status_id")).orElse(EmployeeStatus.DISABLED));
                };
            }
            catch (SQLException exception) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Mapping an Entity to Specified Class",
                        DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(),
                        "Ensure that the column specified is valid");
                return null;
            }
        });
    }

    public Optional<ArrayList<Loan>> mapAsLoans(ResultSet data) {
        return this.mapToList(data, result -> {
            try {
                return new Loan(
                        result.getLong("loan_id"), new Customer(result.getInt("customer_id"),
                        result.getString("first_name"), result.getString("last_name"),
                        result.getString("postal_code")), result.getString("item_id"),
                        ItemFormat.fromIdentifier(result.getInt("item_subtype_id")).orElse(null),
                        LocalDate.parse(result.getString("loan_date")), LocalDate.parse(result.getString("return_date")),
                        result.getBoolean("returned"));
            }
            catch (DateTimeParseException ignored) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Mapping Entity to Loans Class",
                        "Invalid Data Format",
                        "Ensure that the dates are in a logical format (year-month-day)");
            }
            catch (SQLException exception) {
                Logger.getInstance().PrintError(
                        this.getClass().getName(),
                        "Mapping an Entity to Specified Class",
                        DatabaseConstant.DATA_NOT_ACCESSIBLE.toString(),
                        "Ensure that the column specified is valid");
            }
            return null;
        });
    }

}
