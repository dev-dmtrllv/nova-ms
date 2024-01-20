package net.server.services;
/**
 * @author Ronan
 */
public interface ServiceType<T extends Enum<?>> {
    Service createService();
    int ordinal();
    T[] enumValues();
}
