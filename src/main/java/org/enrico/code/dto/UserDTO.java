package org.enrico.code.dto;

import com.google.gson.annotations.SerializedName;

public final class UserDTO {

  private Long id;
  private String email;

  @SerializedName("first_name")
  private String firstName;

  @SerializedName("last_name")
  private String lastName;

  public UserDTO() {}

  public UserDTO(String firstName, String lastName, Long id, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

}
