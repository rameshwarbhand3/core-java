package com.ram.exception;

public class ParkingFullException extends Exception{

	@Override
	public String getMessage() {
		System.out.println("Parking Full");
		return super.getMessage();
	}

}
