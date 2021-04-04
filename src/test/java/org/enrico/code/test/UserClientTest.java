package org.enrico.code.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.enrico.code.PageDTO;
import org.enrico.code.UserClient;
import org.enrico.code.UserDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.google.gson.Gson;

public class UserClientTest extends Mockito {

  private static HttpClient httpClient;
  private static HttpRequest httpRequest;
  private static HttpResponse<String> httpResponse;
  private static Gson gson;
  private static PageDTO pageDTO;
  private static UserDTO userDTO;
  private static List<UserDTO> userDTOList;
  private static int EXPECTED_NUMBER_USERS = 6;
  private static String EXPECTED_FIRST_FIRSTNAME = "George";

  @BeforeAll
  public static void init() throws IOException, InterruptedException {
    httpClient = mock(HttpClient.class);
    httpRequest = mock(HttpRequest.class);
    httpResponse = mock(HttpResponse.class);
    gson = mock(Gson.class);
    pageDTO = mock(PageDTO.class);
    userDTO = mock(UserDTO.class);
    BodyHandler<String> bodyHandler = mock(BodyHandler.class);
    when(httpClient.send(httpRequest, bodyHandler)).thenReturn(httpResponse);
    when(httpResponse.body()).thenReturn(Mockito.any(String.class));
    when(gson.fromJson(Mockito.anyString(), PageDTO.class)).thenReturn(pageDTO);
    when(pageDTO.getData()).thenReturn((ArrayList<UserDTO>) userDTOList);

  }

  @Test
  public void getFirstPage_should_return_6_users() throws IOException, InterruptedException {
    userDTOList =
        Stream.generate(UserDTO::new).limit(EXPECTED_NUMBER_USERS).collect(Collectors.toList());

    UserClient userClient = new UserClient();
    int firstPageSize = userClient.getFirstPage().size();

    assertEquals(EXPECTED_NUMBER_USERS, firstPageSize);
  }

  @Test
  public void getFirstPage_first_user_firstName_should_be_George()
      throws IOException, InterruptedException {
    UserDTO george = new UserDTO("George", "Bluth", 1L, "test@email.com");
    userDTOList = Arrays.asList(george, userDTO, userDTO);
    UserClient userClient = new UserClient();
    String firstFirstName = userClient.getFirstPage().get(0).getFirstName();
    assertEquals(EXPECTED_FIRST_FIRSTNAME, firstFirstName);
  }

}
