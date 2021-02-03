package com.cwild;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.cwild.model.User;
import com.cwild.repository.UserRepository;
import org.junit.jupiter.api.Test;

class BasicSpyTestCase {

  /**
   * Demonstrates basic usage of Spies
   */
  @Test
  void testUserSetAndGet() throws Exception {
    String givenUsername1 = "quite";
    String givenUsername2 = "copper";
    String givenAddress = "sauce@modern.com";

    User user = spy(User.class);
    user.setUsername(givenUsername1);
    user.setAddress(givenAddress);

    doReturn(givenUsername2).when(user).getUsername();

    String foundUsername = user.getUsername();
    String foundAddress = user.getAddress();

    assertEquals(givenUsername2, foundUsername);
    assertEquals(givenAddress, foundAddress);

    verify(user).setUsername(anyString());
    verify(user).setAddress(anyString());
    verify(user).getUsername();
    verify(user).getAddress();
    verifyNoMoreInteractions(user);
  }

  /**
   * Demonstrates the caveat with setters on spies and verify. Also ways to configure hit count in
   * `verify()`
   */
  @Test
  void testUserRepositoryGetLongerUsername() throws Exception {
    String username1 = "scratch";
    String username2 = "q62";

    User user1 = spy(User.class);
    User user2 = spy(User.class);
    user1.setUsername(username1);
    user2.setUsername(username2);

    String foundUsername = new UserRepository().getLongerUsername(user1, user2);
    assertEquals(username1, foundUsername);

    verify(user1).setUsername(eq(username1));
    verify(user2).setUsername(anyString());
    verify(user1, times(2)).getUsername();
//    verify(user1, atLeastOnce()).getUsername();
    verify(user2).getUsername();
    verifyNoMoreInteractions(user1, user2);
  }

}