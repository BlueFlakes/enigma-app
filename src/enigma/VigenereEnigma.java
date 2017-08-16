package enigma;

import services.EnigmaService;

public class VigenereEnigma implements EnigmaService
{
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static final boolean KEY_REQUIRED = true;
    public static final String NAME = "VigenereEnigma";
    private String key;


    public VigenereEnigma(String deliveredKey)
    {
        this.key = deliveredKey;
    }

    public String encipher(String text){
        String encodedString = "";
        for (Integer i = 0; i < text.length(); i++){
            String alphabet = getAlphabetCased(text.charAt(i));
            if (alphabet.indexOf(text.charAt(i)) >= 0){
                Integer charFromPassword = alphabet.indexOf(this.key.charAt(i % this.key.length()));
                Integer charValue = alphabet.indexOf(text.charAt(i));
                Integer encodedValue = charFromPassword + charValue;
                encodedValue = this.loop(encodedValue, alphabet);
                encodedString = encodedString.concat(String.valueOf(alphabet.charAt(encodedValue)));
            }
            else {
                encodedString = encodedString.concat(String.valueOf(text.charAt(i)));
            }

        }
        return encodedString;
    }

    public String decipher(String text){
        String encodedString = "";
        for (Integer i = 0; i < text.length(); i++){
            String alphabet = getAlphabetCased(text.charAt(i));
            if (alphabet.indexOf(text.charAt(i)) >= 0){
                Integer charFromPassword = alphabet.indexOf(this.key.charAt(i % this.key.length()));
                Integer charValue = alphabet.indexOf(text.charAt(i));
                Integer encodedValue = charValue - charFromPassword;
                encodedValue = this.loop(encodedValue, alphabet);
                encodedString = encodedString.concat(String.valueOf(alphabet.charAt(encodedValue)));
            }
            else {
                encodedString = encodedString.concat(String.valueOf(text.charAt(i)));
            }
        }
        return encodedString;
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

    public static Integer loop(Integer movedCharValue, String alphabet){
        if (movedCharValue < 0){
            movedCharValue = alphabet.length() + movedCharValue;
        } else if(movedCharValue >= alphabet.length()){
            movedCharValue -= alphabet.length();
        }

        return movedCharValue;
    }

    public static void main(String[] args){
        VigenereEnigma a = new VigenereEnigma("TAJNE");
        String word = a.encipher("TO JEST BARDZO TAJNY TEKST");
        word = a.decipher(word);
        System.out.println(word);
    }

    public String getAlphabetCased(Character chr){
        String alphabet;
        if(Character.isUpperCase(chr)) alphabet = this.alphabet.toUpperCase();
        else alphabet = this.alphabet;

        return alphabet;
    }
}
