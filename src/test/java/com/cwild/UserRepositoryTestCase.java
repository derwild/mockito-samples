package com.cwild;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.cwild.model.User;
import com.cwild.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

class UserRepositoryTestCase {

  /**
   * Unused here, only shows to to initialize a Mock for each test in a class
   */
  @Mock
  private UserRepository repositoryMock;
  @Spy
  private UserRepository repositorySpy;

  /**
   * Used to initialize the Mocks defined as fields in the Test class and annotated with {@link
   * Mock} or {@link Spy}. There are also other ways to do this with `@RunWith` (JUnit 4) or {@link
   * org.junit.jupiter.api.extension.ExtendWith}
   */
  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Demonstrates the usage of {@link ArgumentCaptor}s
   */
  @Test
  void testAddAll1() throws Exception {
    User user1 = mock(User.class);
    User user2 = mock(User.class);
    List<User> userList = List.of(user1, user2);

    doNothing().when(repositorySpy).add(any(User.class));

    repositorySpy.addAll(userList);

    // Testing the test framework, which we only do to demonstrate, that the spy does what it should
    assertTrue(repositorySpy.repository.isEmpty());

    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
    verify(repositorySpy, atLeastOnce()).add(userArgumentCaptor.capture());
    List<User> allCapturedUsers = userArgumentCaptor.getAllValues();
    assertEquals(new HashSet<>(userList), new HashSet<>(allCapturedUsers));
  }

  /**
   * Demonstrates the usage of `doAnswer()`
   */
  @Test
  void testAddAll2() throws Exception {
    String addedUsernamePrefix = "thisUserWasAdded";
    String user1Username = "sign";
    String user2Username = "health";
    User user1 = new User();
    User user2 = new User();
    user1.setUsername(user1Username);
    user2.setUsername(user2Username);
    List<User> userList = List.of(user1, user2);

    doAnswer(invocation -> {
      User user = invocation.getArgument(0);

      user.setUsername(addedUsernamePrefix + user.getUsername());
      return "Added user with username " + user.getUsername();
    }).when(repositorySpy).add(any(User.class));

    repositorySpy.addAll(userList);

    assertEquals(addedUsernamePrefix + user1Username, user1.getUsername());
    assertEquals(addedUsernamePrefix + user2Username, user2.getUsername());
  }
}