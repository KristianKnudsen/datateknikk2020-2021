import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostFXTest {

    PostFX postFX = new PostFX();

    @Test
    void canAdd(){
        assertEquals(6, evEx("2 4 +"));
    }

    @Test
    void canSubtract(){
        assertEquals(6, evEx("8 2 -"));
    }

    @Test
    void canMultiply(){
        assertEquals(6, evEx("3 2 *"));
    }

    @Test
    void canDivide(){
        assertEquals(6, evEx("12 2 /"));
    }

    @Test
    void canModulo(){
        assertEquals(3, evEx("24 7 %"));
    }

    @Test
    void canMix(){
        assertEquals(-16, evEx("4 5 7 2 + - *"));
        assertEquals(2, evEx("3 4 + 2 * 7 /"));
        assertEquals(48, evEx("5 7 + 6 2 - *"));
        assertEquals(18, evEx("4 2 + 3 5 1 - * +"));
    }

    int evEx(String s){
        return postFX.evaluateExpression(s);
    }

}