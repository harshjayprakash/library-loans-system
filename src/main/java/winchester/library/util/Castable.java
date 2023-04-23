package winchester.library.util;

public interface Castable<Type, BaseType> {
    Type castFrom(BaseType object);
}
