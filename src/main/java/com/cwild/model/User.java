package com.cwild.model;

import com.cwild.exception.NoAgeException;
import lombok.Data;

@Data
public class User {

  private String username;
  private String address;
  private Integer age;

  /**
   * Increment the age of this {@link User}
   *
   * @throws NoAgeException If the age has not been set yet
   */
  public int incrementAge() throws NoAgeException {
    // Intentionally using getter so we can mock it
    if (getAge() == null) {
      throw new NoAgeException();
    }
    return age++;
  }

}
