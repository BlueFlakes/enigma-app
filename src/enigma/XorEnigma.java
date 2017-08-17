package enigma;

import services.EnigmaService;
import enigma.exceptions.WrongKeyException;
import java.util.ArrayList;

public class XorEnigma implements EnigmaService{
    private String key;
    public static final boolean KEY_REQUIRED = true;
    public static final String NAME = "XorEnigma";

    public XorEnigma() {

    }

    public XorEnigma(String deliveredKey)
    {
        this.setKey(deliveredKey);
    }

    public String encipher(String text){
        String encipheredString = "";
        for (int i = 0; i < text.length(); i++){
            Character encipheredChar = (char) (text.charAt(i) ^ key.charAt(i % this.key.length()));
            encipheredString += encipheredChar;
        }
        return encipheredString;
    }

    public String decipher(String text){
        return encipher(text);
    }

    public boolean isKeyRequired()
    {
        return this.KEY_REQUIRED;

    }

    public String getName()
    {
        return this.NAME;

    }

    public void setKey(String deliveredKey)
    {
        this.key = deliveredKey;
    }

}
