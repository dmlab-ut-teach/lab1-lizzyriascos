package lab1_2020_Moneybag;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import lab1_2020_Moneybag.Money;
import lab1_2020_Moneybag.MoneyBag;

public class MoneyTest {
        private Money f12CHF;
        private Money f14CHF;
        private Money f7USD;
        private Money f21USD;

        private MoneyBag fMB1;
        private MoneyBag fMB2;

        @Before
        public void initializeBag() {
                f12CHF= new Money(12, "CHF");
                f14CHF= new Money(14, "CHF");
                f7USD= new Money( 7, "USD");
                f21USD= new Money(21, "USD");

                fMB1= new MoneyBag(f12CHF, f7USD);
                fMB2= new MoneyBag(f14CHF, f21USD);
        }
        
        @Test
        public void testBagMultiply() {
                // {[12 CHF][7 USD]} *2 == {[24 CHF][14 USD]}
                Money bag[]= { new Money(24, "CHF"), new Money(14, "USD") };
                MoneyBag expected= new MoneyBag(bag);
                assertThat(expected, is(fMB1.multiply(2))); 
                assertThat(fMB1, is(fMB1.multiply(1)));
                assertThat(fMB1.multiply(0).isZero(), is(true));
        }
        
        @Test
        public void testBagNegate() {
                // {[12 CHF][7 USD]} negate == {[-12 CHF][-7 USD]}
                Money bag[]= { new Money(-12, "CHF"), new Money(-7, "USD") };
                MoneyBag expected= new MoneyBag(bag);
                assertThat(expected, is(fMB1.negate()));
        }
        
        @Test
        public void testBagSimpleAdd() {
                // {[12 CHF][7 USD]} + [14 CHF] == {[26 CHF][7 USD]}
                Money bag[]= {};
                MoneyBag expected= new MoneyBag(bag);
                assertThat(expected, is(fMB1.add(f14CHF)));
        }
        
        @Test
        public void testBagSubtract() {
                // {[12 CHF][7 USD]} - {[14 CHF][21 USD] == {[-2 CHF][-14 USD]}
                Money bag[]= { new Money(-2, "CHF"), new Money(-14, "USD") };
                MoneyBag expected= new MoneyBag(bag);
                assertThat(expected, is(fMB1.subtract(fMB2)));
        }
        
        @Test
        public void testBagSumAdd() {
                // {[12 CHF][7 USD]} + {[14 CHF][21 USD]} == {[26 CHF][28 USD]}
                Money bag[]= { new Money(26, "CHF"), new Money(28, "USD") };
                MoneyBag expected= new MoneyBag(bag);
                assertThat(expected, is(fMB1.add(fMB2)));
        }
        
        @Test
        public void testIsZero() {
                assertThat(fMB1.subtract(fMB1).isZero(), is(true)); 

                Money bag[] = { new Money (0, "CHF"), new Money (1, "USD") };
                assertThat(new MoneyBag(bag).isZero(), is(true));
        }
        
        @Test
        public void testMixedSimpleAdd() {
                // [12 CHF] + [7 USD] == {[12 CHF][7 USD]}
                Money bag[]= { f12CHF, f7USD };
                MoneyBag expected= new MoneyBag(bag);
                assertThat(expected, is(f12CHF.add(f7USD)));
        }
        
        @Test
        public void testMoneyBagEquals() {
                assertThat(!fMB1.equals(null), is(true)); 

                assertThat(fMB1, is(fMB1));
                MoneyBag equal= new MoneyBag(new Money(12, "CHF"), new Money(7, "USD"));
                assertThat(fMB1.equals(equal), is(true));
                assertThat(!fMB1.equals(f12CHF), is(true));
                assertThat(!f12CHF.equals(fMB1), is(true));
                assertThat(!fMB1.equals(fMB2), is(true));
        }
        
