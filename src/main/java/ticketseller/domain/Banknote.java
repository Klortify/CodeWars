package ticketseller.domain;

public enum Banknote {
    TWENTYFIVE(25),
    FIFTY(50),
    HUNDRED(100);

    private int value;

    Banknote(int i) {
        value = i;
    }

    public static Banknote getByValue(int i){
        if (i == 25){
            return TWENTYFIVE;
        }
        else if (i == 50) {
            return FIFTY;
        }
        else if (i == 100) {
            return HUNDRED;
        }
        else {
            throw new IllegalArgumentException("Banknote of given value does not exist.");
        }

    }
}
