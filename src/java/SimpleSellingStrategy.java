import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class SimpleSellingStrategy implements SellingStrategy, HistoryListener {

	private BigDecimal profitBar;
	private BigDecimal lostBar;
	private Collection<BuyingRecord> buyingrecords;

	/**
	 * A simple selling strategy for selling in a specific profit or lost
	 * 
	 * @param profitRateBar 0.01 for 1%
	 * @param lostRateBar  0.01 for 1%
	 */
	public SimpleSellingStrategy(Collection<HistoryRecord> records,
			double profitRateBar, double lostRateBar) {
		this.profitBar = new BigDecimal(profitRateBar);
		this.lostBar = new BigDecimal(lostRateBar);
		buyingrecords = new ArrayList<BuyingRecord>();
		if (records != null)
			for (HistoryRecord rec : records) {
				addHistoryRecord(rec);
			}
	}

	public void addHistoryRecord(HistoryRecord rec) {
		if (rec.getOperation().getType() == Operation.TYPE.BUY)
			this.buyingrecords.add(new BuyingRecord(rec));
	}

	@Override
	public Operation computeOperation(DayData dd) {

		for (BuyingRecord brec : buyingrecords) {
			if (brec.isSold)
				continue;
			if (testProfitBar(brec, dd)){
				brec.setSold(true,dd.getDate());
				return new Operation(Operation.TYPE.SELL, brec.getAmount(),
						brec.getAmount().multiply( dd.getPrice()), "Sell because matching the profit bar ");
			}
				
			else if( testLostBar(brec, dd)) {
				brec.setSold(true,dd.getDate());
				return new Operation(Operation.TYPE.SELL, brec.getAmount(),
						brec.getAmount().multiply( dd.getPrice()), "Sell because matching the lost bar ");	
				
			}
		}
		return null;

	}
	/**
	 * Test whether current price match the profit bar trigger, 
	 * either the current profit is higher than the bar or the lost is higher than the bar, the return is true 
	 * @param brec
	 * @param dd
	 * @return
	 */

	private boolean testProfitBar(BuyingRecord brec, DayData dd) {
		BigDecimal profitRate = dd.getPrice().subtract( brec.getPrice()).divide( brec.getPrice(),BigDecimal.ROUND_HALF_EVEN);
		if (profitRate.compareTo(profitBar)>=0) {
			System.out.println("Matched one selling condition, on " + java.text.SimpleDateFormat.getDateInstance().format(dd.getDate()) +  " currentPrice:" + dd.getPrice() + ", Profit Rate:"
					+ profitRate +" buy record: " + brec);
			return true;
		} else
			return false;
	}

	private boolean testLostBar(BuyingRecord brec, DayData dd) {
		// TODO Auto-generated method stub
		return false;
	}

}
