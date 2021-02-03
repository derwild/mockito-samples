package com.cwild.repository;

import com.cwild.exception.NoAgeException;
import com.cwild.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

  public static final String DEFAULT_USERNAME = "nice";
  public static final String DEFAULT_ADDRESS = "connect@cheap.com";
  public final List<User> repository = new ArrayList<>();

  /**
   * Adds a {@link User} to the internal repository
   */
  public void add(User user) {
    repository.add(user);
  }

  /**
   * Adds all given {@link User}s into the internal repository by calling {@link #add(User)} for
   * each of them
   */
  public void addAll(List<User> users) {
    users.forEach(this::add);
  }

  /**
   * Sets a default username and address on the given {@link User} if that field is not set yet
   */
  public void populateUser(User user) {
    if (user.getUsername() == null) {
      user.setUsername(DEFAULT_USERNAME);
    }
    if (user.getAddress() == null) {
      user.setAddress(DEFAULT_ADDRESS);
    }
  }

  /**
   * Tries to increment the age of the given {@link User}
   *
   * @return true if the age could successfully be incremented, false if not
   */
  public boolean tryIncrementAge(User user) {
    try {
      user.incrementAge();
      return true;
    } catch (NoAgeException e) {
      return false;
    }
  }

  /**
   * Compares the usernames of the given users and returns the longer of the two
   *
   * @return The longer username and the name of user2, if they have the same length
   */
  public String getLongerUsername(User user1, User user2) {
    // Intentionally not extracting local variables here, so mocking multiple calls to
    // the same method can be demonstrated
    if (user1.getUsername().length() > user2.getUsername().length()) {
      return user1.getUsername();
    } else {
      return user2.getUsername();
    }
  }

}
