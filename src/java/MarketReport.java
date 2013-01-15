import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;


public class MarketReport {
	

	private Collection<HistoryRecord> historyRecords;
	PortfolioManager pm;
	public MarketReport(PortfolioManager portfolioManager) {
		historyRecords = new ArrayList<HistoryRecord>();
		pm = portfolioManager;
	}
	public BigDecimal getHolding() {

		return  pm.getHolding();
	}

	public String getProfit(DayData todayData) {
		BigDecimal cur_value = todayData.getPrice().multiply( pm.getHolding());
		
		BigDecimal profit = getAllSellValue().add( cur_value).subtract( getAllBuyValue());
		return String.valueOf(profit);
	}

	private BigDecimal getAllBuyValue() {
		BigDecimal v = new BigDecimal(0) ;
		for(HistoryRecord rec: historyRecords){
			if(rec.getOperation().getType() == Operation.TYPE.BUY)
			v = v.add(rec.getValue());
		}
		return v;
	}
	private BigDecimal getAllSellValue() {
		BigDecimal v = new BigDecimal(0) ;
		for(HistoryRecord rec: historyRecords){
			if(rec.getOperation().getType() == Operation.TYPE.SELL)
			v = v.add(rec.getValue());
		}
		return v;
	}
	public Collection<HistoryRecord> getHistoryRecords() {
		return historyRecords;
	}
	public void addHistoryRecord(HistoryRecord historyRecord) {
		historyRecords.add(historyRecord);
		
	}
	/**
	 *
	 * @return total value of investment in RMB
	 */
	public String getInvestment() {
		return pm.getInvestment().toString();
	}
	/**
	 * Get the money*month value for the investment
	 * @return
	 */
	public long getInvestmentMT() {
		throw new NotYetImplementedException();
		//return 0;
	}

}
