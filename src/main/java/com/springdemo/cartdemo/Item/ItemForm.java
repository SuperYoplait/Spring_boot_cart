package com.springdemo.cartdemo.Item;

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
public class ItemForm {

    @NotBlank
    private Long id;

    @Length(min = 5, max = 200)
    private String name;

    private String categorie;

    @Length(min = 5)
    private String context;

    @NotBlank
    private Long price;

    @NotBlank
    private Long count;

    private boolean sold;

    @NotBlank
    private String imgName;

    private String imgPath;

}
