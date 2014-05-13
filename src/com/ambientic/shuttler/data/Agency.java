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

class Bounds {
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
};

public class Agency {
	String agency_key;
	String agency_id;
	String agency_name;
	String agency_url;
	String agency_timezone;
	String agency_lang;
	String agency_phone;
	String agency_fare_url;

	Bounds agency_bounds;
	double[] agency_center;

	long date_last_updated;

	public String getAgency_key() {
		return agency_key;
	}

	public void setAgency_key(String agency_key) {
		this.agency_key = agency_key;
	}

	public String getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(String agency_id) {
		this.agency_id = agency_id;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public String getAgency_url() {
		return agency_url;
	}

	public void setAgency_url(String agency_url) {
		this.agency_url = agency_url;
	}

	public String getAgency_timezone() {
		return agency_timezone;
	}

	public void setAgency_timezone(String agency_timezone) {
		this.agency_timezone = agency_timezone;
	}

	public String getAgency_lang() {
		return agency_lang;
	}

	public void setAgency_lang(String agency_lang) {
		this.agency_lang = agency_lang;
	}

	public String getAgency_phone() {
		return agency_phone;
	}

	public void setAgency_phone(String agency_phone) {
		this.agency_phone = agency_phone;
	}

	public String getAgency_fare_url() {
		return agency_fare_url;
	}

	public void setAgency_fare_url(String agency_fare_url) {
		this.agency_fare_url = agency_fare_url;
	}

	public Bounds getAgency_bounds() {
		return agency_bounds;
	}

	public void setAgency_bounds(Bounds agency_bounds) {
		this.agency_bounds = agency_bounds;
	}

	public double[] getAgency_center() {
		return agency_center;
	}

	public void setAgency_center(double[] agency_center) {
		this.agency_center = agency_center;
	}

	public long getDate_last_updated() {
		return date_last_updated;
	}

	public void setDate_last_updated(long date_last_updated) {
		this.date_last_updated = date_last_updated;
	}
}
