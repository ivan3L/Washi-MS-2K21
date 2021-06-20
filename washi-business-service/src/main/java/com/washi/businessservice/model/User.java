package com.washi.businessservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class User {
    public Long id;
    public String name;
}
