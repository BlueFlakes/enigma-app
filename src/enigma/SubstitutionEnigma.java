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
        if(isKeyValid(deliveredKey)) this.key = deliveredKey;
        else throw new WrongKeyException("Key should contain 26 chars of latin alphabet without repetitions.");

    }

    private static boolean isKeyValid(String key){
        for(int i = 97; i <= 122; i++){
            if(key.indexOf((char) i) < 0) return false;
        }
        return (key.length() == 26);
    }


}