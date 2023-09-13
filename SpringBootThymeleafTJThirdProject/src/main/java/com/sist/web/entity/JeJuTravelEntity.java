package com.sist.web.entity;
/*
NO int      고유번호
TITLE text  이름
TAG text    해쉬 태그
INTRODUCTION text  간단소개
LOC text 위치
ADDR text  주소
ROAD text  도로명 주소
TEL text  전화번호
POSTER text  사진
INFO text 상세 설명
LNO int  1.관광지, 2,음식점, 3.숙박, 4.행사/축제
TCNO int 
HIT int
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="travel_detail")
@Getter
@Setter
public class JeJuTravelEntity {
	@Id
	private int no;
	private int lno,hit;
	private String title,tag,introduction,loc,addr,road,tel,poster,info;
}
