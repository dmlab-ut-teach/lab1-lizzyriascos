package lab1_2020_Moneybag;

import lab1_2020_Moneybag.Money;
import lab1_2020_Moneybag.MoneyBag;

import java.util.*;

public class MoneyProgram {

	public static void main(String[] args) {
		List<MoneyBag> myMoneyBags = new ArrayList<MoneyBag>();
		
		for (int i=0; i<5; i++) {
			MoneyBag newMoneyBag = new MoneyBag();
			Money newMoney = new Money( 1, "USD");
			if (newMoney.amount() > 5) {
				newMoneyBag.addMoney(newMoney);
				myMoneyBags.add(newMoneyBag);
			}
		}
		
		System.out.println("I have " + myMoneyBags.size() + " Bags");
	}

}
