package com.beermingham.meets.domain.exception;

public abstract class DomainException extends RuntimeException {

	private static final long serialVersionUID = -4825546453193795408L;

	public DomainException(String message, Throwable e) {
        super(message, e);
    }

    public DomainException(String message) {
        super(message);
    }
}
