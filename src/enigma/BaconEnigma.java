package enigma;

import services.EnigmaService;



public class BaconEnigma implements EnigmaService
{
    private static final boolean KEY_REQUIRED = true;
    private static final String NAME = "BaconEnigma";
    private String key;

    public static void main(String[] dupy){
        BaconEnigma x = new BaconEnigma();
        x.setKey("Hold off until we compromise. ");
        String word = x.encipher("strikenow");
        System.out.println(word);
        word = x.decipher(word);
        System.out.println(word);
    }

    public String encipher(String text)
    {
        text = text.toLowerCase();
        String mask = createMask(text);
        String cryptogram = getKeyCased(mask, this.key);
        return cryptogram;
    }

    private static String createMask(String text ){
        String mask = "";
        for(Character chr : text.toCharArray()){
            mask += convertCharToBacon(chr);
        }

        return mask;
    }


    private static String convertCharToBacon(Character chr){
        if (97 <= chr && chr <= 122) {
            Integer charValue = ((int) chr) - 97;

            String binaryValue = Integer.toBinaryString(charValue);

            while(binaryValue.length() < 5) binaryValue = "0".concat(binaryValue);

            return binaryValue;
        } 
        else {
            return "00000";
        }
    }

    private static String getKeyCased(String mask, String key){
        String casedKey = "";
        int swift = 0;
        for(int i = 0; i < mask.length()-swift; i++){
            Character keyChar = key.charAt(i % key.length());

            if (keyChar.equals(' ')){
                casedKey += ' ';
                swift--;
            }
            else {
                casedKey += matchLetterCase(mask.charAt(i+swift), keyChar);
            }
        }

        return casedKey;
    }

    private static Character matchLetterCase(Character maskChar, Character keyChar){
        Character letter = null;
        if(maskChar == '1'){
            letter = Character.toUpperCase(keyChar);
        }
        else if (maskChar == '0'){
            letter = Character.toLowerCase(keyChar);
        }
        
        return letter;
    }

    public String decipher(String text)
    {
        String mask = computeMask(text);
        String decipheredText = "";
        int i = 0;
        while(mask.length() >= 5){
            int charValue = Integer.parseInt(mask.substring(i, i+5), 2);
            char decipheredChar = (char)(charValue + 97);
            decipheredText += decipheredChar;
            mask = mask.substring(i+5, mask.length());
        }

        return decipheredText;
    }

    private static String computeMask(String text){
        String mask = "";
        for(Character chr : text.toCharArray()){
            if (chr.equals(' ')){
                
            } else if (Character.isUpperCase(chr)){
                mask += '1';
            } else {
                mask += '0';
            }
        }
        return mask;
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
}