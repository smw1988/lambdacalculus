package menq.lex;

public class MenqSymbol extends MenqObject {

	private String token;
	
	public MenqSymbol(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
}
