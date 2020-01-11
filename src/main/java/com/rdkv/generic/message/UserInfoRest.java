package com.rdkv.generic.message;

public class UserInfoRest {

    private String mobile;
    private String otp;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "UserInfoRest{" +
                "mobile='" + mobile + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
