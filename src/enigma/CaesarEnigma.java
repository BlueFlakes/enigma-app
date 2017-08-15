package enigma;

import services.EnigmaService;

public class CaesarEnigma implements EnigmaService {

	public static final boolean KEY_REQUIRED = true;
    private static Integer key;

	public CaesarEnigma() {
	}

	public String encipher(String text){
		String encipheredText = "";
		char letter;
		char encipheredLetter;
		int asciiCode;

			for (int i=0; i<text.length(); i++) {
				letter = text.toUpperCase().charAt(i);
				asciiCode = (int) letter;

				if (asciiCode > 64 && asciiCode < 91) {
					asciiCode += key;

					if (asciiCode > 90) {
						asciiCode -= 26;
					}

					encipheredLetter = (char) asciiCode;

					if ( !Character.isUpperCase(text.charAt(i)) ) {
						encipheredLetter = Character.toLowerCase(encipheredLetter);
					}

				} else {
					encipheredLetter = text.charAt(i);
				}

				encipheredText += encipheredLetter;
			}

		return encipheredText;
	}

	public String decipher(String text) {
        String decipheredText = "";
		char letter;
		char decipheredLetter;
		int asciiCode;

			for (int i=0; i<text.length(); i++) {
				letter = text.toUpperCase().charAt(i);
				asciiCode = (int) letter;

				if (asciiCode > 64 && asciiCode < 91) {
					asciiCode -= key;

					if (asciiCode < 65) {
						asciiCode += 26;
					}

					decipheredLetter = (char) asciiCode;

					if ( !Character.isUpperCase(text.charAt(i)) ) {
						decipheredLetter = Character.toLowerCase(decipheredLetter);
					}

				} else {
					decipheredLetter = text.charAt(i);
				}

				decipheredText += decipheredLetter;
			}

		return decipheredText;
	}

	public String getName(){
		return "CaesarEnigma";
	}

	public boolean isKeyRequired(){
		return KEY_REQUIRED;
	}

	public void setKey(String s) {
        if (isInteger(s)) {
            key = Integer.parseInt(s);
        }
    }

    public static boolean isInteger(String key) {
        try {
            Integer.parseInt(key);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
