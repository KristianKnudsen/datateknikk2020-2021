import chapter11.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTriangle {


    @Test
    public void canEquals(){
        Triangle triangle1 = new Triangle();
        Triangle triangle2 = new Triangle(1,1,1);
        assertTrue( triangle1.equals(triangle2) );

    }

    @Test
    public void canCheckLegality(){
        Triangle triangle = new Triangle();
        assertTrue( triangle.checkLegality(1,1,1) );
        assertTrue( triangle.checkLegality(1,2,1.00000000000001) );
        assertTrue( triangle.checkLegality(1,2,2) );

        assertFalse( triangle.checkLegality(1,2,3) );
        assertFalse( triangle.checkLegality(0,1,1) );
        assertFalse( triangle.checkLegality(1,2,1) );
        assertFalse( triangle.checkLegality(-1,1,1) );

    }

    @Test
    public void canGetArea(){

        assertEquals(433, (int)(new Triangle().getArea()*1000));
        assertEquals(6000, (int)(new Triangle(5,3,4).getArea()*1000));
        assertEquals(1732, (int)(new Triangle(2).getArea()*1000));

    }

    @Test
    public void canGetPerimeter(){

        assertEquals(3, new Triangle().getPerimeter() );
        assertEquals(6, new Triangle(2).getPerimeter() );
        assertEquals(5, new Triangle(1,2,2).getPerimeter() );

    }
}
