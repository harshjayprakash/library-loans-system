package winchester.library.data.access;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A method of marking database entities with their corresponding tables in the database.
 * This can only annotate types.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface DatabaseEntity {
    /**
     * An accessor to get the tables specified.
     * @return the tables specified.
     */
    String table();
}
