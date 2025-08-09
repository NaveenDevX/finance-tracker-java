import java.util.*;

public class FinanceManager {
    private Map<String, List<Transaction>> userTransactions = new HashMap<>();

    public List<Transaction> getTransactions(String username) {
        return userTransactions.computeIfAbsent(username, k -> new ArrayList<>());
    }

    public void addTransaction(String username, Transaction t) {
        getTransactions(username).add(t);
    }

    public double getBalance(String username) {
        double income = 0, expense = 0;
        for (Transaction t : getTransactions(username)) {
            if ("Income".equalsIgnoreCase(t.getType())) income += t.getAmount();
            else expense += t.getAmount();
        }
        return income - expense;
    }
}
