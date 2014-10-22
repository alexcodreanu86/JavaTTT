import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;
/**
 * Created by Alex Codreanu on 10/18/14.
 */
public class MockitoTest {
    @Test
    public void testingMockito() {
        List mockedList = mock(List.class);

// using mock object ; observe that it didn't throw any "unexpected interaction exception" exception
        mockedList.add("one");
        mockedList.clear();

        // selective & explicit verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
}
