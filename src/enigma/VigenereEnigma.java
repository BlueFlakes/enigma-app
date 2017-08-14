package enigma;

import services.EnigmaService;


public class VigenereEnigma implements EnigmaService
{
    public static final boolean KEY_REQUIRED = true;
    public static final String NAME = "VigenereEnigma";
    private String key;


    public VigenereEnigma(String deliveredKey)
    {
        this.key = deliveredKey;

    }

    public String encipher(String text)
    {

        return null;
    }

    public String decipher(String text)
    {

        return null;
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
