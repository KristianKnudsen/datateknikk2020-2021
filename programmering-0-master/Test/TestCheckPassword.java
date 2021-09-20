import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestCheckPassword {

    Password password;

    @BeforeEach
    public void setup(){
        password = new Password();
    }

   @ParameterizedTest
   @ValueSource(strings = {"a234567","","a123"})
   public void canCheckFalsePasswordLength(String input){
       assertFalse(password.checkPassword(input));
   }

   @ParameterizedTest
    @ValueSource(strings = {"a123456789", "abcdefghijklmnopqrstuvwxyzæøå123"})
    public void canCheckTruePasswordLength(String input){
        assertTrue(password.checkPassword(input));
   }

   @ParameterizedTest
    @ValueSource(strings = {"asd1234678!", "abcdefghijklmno12", "abcde12345(",
    "0123456789"})
    public void canCheckFalsePasswordCharacters(String input){
        assertFalse(password.checkPassword(input));
   }

   @ParameterizedTest
    @ValueSource(strings = {"AbCdE12345", "ØæÅöä123èà", "000000000a",
    "ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅÄÖabcdefghijklmnopqrstuvwxyzæøå123"})
    public void canCheckTruePasswords(String input){
        assertTrue(password.checkPassword(input));
   }
}
