package test.myjava.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

import com.myjava.practice.securities.Portfolio;
import com.myjava.practice.securities.Security;
import com.myjava.practice.securities.SecurityType;
import com.myjava.practice.securities.proto.*;

public class Securities2ProtoTest {

	@Test
	public void testWriteRead() {
		testWrite();
		testRead();
	}

	@Test
	public void testWrite() {
		// create Security
		Securities.Security secStock = Securities.Security.newBuilder()
				.setId("E123SSOMY")
				.setName("holding 2")
				.setType(Securities.SecurityType.Stock)
				.build();
		Securities.Security secOE = Securities.Security.newBuilder()
				.setId("FOUSA000I")
				.setName("holding 3")
				.setType(Securities.SecurityType.OpenEndFund)
				.build();
		// create Holding
		Securities.Holding holding1 = Securities.Holding.newBuilder()
				.setSecurity(Securities.Security.newBuilder()
						.setId("E000S3REE")
						.setName("holding 1")
						.setType(Securities.SecurityType.Stock)
						.build())
				.setWeight(0.20)
				.build();
		Securities.Holding holding2 = Securities.Holding.newBuilder()
				.setSecurity(secStock)
				.setWeight(0.31)
				.build();
		Securities.Holding holding3 = Securities.Holding.newBuilder()
				.setSecurity(secOE)
				.setWeight(0.49)
				.build();
		Securities.Holding holding4 = Securities.Holding.newBuilder()
				.setSecurity(Securities.Security.newBuilder()
						.setId("M678YYUD80")
						.setName("holding 4")
						.setType(Securities.SecurityType.ETF)
						.build())
				.setWeight(0.30)
				.build();
		Securities.Holding holding5 = Securities.Holding.newBuilder()
				.setSecurity(secStock)
				.setWeight(0.21)
				.build();
		Securities.Holding holding6 = Securities.Holding.newBuilder()
				.setSecurity(secOE)
				.setWeight(0.21)
				.build();
		
		Securities.Portfolio port1 = Securities.Portfolio.newBuilder()
				.setId("1483")
				.setDate((new Date(2012,12,31)).getTime())
				.addAllHoldings(Arrays.asList(new Securities.Holding[] {holding1, holding2, holding3}))
				.build();
		Securities.Portfolio port2 = Securities.Portfolio.newBuilder()
				.setId("1465")
				.setDate((new Date(2013,3,31)).getTime())
				.addAllHoldings(Arrays.asList(new Securities.Holding[] {holding4, holding2, holding3}))
				.build();
		
		Securities.ShareClass sc = Securities.ShareClass.newBuilder()
				.setId("FOMSD098UI")
				.setName("Equity Fund Share Class 1")
				.addFunds(Securities.Fund.newBuilder()
						.setInvestment(Securities.Investment.newBuilder()
								.setSecurity(Securities.Security.newBuilder()
										.setId("FOUSA8899")
										.setName("Balanced Fund Retail")
										.setType(Securities.SecurityType.OpenEndFund)
										.build())
								.addAllManagers(Arrays.asList(new String[] {"Jon", "Mike Harral"}))
								.addAllPortfolios(Arrays.asList(new Securities.Portfolio[] {port1, port2}))
								.build())
						.setManagementFee(0.15)
						.build())
				.addFunds(Securities.Fund.newBuilder()
						.setInvestment(Securities.Investment.newBuilder()
								.setSecurity(Securities.Security.newBuilder()
										.setId("FOUSA8800")
										.setName("Balanced Fund Instl")
										.setType(Securities.SecurityType.OpenEndFund)
										.build())
								.addAllManagers(Arrays.asList(new String[] {"Jon", "Mike Harral"}))
								.addAllPortfolios(Arrays.asList(new Securities.Portfolio[] {port1, port2}))
								.build())
						.setManagementFee(0.09)
						.build())
				.build();
		
		Securities.Portfolio port = Securities.Portfolio.newBuilder()
				.setId("1483")
				.setDate((new Date(2012, 12, 31)).getTime())
				.build();
		Securities.Security sec3 = Securities.Security.newBuilder()
				.setId("FOUSA000I")
				.setName("holding 3")
				.setType(Securities.SecurityType.OpenEndFund)
				.build();
		Securities.Security sec4 = Securities.Security.newBuilder()
				.setId("M678YYUD80")
				.setName("holding 4")
				.setType(Securities.SecurityType.ETF)
				.build();
		if (sc.isInitialized()) {
			System.out.println(sc.toString());
			System.out.println(sc.toByteString().toStringUtf8());
			
			final String fpath = "c:\\temp\\fop.txt";
			try (FileOutputStream fop = new FileOutputStream(fpath, true)) {
				sc.writeDelimitedTo(fop);
				//sec3.writeTo(fop);
				//sec4.writeTo(fop);

				//fop.write(sec4.toByteArray());
				//fop.write(sec3.toByteArray());
				
				//sec3.writeDelimitedTo(fop);
				//sec4.writeDelimitedTo(fop);
				//port.writeDelimitedTo(fop);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// mergeFrom...
			Securities.Security sec = sec3.toBuilder().mergeFrom(sec4).build();
			System.out.println(sec.toString());
		}
	}
	
	@Test
	public void testRead() {
		final String fpath = "c:\\temp\\fop.txt";
		try (FileInputStream fip = new FileInputStream(fpath)) {
			while (true) {
				Securities.ShareClass sc = Securities.ShareClass.parseDelimitedFrom(fip);
				// parse and build pojo ShareClass object
				
				Securities.Portfolio port = Securities.Portfolio.parseDelimitedFrom(fip);
				Portfolio oPort = new Portfolio();
				oPort.Id = port.getId();
				oPort.Date = new Date(port.getDate());
				
				//Securities.Security sec = Securities.Security.parseFrom(fip);
				Securities.Security sec = Securities.Security.parseDelimitedFrom(fip);
				if (null == sec) break;

				System.out.println(sec.toString());

				Security oSec = new Security();
				oSec.Id = sec.getId();
				oSec.Name = sec.getName();
				oSec.Type = SecurityType.values()[sec.getType().ordinal()];
				System.out.println(oSec);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
