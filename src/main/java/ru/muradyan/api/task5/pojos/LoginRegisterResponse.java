package ru.muradyan.api.task5.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LoginRegisterResponse {
    private int id;
    private String token;
    private String error;
}
