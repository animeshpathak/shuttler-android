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

import java.util.Date;

class StopTimeInfo {
	String stop_id;
	int order;
	String name;
	String headsign;
	double lat;
	double lon;
	Date expected_arrival;
	Date expected_departure;
	Date actual_arrival;
	Date actual_departure;

	// public String getStop_id() {
	// return stop_id;
	// }
	//
	// public void setStop_id(String stop_id) {
	// this.stop_id = stop_id;
	// }
	//
	// public int getOrder() {
	// return order;
	// }
	//
	// public void setOrder(int order) {
	// this.order = order;
	// }
	//
	// public String getName() {
	// return name;
	// }
	//
	// public void setName(String name) {
	// this.name = name;
	// }
	//
	// public String getHeadsign() {
	// return headsign;
	// }
	//
	// public void setHeadsign(String headsign) {
	// this.headsign = headsign;
	// }
	//
	// public double getLat() {
	// return lat;
	// }
	//
	// public void setLat(double lat) {
	// this.lat = lat;
	// }
	//
	// public double getLon() {
	// return lon;
	// }
	//
	// public void setLon(double lon) {
	// this.lon = lon;
	// }
	//
	// public Date getExpected_arrival() {
	// return expected_arrival;
	// }
	//
	// public void setExpected_arrival(Date expected_arrival) {
	// this.expected_arrival = expected_arrival;
	// }
	//
	// public Date getExpected_departure() {
	// return expected_departure;
	// }
	//
	// public void setExpected_departure(Date expected_departure) {
	// this.expected_departure = expected_departure;
	// }
	//
	// public Date getActual_arrival() {
	// return actual_arrival;
	// }
	//
	// public void setActual_arrival(Date actual_arrival) {
	// this.actual_arrival = actual_arrival;
	// }
	//
	// public Date getActual_departure() {
	// return actual_departure;
	// }
	//
	// public void setActual_departure(Date actual_departure) {
	// this.actual_departure = actual_departure;
	// }
}

class PositionInfo {
	double lat;
	double lon;
	double bearing;
	double alt;
	double accur;
	double speed;

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

	public double getBearing() {
		return bearing;
	}

	public void setBearing(double bearing) {
		this.bearing = bearing;
	}

	public double getAlt() {
		return alt;
	}

	public void setAlt(double alt) {
		this.alt = alt;
	}

	public double getAccur() {
		return accur;
	}

	public void setAccur(double accur) {
		this.accur = accur;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}

class ShapePoint {
	double lat;
	double lon;
	int order;

	// public double getLat() {
	// return lat;
	// }
	//
	// public void setLat(double lat) {
	// this.lat = lat;
	// }
	//
	// public double getLon() {
	// return lon;
	// }
	//
	// public void setLon(double lon) {
	// this.lon = lon;
	// }
	//
	// public int getOrder() {
	// return order;
	// }
	//
	// public void setOrder(int order) {
	// this.order = order;
	// }
}

class TripInfo {
	String route_id;
	String trip_id;
	String route_short_name;
	String route_long_name;
	int route_type;
	int direction;
	StopTimeInfo[] stops;

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public String getRoute_short_name() {
		return route_short_name;
	}

	public void setRoute_short_name(String route_short_name) {
		this.route_short_name = route_short_name;
	}

	public String getRoute_long_name() {
		return route_long_name;
	}

	public void setRoute_long_name(String route_long_name) {
		this.route_long_name = route_long_name;
	}

	public int getRoute_type() {
		return route_type;
	}

	public void setRoute_type(int route_type) {
		this.route_type = route_type;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public StopTimeInfo[] getStops() {
		return stops;
	}

	public void setStops(StopTimeInfo[] stops) {
		this.stops = stops;
	}

}

class NotifiedPositionInfo {
	String nickname;
	Date date;
	PositionInfo position;

	// public String getNickname() {
	// return nickname;
	// }
	//
	// public void setNickname(String nickname) {
	// this.nickname = nickname;
	// }
	//
	// public Date getDate() {
	// return date;
	// }
	//
	// public void setDate(Date date) {
	// this.date = date;
	// }
	//
	// public PositionInfo getPosition() {
	// return position;
	// }
	//
	// public void setPosition(PositionInfo position) {
	// this.position = position;
	// }
}

public class ShuttleInfo {
	String shuttle_id;
	String agency_name;
	String agency_key;
	long lastUpdated;
	UserTag[] users;
	UserMessage[] messages;
	TripInfo trip;
	PositionInfo lastPosition;
	NotifiedPositionInfo[] notifiedPositions;
	ShapePoint[] tripShape;

	public String getShuttle_id() {
		return shuttle_id;
	}

	public void setShuttle_id(String shuttle_id) {
		this.shuttle_id = shuttle_id;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
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

	public TripInfo getTrip() {
		return trip;
	}

	public void setTrip(TripInfo trip) {
		this.trip = trip;
	}

	public PositionInfo getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(PositionInfo lastPosition) {
		this.lastPosition = lastPosition;
	}

	public NotifiedPositionInfo[] getNotifiedPositions() {
		return notifiedPositions;
	}

	public void setNotifiedPositions(NotifiedPositionInfo[] notifiedPositions) {
		this.notifiedPositions = notifiedPositions;
	}

	public ShapePoint[] getTripShape() {
		return tripShape;
	}

	public void setTripShape(ShapePoint[] tripShape) {
		this.tripShape = tripShape;
	}
}
