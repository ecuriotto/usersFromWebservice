package org.enrico.code;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.gson.Gson;

/*
 * 
 * Provides a list of users
 * 
 * @Author Enrico
 * 
 * 
 */
public class UserClient extends UserClientAbstract {

  private static final String REST_URI_PAGE_1 = "https://reqres.in/api/users?page=1;";

  /*
   * Execute getFirstPage method
   */
  public static void main(String args[]) {
    UserClient userClient = new UserClient();
    try {
      List<User> userList = userClient.getFirstPage();
      userList.forEach(user -> System.out.println(user));

      // Additional tests
      Map<String, Long> resultGroupBy = userList.stream()
          .collect(Collectors.groupingBy(User::getFirstName, Collectors.counting()));
      System.out.println(resultGroupBy);
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


}
