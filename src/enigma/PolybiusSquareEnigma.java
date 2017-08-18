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
    private boolean isUsed;

    public PolybiusSquareEnigma(){
        this.isUsed = false;
    }

    public boolean getIsUsed(){
        return this.isUsed;
    }

    public void changeIsUsed(){
        if(this.isUsed) this.isUsed = false;
        else this.isUsed = true;
    }
    public static void main(String[] args){
        PolybiusSquareEnigma x = new PolybiusSquareEnigma();
        try{
            x.setKey("phqgmeaylnofdxkrcvszwbuti");

            String word = "defend the east Wall of the castle";
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
        text = text.toLowerCase();
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
        if(!isValidCryptogram(text)) return text;
        text = text.toLowerCase();
        String decipheredText = "";
        String alphabet = this.alphabet.substring(0, 5);
        for(int i = 0; i < text.length(); i+= 2){
            if(alphabet.indexOf(text.charAt(i)) >= 0){
                Integer column = alphabet.indexOf(text.charAt(i));
                Integer row = alphabet.indexOf(text.charAt(i+1));
                decipheredText += key.charAt((column*5) + row);
            } else{
                decipheredText += text.charAt(i);
                i--;
            }
        }
        return decipheredText;
    }

    private boolean isValidCryptogram(String text){
        for(Character c : text.toCharArray()){
            if(this.alphabet.indexOf(c) >= 5) return false;
        }
        return true;
    }

    public boolean isKeyRequired()
    {
        return this.KEY_REQUIRED;

    }

    public String getName()
    {
        return this.NAME;

    }

    public void setKey(String deliveredKey) throws WrongKeyException
    {
        if(isKeyValid(deliveredKey)) this.key = deliveredKey;
        else if (this.GENERATE_KEY) this.key = generateKey();
        else throw new WrongKeyException("Key should contain 26 chars of latin alphabet without repetitions.");

    }

    private boolean isKeyValid(String key){
        for(int i = 97; i <= 122; i++){
            if(key.indexOf((char) i) < 0 && !isExcludedChar((char) i)) return false;
        }
        return (key.length() == 25);
    }

    private String generateKey(){
        String key = "";
        Random generator = new Random();
        while(key.length() < 25){
            Character newChar = (char) (generator.nextInt(26) + 97);
            if(key.indexOf(newChar) < 0 && !isExcludedChar(newChar)) key += newChar;
        }
        System.out.printf("Your key is: %s%n", key);
        return key;
    }

    private boolean isExcludedChar(Character chr){
        return chr.equals(this.EXCLUDED_CHAR);
    }




}