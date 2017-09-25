package ticketseller;

import java.util.EnumMap;
import java.util.Map;

class TicketClerk {
    // TODO Cashier class with put and get features.
    private Map<Banknote, Integer> cashier;

    TicketClerk() {
        cashier = new EnumMap<>(Banknote.class);
    }

    String tickets(int[] input) {
        // Handle when there is nobody
        if (input.length == 0){
            return "YES";
        }

        // Handle invalid inputs
        for (int i : input) {
            if (i != 25 && i!=50 && i!=100){
                throw new IllegalArgumentException("Allowed banknotes are: 25$, 50$ and" +
                        " 100$");
            }
        }

        for (int i : input) {
            Banknote current = Banknote.getByValue(i);
            cashier.get(current);
        }

        return "YES";
    }
}
