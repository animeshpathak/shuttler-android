/**
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 * 
 * Copyright : Ambientic, 2014
 * 
 * Author : Pierre-Guillaume Raverdy
 */
package com.ambientic.shuttler.data;

public class UserTag {
	String nickname;
	long lastChecked;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public long getLastChecked() {
		return lastChecked;
	}

	public void setLastChecked(long lastChecked) {
		this.lastChecked = lastChecked;
	}

}