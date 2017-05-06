package com.sternik.samujarek.entities;

public enum Status {
	GASOLINE("Gasoline"), DIESEL("Diesel"), GAS("Gas");

	public static final Status[] ALL = { GASOLINE, DIESEL, GAS };
	private final String name;

	private Status(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}