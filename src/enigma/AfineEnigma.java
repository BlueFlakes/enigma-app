package enigma;
import java.util.Arrays;
import services.EnigmaService;
import enigma.exceptions.WrongKeyException;


public class AfineEnigma implements EnigmaService
{
    private static final boolean KEY_REQUIRED = true;
    private static final String NAME = "AfineEnigma";
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String key;
    private Integer key1;
    private Integer key2;
    private boolean isUsed;

    public AfineEnigma(){
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
        AfineEnigma x = new AfineEnigma();
        x.setKey("11:812");
        String word = "Ala ma kota a kot ma alę!";
        System.out.println(word);
        word = x.encipher(word);
        System.out.println(word);
        word = x.decipher(word);
        System.out.println(word);


    }

    public String encipher(String text)
    {
        String encipheredText = "";
        
        for(Character c : text.toCharArray()){
            String alphabet = getAlphabetCased(c);
            if (alphabet.indexOf(c) >= 0){
                Integer charValue = alphabet.indexOf(c);
                charValue = ((this.key1 * charValue) + this.key2) % alphabet.length();
                encipheredText += alphabet.charAt(charValue);
            }
            else {
                encipheredText += c;
            }
        }
        return encipheredText;
    }

    public String getAlphabetCased(Character chr){
        String alphabet;
        if(Character.isUpperCase(chr)) alphabet = this.alphabet.toUpperCase();
        else alphabet = this.alphabet;

        return alphabet;
    }

    public String decipher(String text)
    {
        String decipheredText = "";
        Integer multiplicativeInverse = findMultiplicativeInverse();
        for(Character c : text.toCharArray()){
            String alphabet = getAlphabetCased(c);
            if (alphabet.indexOf(c) >= 0){
                Integer charValue = alphabet.indexOf(c);
                charValue -= this.key2;
                if(charValue < 0) charValue = alphabet.length() + charValue;
                charValue = multiplicativeInverse * charValue;
                charValue %= alphabet.length();
                decipheredText += alphabet.charAt(charValue);
            }
            else{
                decipheredText += c;
            }
        }
        return decipheredText;
    }

    private Integer findMultiplicativeInverse(){
        Integer x = 1;
        while((this.key1 * x) % alphabet.length() != 1){
            x++;
        }
        return x;
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
        try {
            validateKey(deliveredKey);
		} catch (WrongKeyException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		} 
        this.key = deliveredKey;
        String[] parts = this.key.split(":");
        this.key1 = Integer.valueOf(parts[0]);
        this.key2 = Integer.valueOf(parts[1]);

    }

    public void validateKey(String key) throws WrongKeyException{
        String[] parts = key.split(":");
        if(parts.length != 2) throw new WrongKeyException("Key should be two integers separated be : ex. 7:2");
        
        Integer key1 = Integer.valueOf(parts[0]);
        Integer key2 = Integer.valueOf(parts[1]);
        Integer[] allowedKeys = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
        if(!Arrays.asList(allowedKeys).contains(key1)) throw new WrongKeyException("First key should be: 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23 or 25");

        if(key2 > this.alphabet.length() || key2 < 1) throw new WrongKeyException("Key should be: 1 <= key <= size of alphabet");
        
    }
}