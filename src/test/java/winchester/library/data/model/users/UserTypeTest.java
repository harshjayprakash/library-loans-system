package winchester.library.data.model.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTypeTest {

    @Test
    void fromIdentifier() {
        Assertions.assertEquals(UserType.fromIdentifier(1).orElse(null), UserType.CUSTOMER);
        Assertions.assertEquals(UserType.fromIdentifier(2).orElse(null), UserType.STANDARD);
        Assertions.assertEquals(UserType.fromIdentifier(3).orElse(null), UserType.ADMINISTRATOR);
    }

    @Test
    void getIdentifier() {
        Assertions.assertEquals(UserType.CUSTOMER.getIdentifier(), 1);
        Assertions.assertEquals(UserType.STANDARD.getIdentifier(), 2);
        Assertions.assertEquals(UserType.ADMINISTRATOR.getIdentifier(), 3);
    }

}