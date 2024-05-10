package ru.muradyan.api.task5.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.checkerframework.checker.units.qual.N;

@Data
@Builder
@AllArgsConstructor @N
public class LoginRegisterRequest {
    private String email;
    private String password;

}
