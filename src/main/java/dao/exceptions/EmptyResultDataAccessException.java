package dao.exceptions;

import dao.exceptions.DataAccessException;

public class EmptyResultDataAccessException extends DataAccessException {

    public EmptyResultDataAccessException(String message) {
        super(message);
    }
}
