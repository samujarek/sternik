package com.sternik.samujarek.repositories;

public class NoSuchBusException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchBusException(String string) {
		super(string);
	}

	public NoSuchBusException() {
	}
}