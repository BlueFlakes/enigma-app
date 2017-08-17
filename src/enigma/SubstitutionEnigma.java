package enigma;

import services.EnigmaService;
import enigma.exceptions.WrongKeyException;


public class SubstitutionEnigma implements EnigmaService
{
    private static final boolean KEY_REQUIRED = false;
    private static final String NAME = "SubstitutionEnigma";
    private String key;
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args){
        SubstitutionEnigma x = new SubstitutionEnigma();
        try{
            x.setKey("phqgiumeaylnofdxjkrcvstzwb");
            String word = x.encipher("defend the east wall of the castle");
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
        for(Character c: text.toCharArray()){
            if(key.indexOf(c) >= 0){
                encipheredText += key.charAt(alphabet.indexOf(c));
            }
            else{
                encipheredText += c;
            }
        }
        return encipheredText;
    }

    public String decipher(String text)
    {
        String encipheredText = "";
        for(Character c: text.toCharArray()){
            if(key.indexOf(c) >= 0){
                encipheredText += this.alphabet.charAt(this.key.indexOf(c));
            }
            else{
                encipheredText += c;
            }
        }
        return encipheredText;
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
        else throw new WrongKeyException("Key should contain 26 chars of latin alphabet without repetitions.");

    }

    private static boolean isKeyValid(String key){
        for(int i = 97; i <= 122; i++){
            if(key.indexOf((char) i) < 0) return false;
        }
        return (key.length() == 26);
    }


}