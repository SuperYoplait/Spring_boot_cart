package com.springdemo.cartdemo.cartitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemForm {
    private Long id;

    private Long cnt;

    private Long CartId;

    private Long ItemId;
}
