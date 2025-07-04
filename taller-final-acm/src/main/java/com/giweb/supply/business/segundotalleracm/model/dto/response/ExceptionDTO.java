package com.giweb.supply.business.segundotalleracm.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDTO {
    private String message;
    private String details;

    public ExceptionDTO(String message) {
        this.message = message;
    }

    public ExceptionDTO(String message, String details) {
        this.message = message;
        this.details = details;
    }
}
