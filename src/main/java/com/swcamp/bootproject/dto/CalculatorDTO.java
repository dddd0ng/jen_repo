package com.swcamp.bootproject.dto;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class CalculatorDTO {

    @NonNull
    private Integer num1;

    @NonNull
    private Integer num2;
    private int sum;


}
