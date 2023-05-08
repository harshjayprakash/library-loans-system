package winchester.library.data.access;

/**
 * A set of constant values that provide a format string to help set up a sql query.
 */
public enum QueryType {
    GET("SELECT %s FROM %s;"),
    GET_AND_FILTER("SELECT %s FROM %s WHERE %s;"),
    INSERT_ONE("INSERT INTO %s VALUE %s;"),
    ORDERED_GET("SELECT %s FROM %s ORDER BY %s"),
    ORDERED_GET_AND_FILTER("SELECT %s FROM %s WHERE %s ORDER BY %s"),
    UPDATE("UPDATE %s SET %s;"),
    UPDATE_AND_FILTER("UPDATE %s SET %s WHERE %s;");

    private final String queryFormat;

    /**
     * A constructor to provide additional information to the enumeration values such as the sql format string.
     * @param queryFormat the query format for each constant.
     */
    QueryType(String queryFormat) {
        this.queryFormat = queryFormat;
    }

    /**
     * An accessor for the query format specified as a string.
     * @return the query format.
     */
    public String getQueryFormat() {
        return this.queryFormat;
    }
}
