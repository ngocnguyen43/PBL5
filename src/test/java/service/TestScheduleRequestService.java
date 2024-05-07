package service;

import dao.implement.ScheduleRequestDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import service.impl.ScheduleRequestService;
import utils.exceptions.api.BadRequestException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@RunWith(MockitoJUnitRunner.class)
public class TestScheduleRequestService {
    @Mock
    private ScheduleRequestDAO scheduleRequestDAO;

    @InjectMocks
    private ScheduleRequestService scheduleRequestService;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();

    @Test
    @DisplayName("update with null id")
    public void testUpdate() throws Exception {

        var throwable = assertThrowsExactly(BadRequestException.class, () -> this.scheduleRequestService.UpdateStatus(null, null), "lol");
        assertEquals(BadRequestException.class, throwable.getClass());
        assertEquals(throwable.getMessage(), "Invalid properties");
    }
}
