package com.hugo83.webmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hugo83.webmall.entity.Item;
import com.hugo83.webmall.entity.ItemSellStatus;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    /*
     * - find + (Entity) + By + (Property)
     * - And
     * - Or
     * - Is, Equals
     * - Between
     * - LessThan, LessThanEqual, GreaterThan, GreaterThanEqual
     * - After, Before
     * - IsNull, Null, IsNotNull
     * - Like, NotLike
     * - StartingWith, EndingWith
     * - OrderBy, Not, In, NotIn, True, False, IgnoreCase ...
     */
    // 상품 이름으로 검색
    List<Item> findByItemNameContaining(String itemName);

     // 상품 이름으로 검색
    List<Item> findByItemName(String itemName);

    // 판매 상태로 검색
    List<Item> findByItemSellStatus(ItemSellStatus itemSellStatus);

    // 가격 범위로 검색
    List<Item> findByPriceBetween(int minPrice, int maxPrice);

    // 재고 수량이 특정 값 이상인 상품 검색
    List<Item> findByStockQuantityGreaterThanEqual(int stockQuantity);

    // 가격이 특정 값 이하인 상품 검색
    List<Item> findByPriceLessThanEqual(int price);

    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    // JPA @query`
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    // @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    // List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);
}
