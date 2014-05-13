/**
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 * 
 * Copyright : Ambientic, 2014
 * 
 * Author : Pierre-Guillaume Raverdy
 */
package com.ambientic.shuttler;

import java.io.IOException;

import com.ambientic.shuttler.data.Agency;
import com.ambientic.shuttler.data.ShuttleInfo;
import com.ambientic.shuttler.data.StopInfo;

public interface ShuttlerService {

	/**
	 * Get the ids for all the agencies registered in the server.
	 * 
	 * @return
	 * @throws IOException
	 */
	public Agency[] getAllAgencies() throws IOException;

	/**
	 * Register a nickname in the system if not yet registered.
	 * 
	 * @param nickname
	 * @return -1 if error, positive long otherwise
	 * @throws IOException
	 *             if server connection error
	 */
	public long registerUser(String nickname) throws IOException;

	/**
	 * Unregisters a nickname in the system.
	 * 
	 * @param nickname
	 * @return true if user unregisterd, false otherwise
	 * @throws IOException
	 *             if server connection error
	 */
	public boolean unregisterUser(String nickname) throws IOException;

	/**
	 * Get nearby stops given a GPS location.
	 * 
	 * @param lon
	 * @param lat
	 * @return
	 * @throws IOException
	 */
	public String[] getNearbyStopIds(double lon, double lat) throws IOException;

	/**
	 * Get the ids of all routes for a stop.
	 * 
	 * @param stopId
	 * @return
	 * @throws IOException
	 */
	public String[] getRouteIdsForStop(String stopId) throws IOException;

	/**
	 * Retrieves the latest information for a stop.
	 * 
	 * @param stopId
	 * @return
	 * @throws IOException
	 */
	public StopInfo getStopInfo(String stopId) throws IOException;

	/**
	 * Post a comments to the stop or the shuttle it registered to.
	 * 
	 * @param stopId
	 * @return tyrue if comment poster, false otherwise (not registered
	 *         anywhere)
	 * @throws IOException
	 */
	public boolean postComment(String nickname, String comment)
			throws IOException;

	/**
	 * User checks in at a stop. Will leave/checkout any other shuttle or any
	 * stop where it registered. User will be able to post comment to this stop
	 * afterwards.
	 * 
	 * @param stopId
	 * @return
	 * @throws IOException
	 */
	public boolean waitAtStop(String stopId, String nickname)
			throws IOException;

	/**
	 * 
	 * @param stopId
	 * @return
	 * @throws IOException
	 */
	public boolean leaveStop(String nickname) throws IOException;

	/**
	 * User enters a shuttle. Will leave/checkout any other shuttle or any stop
	 * where it regitered. User will be able to post comment and update its
	 * position afterwards.
	 * 
	 * @param stopId
	 * @return
	 * @throws IOException
	 */
	public boolean enterShuttle(String nickname, String shuttleId)
			throws IOException;

	/**
	 * User leaves a shuttle. Will not be able to post comment or update
	 * position aftewards.
	 * 
	 * @param stopId
	 * @return
	 * @throws IOException
	 */
	public boolean leaveShuttle(String nickname) throws IOException;

	/**
	 * Retrieves the info for a shuttle
	 * 
	 * @param shuttleId
	 * @return
	 * @throws IOException
	 */
	public ShuttleInfo getShuttleInfo(String shuttleId) throws IOException;

	/**
	 * Retrieves the list of ids for the current active shuttles for a route
	 * 
	 * @param shuttleId
	 * @return
	 * @throws IOException
	 */
	public ShuttleInfo[] getOngoingShuttles(String routeId) throws IOException;

	/**
	 * Updates the current position of a user. If user is in a shuttle, the
	 * actual position of the shuttle may be changed.
	 * 
	 * @param nickname
	 * @param position
	 * @return
	 * @throws IOException
	 */
	// public boolean updatePosition(String nickname, PositionInfo position)
	// throws IOException;

}
