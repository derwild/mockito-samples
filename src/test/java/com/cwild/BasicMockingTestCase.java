package com.cwild;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.cwild.exception.NoAgeException;
import com.cwild.model.User;
import com.cwild.repository.UserRepository;
import org.junit.jupiter.api.Test;

class BasicMockingTestCase {

  /**
   * Demonstrates basic usage of a Mock including `doReturn()` and `verify()`
   */
  @Test
  void mockUserUsername() throws Exception {
    String mockedUsername = "guide";
    User user = mock(User.class);

    doReturn(mockedUsername).when(user).getUsername();

    String foundUsername = user.getUsername();
    assertEquals(mockedUsername, foundUsername);

    verify(user).getUsername();
    verifyNoMoreInteractions(user);
  }

  /**
   * Demonstrates the use of simple {@link org.mockito.ArgumentMatchers} in `verify()` statements
   */
  @Test
  void testUserRepositoryPopulateUser() throws Exception {
    String mockedUsername = "guide";
    User user = mock(User.class);

    doReturn(mockedUsername).when(user).getUsername();
    doReturn(null).when(user).getAddress();

    new UserRepository().populateUser(user);

    verify(user).getUsername();
    verify(user).getAddress();
    verify(user).setAddress(eq(UserRepository.DEFAULT_ADDRESS));
    verifyNoMoreInteractions(user);
  }

  /**
   * Demonstrates the usage of `doThrow()` and `verifyNoMoreInteractions()`
   */
  @Test
  void testUserRepositoryTryIncrementAge() throws Exception {
    User user = mock(User.class);

    doThrow(NoAgeException.class).when(user).incrementAge();

    boolean found = new UserRepository().tryIncrementAge(user);
    assertFalse(found);

    verify(user).incrementAge();
    // Makes sure that "incrementAge()" was not actually executed by verifying "getAge()" was not called
    verifyNoMoreInteractions(user);
  }

  /**
   * Demonstrates the usage of multiple `doReturns()` in succession to return different values on
   * multiple calls
   */
  @Test
  void testUserRepositoryGetLongerUsername() throws Exception {
    String user1Username1 = "just";
    String user2Username1 = "gradual";
    String user2Username2 = "to";

    User user1 = mock(User.class);
    User user2 = mock(User.class);

    doReturn(user1Username1)
        .when(user1).getUsername();
    doReturn(user2Username1)
        .doReturn(user2Username2)
        .when(user2).getUsername();

    String foundUsername = new UserRepository().getLongerUsername(user1, user2);
    assertEquals(user2Username2, foundUsername);
  }

}
