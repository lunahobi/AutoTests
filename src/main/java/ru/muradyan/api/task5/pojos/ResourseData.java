package ru.muradyan.api.task5.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ResourseData {
     private int id;
     private String name;
     private int year;
     private String color;
     private String pantone_value;
}
