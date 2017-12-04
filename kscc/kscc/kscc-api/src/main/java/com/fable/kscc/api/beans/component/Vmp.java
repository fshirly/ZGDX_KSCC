package com.fable.kscc.api.beans.component;

import java.util.List;

public class Vmp {

	private int mode ;
	private int layout ;
	private int voice_hint;
	private int broadcast;
	private int show_mt_name;
	private List<VmpMem> members;

	
	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getLayout() {
		return layout;
	}

	public void setLayout(int layout) {
		this.layout = layout;
	}

	public int getVoice_hint() {
		return voice_hint;
	}

	public void setVoice_hint(int voice_hint) {
		this.voice_hint = voice_hint;
	}

	public int getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(int broadcast) {
		this.broadcast = broadcast;
	}

	public int getShow_mt_name() {
		return show_mt_name;
	}

	public void setShow_mt_name(int show_mt_name) {
		this.show_mt_name = show_mt_name;
	}

	public List<VmpMem> getMembers() {
		return members;
	}

	public void setMembers(List<VmpMem> members) {
		this.members = members;
	}

}
