package org.enrico.code;

import java.io.IOException;
import java.util.List;

public abstract class UserClientAbstract {

  public abstract List<User> getFirstPage() throws IOException, InterruptedException;

  protected User convertToUser(UserDTO userDTO) {
    return new User(userDTO.getFirstName(), userDTO.getLastName());
  }

}
