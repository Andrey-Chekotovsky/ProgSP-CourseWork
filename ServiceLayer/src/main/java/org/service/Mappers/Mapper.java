package org.service.Mappers;

public interface Mapper<T, P> {
    P map(T source);
}
