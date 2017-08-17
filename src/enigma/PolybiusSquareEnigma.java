package enigma;

import services.EnigmaService;
import enigma.exceptions.WrongKeyException;
import java.util.Random;

public class PolybiusSquareEnigma implements EnigmaService
{
    private static final String alphabet = "abcdefghiklmnopqrstuvwxyz";
    private static final boolean KEY_REQUIRED = true;
    public static final boolean GENERATE_KEY = true;
    private static final String NAME = "PolybiusSquareEnigma";
    private static final Character EXCLUDED_CHAR = 'j';
    private String key;

    public static void main(String[] args){
        PolybiusSquareEnigma x = new PolybiusSquareEnigma();
        try{
            x.setKey("phqgmeaylnofdxkrcvszwbuti");

            String word = "defend the east wall of the castle";
            System.out.println(word);
            word = x.encipher(word);
            System.out.println(word);
            word = x.decipher(word);
            System.out.println(word);
        }
        catch (WrongKeyException e){
            System.out.println(e);
        }
}

    public String encipher(String text)
    {
        String encipheredText = "";
        for(Character c : text.toCharArray()){
            if(this.key.indexOf(c) >= 0){
                encipheredText += this.alphabet.charAt(this.key.indexOf(c) / 5);
                encipheredText += this.alphabet.charAt(this.key.indexOf(c) % 5);
            }
            else {
                encipheredText += c;
            }

        }
        return encipheredText;
    }

    public String decipher(String text)
    {

        return null;
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