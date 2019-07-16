package com.lisong.repository;

import com.lisong.domain.appoint.user.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserinfoRepository extends JpaRepository<Userinfo, Long> {

    Userinfo findByAcctAndDeleted(String acct, Integer deleted);

    Userinfo findByIdAndDeleted(Long id, Integer deleted);
}