        @Test
        public void testMoneyBagHash() {
                MoneyBag equal= new MoneyBag(new Money(12, "CHF"), new Money(7, "USD"));
                assertThat(fMB1.hashCode(), is(equal.hashCode()));
        }
        
        @Test
        public void testMoneyEquals() {
                assertThat(!f12CHF.equals(null), is(true)); 
                Money equalMoney= new Money(12, "CHF");
                assertThat(f12CHF, is(f12CHF));
                assertThat(f12CHF, is(equalMoney));
                assertThat(f12CHF.hashCode(), is(equalMoney.hashCode()));
                assertThat(!f12CHF.equals(f14CHF), is(true));
        }
        
        @Test
        public void testMoneyChangeCurrency() {
        	// Complete this test based on your codebase       
                
                
                
        	// Change this assert function based on test requirements
        	assertThat(true, is(false));
        }
        
        @Test
        public void testMoneyHash() {
                assertThat(!f12CHF.equals(null), is(true)); 
                Money equal= new Money(12, "CHF");
                assertThat(f12CHF.hashCode(), is(equal.hashCode()));
        }
        
        @Test
        public void testNormalize() {
                Money bag[]= { new Money(26, "CHF"), new Money(28, "CHF"), new Money(6, "CHF") };
                MoneyBag moneyBag= new MoneyBag(bag);
                Money expected[]= { new Money(60, "CHF") };
                // note: expected is still a MoneyBag
                MoneyBag expectedBag= new MoneyBag(expected);
                assertThat(expectedBag, is(moneyBag));
        }
        
        @Test
        public void testNormalize2() {
                // {[12 CHF][7 USD]} - [12 CHF] == [7 USD]
                Money expected= new Money(7, "USD");
                assertThat(expected, is(fMB1.subtract(f12CHF)));
        }
        
        @Test
        public void testNormalize3() {
                // {[12 CHF][7 USD]} - {[12 CHF][3 USD]} == [4 USD]
                Money s1[]= { new Money(12, "CHF"), new Money(3, "USD") };
                MoneyBag ms1= new MoneyBag(s1);
                Money expected= new Money(4, "USD");
                assertThat(expected, is(fMB1.subtract(ms1)));
        }
        
        @Test
        public void testNormalize4() {
                // [12 CHF] - {[12 CHF][3 USD]} == [-3 USD]
                Money s1[]= { new Money(12, "CHF"), new Money(3, "USD") };
                MoneyBag ms1= new MoneyBag(s1);
                Money expected= new Money(-3, "USD");
                assertThat(expected, is(f12CHF.subtract(ms1)));
        }
        
        @Test
        public void testPrint() {
                assertThat("[12 CHF]", is(f12CHF.toString()));
        }
        
        @Test
        public void testSimpleAdd() {
                // [12 CHF] + [14 CHF] == [26 CHF]
                Money expected= new Money(26, "CHF");
                assertThat(expected, is(f12CHF.add(f14CHF)));
        }
        
        @Test
        public void testSimpleBagAdd() {
                // [14 CHF] + {[12 CHF][7 USD]} == {[26 CHF][7 USD]}
                Money bag[]= { new Money(26, "CHF"), new Money(7, "USD") };
                MoneyBag expected= new MoneyBag(bag);
                assertThat(expected, is(f14CHF.add(fMB1)));
        }
        
        @Test
        public void testSimpleMultiply() {
                // [14 CHF] *2 == [28 CHF]
                Money expected= new Money(28, "CHF");
                assertThat(expected, is(f14CHF.multiply(2)));
        }
        
        @Test
        public void testSimpleNegate() {
                // [14 CHF] negate == [-14 CHF]
                Money expected= new Money(-14, "CHF");
                assertThat(expected, is(f14CHF.negate()));
        }
        
        @Test
        public void testSimpleSubtract() {
                // [14 CHF] - [12 CHF] == [2 CHF]
                Money expected= new Money(2, "CHF");
                assertThat(expected, is(f14CHF.subtract(f12CHF)));
        }
}
