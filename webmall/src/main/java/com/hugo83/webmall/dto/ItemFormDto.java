package com.hugo83.webmall.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import com.hugo83.webmall.entity.Item;
import com.hugo83.webmall.entity.ItemSellStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFormDto {

    private Long id;   // 상품 ID

    @NotBlank(message = "상품명은 필수입니다")
    private String itemName; // 상품 이름

    @NotNull(message = "가격은 필수입니다")
    private int price; // 상품 가격

    @NotNull(message = "재고는 필수입니다")
    private int stockQuantity; // 재고 수량

    @NotBlank(message = "이름은 필수입니다")
    private String itemDetail; // 상품 상세 설명

    private ItemSellStatus itemSellStatus; // 판매 상태 (판매중, 품절 등)

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }
}
