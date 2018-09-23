package menq.lex;

public class MenqLiteral extends MenqObject {

	private String value;
	
	public MenqLiteral(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
