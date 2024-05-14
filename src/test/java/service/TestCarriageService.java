package service;

import dao.implement.CarriageDAO;
import dto.CarriageDto;
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
import service.impl.CarriageService;
import utils.exceptions.api.BadRequestException;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@RunWith(MockitoJUnitRunner.class)
public class TestCarriageService extends TestCase {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();
    @Mock
    private CarriageDAO carriageDAO;
    @InjectMocks
    private CarriageService carriageService;

    @Test
    @DisplayName("Update with null id")
    public void testUpdate() throws Exception {

        var throwable = assertThrowsExactly(BadRequestException.class, () -> this.carriageService.UpdateOne(new CarriageDto(), null), "lol");
        assertEquals(BadRequestException.class, throwable.getClass());
        assertEquals(throwable.getMessage(), "Invalid properties");
    }
}
