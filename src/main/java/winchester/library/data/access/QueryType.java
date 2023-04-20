package winchester.library.data.access;

/**
 * A set of values to help set up a sql query.
 */
public enum QueryType {
    GET("SELECT %s FROM %s;"),
    GET_AND_FILTER("SELECT %s FROM %s WHERE %s;"),
    INSERT_ONE("INSERT INTO %s VALUE %s;"),
    INSERT_MANY("INSERT INTO %s VALUES %s;"),
    UPDATE("UPDATE %s SET %s;"),
    UPDATE_AND_FILTER("UPDATE %s SET %s WHERE %s;");

    private final String queryFormat;

    QueryType(String queryFormat) {
        this.queryFormat = queryFormat;
    }

    public String getQueryFormat() {
        return this.queryFormat;
    }
}
