package com.weber.model;

public class Member {
	private String firstName;
	private String lastName;
	private int memberId;

	public Member() {

	}

	public Member(String firstName, String lastName, int memberId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "firstName=" + firstName + ", lastName=" + lastName + ", memberId=" + memberId;
	}

}
