package service;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("Junit test test service")
public class TestService extends TestCase {
    @DisplayName("JUnit test")
    @Test
    public void testMethod(){
        // given - precondition or setup
        String t = "a";
        assertNotNull(t);
    }
}
