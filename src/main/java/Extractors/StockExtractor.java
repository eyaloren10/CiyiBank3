package Extractors;

import java.util.List;
import java.util.Locale;

public class StockExtractor implements  IEntiyExtractor {





    @Override
    public String findEntity(String[] words) {
        for(String word: words)
        {
            if (word.length()==3 && word.toUpperCase(Locale.ROOT).equals(word))
            {
                return  word;
            }
        }
        return "N/A";
    }
}
