package test.myjava.bootservice.json;

import static org.junit.Assert.*;

import org.junit.Test;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import com.myjava.bootservice.securities.*;

public class Securities2JsonTest {

	@Test
	public void testRun() {
		// anonymous class - new ABC() {};
		// http://stackoverflow.com/questions/12110450/assigning-value-for-member-variables-while-creating-instance-in-java
		Portfolio port1 = new Portfolio() {
			{
				Id = "1483";
				Date = new Date(2012, 12, 31);
				// methods to initialize ArrayList
				// http://stackoverflow.com/questions/21696784/how-to-declare-an-arraylist-with-values
				Holdings = new ArrayList<Holding>() {
					{
						add(new Holding() {{
							Id = "E000S3REE";
							Name = "holding 1"; 
							Type = SecurityType.Stock;
							Weight = 0.20; }});
						add(new Holding() {{
							Id = "E123SSOMY";
							Name = "holding 2"; 
							Type = SecurityType.Stock;
							Weight = 0.31; }});
						add(new Holding() {{
							Id = "FOUSA000I";
							Name = "holding 3"; 
							Type = SecurityType.OpenEndFund;
							Weight = 0.49; }});
					}
				};
			};
		};
		Portfolio port2 = new Portfolio() {
			{
				Id = "1465";
				Date = new Date(2013, 3, 31);
				Holdings = new ArrayList<Holding>(
					Arrays.asList(
							new Holding() {{
								Id = "M678YYUD80";
								Name = "holding 4";
								Type = SecurityType.ETF;
								Weight = 0.30; }},
							new Holding() {{
								Id = "E123SSOMY";
								Name = "holding 2";
								Type = SecurityType.ETF;
								Weight = 0.21; }},
							new Holding() {{
								Id = "FOUSA000I";
								Name = "holding 3";
								Type = SecurityType.OpenEndFund;
								Weight = 0.49; }}
					));
			};
		};
		ShareClass shareClass = new ShareClass() {
			{
				Id = "FOMSD098UI";
				Name = "Equity Fund Share Class 1";
				Funds = new ArrayList<Fund>( 
					Arrays.asList(
							new Fund() {{
								Id = "FOUSA8899";
								Name = "Balanced Fund Retail";
								MangementFee = 0.15;
								Managers = Arrays.asList("Jon", "Mike Harral");
								Portfolios = Arrays.asList(port1, port2);
								Type = SecurityType.OpenEndFund; }},
							new Fund() {{
								Id = "FOUSA8800";
								Name = "Balanced Fund Instl";
								MangementFee = 0.09;
								Managers = Arrays.asList("Jon", "Mike Harral");
								Portfolios = Arrays.asList(port1, port2);
								Type = SecurityType.OpenEndFund; }}
				));
			}
		};
		ShareClass sc = new ShareClass();
		sc.Id = "FOMSD098UI";
		sc.Name = "Equity Fund Share Class 1";
		sc.Funds = new ArrayList<Fund>( 
			Arrays.asList(
				new Fund() {{
					Id = "FOUSA8899";
					Name = "Balanced Fund Retail";
					MangementFee = 0.15;
					Managers = Arrays.asList("Jon", "Mike Harral");
					Portfolios = Arrays.asList(port1, port2);
					Type = SecurityType.OpenEndFund; }},
				new Fund() {{
					Id = "FOUSA8800";
					Name = "Balanced Fund Instl";
					MangementFee = 0.09;
					Managers = Arrays.asList("Jon", "Mike Harral");
					Portfolios = Arrays.asList(port1, port2);
					Type = SecurityType.OpenEndFund; }}
		));
		
		//assertTrue(new TestJson().run(shareClass));
		assertTrue(new TestJson().run(sc));

		//fail("Not yet implemented");
	}

}
