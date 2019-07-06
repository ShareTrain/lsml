package com.lisong.repository;

import com.lisong.domain.user.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findByIdInAndDeleted(List<Long> shopIdList, Integer deleted);
}
