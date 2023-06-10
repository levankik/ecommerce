package com.softgen.softlab.ecommercebackend.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataChange <T> {

    private ChangeType changeType;

    private T data;

    public DataChange(ChangeType changeType, T data) {
        this.changeType = changeType;
        this.data = data;
    } //


    public enum ChangeType {
        INSERT,
        UPDATE,
        DELETE
    }
}
