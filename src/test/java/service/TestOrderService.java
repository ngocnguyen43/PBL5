package service;

import dao.implement.CarriageDAO;
import dao.interfaces.IOrderDAO;
import dao.interfaces.IRoleDAO;
import dao.interfaces.IUserPermissionDAO;
import dto.CarriageDto;
import jakarta.inject.Inject;
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
import service.impl.OrderService;
import utils.exceptions.api.BadRequestException;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TestOrderService {
    @Mock
    private IOrderDAO iOrderDAO;
    @Mock
    private IUserPermissionDAO iUserPermissionDAO;
    @Mock
    private IRoleDAO iRoleDAO;

    @InjectMocks
    private OrderService orderService;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();

    @Test
    @DisplayName("Update with null id")
    public void testUpdate() throws Exception {

        var message = this.orderService.FindAllOrders(null);
        assertTrue(message.getMeta().getStatusCode() == 200);
    }
}
