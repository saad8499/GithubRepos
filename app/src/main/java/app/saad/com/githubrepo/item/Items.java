package app.saad.com.githubrepo.item;

/**
 * Created by saad9 on 6/28/17.
 */

public class Items {

    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String watchersCount;
    private String language;
    private String CollaboratorsUrl;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    private String avatar_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(String watchersCount) {
        this.watchersCount = watchersCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCollaboratorsUrl() {
        return CollaboratorsUrl;
    }

    public void setCollaboratorsUrl(String collaboratorsUrl) {
        CollaboratorsUrl = collaboratorsUrl;
    }
}
