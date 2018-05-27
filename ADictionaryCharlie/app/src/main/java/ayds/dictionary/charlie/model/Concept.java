package ayds.dictionary.charlie.model;

public class Concept {
    private String concept;
    private String meaning;
    private Source source;

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getConcept() {
        return concept;
    }

    public String getMeaning() {
        return meaning;
    }

    public Source getSource() {
        return source;
    }
}
