package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
CNO int 
NAME text 
ADDRESS text 
PHONE text 
MSG text 
IMAGE text 
HIT int
 */
@Entity
@Table(name="camp2")
@Getter
@Setter
public class CampingEntity {
	@Id
	private int cno;
	private String name,address,phone,msg,image;
}
