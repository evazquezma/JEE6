package es.sisifo.utilidades.escape;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.text.StringEscapeUtils;

import es.sisifo.utilidades.escape.formatters.EscapeLineFormatter;
import es.sisifo.utilidades.escape.formatters.LineFormatter;

public class Encoding {

	public void mostrarFichero(final String filePath, final LineFormatter formatter) {
		final Path path = Paths.get(filePath);
		
		int lines = 0;
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {
			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {
				lines ++;
				System.out.println(formatter.format(currentLine));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("Total: " + lines + " líneas");
	}

	
	public static void main(String[] args) {
		final Encoding encoding = new Encoding();
		//encoding.mostrarFichero("src/main/resources/traducciones/traducciones_encoded_es.properties", new UnEscapeLineFormatter());
		encoding.mostrarFichero("src/main/resources/traducciones/traducciones_unencoded_gl.properties", new EscapeLineFormatter());		
	}
}
