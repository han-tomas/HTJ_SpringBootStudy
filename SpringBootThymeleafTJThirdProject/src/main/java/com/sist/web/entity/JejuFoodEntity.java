package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
FINO bigint 
MCNO bigint 
POSTER varchar(4000) 
NAME varchar(200) 
SCORE float 
ADDRESS varchar(1000) 
PHONE varchar(20) 
TYPE varchar(100) 
PRICE varchar(100) 
PARKING varchar(50) 
TIME varchar(100) 
MENU varchar(4000) 
HIT bigint 
LIKE_COUNT bigint
 */
@Entity
@Table(name="food_info")
@Getter
@Setter
public class JejuFoodEntity {
	@Id
	private int fino;
	private double score;
	private String poster,name,address,phone,type,price,parking,time,menu;
}
