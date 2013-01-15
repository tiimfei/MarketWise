import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MarketDataSource {

	List<DayData> dayData;
	private int count = 0;

	public MarketDataSource(String fn) {
		dayData = new ArrayList<DayData>(100);
		createDayData(fn);
	}

	private void createDayData(String fn) {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(
				fn);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				String token = st.nextToken();
				DayData dd = new DayData();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				dd.setDate(df.parse(token));
				token = st.nextToken();

				token = st.nextToken();
				dd.setPrice(BigDecimal.valueOf(Double.valueOf(token)));
				dayData.add(0, dd);

			}
			System.out.println(dayData.size() + " day data processed.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DayData getNextDayData() {
		if (count < dayData.size())
			return dayData.get(count++);
		else
			return null;
	}

	public DayData getLastDayData() {
		return dayData.get(dayData.size()-1);
	}

}
