public class PatternMatching{

    public int findPattern( String s, String pattern){
        Integer[] k = {0, 0}; //0 rette starting at index 0
        for ( int i = 0; i < s.length(); i++){
            if ( s.charAt(i) == pattern.charAt(k[0]) ){
                if ( k[0] == 0) {
                    k[1] = i;
                }
                k[0]++;
                if ( k[0] == pattern.length() ){
                    return k[1];
                }
            } else {
                k[0] = 0;
            }
        }
        return -1;
    }
}

class TestPatternMathcing{
    public static void main(String[] args ) {

        PatternMatching p = new PatternMatching();

        System.out.println(p.findPattern("abcdefghijklmnopqrstu", "stu"));

    }
}