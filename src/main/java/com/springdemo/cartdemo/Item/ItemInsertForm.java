package com.springdemo.cartdemo.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemInsertForm {
    private Long itemId;
    private Long cnt;
}
