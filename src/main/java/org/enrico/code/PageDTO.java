package org.enrico.code;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDTO {
  /*
   * private long page; private long per_page; private long total; private long total_pages;
   */
  public ArrayList<UserDTO> getData() {
    return data;
  }

  private ArrayList<UserDTO> data;
}
