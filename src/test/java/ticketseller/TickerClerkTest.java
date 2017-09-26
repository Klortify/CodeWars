package ticketseller;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TickerClerkTest {
    private TicketClerk clerk;

    @BeforeMethod
    public void setup() {
        clerk = new TicketClerk();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void ignoreInvalidBanknotes(){
        int[] input = {25,25,50,10};

        clerk.work(input);
    }

    @Test
    public void shouldSayYesWhenNobodyComes(){
//        int[] input = new int[]{};

        String output = clerk.work(new int[]{});

        Assert.assertTrue(output.matches("YES"));
    }

    @Test(dataProvider = "Sellable")
    public void shouldSayYes(int... input){
        String output = clerk.work(input);

        Assert.assertEquals(output, "YES");
    }

    @Test(dataProvider = "nonSellable")
    public void shouldSayNo(int... input){
        String output = clerk.work(input);

        Assert.assertEquals(output, "NO");
    }

    @DataProvider(name = "Sellable")
    public Object[][] sellableScenarios(){

        return new Object[][]{
                {25},
                {25,25,50},
                {25,25,25,100},
                {25,50,25,100},
                {25,50,25,100},
                {25,25,25,25,50,100},
                {25,25,25,25,50,100,50,50}  // tricky
        };
    }

    @DataProvider(name = "nonSellable")
    public Object[][] nonSellableScenarios(){

        return new Object[][]{
                {50},
                {100},
                {25,100},
                {25,25,50,50,50}
        };
    }

}
