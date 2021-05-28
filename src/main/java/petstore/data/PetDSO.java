package petstore.data;

import java.util.List;

public class PetDSO {

    private Integer id;
    private CategoryDSO category;
    private String name;
    private List<String> photoUrls;
    private List<TagDSO> tags;
    private String status;

    private PetDSO(Builder builder) {
        this.id = builder.id;
        this.category = builder.category;
        this.name = builder.name;
        this.photoUrls = builder.photoUrls;
        this.tags = builder.tags;
        this.status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public CategoryDSO getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public List<TagDSO> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {

        private Integer id;
        private CategoryDSO category;
        private String name;
        private List<String> photoUrls;
        private List<TagDSO> tags;
        private String status;

        public Builder id(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder category(final CategoryDSO category) {
            this.category = category;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder photoUrls(final List<String> photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }

        public Builder tags(final List<TagDSO> tags) {
            this.tags = tags;
            return this;
        }

        public Builder status(final String status) {
            this.status = status;
            return this;
        }

        public PetDSO build() {
            return new PetDSO(this);
        }
    }






}
