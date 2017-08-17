package enigma;

import services.EnigmaService;
import enigma.exceptions.WrongKeyException;


public class SubstitutionEnigma implements EnigmaService
{
    private static final boolean KEY_REQUIRED = false;
    private static final String NAME = "SubstitutionEnigma";
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

    public void setKey(String deliveredKey) throws WrongKeyException
    {
        this.key = deliveredKey;

    }



}