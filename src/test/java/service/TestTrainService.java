package service;

import dao.implement.TrainDAO;
import dto.TrainDto;
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
import service.impl.TrainService;
import utils.exceptions.api.BadRequestException;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@RunWith(MockitoJUnitRunner.class)
public class TestTrainService extends TestCase {
    @Mock
    private TrainDAO trainDAO;

    @InjectMocks
    private TrainService trainService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();

    @Test
    @DisplayName("update with null id")
    public void testUpdate() throws Exception {
        TrainDto dto = new TrainDto();
        var throwable = assertThrowsExactly(BadRequestException.class, () -> this.trainService.UpdateOne(dto, null), "lol");
        assertEquals(BadRequestException.class, throwable.getClass());
        assertEquals(throwable.getMessage(), "Invalid properties");
    }

    @Test
    @DisplayName("delete with null id")
    public void testDelete() throws Exception {
        TrainDto dto = new TrainDto();
        var throwable = assertThrowsExactly(BadRequestException.class, () -> this.trainService.DeleteOne(null), "lol");
        assertEquals(BadRequestException.class, throwable.getClass());
        assertEquals(throwable.getMessage(), "Invalid properties");
    }

    @Test
    @DisplayName("get all trains")
    public void testGetAll() throws Exception {
        var message = this.trainService.FindAll();
        assertTrue(message.getMeta().getStatusCode() == 200);
    }
}
