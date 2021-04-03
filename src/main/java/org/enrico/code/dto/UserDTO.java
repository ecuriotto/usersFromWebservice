package org.enrico.code.dto;

import com.google.gson.annotations.SerializedName;

public class UserDTO {

	private String id;
    private String email;

    @SerializedName("first_name")
    private String firstName;

	@SerializedName("last_name")
    private String lastName;

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
    
    public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

}
