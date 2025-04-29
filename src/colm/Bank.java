package colm;

import java.util.HashMap;
import java.util.Map;
//
public class Bank {
    private Map<String, AccountCheck> accountCheckMap;

    public Bank(){
        accountCheckMap = new HashMap<>();
    }

    public void addAccountCheck(AccountCheck accountCheck){
        accountCheckMap.put(accountCheck.getAccountNumber(),accountCheck);
    }

    public AccountCheck getAccountCheck(String accountNumber){
        return accountCheckMap.get(accountNumber);
    }
}
