import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple strategy used to buy a fix amount of shares on a specified day of every month
 * @author feijia
 *
 */
public class MonthlyBuyingStrategy implements BuyingStrategy {

	private int day;
	private BigDecimal amount;
	private BigDecimal value;
	private int lastmonth;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * 
	 * @param day
	 * @param amount
	 */
	public MonthlyBuyingStrategy(int day, BigDecimal value) {
		this.day = day;
		this.value = value;
	}

	@Override
	public Operation computeOperation(DayData dd) {
		Date d = dd.getDate();
		
		//System.out.println("Computing operation for date:" + dd.getDate());
		if (d.getDate() == this.day ||  d.getMonth()!= lastmonth){
			
			
			 lastmonth = d.getMonth();	
			//System.out.println("Found matching condition, buying day:" + d + " setting day:" + day);
			
			amount = value.divide(dd.getPrice(),2,BigDecimal.ROUND_HALF_EVEN);
			return new Operation(Operation.TYPE.BUY, amount, value, "Monthly Buy");
		}else {
			//System.out.println("Not Found matching condition, buying day:" + d);
			return new Operation(Operation.TYPE.NOP, null, null, "");
		}
	}

}
