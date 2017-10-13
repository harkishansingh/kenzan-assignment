package com.kenzan.model;

public class FizzBuzz {
	private int integer;
	private FizzBuzzType type;

	public FizzBuzz(int integer, FizzBuzzType type) {
		super();
		this.integer = integer;
		this.type = type;
	}

	public int getInteger() {
		return integer;
	}

	public FizzBuzzType getType() {
		return type;
	}

	public FizzBuzz(int integer) {
		super();
		this.integer = integer;
	}

	@Override
	public String toString() {
		return "FizzBuzz [integer=" + integer + ", type=" + type.name() + "]";
	}

}
