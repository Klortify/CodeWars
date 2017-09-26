package ticketseller;

import ticketseller.domain.Banknote;
import ticketseller.domain.ChangeNotPossibleException;

class TicketClerk {
    private Cashier cashier;
    private Integer priceOfTicket = 25;

    TicketClerk() {
        cashier = new Cashier(100);
    }

    void tickets(int[] input) throws ChangeNotPossibleException {
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
                throw new ChangeNotPossibleException();
            }

        }
    }

    private boolean giveChange(Banknote banknote) {
        boolean canGiveChange = false;

        if (banknote == Banknote.TWENTYFIVE) {
            canGiveChange = true;
        }
        else if (banknote == Banknote.FIFTY) {
            canGiveChange = (cashier.out(Banknote.TWENTYFIVE));
        }
        // TODO - This can make the state of the cashier inconsistent.
        else if (banknote == Banknote.HUNDRED) {
            canGiveChange = (cashier.out(Banknote.FIFTY) && cashier.out(Banknote
                    .TWENTYFIVE) ||
                    cashier.out(Banknote.TWENTYFIVE) && cashier.out(Banknote
                            .TWENTYFIVE) && cashier.out(Banknote.TWENTYFIVE));

        }
        return canGiveChange;
    }
}
