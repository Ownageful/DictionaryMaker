import com.fasterxml.jackson.annotation.JsonIgnore;


public class Term {
	

	@JsonIgnore
	private String type;
	
	private String text;

	@JsonIgnore
	private String language;

	@JsonIgnore
	private Label[] labels;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Label[] getLabels() {
		return labels;
	}
	public void setLabels(Label[] labels) {
		this.labels = labels;
	}

}
