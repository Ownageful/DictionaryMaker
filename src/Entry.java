import com.fasterxml.jackson.annotation.JsonIgnore;


public class Entry {

	@JsonIgnore
	private String type;
	private Term[] terms;
	@JsonIgnore
	private Entry[] entries;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Term[] getTerms() {
		return terms;
	}
	public void setTerms(Term[] terms) {
		this.terms = terms;
	}
	public Entry[] getEntries() {
		return entries;
	}
	public void setEntries(Entry[] entries) {
		this.entries = entries;
	}
	
}
