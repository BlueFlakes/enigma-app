package enigma;

import services.EnigmaService;
import enigma.exceptions.WrongKeyException;

public class RailFenceEnigma implements EnigmaService {

    public static final boolean KEY_REQUIRED = true;
    private static Integer key;
	public static final String NAME = "RailFenceEnigma";
    private boolean isUsed;

    public RailFenceEnigma(){
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
        String[] lines = splitIntoLines(text, false);
        String encipheredText = "";

        for (String line : lines) {
            encipheredText += line;
        }

        return encipheredText;
	}

	public String decipher(String text) {
        String[] lines = splitIntoLines(text, true);
        String decipheredText;

        /* replace ?s with the actual letter */
        int charIndex = 0;
        for (int i = 0; i < key; ++i) {
            while (lines[i].contains("?")) {
                String letter = String.valueOf(text.charAt(charIndex));
                lines[i] = lines[i].replaceFirst("\\?", letter);
                charIndex++;
            }
        }

        decipheredText = condenseToString(lines, text);

        return decipheredText;
	}

	public String getName(){
		return NAME;
	}

	public boolean isKeyRequired(){
		return KEY_REQUIRED;
	}

	public void setKey(String s) throws WrongKeyException {
		if (CaesarEnigma.isInteger(s)) {
            key = Integer.parseInt(s) % 26;
        }
    }

    private static String[] splitIntoLines(String text, boolean enciphered) {
        String[] result = generateEmptyStrings();
        int lineIndex = 0;
        int direction = -1;

        for (char c : text.toCharArray()) {
            String letter = String.valueOf(c);
            /* if the message is already encrypted, use '?' as placeholder */
            result[lineIndex] += enciphered ? "?" : letter;
            direction *= (lineIndex == 0 || lineIndex == key - 1) ? -1 : 1;
            lineIndex += direction;
        }
        return result;
    }

    private static String[] generateEmptyStrings() {
        String[] strings = new String[key];
        for (int i = 0; i < key; ++i) {
            strings[i] = "";
        }
        return strings;
    }

    private static String condenseToString(String[] lines, String text) {

        String decipheredText = "";
        int lineIndex = 0;
        int direction = -1;

        for (int i = 0; i < text.length(); ++i) {
            /* Add first letter to decipheredText by removing it from the line */
            String letter = String.valueOf(lines[lineIndex].charAt(0));
            lines[lineIndex] = lines[lineIndex].substring(1);
            decipheredText += letter;
            direction *= (lineIndex == 0 || lineIndex == key - 1) ? -1 : 1;
            lineIndex += direction;
        }

        return decipheredText;
    }
}
