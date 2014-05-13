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

class StopAreaInfo {
	double[] sw;
	double[] ne;

	public double[] getSw() {
		return sw;
	}

	public void setSw(double[] sw) {
		this.sw = sw;
	}

	public double[] getNe() {
		return ne;
	}

	public void setNe(double[] ne) {
		this.ne = ne;
	}

}

class StopCenterInfo {
	double lat;
	double lon;
	int range;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

}

public class StopInfo {
	String stop_id;
	String agency_key;
	long lastUpdated;
	String stop_name;
	String agency_name;
	UserTag[] users;
	UserMessage[] messages;
	StopCenterInfo stop_center;
	StopAreaInfo stop_area;

	public String getStop_id() {
		return stop_id;
	}

	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	public String getAgency_key() {
		return agency_key;
	}

	public void setAgency_key(String agency_key) {
		this.agency_key = agency_key;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getStop_name() {
		return stop_name;
	}

	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public UserTag[] getUsers() {
		return users;
	}

	public void setUsers(UserTag[] users) {
		this.users = users;
	}

	public UserMessage[] getMessages() {
		return messages;
	}

	public void setMessages(UserMessage[] messages) {
		this.messages = messages;
	}

	public StopCenterInfo getStop_center() {
		return stop_center;
	}

	public void setStop_center(StopCenterInfo stop_center) {
		this.stop_center = stop_center;
	}

	public StopAreaInfo getStop_area() {
		return stop_area;
	}

	public void setStop_area(StopAreaInfo stop_area) {
		this.stop_area = stop_area;
	}
}
