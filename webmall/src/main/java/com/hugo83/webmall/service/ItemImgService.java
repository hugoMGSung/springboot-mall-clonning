package com.hugo83.webmall.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.hugo83.webmall.entity.ItemImg;
import com.hugo83.webmall.repository.ItemImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
        String originImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // 파일 업로드
        if (!StringUtils.isEmpty(originImgName)) {
            imgName = fileService.uploadFile(itemImgLocation, originImgName, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        // 상품이미지 정보 저장
        itemImg.updateItemImg(originImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }
}
