package com.aeca.email.service.modify;

public interface AecaEmailModify<E> {

    void update(E existEntity, E updatedEntity);
}
