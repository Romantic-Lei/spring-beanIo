package com.example.demo.entity;

import java.util.Date;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import lombok.Data;

@Data
@Record
public class FileO1CSV {

	/**
	 * 年月日時分秒
	 */
	@Field(at=0, length=19, format="yyyy/MM/dd HH:mm:ss")
	private Date collection_time;
	
//	/**
//	 * 事業体コード
//	 */
//	@Field(at=14, length=2)
//	private String ent_code;
	
	/**
	 * お客さま番号
	 */
	@Field(at=1, length=11)
	private String cli_no;
	
//	/**
//	 * 通信種別
//	 */
//	@Field(at=27, length=3)
//	private String com_type;
	
	/**
	 * 需要家ID
	 */
	@Field(at=2, length=14)
	private String customer_id;
	
	/**
	 * 現在指針値
	 */
	@Field(at=3, length=9)
	private String guideline_value;
	
	/**
	 * 流量計測回数
	 */
	@Field(at=4, length=9)
	private String flow_number;
	
	/**
	 * 大脈動計測回数
	 */
	@Field(at=5, length=9)
	private String pulsation_number;
	
	/**
	 * 限界脈動計測回数
	 */
	@Field(at=6, length=9)
	private String lim_pulsation_count;
	
	/**
	 * 流量無し時流量計測回数
	 */
	@Field(at=7, length=9)
	private String flow_measurements_count;
	
	/**
	 * 流量無し時限界脈動計測回数
	 */
	@Field(at=8, length=9)
	private String limit_pulsation_count;
	
	/**
	 * 30秒間の流量
	 */
	@Field(at=9, length=6)
	private String flow_rate;
	
	/**
	 * Q120内の位置
	 */
	@Field(at=10, length=1)
	private String position;
	
	/**
	 * 遮断弁駆動回数
	 */
	@Field(at=11, length=6)
	private String shutoff_number;
	
	/**
	 * 上流側ゲイン設定値
	 */
	@Field(at=12, length=3)
	private String upstream_gain;
	
	/**
	 * 下流側ゲイン設定値
	 */
	@Field(at=13, length=3)
	private String downstream_gain;

	
	@Override
	public String toString() {
		return "FileO1CSV [collection_time=" + collection_time + ", cli_no=" + cli_no + ", customer_id=" + customer_id
				+ ", guideline_value=" + guideline_value + ", flow_number=" + flow_number + ", pulsation_number="
				+ pulsation_number + ", lim_pulsation_count=" + lim_pulsation_count + ", flow_measurements_count="
				+ flow_measurements_count + ", limit_pulsation_count=" + limit_pulsation_count + ", flow_rate="
				+ flow_rate + ", Position=" + position + ", shutoff_number=" + shutoff_number + ", upstream_gain="
				+ upstream_gain + ", downstream_gain=" + downstream_gain + "]";
	}

}
