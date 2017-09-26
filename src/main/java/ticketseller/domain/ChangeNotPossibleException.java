package ticketseller.domain;

public class ChangeNotPossibleException extends Exception {
    public ChangeNotPossibleException(Banknote banknote) {
        super("Sorry, cannot give change for " + banknote);
    }
}
