package Extractors;

public class LineResult {

    public String stock;
    public String amount;

    public LineResult(String stock,String amount)
    {
        this.stock = stock;
        this.amount = amount;
    }

    public  boolean isEqal(LineResult other)
    {
        return  this.stock.equals(other.stock) && this.amount.equals(other.amount);
    }





}
