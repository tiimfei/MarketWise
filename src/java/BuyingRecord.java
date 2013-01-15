import java.util.Date;

public class BuyingRecord extends HistoryRecord {
	public BuyingRecord(DayData dd, Operation op) {
		super(dd, op);

	}

	public BuyingRecord(HistoryRecord rec) {
		super(rec.getDayData(), rec.op);
	}

	boolean isSold;
	long holdPeriod;

	public boolean isSold() {
		return isSold;
	}

	public void setSold(boolean isSold, Date date) {
		this.isSold = isSold;
		if (!date.after(this.date))
			throw new RuntimeException(
					"Illegal selling operation: date is before the buying date");
		;
		holdPeriod = date.getTime() - this.date.getTime();

	}

}
