package service;


import dao.implement.UserDAO;
import dto.UserDto;
import jakarta.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import service.impl.UserService;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.response.Message;
import utils.response.MessageResponse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.doThrow;


@RunWith(MockitoJUnitRunner.class)
public class TestUserService {

    @Mock
    private UserDAO iUserDAO;

    @InjectMocks
    private UserService userService;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    @DisplayName("Find One User By Id")
    public void testFindOneByID() throws Exception {
        Message expectedMessage = new Message.Builder(null).build();
//        when(userService.FindOne(anyString())).thenReturn(expectedMessage);
        Message result = userService.FindOne("");
        Assert.assertTrue(HttpServletResponse.SC_OK == result.getMeta().getStatusCode());
        Assert.assertEquals(result.getMeta().getMessage(), MessageResponse.OK);
    }

    @Test
    @DisplayName("Update One Test failed input")
    public void testUpdateOneFailedInput() throws Exception {
        assertThrowsExactly(BadRequestException.class, () -> userService.UpdateOne(null, new UserDto()), "Invalid properties");
    }

    @Test
    @DisplayName("Update One Test success")
    public void testUpdateOneSuccess() throws Exception {
        assertThrowsExactly(BadRequestException.class, () -> userService.UpdateOne("z0ZWHzTMNT", new UserDto()), "");
    }

    @Test
    @DisplayName("Find Role Test success")
    public void testFindRole() throws Exception {
        Message result = userService.FindRole("");
        Assert.assertTrue(HttpServletResponse.SC_OK == result.getMeta().getStatusCode());
        Assert.assertEquals(result.getMeta().getMessage(), MessageResponse.OK);
    }

    @Test
    @DisplayName("Delete One Test success")
    public void testDeleteOne() throws Exception {
        Message result = userService.DeleteOne("");
        Assert.assertTrue(HttpServletResponse.SC_OK == result.getMeta().getStatusCode());
        Assert.assertEquals(result.getMeta().getMessage(), MessageResponse.OK);
    }

}
