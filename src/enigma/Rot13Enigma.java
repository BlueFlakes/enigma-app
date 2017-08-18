package enigma;

import services.EnigmaService;

public class Rot13Enigma implements EnigmaService {

	private static final boolean KEY_REQUIRED = false;
	private static final String NAME = "Rot13Enigma";
    private boolean isUsed;

    public Rot13Enigma(){
        this.isUsed = false;
    }

    public boolean getIsUsed(){
        return this.isUsed;
    }

    public void changeIsUsed(){
        if(this.isUsed) this.isUsed = false;
        else this.isUsed = true;
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
					asciiCode += 13;

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

	public String decipher(String text){
		return encipher(text);
	}

	public String getName(){
		return this.NAME;
	}

	public boolean isKeyRequired(){
		return KEY_REQUIRED;
	}

	public void setKey(String key) {}

}
