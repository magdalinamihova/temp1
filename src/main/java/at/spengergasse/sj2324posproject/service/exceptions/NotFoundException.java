package at.spengergasse.sj2324posproject.service.exceptions;

import lombok.Getter;

@Getter
public abstract class NotFoundException extends RuntimeException {

    protected Object key;
    protected Class clazz;

    public NotFoundException(Object key, Class clazz, String message) {
        super(message);
        this.key = key;
        this.clazz = clazz;
    }
}