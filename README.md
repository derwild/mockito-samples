# mockito-samples

Just a simple project containing some sample code to demonstrate the usage of the Mockito Test Library together with JUnit 5.  
Feel free to use the code to your hearts content or request a merge if there is more to demonstrate.

Note that these examples are written for JUnit 5, but most of them will probably also work with JUnit 4 or even other test libraries.

Some caveats:
* Most classes in this project are pretty nonsensical as they are only supposed to demonstrate how to mock/verify them.
* A lot of the tests "violate" good conventions for Unit testing (like mocking as few as possible). Again, this is only done to demonstrate how you CAN mock, not how you SHOULD mock

### Concepts covered in these Tests

* Basic usage of Mocks and their creation using `mock()` and `@Mock`
* Basic usage of Spies and their creation using `spy()` and `@Spy`
* `doReturn()`, `doThrow()`, `noNothing()` and `doAnswer()`
* Usage of different `verify()`s and simple ArgumentMatchers (e.g. `eq()`)
* `ArgumentCaptor` usage in `verify()`

### Some more advanced topics to look into

* `PowerMock`
* Mocking `final` methods and classes
* Advanced `ArgumentMatcher`s
* Injecting mocks as beans into Spring (Boot)