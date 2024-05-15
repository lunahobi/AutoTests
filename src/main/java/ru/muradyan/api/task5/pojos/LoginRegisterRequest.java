package ru.muradyan.api.task5.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class LoginRegisterRequest {
    private String email;
    private String password;

}
