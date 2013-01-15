import java.math.BigDecimal;

public class Operation {
	static public enum TYPE {
		BUY, SELL, NOP
	};

	private TYPE type;
	private BigDecimal amount;
	private BigDecimal value;
	private String reason;

	public Operation(TYPE type, BigDecimal amount, BigDecimal value,
			String reason) {
		this.type = type;
		this.amount = amount;
		this.value = value;
		this.reason = reason;
	}

	public Object getType() {

		return type;
	}

	public BigDecimal getAmount() {

		return amount;
	}

	public BigDecimal getValue() {

		return value;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[" + type + ",");
		builder.append(amount + " share,");
		
		builder.append(value + " RMB" + ",");
		builder.append(reason + this.reason + "]");
		return builder.toString();

	}

	public String getReason() {
		return this.reason;

	}

}
