package winchester.library.data.access;

import winchester.library.data.model.items.Item;
import winchester.library.data.model.loans.Loan;
import winchester.library.data.model.users.Employee;

import java.util.Optional;

public class SQLQuery {

    private String tables;
    private String columns;
    private String conditions;
    private String values;
    private final SQLQueryType type;

    private SQLQuery(SQLQueryType type) {
        this.type = type;
    }

    public static SQLQuery createQuery(SQLQueryType type) {
        return new SQLQuery(type);
    }

    public SQLQuery select(String... column) {
        return this;
    }

    public SQLQuery from(String... table) {
        return this;
    }

    public SQLQuery where(String... condition) {
        return this;
    }

    public SQLQuery insertInto(String table) {
        return this;
    }

    public SQLQuery values(Employee... employees) {
        return this;
    }

    public SQLQuery values(Item... items) {
        return this;
    }

    public SQLQuery values(Loan... loans) {
        return this;
    }

    public Optional<String> buildQuery() {
        return Optional.empty();
    }

}
