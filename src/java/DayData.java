import java.math.BigDecimal;
import java.util.Date;

public class DayData {

	private Date date;
	private BigDecimal price;

	public Date getDate() {

		return date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setDate(Date date) {
		this.date = date;

	}

	public void setPrice(BigDecimal price) {
		this.price = price;

	}

}
