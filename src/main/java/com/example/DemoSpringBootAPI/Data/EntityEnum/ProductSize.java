package com.example.DemoSpringBootAPI.Data.EntityEnum;

public enum ProductSize {
	S("S"),
    M("M"),
    L("L"),
    XL("XL"),
    XXL("2XL"),
    XXXL("3XL");

    private String value;

    ProductSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
