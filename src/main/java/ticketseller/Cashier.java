package ticketseller;

import ticketseller.domain.Banknote;

import java.util.EnumMap;
import java.util.Map;

public class Cashier {
    private Map<Banknote, Integer> register;
    private int capacity;

    public Cashier(int capacity) {
        this.capacity = capacity;
        register = new EnumMap<>(Banknote.class);
        for (Banknote banknote : Banknote.values()) {
            register.put(banknote, 0);
        }
    }

    public boolean in(Banknote banknote) {
        return addOne(banknote);
    }

    public boolean out(Banknote banknote) {
        return removeOne(banknote);
    }

    private boolean removeOne(Banknote banknote) {
        Integer currentCount = register.get(banknote);
        if (currentCount > 0) {
            register.put(banknote, currentCount - 1);
            return true;
        } else {
            return false;
        }
    }

    private boolean addOne(Banknote banknote) {
        Integer currentCount = register.get(banknote);
        if (currentCount < capacity) {
            register.put(banknote, currentCount + 1);
            return true;
        } else {
            return false;
        }
    }

    public int getCapacity() {
        return capacity;
    }
}
