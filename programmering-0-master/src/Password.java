public class Password {

    public Password(){}

    public boolean checkPassword(String s){
        return checkLength(s) && checkCharacters(s);
    }

    private boolean checkLength(String s){
        return s.length() >= 10;
    }

    private boolean checkCharacters(String s){
        int countD = 0;
        int countL = 0;
        for ( int i = 0; i < s.length(); i++){
            if (Character.isDigit( s.charAt(i) )){
                countD++;
            } else if ( Character.isLetter( s.charAt(i) ) ){
                countL++;
            } else { return false; }
        }
      return countD >= 3 && countL >= 1;
    }
}











