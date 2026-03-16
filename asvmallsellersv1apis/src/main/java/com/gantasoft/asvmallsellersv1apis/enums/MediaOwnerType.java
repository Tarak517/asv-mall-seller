package com.gantasoft.asvmallsellersv1apis.enums;

public enum MediaOwnerType {
    PRODUCT(1),
    CATEGORY(2),
    SELLER(3);

    private final int value;

    MediaOwnerType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
