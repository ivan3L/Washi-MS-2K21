package com.washi.securityservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class District {
    private Long id;
    private String name;
}
