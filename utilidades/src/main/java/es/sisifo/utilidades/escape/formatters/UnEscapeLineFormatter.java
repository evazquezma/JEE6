package es.sisifo.utilidades.escape.formatters;

import org.apache.commons.text.StringEscapeUtils;

public class UnEscapeLineFormatter implements LineFormatter {

	@Override
	public String format(String line) {		
		return StringEscapeUtils.unescapeJava(line).trim();
	}

}
