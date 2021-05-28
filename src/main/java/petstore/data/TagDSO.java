package petstore.data;

public class TagDSO {

    private int id;
    private String name;

    public TagDSO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
