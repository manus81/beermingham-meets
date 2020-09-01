package com.beermingham.meets.application.exception;

public class ForbiddenException extends ServiceException {

	private static final long serialVersionUID = 3238689464888520684L;

	public ForbiddenException(String message, Throwable e) {
        super(message, e);
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
