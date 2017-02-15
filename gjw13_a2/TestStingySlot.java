// TestStingySlot.java
// Author: Greg Wills
// NetId: gjw13
// JUnit Testing - Assignment 2

package gjw13_a2;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;

public class TestStingySlot {
	
	MyStingySlot s; // make Stingy slot object to use throughout testing
	//StingySlot ss;
	
	@Before 
	public void before() 
	{
		s = new MyStingySlot();
		//ss = new StingySlot();
	} 
	
	// Test to see if payoff is right for five of a kind
	@Test
	public void fiveOfAKindPayoff()
	{
		int[] m = { 27,27,27,27,27 };
		s.setSpin(m);
		assertTrue("5 of a kind check: ", s.payoff() == 1000000);
	}
	
	// Test to see if payoff is right for four of a kind
	@Test
	public void fourOfAKindPayoff()
	{
		int[] m = { 27,27,27,27,60 };
		s.setSpin(m);
		assertTrue("4 of a kind check: ", s.payoff() == 10000);
	}
	
	// Test to see if number is less than 1 or greater than 60
	@Test
	public void rangeTest()
	{
		s.doSpin();
		for(int i=0; i<s.spinLength; i++)
		{
			assertTrue("range test: ", s.spin[i] <= 60 && s.spin[i] >=1);
		}
	}
	
	// Test to see if full house payoff is correct
	@Test
	public void fullHousePayoff()
	{
		int[] m = { 22,22,19,19, 19 };
		s.setSpin(m);
		assertTrue("Full house payoff check", s.payoff() == 500);
	}
	
	// Test to see if 3 of a kind payoff is correct
	@Test
	public void threeOfAKindPayoff()
	{
		int[] m = { 10,10,10, 59, 14 };
		s.setSpin(m);
		assertTrue("3 of a kind payoff check", s.payoff() == 10);
	}
	
	// Test to see if one pair payoff is correct
	@Test
	public void onePairPayoff()
	{
		int[] m = { 10,10,13,15,19 };
		s.setSpin(m);
		assertTrue("One pair payoff check", s.payoff() == 2);
	}
	
	// Test to see if 2 pair payoff is correct
	@Test
	public void twoPairPayoff()
	{
		int[] m = { 10,10,20, 20, 14 };
		s.setSpin(m);
		assertTrue("Two pair payoff check", s.payoff() == 4);
	}
	
	// Test to see if 5 of a kind and all perfect squares payoff is correct
	@Test
	public void fiveAndPerfectPayoff()
	{
		int[] m = { 16,16,16,16,16 };
		s.setSpin(m);
		assertTrue("5 of a kind and all perfect squares", s.payoff() == 1000000.5);
	}
	
	// Test to see if 5 of a kind and all 42s payoff is correct
	@Test
	public void fiveAnd42Payoff()
	{
		int[] m = { 42,42,42,42,42 };
		s.setSpin(m);
		assertTrue("5 of a kind and all 42 check", s.payoff() == 1000001.75);
	}
	
	// Test to see if 5 of a kind and all divisible by 17 payoff is correct
	@Test
	public void fiveAndDivisibleBy17()
	{
		int[] m = { 51,51,51,51,51 };
		s.setSpin(m);
		assertTrue("5 of a kind, all /17 check", s.payoff() == 1000000.85);
	}
	
	// Test to see if 4 of a kind and all perfect squares
	@Test
	public void fourAndPerfectSquares()
	{
		int[] m = { 16,16,16,16,25 };
		s.setSpin(m);
		assertTrue("4 of a kind, all perfect squares", s.payoff() == 10000.5);
	}
	
	// Test to see if 4 of a kind and 4 42s
	@Test 
	public void fourAndFour42s()
	{
		int[] m = { 42,42,42,42, 14 };
		s.setSpin(m);
		assertTrue("4 of a kind, four 42s check", s.payoff() == 10001.4);
	}
	
	// Test to see if 4 of a kind and five #s divisible by 17
	@Test
	public void fourAndAllDivisibleBy17()
	{
		int[] m = { 34,34,34,34,17 };
		s.setSpin(m);
		assertTrue("4 of a kind, five #s divsible by 17", s.payoff() == 10000.85);
	}
	
	// Test to see if 4 of a kind and five #s divisible by 17
	@Test
	public void fourAndFourDivisibleBy17()
	{
		int[] m = { 34,34,34,34,12 };
		s.setSpin(m);
		assertTrue("4 of a kind, four #s divsible by 17", s.payoff() == 10000.68);
	}
	
	// Test to see if 4 of a kind and four #s divisible by 17, one perfect square
	@Test
	public void fourDivisibleBy17With1PerfectSquare()
	{
		int[] m = { 34,34,34,34,16 };
		s.setSpin(m);
		assertTrue("4 of a kind, four #s divsible by 17", s.payoff() == 10000.78);
	}
	
	// Test to see if 4 of a kind and four #s divisible by 17, one perfect square
	@Test
	public void fourDivisibleBy17WithOne42()
	{
		int[] m = { 34,34,34,34,42 };
		s.setSpin(m);
		assertTrue("4 #s divsible by 17, one 42", s.payoff() == 10001.03);
	}
	
