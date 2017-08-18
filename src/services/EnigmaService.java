package services;
import enigma.exceptions.WrongKeyException;

public interface EnigmaService {
    public String encipher(String text);
    public String decipher(String text);
    public String getName();
    public boolean isKeyRequired();
    public void setKey(String key) throws WrongKeyException;
    public boolean getIsUsed();
    public void changeIsUsed();
}
