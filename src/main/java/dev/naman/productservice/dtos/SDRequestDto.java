package dev.naman.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SDRequestDto {

    private String title;
    private Long price;
    private String email;

}
