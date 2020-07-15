package com.asa.api.demo.constant.car;

import com.asa.api.demo.constant.Dropdown;
import com.asa.api.demo.constant.MessageErrorCode;
import com.asa.api.demo.exception.ResourceException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public enum Mark {
    Peugeot("PEU"),
    Renault("REN"),
    Citroen("CIT"),
    Bmw("BMW"),
    Volkswagen("VOL");

    private final String code;
    Mark(String code) {
        this.code =code;
    }
    public String getCode () {
        return code;
    }

    public static Mark of(String code) {
        Assert.hasText(code, MessageErrorCode.INVALID.getCode());
        for(Mark mar: values()){
            if(mar.getCode().equals(code)) {
                return mar;
            }
        }
        throw new ResourceException(MessageErrorCode.NOT_FOUND.getCode());
    }

    public static List<Dropdown> getDropDown() {
        List<Dropdown> result = new ArrayList<>();
        for (Mark m : values()) {
            result.add(Dropdown.builder().key(m.getCode()).value(m.name()).build());
        }
        return result;
    }
}
