import java.time.LocalDate;
public class Transaction{
    private String type;
    private double amount;
    private LocalDate date;
    private String category;
    public Transaction(String type, double amount, LocalDate date, String category)
    {
        this.type = type;
        this.amount = amount;
        this.date= date;
        this.category = category;
    }
    public String getType(){
        return type;
    }
    public double getAmount(){
        return amount;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getCategory(){
        return category;
    }
    public String toString() {
        return type + ": " + category + " $" + amount + " (" + date + ")";
    }
}