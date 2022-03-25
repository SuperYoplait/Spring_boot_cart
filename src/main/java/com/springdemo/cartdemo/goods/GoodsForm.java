package com.springdemo.cartdemo.goods;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsForm {

    //@NotBlank
    private Long id;

    //@Length(min = 5, max = 400)
    private String name;

    //@Length(min = 5)
    private String context;

    //@NotBlank
    private Long price;

    //@NotBlank
    private String count;

    private boolean sold;

    //@NotBlank
    private String image;

}
