CREATE TABLE cwdemo.userinfo (
 id bigint not null,
 mobile varchar(12) not null,
 otp varchar(8),
 primary key (id),
 unique key (otp)
);