package com.lisong.repository;

import com.lisong.domain.appoint.user.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByIdInAndDeleted(List<Long> menuIdList, Integer deleted);
}
