package com.sp.fc.user.repository;

import com.sp.fc.user.domain.SpUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpUserRepository extends JpaRepository<SpUser,Long> {
    //이메일로 유저를 찾아온다
    Optional<SpUser> findSpUserByEmail(String email);

}
