package org.enrico.code.controller;

import com.google.gson.Gson;

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
    //private static final String PATH = "data";
    
    HttpClient httpClient = HttpClient.newHttpClient();
    Gson gson = new Gson();

    public static void main(String args[]){
        UserClient userClient = new UserClient();
        List<User> userList;
		try {
			userList = userClient.getFirstPage();
	        for (User user : userList) {
	            System.out.println(String.format("%1$s - %2$s", user.getFirstName(), user.getLastName()));
	        }
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

    }

    public List<User> getFirstPage() throws IOException, InterruptedException{
    	//Variable initialisation
    	List<User> userList = new ArrayList<User>();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(REST_URI_PAGE_1))
                .GET()
                .build();
        
        //Call firstpage service
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if(response != null) {
        	//Conversion to DTO through Gson
            PageDTO page = gson.fromJson((String)response.body(), PageDTO.class);
            if(page != null) {
	            List<UserDTO> userDTOList = page.getData();
	            if(userDTOList !=null) {
	            	//Mapping UserDTO to User
		            userList = userDTOList.stream()
		            					  .map(this::convertToUser)
		            					  .collect(Collectors.toList());
	            }
            }
        }
        return userList;
    }
    
    private User convertToUser(UserDTO userDTO) {
    	User user = new User();
    	if(userDTO != null) {
	    	user.setFirstName(userDTO.getFirstName());
	    	user.setLastName(userDTO.getLastName());
    	}
    	return user;
    }

}
