import java.math.BigDecimal;
import java.util.Date;

public class HistoryRecord {
	Date date;
	Operation op;
	
	private DayData dayData;
	

	public HistoryRecord(DayData dd, Operation op) {
		this.date = dd.getDate();
		this.op = op;
		
		this.dayData = dd;
	}

	public Date getDate() {
		return date;
	}

	public String toString() {
		return op.toString() + "on " +  java.text.SimpleDateFormat.getDateInstance().format(dayData.getDate() );
	}
	public Operation getOperation(){
		return op;
	}

	public String getReason() {

		return this.op.getReason();
	}

	public BigDecimal getAmount() {
		return op.getAmount();
	}

	public BigDecimal getValue() {

		return op.getValue();
	}

	public BigDecimal getPrice(){
		return dayData.getPrice();
	}

	public DayData getDayData() {
		
		return dayData;
	}
}
