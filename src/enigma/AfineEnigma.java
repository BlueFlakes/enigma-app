package enigma;

import services.EnigmaService;



public class AfineEnigma implements EnigmaService
{
    private static final boolean KEY_REQUIRED = true;
    private static final String NAME = "AfineEnigma";
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String key;
    private Integer key1;
    private Integer key2;

    public static void main(String[] args){
        AfineEnigma x = new AfineEnigma();
        x.setKey("19:8");
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
        this.key = deliveredKey;
        String[] parts = this.key.split(":");
    
        this.key1 = Integer.valueOf(parts[0]);
        this.key2 = Integer.valueOf(parts[1]);
    }


}