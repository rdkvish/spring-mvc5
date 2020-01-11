package com.rdkv.generic.service;

import com.rdkv.generic.exception.ConflictException;
import com.rdkv.generic.message.SearchResponse;
import com.rdkv.generic.message.UserInfoRest;
import com.rdkv.generic.model.UserInfo;
import com.rdkv.generic.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;


@Service
public class UserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    public ResponseEntity<UserInfo> addUserInformation(UserInfoRest userInfoRest, UriComponentsBuilder ucBuilder){
        String mobileNumber = userInfoRest.getMobile();
        String otp = userInfoRest.getOtp();
        Set<UserInfo> userInfo = userInfoRepository.findUserInfoByOtp(otp);
        if(0 < userInfo.size()) {
            LOGGER.warn("Conflict Error: OTP {} exists already", otp);
            throw new ConflictException("OTP", "", otp);
        } else {
            LOGGER.debug("Creating new record with mobile number {}", mobileNumber);
            UserInfo userInfoNew = new UserInfo();
            userInfoNew.setMobileNumber(mobileNumber);
            userInfoNew.setOtp(otp);
            userInfoRepository.save(userInfoNew);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/cwdemo/user/{id}").buildAndExpand(userInfoNew.getId()).toUri());

            return new ResponseEntity<>(userInfoNew, headers, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<SearchResponse> findUserInfoByMobile(String mobileNumber){
        Set<UserInfo> userInfo = userInfoRepository.findUserInfoByMobile(mobileNumber);
        SearchResponse searchResponse = new SearchResponse();
        if(0 < userInfo.size()){
            searchResponse.setExists(true);
        } else {
            searchResponse.setExists(false);
        }
        return new ResponseEntity<>(searchResponse, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<SearchResponse> findUserInfoByOtp(String otp){
        Set<UserInfo> userInfoSet = userInfoRepository.findUserInfoByOtp(otp);
        SearchResponse searchResponse = new SearchResponse();
        if(0 < userInfoSet.size()){
            searchResponse.setExists(true);
        } else {
            searchResponse.setExists(false);
        }
        return new ResponseEntity<>(searchResponse, new HttpHeaders(), HttpStatus.OK);
    }
}
