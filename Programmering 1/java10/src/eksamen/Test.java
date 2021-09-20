package eksamen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {

    public static void main(String[] args){
        Test t = new Test();
        System.out.println(t.myPow(2,-2));
    }

    public double myPow(double x, int n) {

        double result = x;

        if ( n == 0){
            return 1;
        } else if (n > 0) {
            for ( int i = 1; i < n; i++){
                result=result*x;
            }
        } else if( n < 0) {
            for ( int i = -1; i > n; i--){
                result=result*x;
            }
            result=1/result;
        }

        return result;
    }

}

