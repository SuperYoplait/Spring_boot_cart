package com.springdemo.cartdemo.Item;

//import javax.validation.constraints.NotBlank;

//import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemInsertForm {
    //@NotBlank
    private Long id;
    //@NotBlank
    private Long count;
}
