package com.fable.kscc.api.beans.component;

import java.util.List;

public class Mix {

	private int mode;
	private List<Account> members;

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public List<Account> getMembers() {
		return members;
	}

	public void setMembers(List<Account> members) {
		this.members = members;
	}

}
