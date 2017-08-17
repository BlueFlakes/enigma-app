package enigma;

import services.EnigmaService;



public class PolybiusSquareEnigma implements EnigmaService
{
    private static final boolean KEY_REQUIRED = true;
    private static final String NAME = "PolybiusSquareEnigma";
    private String key;


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