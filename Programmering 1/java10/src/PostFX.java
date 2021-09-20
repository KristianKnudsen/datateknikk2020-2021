import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PostFX {

    final Set<String> operators = new HashSet<String>(Arrays.asList("+", "-", "/", "*", "%"));
    private ArrayDeque<String> dq = new ArrayDeque<>();

    public int evaluateExpression(String expression){

        scanAndProcessTokens(expression);

        return Integer.parseInt(dq.pop());
    }

    public String getTokenAt(String expression, int i){
        return expression.split("\\s")[i];
    }

    public int getTokenAmount(String expression){
        return expression.split("\\s").length;
    }

    private void scanAndProcessTokens(String e){

        for ( int i = 0; i < getTokenAmount(e); i++){

            if ( operators.contains(getTokenAt(e, i)) ){

                int val1 = Integer.parseInt(dq.pop());
                int val2 = Integer.parseInt(dq.pop());

                switch (getTokenAt(e, i)){
                    case "+":
                        dq.push( String.valueOf(val1 + val2) );
                        break;
                    case "-":
                        dq.push( String.valueOf(val2 - val1) );
                        break;
                    case "*":
                        dq.push( String.valueOf(val1 * val2) );
                        break;
                    case "/":
                        dq.push( String.valueOf( val2 / val1) );
                        break;
                    case "%":
                        dq.push( String.valueOf(val2 % val1) );
                        break;
                }
            }
            else {
                dq.push(getTokenAt(e, i));
            }
        }
    }
}
