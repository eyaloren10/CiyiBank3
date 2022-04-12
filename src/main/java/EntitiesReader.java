import Extractors.ExtractorFactory;
import Extractors.IEntiyExtractor;
import Extractors.LineResult;

import java.io.FileNotFoundException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class EntitiesReader {

    private  IDBReader reader;



    public  EntitiesReader(IDBReader reader)
    {
        this.reader= reader;
    }

    public static void checkCsvReader() throws Exception {
        EntitiesReader reader = new EntitiesReader(new CsvReader("c:\\input.csv"));
        List<LineResult> results =
        reader.readAllEntities();


    }

    private String [] fixWords(String[] words)
    {
        List<String> fixedWords = new ArrayList<>();
        for(String word : words)
        {
            String addedWord = word;
            char []wordChars =   word.toCharArray();
            if(wordChars.length >= 3 && wordChars[wordChars.length-1]=='M' && wordChars[wordChars.length-2] =='M' )
            {
                boolean isCharecterFound = false;
                for(int i=0;i<wordChars.length-2;i++ )
                {
                    if(!Character.isDigit(wordChars[i]))
                    {
                        isCharecterFound = true;
                        break;
                    }
                }
                if(!isCharecterFound)
                {
                    addedWord =word.replace("MM","000000");
                }


            }
            fixedWords.add(addedWord);

        }

       return   fixedWords.stream().toArray(String[]::new);


    }





    public  List<LineResult> readAllEntities() throws Exception {


        List<LineResult> results = new ArrayList<>();

         List<String> lines = reader.getAllLines();
         for(String line : lines)
         {

             String []lineWords = line.split(" ");

             lineWords =fixWords(lineWords);



             String stock="";
             String amount="";
             ExtractorFactory.ExtraorType extractors[] = new ExtractorFactory.ExtraorType[] {ExtractorFactory.ExtraorType.Stock,
                     ExtractorFactory.ExtraorType.Amount};
             for(ExtractorFactory.ExtraorType exType : extractors)
             {
                 IEntiyExtractor extractor = ExtractorFactory.createExtractor(exType);
                 String ent =extractor.findEntity(lineWords);
                 System.out.println(String.format("%s : %s",exType,ent));
                 if(exType.equals(ExtractorFactory.ExtraorType.Stock))
                 {
                     stock= ent;
                 }
                 else if(exType.equals(ExtractorFactory.ExtraorType.Stock))
                 {
                     amount= ent;
                 }


             }
             results.add(new LineResult(stock,amount));

         }
         return  results;





    }





}
