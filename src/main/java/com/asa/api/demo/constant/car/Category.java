package com.asa.api.demo.constant.car;

import com.asa.api.demo.constant.Dropdown;
import com.asa.api.demo.constant.MessageErrorCode;
import com.asa.api.demo.exception.ResourceException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    Urban("UR"),
    Sedan("SE"),
    Break("BR"),
    Monospace("MO"),
    Coupe("CO"),
    Cabriolet("CA"),
    Pickup("PI");
    private final String code;
    Category(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Category of(String code) {
        Assert.hasText(code, MessageErrorCode.INVALID.getCode());
        for(Category cat: values()){
            if(cat.getCode().equals(code)) {
                return cat;
            }
        }
        throw new ResourceException(MessageErrorCode.NOT_FOUND.getCode());
    }

    public static List<Dropdown> getDropDown() {
        List<Dropdown> result = new ArrayList<>();
        for(Category cat: values()) {
            result.add(Dropdown.builder().key(cat.getCode()).value(cat.name()).build());
        }
        return result;
    }
}
