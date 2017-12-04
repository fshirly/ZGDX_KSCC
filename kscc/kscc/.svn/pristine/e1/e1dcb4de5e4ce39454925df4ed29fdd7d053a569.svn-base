package com.fable.kscc.api.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfParamConfig {

	private Logger logger = LoggerFactory.getLogger("API") ;
	
	private int format = 106;
	private int resolution = 35;
	private int frame = 30;
	private  boolean is_hp = false;
	private int bitrate = 2048 ;
	private int vmp_mode ;
	private int mix_mode ;
	private int dual_mode ;
	
	private String strFormat ;
	private String strResolution ;
	private String strFrame ;
	private  String strIsHp ;
	private String strBitrate ;
	
	
	public ConfParamConfig() {}
	
	public void print() {
		logger.info(this.toString());
	}
	
	@Override
	public String toString() {
		return "ConfParamConfig [format=" + format
				+ ", resolution=" + resolution + ", frame=" + frame
				+ ", is_hp=" + is_hp + ", bitrate=" + bitrate + ", vmp_mode="
				+ vmp_mode + ", mix_mode=" + mix_mode + ", dual_mode="
				+ dual_mode + "]";
	}



	public int getFormat() {
		return format;
	}
	public void setFormat(int format) {
		this.format = format;
	}
	public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	public int getFrame() {
		return frame;
	}
	public void setFrame(int frame) {
		this.frame = frame;
	}
	public boolean isIs_hp() {
		return is_hp;
	}
	public void setIs_hp(boolean is_hp) {
		this.is_hp = is_hp;
	}
	public int getBitrate() {
		return bitrate;
	}
	public void setBitrate(int bitrate) {
		this.bitrate = bitrate;
	}
	public String getStrFormat() {
		return strFormat;
	}
	public void setStrFormat(String strFormat) {
		this.strFormat = strFormat;
		if(strFormat.matches("\\d+")) {
			this.format = Integer.parseInt(strFormat) ;
		}
	}
	public String getStrResolution() {
		return strResolution;
	}
	public void setStrResolution(String strResolution) {
		this.strResolution = strResolution;
		if(strResolution.matches("\\d+")) {
			this.resolution = Integer.parseInt(strResolution) ;
		}
	}
	public String getStrFrame() {
		return strFrame;
	}
	public void setStrFrame(String strFrame) {
		this.strFrame = strFrame;
		if(strFrame.matches("\\d+")) {
			this.frame = Integer.parseInt(strFrame) ;
		}
	}
	public String getStrIsHp() {
		return strIsHp;
	}
	public void setStrIsHp(String strIsHp) {
		this.strIsHp = strIsHp;
		if(strIsHp.equals("true") || strIsHp.equals("false")) {
			this.is_hp = Boolean.parseBoolean(strIsHp) ;
		}
	}
	public String getStrBitrate() {
		return strBitrate;
	}
	public void setStrBitrate(String strBitrate) {
		this.strBitrate = strBitrate;
		if(strBitrate.matches("\\d+")) {
			this.bitrate = Integer.parseInt(strBitrate) ;
		}
	}

	public int getVmp_mode() {
		return vmp_mode;
	}

	public void setVmp_mode(int vmp_mode) {
		this.vmp_mode = vmp_mode;
	}

	public int getMix_mode() {
		return mix_mode;
	}

	public void setMix_mode(int mix_mode) {
		this.mix_mode = mix_mode;
	}

	public int getDual_mode() {
		return dual_mode;
	}

	public void setDual_mode(int dual_mode) {
		this.dual_mode = dual_mode;
	}
	
}

