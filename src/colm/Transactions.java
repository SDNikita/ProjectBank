package colm;

import java.time.LocalDateTime;

public class Transactions {
    private String timeTransaction;
    private String type; //тип транзакции
    private double amount; //сумма транзакции
    //
    public Transactions(String type, double amount){
        this.type = type;
        this.amount = amount;
        this.timeTransaction = LocalDateTime.now().toString();
    }

    @Override
    public String toString(){
        return "Тип: " + type + ", Сумма: " + amount + ", Время транзакции: " + timeTransaction;
    }
}
