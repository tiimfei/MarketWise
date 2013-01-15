import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PortfolioManager {

	private BigDecimal holding;
	private BigDecimal invest;
	private MarketReport report;
	private Collection<HistoryListener> historyListeners;
	/**
	 * yuanDay is a concept of the money you spent in the market multiplied by
	 * the periods of this investment
	 */
	private float yuanDay;
	private Date lastOpDate;

	public PortfolioManager() {
		report = new MarketReport(this);
		historyListeners = new ArrayList<HistoryListener>();
		holding=new BigDecimal(0d);
		invest=new BigDecimal(0d);
	}

	public void performOp(Operation op, DayData dd) {
		if (op.getType() == Operation.TYPE.BUY) {
			
			holding = holding.add(op.getAmount());
			invest = invest.add(op.getValue());

		} else if (op.getType() == Operation.TYPE.SELL) {
			if (holding.compareTo(op.getAmount()) < 0 )
				throw new IllegalArgumentException(
						"Illegal operations, not sufficient holdings to sell, holding:"
								+ holding + " tried to sell:" + op.getAmount());
			holding = holding.subtract(op.getAmount());
			invest = invest.subtract(op.getValue());
		}

		if (op.getType() != Operation.TYPE.NOP) {
			HistoryRecord rec = new HistoryRecord(dd, op);
			report.addHistoryRecord(rec);
			for (HistoryListener listener : historyListeners) {
				listener.addHistoryRecord(rec);
			}
		}

	}

	private void calcYuanDay(Date date) {
		throw new NotYetImplementedException();
	}

	public MarketReport getReport() {

		return report;

	}

	public BigDecimal getHolding() {
		return holding;
	}

	public BigDecimal getInvestment() {

		return invest;
	}

	public void registerHistoryListener(HistoryListener l) {
		historyListeners.add(l);

	}

}
