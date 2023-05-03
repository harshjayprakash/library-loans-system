package winchester.library.data.access;

/**
 * A combination of a factory and builder to create a sql query.
 */
public class QueryBuilder {

    private String tables;
    private String columns;
    private String conditions;
    private String orderedColumns;
    private String values;
    private final QueryType type;

    private QueryBuilder(QueryType type) {
        this.type = type;
    }

    public static QueryBuilder createQuery(QueryType type) {
        return new QueryBuilder(type);
    }

    public QueryBuilder select(String... columns) {
        this.columns = this.convertToSingleString(columns, ", ");
        return this;
    }

    public QueryBuilder from(String... tables) {
        this.tables = this.convertToSingleString(tables, ", ");
        return this;
    }

    public QueryBuilder where(String... conditions) {
        this.conditions = this.convertToSingleString(conditions, "  ");
        return this;
    }

    public QueryBuilder orderBy(String... orderedColumns) {
        this.orderedColumns = this.convertToSingleString(orderedColumns, ", ");
        return this;
    }

    public QueryBuilder insertInto(String table) {
        this.tables = table;
        return this;
    }

    public QueryBuilder values(String entity) {
        this.values = entity;
        return this;
    }

    public QueryBuilder values(String... entities) {
        this.values = convertToSingleString(entities, ", ");
        return this;
    }
    
    private String convertToSingleString(String[] tokens, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String token : tokens) {
            stringBuilder.append(token).append(separator);
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

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
