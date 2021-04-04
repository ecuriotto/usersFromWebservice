package org.enrico.code.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.enrico.code.dto.PageDTO;
import org.enrico.code.dto.UserDTO;
import org.enrico.code.view.User;
import com.google.gson.Gson;

/*
 * 
 * Provides a list of users
 * 
 * @Author Enrico
 * 
 * 
 */
public class UserClient {

  private static final String REST_URI_PAGE_1 = "https://reqres.in/api/users?page=1;";

  public static void main(String args[]) {
    UserClient userClient = new UserClient();
    try {
      List<User> userList = userClient.getFirstPage();
      userList.forEach(user -> System.out.println(user));
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

  }

  /*
   * Call a webservice, get a List<UserDTO>, convert them in List<User> and send back to the caller
   */
  public List<User> getFirstPage() throws IOException, InterruptedException {
    HttpClient httpClient = HttpClient.newHttpClient();
    Gson gson = new Gson();
    List<User> userList = new ArrayList<User>();
    HttpRequest httpRequest =
        HttpRequest.newBuilder().uri(URI.create(REST_URI_PAGE_1)).GET().build();
    HttpResponse<String> response =
        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    PageDTO page = gson.fromJson((String) response.body(), PageDTO.class);
    List<UserDTO> userDTOList = page.getData();
    userList = userDTOList.stream().map(this::convertToUser).collect(Collectors.toList());
    return userList;
  }

  /*
   * Converter UserDTO -> User
   */
  private User convertToUser(UserDTO userDTO) {
    return new User(userDTO.getFirstName(), userDTO.getLastName());
  }

}
