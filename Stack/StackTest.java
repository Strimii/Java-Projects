import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StackTest {
    private Stack sut;

    @Before
    public void setup() {
        sut = new Stack(10);
    }

    @Test
    public void PopAfterPush() {
        sut.push(10);
        int result = sut.pop();

        assertEquals("Pop After Push", 10, result);
    }
    @Test
    public void checkIfNull() {
        assertNotEquals("null", null , sut);
    }

    @Test
    public void push_TwoNumbers() {
        sut.push(5);
        sut.push(7);
        int result = sut.peek();
        assertEquals( 7, result);
    }
    @Test
    public void push_SingleNumbers() {
        sut.push(5);
        int result = sut.peek();
        assertEquals( 5, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Peek_Empty_InvalidStack() {
        sut.peek();
    }

    @Test
    public void isEmpty_StackHasElements_StackFalse() {
        sut.push(1);
        assertEquals(false,sut.isEmpty());
    }

    @Test
    public void isEmpty_StackIsEmpty_StackTrue() {
        assertEquals(true,sut.isEmpty());
    }


}