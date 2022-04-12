package Extractors;

import static Extractors.ExtractorFactory.ExtraorType.*;

public class ExtractorFactory {

    public enum ExtraorType
    {
        Stock,
        Amount,
    }

    public static IEntiyExtractor createExtractor(ExtraorType extractor) throws Exception {
        switch (extractor)
        {
            case Stock:
                return  new StockExtractor();
            case Amount:
                return  new AmountExtracor();
            default:
                throw  new Exception(String.format("Extracot of tpe [%s]not implementecd",extractor));
        }
    }

}
