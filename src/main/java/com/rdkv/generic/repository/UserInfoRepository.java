package com.rdkv.generic.repository;

import com.rdkv.generic.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

    @Query("SELECT u FROM UserInfo u WHERE u.mobileNumber = ?1")
    Set<UserInfo> findUserInfoByMobile(String mobileNumber);

    @Query("SELECT u FROM UserInfo u WHERE u.otp = ?1")
    Set<UserInfo> findUserInfoByOtp(String otp);
}
