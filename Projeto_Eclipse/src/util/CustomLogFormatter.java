package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter {

	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");

	public CustomLogFormatter() {
	}

	@Override
	public String format(LogRecord record) {
		StringBuilder builder = new StringBuilder(1000);
		builder.append("[").append(record.getLevel()).append("] - ");
        builder.append(df.format(new Date(record.getMillis()))).append(" - ");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
	}

}
