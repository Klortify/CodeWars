package ticketseller;

import ticketseller.domain.Banknote;
import ticketseller.domain.ChangeNotPossibleException;

class TicketClerk {
    private Cashier cashier;

    TicketClerk() {
        cashier = new Cashier(100);
    }

    public String work(int[] input){
        String result;
        try {
            tickets(input);
            result = "YES";
        } catch (ChangeNotPossibleException e) {
            System.out.println(e.getMessage());
            result = "NO";
        }
        return result;
    }

    private void tickets(int[] input) throws ChangeNotPossibleException {
        // Handle when there is nobody
        if (input.length == 0){
            return;
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

            if (giveChange(current)) {
                cashier.in(current);
            }
            else {
                throw new ChangeNotPossibleException(current);
            }
        }
    }

    private boolean giveChange(Banknote banknote) {
        boolean canGiveChange = false;

        if (banknote == Banknote.TWENTYFIVE) {
            canGiveChange = true;
        }
        else if (banknote == Banknote.FIFTY) {
            canGiveChange = cashier.out(Banknote.TWENTYFIVE);
        }
        else if (banknote == Banknote.HUNDRED) {
            canGiveChange = changeHundred();

        }
        return canGiveChange;
    }

    private boolean changeHundred() {
        boolean canGiveChange;

        // This is algorithmically the preferred way to change 100$
        if (cashier.hasOne(Banknote.TWENTYFIVE) && cashier.hasOne(Banknote.FIFTY)) {
            cashier.out(Banknote.FIFTY);
            cashier.out(Banknote.TWENTYFIVE);
            canGiveChange = true;
        }
        else if (cashier.has(Banknote.TWENTYFIVE, 3)) {
            cashier.out(Banknote.TWENTYFIVE);
            cashier.out(Banknote.TWENTYFIVE);
            cashier.out(Banknote.TWENTYFIVE);
            canGiveChange = true;
        }
        else {
            canGiveChange = false;
        }
        return canGiveChange;
    }
}
