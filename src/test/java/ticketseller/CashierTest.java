package ticketseller;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ticketseller.domain.Banknote;

public class CashierTest {

    Cashier cashier;

    @BeforeMethod
    private void setup() {
        cashier = new Cashier(10);
    }

    @Test
    public void registerShouldBeEmptyAfterInit() {
        for (Banknote banknote : Banknote.values()) {
            Assert.assertFalse(cashier.out(banknote));
        }
    }

    @Test
    public void shouldAvoidNewBanknotesWhenFull(){
        Banknote banknote = Banknote.FIFTY;

        for (int i = 0; i < cashier.getCapacity(); i++) {
            Assert.assertTrue(cashier.in(banknote));
        }
        Assert.assertFalse(cashier.in(banknote));
    }
}
