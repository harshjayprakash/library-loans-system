package winchester.library.data.access;

public class SQLQueryBuilder {

    private String tables;
    private String columns;
    private String conditions;

    private SQLQueryBuilder() {

    }

    public static SQLQueryBuilder createSQLQuery() {
        return new SQLQueryBuilder();
    }

    public SQLQueryBuilder fromColumns(String... columns) {
        this.columns = this.buildString(columns, ", ");
        return this;
    }

    public SQLQueryBuilder fromTables(String... tables) {
        this.tables = this.buildString(tables, ", ");
        return this;
    }

    public SQLQueryBuilder setConditions(String... conditions) {
        this.conditions = this.buildString(conditions, "  ");
        return this;
    }

    public String get() {
        String output = String.format(
                "SELECT %s FROM %s", this.columns, this.tables);
        if (!this.conditions.isEmpty()) {
            output += String.format(" WHERE %s", this.conditions);
        }
        return output + ";";
    }

    private String buildString(String[] tokens, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String token : tokens) {
            stringBuilder.append(token).append(separator);
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

}
