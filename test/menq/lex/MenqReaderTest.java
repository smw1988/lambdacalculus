package menq.lex;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MenqReaderTest {

	@Test
	void testTokenization() throws IOException {
		List<String> tokens;
		
		tokens = tokenize("(a b \"cde\")");
		assertEquals(5, tokens.size());
		assertEquals("\"cde\"", tokens.get(3));
		
		tokens = tokenize("() \"\" \"ab \n cd\"ef()");
		assertEquals(7, tokens.size());
		assertEquals(")", tokens.get(1));
		assertEquals("\"\"", tokens.get(2));
		assertEquals("ef", tokens.get(4));
		
		Assertions.assertThrows(MenqReaderException.class, () -> tokenize("() \"ab"));
		
		tokens = tokenize("abc");
		assertEquals(1, tokens.size());
	}
	
	static List<String> tokenize(String code) throws IOException {
		StringReader input = new StringReader(code);
		MenqReader reader = new MenqReader(input);
		return reader.readTokens();
	}

}
