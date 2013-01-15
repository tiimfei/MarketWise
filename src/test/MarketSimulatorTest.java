import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;

public class MarketSimulatorTest extends TestCase {
	public void testSimulate() throws Exception {
		PortfolioManager pm = new PortfolioManager();
		MarketSimulator  ms = new MarketSimulator(pm);
		MarketDataSource ds = new MarketDataSource("resource/js300.csv");
		ms.addDataSource(ds);
		ms.addBuyingStrategy(new MonthlyBuyingStrategy(1,new BigDecimal(2000d)));
		SimpleSellingStrategy ss = new SimpleSellingStrategy(null, 0.30,-0.1);
		ms.addSellingStrategy(ss);
		pm.registerHistoryListener(ss);
		ms.simulate();
		
		MarketReport report = ms.getReport();
		
		System.out.println("Final holdings: " + report.getHolding() + " shares.");
		System.out.println("Today's price: " + ds.getLastDayData().getPrice());
		System.out.println("Today's total value:" + ds.getLastDayData().getPrice().multiply( report.getHolding()) + "RMB.");
		System.out.println("Total investment: " + report.getInvestment() + "RMB.");
		System.out.println("Profit: " + report.getProfit(ds.getLastDayData()) + " RMB.");
		//System.out.println("Total investment in (money*time):" + report.getInvestmentMT() + " RMB*Month");
		
		//if(printRecord)
		printRecords(report);
		
	}

	private void printRecords(MarketReport report) {
		System.out.println("Print History Record: " );
		int buyCount=0;
		int sellCount=0;
		for(HistoryRecord rec : report.getHistoryRecords()){
			System.out.println("----------------");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("Date:" + sdf.format(rec.getDate()));
			System.out.println("Type:" + rec.getOperation());
			if(rec.getOperation().getType() == Operation.TYPE.BUY)
				buyCount++;
			else if(rec.getOperation().getType()==Operation.TYPE.SELL)
				sellCount++;
			System.out.println("Reason:" + rec.getReason());
			
		}
		System.out.println("Total Buy records:" + buyCount);
		System.out.println("Total Sell records:" + sellCount);
	}
}
