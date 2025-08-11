package com.hugo83.webmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo83.webmall.entity.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}
