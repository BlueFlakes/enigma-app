package enigma;

import services.EnigmaService;
import enigma.exceptions.WrongKeyException;

public class VigenereEnigma implements EnigmaService
{
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static final boolean KEY_REQUIRED = true;
    public static final String NAME = "VigenereEnigma";
    private String key;

    public VigenereEnigma() {}

    public VigenereEnigma(String deliveredKey)
    {
<<<<<<< HEAD
        setKey(deliveredKey);
=======
        this.setKey(deliveredKey);
    }

    private void checkDeliveredKey(String deliveredKey) throws WrongKeyException{
        for (Character c : deliveredKey.toCharArray()){
            if(alphabet.indexOf(c) < 0 && alphabet.toUpperCase().indexOf(c) < 0){
                throw new WrongKeyException("Given keys should contains only latin letters!");
            }
        }
>>>>>>> fd6f3de3a1236a3df19d59bdfdb3387022edaef2
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
<<<<<<< HEAD
        this.key = deliveredKey.toLowerCase();

=======
        try {
            checkDeliveredKey(deliveredKey);
		} catch (WrongKeyException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
        this.key = deliveredKey;
>>>>>>> fd6f3de3a1236a3df19d59bdfdb3387022edaef2
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
        String word = a.encipher("taki tam tekst");
        System.out.println(word);
    }

    public String getAlphabetCased(Character chr){
        String alphabet;
        if(Character.isUpperCase(chr)) alphabet = this.alphabet.toUpperCase();
        else alphabet = this.alphabet;

        return alphabet;
    }
}
