package org.enrico.code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

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

  @JsonProperty("first_name")
  public String getFirstName() {
    return firstName;
  }

  @JsonProperty("last_name")
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
