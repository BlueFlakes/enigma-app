package enigma;

import services.EnigmaService;
import enigma.exceptions.WrongKeyException;

public class VigenereEnigma implements EnigmaService
{
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static final boolean KEY_REQUIRED = true;
    public static final String NAME = "VigenereEnigma";
    private String key;

    public VigenereEnigma()
    {
        // default constructor
    }

    public VigenereEnigma(String deliveredKey) throws WrongKeyException
    {
        setKey(deliveredKey);

    }

    public String encipher(String text){
        String encodedString = "";
        for (Integer i = 0; i < text.length(); i++){
            String alphabet = getAlphabetCased(text.charAt(i));
            String key = getKeyCased(text.charAt(i));
            if (alphabet.indexOf(text.charAt(i)) >= 0){
                Integer charFromPassword = alphabet.indexOf(key.charAt(i % key.length()));
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
            String key = getKeyCased(text.charAt(i));
            if (alphabet.indexOf(text.charAt(i)) >= 0){
                Integer charFromPassword = alphabet.indexOf(key.charAt(i % key.length()));
                Integer charValue = alphabet.indexOf(text.charAt(i));
                Integer encodedValue = charValue - charFromPassword;
                encodedValue = this.loop(encodedValue, alphabet);
                encodedString += alphabet.charAt(encodedValue);
            }
            else {
                encodedString += text.charAt(i);
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

    public void setKey(String deliveredKey) throws WrongKeyException
    {
        if (isAlphabetical(deliveredKey) && (!(deliveredKey.isEmpty())))
            this.key = deliveredKey.toLowerCase();

        else
            throw new WrongKeyException("Given key must contain only letters from latin alphabet!");

    }

    private boolean isAlphabetical(String key)
    {
        String lowerCaseLatinAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseLatinAlphabet = lowerCaseLatinAlphabet.toUpperCase();

        for (Character c : key.toCharArray())
        {
            String letter = Character.toString(c);

            if (!(lowerCaseLatinAlphabet.contains(letter) || upperCaseLatinAlphabet.contains(letter)))
            {
                return false;
            }
        }

        return true;
    }

    public static Integer loop(Integer movedCharValue, String alphabet){
        if (movedCharValue < 0){
            movedCharValue = alphabet.length() + movedCharValue;
        } else if(movedCharValue >= alphabet.length()){
            movedCharValue -= alphabet.length();
        }

        return movedCharValue;
    }



    public String getAlphabetCased(Character chr){
        String alphabet;
        if(Character.isUpperCase(chr)) alphabet = this.alphabet.toUpperCase();
        else alphabet = this.alphabet;

        return alphabet;
    }

    public String getKeyCased(Character chr){
        String key;
        if(Character.isUpperCase(chr)) key = this.key.toUpperCase();
        else key = this.key;

        return key;
    }
}
