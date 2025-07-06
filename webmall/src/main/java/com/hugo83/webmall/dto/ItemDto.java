package com.hugo83.webmall.dto;

import java.time.LocalDateTime;

import com.hugo83.webmall.entity.ItemSellStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemDto {
    private Long id;   // 상품 ID

    private String itemName; // 상품 이름

    private int price; // 상품 가격

    private int stockQuantity; // 재고 수량

    private String itemDetail; // 상품 상세 설명

    private ItemSellStatus itemSellStatus; // 판매 상태 (판매중, 품절 등)

    private LocalDateTime createdAt; // 생성 일시

    private LocalDateTime updatedAt; // 수정 일시
}
