package com.asa.api.demo.constant.car;

import com.asa.api.demo.constant.MessageErrorCode;
import com.asa.api.demo.exception.ResourceException;
import org.springframework.util.Assert;

public enum Energy {
    Essence("ES"),
    Diesel("GA"),
    Electric("EL");
    private final String code;
    Energy(String code) {
        this.code = code;
    }
    public String getCode(){
        return code;
    }
    public static Energy of(String code) {
        Assert.hasText(code, MessageErrorCode.INVALID.getCode());
        for(Energy eng: Energy.values()){
            if(eng.getCode().equals(code)) {
                return eng;
            }
        }
        throw new ResourceException(MessageErrorCode.NOT_FOUND.getCode());
    }
}
