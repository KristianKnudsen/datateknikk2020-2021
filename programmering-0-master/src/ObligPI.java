public class ObligPI {
    public static void main(String[] args) {

        int MaxLedd = 100000;
        double x = 0;

        for( int i = 1; i <= MaxLedd; i++){
            x += (Math.pow(-1,i+1))/(2*i-1);
            if ( i%10000==0 ){
                System.out.println("For " + i + " ledd i rekken er verdien av pi: " + 4*x);
            }
        }
    }
}
