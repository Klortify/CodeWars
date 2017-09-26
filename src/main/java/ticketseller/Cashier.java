package ticketseller;

import ticketseller.domain.Banknote;

import java.util.EnumMap;
import java.util.Map;

public class Cashier {
    private Map<Banknote, Integer> banknotes;
    private int capacity;

    public Cashier(int capacity) {
        this.capacity = capacity;
        banknotes = new EnumMap<>(Banknote.class);
        for (Banknote banknote : Banknote.values()) {
            banknotes.put(banknote, 0);
        }
    }

    public boolean in(Banknote banknote) {
        return addOne(banknote);
    }

    public boolean out(Banknote banknote) {
        return removeOne(banknote);
    }

    private boolean removeOne(Banknote banknote) {
        Integer currentCount = banknotes.get(banknote);
        if (currentCount > 0) {
            banknotes.put(banknote, currentCount - 1);
            return true;
        } else {
            return false;
        }
    }

    private boolean addOne(Banknote banknote) {
        Integer currentCount = banknotes.get(banknote);
        if (currentCount < capacity) {
            banknotes.put(banknote, currentCount + 1);
            return true;
        } else {
            return false;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean hasOne(Banknote banknote) {
        return has(banknote, 1);
    }

    public boolean has(Banknote banknote, int howMany) {
        return banknotes.get(banknote) >= howMany;
    }
}
