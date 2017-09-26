package ticketseller.domain;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BanknoteTest {

    @Test
    public void shouldGetValueIfValid(){
        Assert.assertNotNull(Banknote.getByValue(25));
        Assert.assertNotNull(Banknote.getByValue(50));
        Assert.assertNotNull(Banknote.getByValue(100));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldIgnoreIfInvalid1(){
        Banknote.getByValue(0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldIgnoreIfInvalid2(){
        Banknote.getByValue(30);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldIgnoreIfInvalid3(){
        Banknote.getByValue(-10);
    }
}
