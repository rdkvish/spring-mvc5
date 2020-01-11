package com.rdkv.generic.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "userinfo")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "mobile")
	private String mobileNumber;

	@Column(name = "otp")
	private String otp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserInfo)) return false;
		UserInfo userInfo = (UserInfo) o;
		return getId().equals(userInfo.getId()) &&
				getMobileNumber().equals(userInfo.getMobileNumber()) &&
				Objects.equals(getOtp(), userInfo.getOtp());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getMobileNumber(), getOtp());
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"id=" + id +
				", mobileNumber='" + mobileNumber + '\'' +
				", otp='" + otp + '\'' +
				'}';
	}
}
