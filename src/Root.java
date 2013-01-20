import com.fasterxml.jackson.annotation.JsonIgnore;


public class Root {
	
	private String query;
	@JsonIgnore
	private String sourceLanguage;
	@JsonIgnore
	private String targetLanguage;
	private Primary[] primaries;
	@JsonIgnore
	private WebDefinition[] webDefinitions;
	
	public WebDefinition[] getWebDefinitions() {
		return webDefinitions;
	}
	public void setWebDefinitions(WebDefinition[] webDefinitions) {
		this.webDefinitions = webDefinitions;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getSourceLanguage() {
		return sourceLanguage;
	}
	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}
	public String getTargetLanguage() {
		return targetLanguage;
	}
	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}
	public Primary[] getPrimaries() {
		return primaries;
	}
	public void setPrimaries(Primary[] primaries) {
		this.primaries = primaries;
	}

}
