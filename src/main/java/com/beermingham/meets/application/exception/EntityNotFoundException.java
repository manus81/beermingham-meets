package com.beermingham.meets.application.exception;

public class EntityNotFoundException extends ServiceException {

	private static final long serialVersionUID = -5783404370467129656L;

	public EntityNotFoundException(String message, Throwable e) {
        super(message, e);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
