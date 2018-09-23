package menq.lex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class MenqReader {

	private BufferedReader reader;
	
	public MenqReader(Reader inputReader) {
		this.reader = new BufferedReader(inputReader);
	}
	
	public List<String> readTokens() throws IOException {
		ArrayList<String> tokens = new ArrayList<>();
		
		int readResult;
		while ((readResult = reader.read()) >= 0) {
			char c = (char) readResult;
			
			if (isBlockSeparator(c)) {
				tokens.add(String.valueOf(c));
				continue;
			}
			
			if (Character.isWhitespace(c)) {
				continue;
			}
			
			if (c == '"') {
				tokens.add(readStringLiteral());
			} else {
				tokens.add(readSymbol(c));
			}
		}
		
		return tokens;
	}
	
	private String readStringLiteral() throws IOException {
		StringBuilder sb = new StringBuilder("\"");
		
		char c = 0;
		while (c != '"') {
			int readResult = reader.read();
			if (readResult < 0) {
				throw new MenqReaderException("Endding quote is missing");
			}
			
			c = (char) readResult;
			sb.append(c);
		}
		
		return sb.toString();
	}
	
	private String readSymbol(char c) throws IOException {
		StringBuilder sb = new StringBuilder(String.valueOf(c));
		
		while (true) {
			reader.mark(1);
			int readResult = reader.read();
			
			if (readResult < 0)
				break;
			
			c = (char) readResult;
			if (isBlockSeparator(c) || Character.isWhitespace(c)) {
				reader.reset();
				break;
			}
			
			sb.append(c);
		}
		
		return sb.toString();
	}
	
	private static boolean isBlockSeparator(char c) {
		return c == '(' || c == ')';
	}
}
