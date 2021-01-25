package collection;

public class ExampleEntity {

    private String id;
    private String text;
    private Integer softer;

    public ExampleEntity(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSofter() {
        return softer;
    }

    public ExampleEntity setSofter(Integer softer) {
        this.softer = softer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExampleEntity that = (ExampleEntity) o;

        if (!id.equals(that.id)) return false;
        if (!text.equals(that.text)) return false;
        return softer.equals(that.softer);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + softer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ExampleEntity{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", softer=" + softer +
                '}';
    }
}
