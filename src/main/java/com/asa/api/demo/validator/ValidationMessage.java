package com.asa.api.demo.validator;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ValidationMessage {
    private boolean error;
    private Map<String, String> errors;
}
