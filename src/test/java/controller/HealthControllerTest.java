package controller;

import controller.health.HealthController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@RunWith(MockitoJUnitRunner.class)
public class HealthControllerTest extends TestCase {
    @Test
    public void testHealthController() throws ServletException, IOException {
        // Create mock objects for request and response
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Use Mockito to mock the behavior of request and response
        // Create a StringWriter to capture the response output
        StringWriter stringWriter = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        // Call the doGet method of a subclass of HealthController
        HealthController healthControllerMock = new HealthController();
        healthControllerMock.doGet(request, response);

        // Verify that the response contains "OK"
        assertEquals("\"OK\"", stringWriter.toString());
    }
}
