package com.giweb.supply.business.segundotalleracm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeedResponseDTO {
    private boolean success;
    private String message;
    private Map<String, List<Integer>> createdEntities;
    private Map<String, String> credentials;
}
