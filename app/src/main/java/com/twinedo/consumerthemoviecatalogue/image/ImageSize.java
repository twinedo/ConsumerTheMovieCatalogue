package com.twinedo.consumerthemoviecatalogue.image;


public enum ImageSize {
    w500(500, "w500");

    public int key;
    private String value;

    ImageSize(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}