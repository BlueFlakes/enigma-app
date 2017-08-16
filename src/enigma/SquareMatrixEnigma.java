package enigma;

import services.EnigmaService;
import java.util.Scanner;



public class SquareMatrixEnigma implements EnigmaService
{
    private static final boolean KEY_REQUIRED = false;
    private static final String NAME = "SquareMatrixEnigma";
    private String key;

    public String encipher(String text)
    {
        String result = "";

        Integer rowSize = calculateRowSize(text);
        text = fillWithSpaces(text);
        rowSize = calculateRowSize(text);

        Integer column = 0;
        Integer row = 0;
        for(Character c : text.toCharArray()){
            if(column >= rowSize){
                column = 0;
                row++;
            }
            result = result.concat(String.valueOf(text.charAt((column*rowSize)+row)));
            column ++;
        }
        
        return result;
    }

    private Integer calculateRowSize(String text){
        return (int) Math.sqrt(text.length());
    }

    private String fillWithSpaces(String text){
        Integer rowSize = calculateRowSize(text);
        if(text.length() != (rowSize*rowSize)){
            rowSize++;
            while(text.length() < rowSize*rowSize){
            text += " ";
            }
        }
        return text;
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

    private String extendText(String text)
    {
        return null;
    }
}