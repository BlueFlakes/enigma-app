package enigma;
import services.EnigmaService;


public class AtbashEnigma implements EnigmaService {

    public static final boolean KEY_REQUIRED = false;
	public static final String NAME = "AtbashEnigma";
    private static final String ALPHABET = generateAlphabet();

	public AtbashEnigma() {
	}

	public String encipher(String text){
		String encipheredText = "";
        String encipheredLetter;
        Integer alphabetSize = ALPHABET.length() - 1;
        Integer charValueDifference;

        for (Character chr : text.toCharArray()) {
            String temp_chr = Character.toString(chr);
            temp_chr = temp_chr.toUpperCase();

            if (ALPHABET.contains(temp_chr)) {
                charValueDifference = alphabetSize - ALPHABET.indexOf(temp_chr);
                encipheredLetter = String.valueOf(ALPHABET.charAt(charValueDifference));

                if (Character.isLowerCase(chr)) {
                    encipheredLetter = encipheredLetter.toLowerCase();
                }

                encipheredText = encipheredText.concat(encipheredLetter);
            }
            else {
                encipheredText += chr;
            }
        }

        return encipheredText;
	}

	public String decipher(String text) {
        return encipher(text);
	}

	public String getName(){
		return NAME;
	}

	public boolean isKeyRequired(){
		return KEY_REQUIRED;
	}

	public void setKey(String s) {}

    public static String generateAlphabet(){
       String alphabet = "";

       for(int asciiCode = 65; asciiCode < 91; asciiCode++){
           alphabet += (char)(asciiCode);
       }
       return alphabet;
   }
}
