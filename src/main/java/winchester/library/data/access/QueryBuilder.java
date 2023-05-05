package winchester.library.data.access;

/**
 * A combination of a factory and builder to encapsulate the creation of a SQL Query.
 * <p>
 * An example of the use of this class can be seen below.
 * <pre><code>
 *     String sql = QueryBuilder.createQuery(QueryType.GET_AND_FILTER)
 *          .select("film_id", "item_subtype_id", "copies_available")
 *          .from("library.films", "library.item_copies")
 *          .where("library.films.film_id = library.item_copies.item_id")
 *          .orderBy("title asc")
 *          .toString();
 * </code></pre>
 */
public class QueryBuilder {

    private String tables;
    private String columns;
    private String conditions;
    private String orderedColumns;
    private String values;
    private final QueryType type;

    /**
     * A private QueryBuilder to abstract the creation of the query.
     * @param type specifies the type based on the QueryType enumeration.
     */
    private QueryBuilder(QueryType type) {
        this.type = type;
    }

    /**
     * A factory method to create an instance of the QueryBuilder class, specifying the type.
     * @param type the type of sql query based on the QueryType enumeration.
     * @return the new instance of the QueryType class to allow the chaining of methods.
     */
    public static QueryBuilder createQuery(QueryType type) {
        return new QueryBuilder(type);
    }

    /**
     * A method to create a select part of the sql query.
     * @param columns the table columns to be included in the query.
     * @return the current instance of the QueryType class to allow the chaining of methods.
     */
    public QueryBuilder select(String... columns) {
        this.columns = this.convertToSingleString(columns, ", ");
        return this;
    }

    /**
     * A method to create a from part of the sql query.
     * @param tables the tables to be included in the query.
     * @return the current instance of the QueryType class to allow the chaining of methods.
     */
    public QueryBuilder from(String... tables) {
        this.tables = this.convertToSingleString(tables, ", ");
        return this;
    }

    /**
     * A method to create a from part of the sql query.
     * @param conditions the conditions to filter columns.
     * @return the current instance of the QueryType class to allow the chaining of methods.
     */
    public QueryBuilder where(String... conditions) {
        this.conditions = this.convertToSingleString(conditions, "  ");
        return this;
    }

    /**
     * A method to create the order by part of the sql query.
     * @param orderedColumns the columns to be ordered.
     * @return the current instance of the QueryType class to allow the chaining of methods.
     */
    public QueryBuilder orderBy(String... orderedColumns) {
        this.orderedColumns = this.convertToSingleString(orderedColumns, ", ");
        return this;
    }

    /**
     * A method to create the insert into part of the sql query.
     * @param table the table to be inserted to.
     * @return the current instance of the QueryType class to allow the chaining of methods.
     */
    public QueryBuilder insertInto(String table) {
        this.tables = table;
        return this;
    }

    /**
     * A method to create the insert into part of the sql query.
     * @param entity the entities to be inserted to.
     * @return the current instance of the QueryType class to allow the chaining of methods.
     */
    public QueryBuilder values(String entity) {
        this.values = entity;
        return this;
    }

    /**
     * A method to create the insert into part of the sql query.
     * @param entities multiple entities as parameters to be inserted to.
     * @return the current instance of the QueryType class to allow the chaining of methods.
     */
    public QueryBuilder values(String... entities) {
        this.values = convertToSingleString(entities, ", ");
        return this;
    }

    /**
     * A method that concatenate the set of strings specified.
     * @param tokens the list of strings to be concatenated.
     * @param separator the separator to be inserted between the strings.
     * @return the concatenated string.
     */
    private String convertToSingleString(String[] tokens, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String token : tokens) {
            stringBuilder.append(token).append(separator);
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    /**
     * Returns the sql query based on the type specified when constructing the class.
     * @return the final sql query.
     */
    @Override
    public String toString() {
        return switch (this.type) {
            case GET -> String.format(
                    this.type.getQueryFormat(), this.columns, this.tables);
            case GET_AND_FILTER -> String.format(
                    this.type.getQueryFormat(), this.columns, this.tables, this.conditions);
            case ORDERED_GET -> String.format(
                    this.type.getQueryFormat(), this.columns, this.tables, this.orderedColumns);
            case ORDERED_GET_AND_FILTER -> String.format(
                    this.type.getQueryFormat(), this.columns, this.tables, this.conditions, this.orderedColumns);
            case INSERT_ONE -> String.format(
                    this.type.getQueryFormat(), this.tables, this.values);
            default -> null;
        };
    }

}
