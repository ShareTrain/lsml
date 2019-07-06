package com.lisong.repository;

import com.lisong.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByIdAndDeleted(Long id, Integer deleted);
}
