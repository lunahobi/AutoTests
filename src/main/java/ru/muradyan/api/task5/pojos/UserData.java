package ru.muradyan.api.task5.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserData{
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
