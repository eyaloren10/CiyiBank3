package Extractors;

import java.util.Locale;

public class AmountExtracor implements IEntiyExtractor  {


    @Override
    public String findEntity(String[] words) {

        for(String word: words)
        {
            if(word.chars().allMatch( Character::isDigit ))
            {
                return word;
            }
        }
        return "N/A";


    }
}
