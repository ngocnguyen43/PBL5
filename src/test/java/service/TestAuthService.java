package service;

import dao.implement.UserDAO;
import dto.UserDto;
import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import service.impl.AuthService;
import utils.exceptions.api.InvalidCredentialsException;
import utils.response.MessageResponse;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@RunWith(MockitoJUnitRunner.class)
public class TestAuthService extends TestCase {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();
    @Mock
    private UserDAO iUserDAO;
    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("Login with null fields")
    public void testLogin() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail(null);
        userDto.setPassword(null);
        var throwable = assertThrowsExactly(InvalidCredentialsException.class, () -> this.authService.Login(userDto), "lol");
        assertEquals(InvalidCredentialsException.class, throwable.getClass());
        assertEquals(throwable.getMessage(), MessageResponse.INVALID_EMAIL_OR_PASSWORD);
    }

    @Test
    @DisplayName("should throw error when username is null")
    public void testLoginWithNullUsername() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail(null);
        userDto.setPassword("1234567");
        assertThrowsExactly(InvalidCredentialsException.class, () -> this.authService.Login(userDto), "Invalid properties");
    }

}
