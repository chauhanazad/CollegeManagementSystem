package com.example.college.ui.gallery;

public class Images {

    String image_id;
    String image_paths;
    String response;

    public Images(String image_id, String image_paths, String response) {
        this.image_id = image_id;
        this.image_paths = image_paths;
        this.response = response;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getImage_paths() {
        return image_paths;
    }

    public void setImage_paths(String image_paths) {
        this.image_paths = image_paths;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
