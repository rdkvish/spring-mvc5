package com.rdkv.generic.controller;

import com.rdkv.generic.message.SearchResponse;
import com.rdkv.generic.message.UserInfoRest;
import com.rdkv.generic.model.UserInfo;
import com.rdkv.generic.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path="/cwdemo")
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;

	@PostMapping(path="/user")
	@ResponseBody
	public ResponseEntity<UserInfo> addNewUser (@RequestBody UserInfoRest userInfoRest, UriComponentsBuilder ucBuilder) {
		return userInfoService.addUserInformation(userInfoRest, ucBuilder);
	}

	@GetMapping(path="/mobile/{number}")
	public ResponseEntity<SearchResponse> checkMobileExists(@PathVariable("number") String number) {
		return userInfoService.findUserInfoByMobile(number);
	}

	@GetMapping(path="/otp/{otp}")
	public ResponseEntity<SearchResponse> checkOtpExists(@PathVariable("otp") String otp) {
		return userInfoService.findUserInfoByOtp(otp);
	}
}