	// Test to see if the slot machine will turn a profit
	@Test
	public void averageWinnings()
	{
		double totalWinnings = 0.0;
		double totalWinnings2 = 0.0;
		for(int i=0; i < 1000000;i++){
			s.doSpin();
			//ss.setSpin(m);
			totalWinnings += s.payoff();
			//totalWinnings2 += ss.payoff();
		}
		DecimalFormat df;
		df = new DecimalFormat("#.##");
		double d = (2000000 - totalWinnings);
		
		System.out.println("total winnings for 1mil spins: $"+df.format(totalWinnings));
		System.out.println("total profit for 1mil spins:   $" + (df.format(d)));
//		System.out.println("versus totalWinnings2: $" + df.format(totalWinnings2));
//		System.out.println("the difference is: " + (totalWinnings-totalWinnings2));
		assertTrue("Average winnings test", totalWinnings < (2*1000000));
	}
	
	// Test to see if payoff is correct for 3 of a kind and all perfect squares
	@Test
	public void threeOfAKindPerfectSquares()
	{
		int[] m = { 16,25,4,4,4 };
		s.setSpin(m);
		assertTrue("3 of a kind, all perfect squares", s.payoff() == 10.5);
	}
	
	// Test to see if payoff is correct for 3 of a kind and 4 perfect squares
	@Test
	public void threeOfAKind4PerfectSquares()
	{
		int[] m = { 16,4,4,20,4 };
		s.setSpin(m);
		assertTrue("3 of a kind, 4 perfect squares", s.payoff() == 10.4);
	}
	
	// Test to check if payoff is correct for 3 of a kind and 3 perfect squares
	@Test
	public void threeOfAKind3PerfectSquares()
	{
		int[] m = { 18,4,4,20,4 };
		s.setSpin(m);
		assertTrue("3 of a kind, 3 perfect squares", s.payoff() == 10.3);
	}
	
	// Test to check if payoff is correct for 3 of a kind and 2 perfect squares
	@Test
	public void threeOfAKind2PerfectSquares()
	{
		int[] m = { 15,15,15,4,16 };
		s.setSpin(m);
		assertTrue("3 of a kind, 2 perfect squares", s.payoff() == 10.2);
	}
	
	// Test to check if payoff is correct for 3 of a kind and 2 perfect squares
	@Test
	public void threeOfAKind1PerfectSquares()
	{
		int[] m = { 15,15,15,4,3 };
		s.setSpin(m);
		assertTrue("3 of a kind, 1 perfect squares", s.payoff() == 10.1);
	}
	
	// ***** Need to keep going on this *****
	
	// Test to check if one perfect square match
	@Test
	public void onePerfectSquare()
	{
		int[] m = { 6,9,10,11,12 };
		s.setSpin(m);
		assertTrue("One perfect square check", s.payoff() == 0.1);
	}
	
	// Test to check if two perfect squares payoff is correct
	@Test
	public void twoPerfectSquares()
	{
		int[] m = { 6,9,16,11,12 };
		s.setSpin(m);
		assertTrue("Two perfect squares check", s.payoff() == 0.2);
	}
	
	// Test to check if three perfect squares payoff is correct
	@Test
	public void threePerfectSquares()
	{
		int[] m = { 36,49,16,3,7 };
		s.setSpin(m);
		// Sometimes rounding error occurs and causes the test to fail
		assertTrue("Three perfect squares check", s.payoff() == 0.30000000000000004);
	}
	
	// Test to check if payoff is correct for 4 perfect squares
	@Test
	public void fourPerfectSquares()
	{
		int[] m = {4,9,16,25,37};
		s.setSpin(m);
		assertTrue("Four perfect squares check", s.payoff() == 0.4);
	}
	
	// Test to check if payoff is correct for 5 perfect squares
	@Test
	public void fivePerfectSquares()
	{
		int[] m = {1,9,16,25,49};
		s.setSpin(m);
		assertTrue("Five perfect squares check", s.payoff() == 0.5);
	}
	
	// Test to check if payoff is correct for 
	@Test
	public void perfectSquareTest()
	{
		int[] m = {1,9,16,25,49};
		int[] m1 = {1,4,36,49,16};
		s.setSpin(m);
		assertTrue("Perfect squares check", s.payoff() == 0.5);
		s.setSpin(m1);
		assertTrue("Perfect squares check", s.payoff() == 0.5);
	}
	
	// Test to check if all numbers divisible by 17 are correct
	@Test
	public void divisibleBy17Test()
	{
		int[] m = {17,34,51,2,23};
		s.setSpin(m);
		assertTrue("Divisible by 17", s.payoff() == .51);
	}
	
	// Test to check if all numbers divisible by 17 are correct
	@Test
	public void is42Check()
	{
		int[] m = {42, 5,6,7,8};
		s.setSpin(m);
		assertTrue("Divisible by 17", s.payoff() == .35);
	}
	
}
