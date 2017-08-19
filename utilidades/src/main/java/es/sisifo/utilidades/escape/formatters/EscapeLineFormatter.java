package es.sisifo.utilidades.escape.formatters;

import org.apache.commons.text.StringEscapeUtils;

public class EscapeLineFormatter implements LineFormatter {

	@Override
	public String format(String line) {
		return StringEscapeUtils.escapeJava(line).trim();
	}

}
