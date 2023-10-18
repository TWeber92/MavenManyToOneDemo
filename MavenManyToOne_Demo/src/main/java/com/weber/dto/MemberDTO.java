package com.weber.dto;

public class MemberDTO {
	
	private String firstName;
	private String lastName;
	private String memberId;
	
	public MemberDTO() {
		
	}
	
	public MemberDTO(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "MemberDTO [firstName=" + firstName + ", lastName=" + lastName + ", memberId=" + memberId + "]";
	}

}
