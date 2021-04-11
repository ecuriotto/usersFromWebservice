package org.enrico.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

/*
 * 
 * Provides a list of users with Jersey
 * 
 * @Author Enrico
 * 
 * 
 */

public class UserClientJersey extends UserClientAbstract {

  private static final String REST_URI_PAGE_1 = "https://reqres.in/api/users?page=1;";

  public static void main(String[] args) {
    UserClientJersey userClient = new UserClientJersey();
    try {
      List<User> userList = userClient.getFirstPage();
      userList.forEach(user -> System.out.println(user));
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

  }

  public List<User> getFirstPage() throws IOException, InterruptedException {
    List<User> userList = new ArrayList<User>();
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target(REST_URI_PAGE_1);
    Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
    PageDTO page = invocationBuilder.get(PageDTO.class);
    List<UserDTO> userDTOList = page.getData();
    userList = userDTOList.stream().map(this::convertToUser).collect(Collectors.toList());
    return userList;
  }


}
