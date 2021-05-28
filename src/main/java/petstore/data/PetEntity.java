package petstore.data;

import java.util.List;

public class PetEntity {

    private Integer id;
    private String name;
    private PetStatus petStatus;
    private List<TagDSO> tags;
    private CategoryDSO category;
    private List<String> photoUrls;
    private boolean deleted = false;

    public PetEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.petStatus = builder.petStatus;
        this.tags = builder.tags;
        this.category = builder.category;
        this.photoUrls = builder.photoUrls;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PetStatus getPetStatus() {
        return petStatus;
    }

    public List<TagDSO> getTags() {
        return tags;
    }

    public CategoryDSO getCategory() {
        return category;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPetStatus(PetStatus petStatus) {
        this.petStatus = petStatus;
    }

    public void setTags(List<TagDSO> tags) {
        this.tags = tags;
    }

    public void setCategory(CategoryDSO category) {
        this.category = category;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public static class Builder {

        private Integer id;
        private String name;
        private PetStatus petStatus;
        private List<TagDSO> tags;
        private CategoryDSO category;
        private List<String> photoUrls;

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

        public Builder petStatus(final PetStatus petStatus) {
            this.petStatus = petStatus;
            return this;
        }

        public PetEntity build() {
            return new PetEntity(this);
        }

    }

}
