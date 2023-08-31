package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="food_location")
@Getter
@Setter
public class FoodEntity {
	@Id	// 자동증가번호 앞에 붙인다.
	private int fno;
	private int hit;
	private double score;
	private String name,address,tel,type,price,time,parking,menu,poster;
}
