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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ambientic.shuttler.data.Agency;
import com.ambientic.shuttler.data.PositionInfo;
import com.ambientic.shuttler.data.ShuttleInfo;
import com.ambientic.shuttler.data.StopInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class ShuttlerServiceImpl implements ShuttlerService {
	private static final String GET_ALL_AGENCIES = "mobile/getAllAgencies";
	private static final String REGISTER_USER = "mobile/registerUser";
	private static final String UNREGISTER_USER = "mobile/unregisterUser";
	private static final String GET_NEARBY_STOPS_IDS = "mobile/getNearbyStopIds";
	private static final String GET_STOP_ROUTES = "mobile/getStopRoutes";
	private static final String GET_STOP_INFO = "mobile/getStopInfo";

	private static final String GET_SHUTTLE_INFO = "mobile/getShuttleInfo";
	private static final String GET_ONGOING_SHUTTLES = "mobile/getOngoingShuttles";

	private static final String POST_COMMENT = "mobile/postComment";
	private static final String WAIT_AT_STOP = "mobile/waitAtStop";
	private static final String LEAVE_STOP = "mobile/leaveStop";
	private static final String ENTER_SHUTTLE = "mobile/enterShuttle";
	private static final String LEAVE_SHUTTLE = "mobile/leaveShuttle";
	private static final String UPDATE_POSITION = "mobile/updatePosition";

	private String serverUrl;
	private String agencyKey;
	private Gson gson;

	/**
	 * 
	 * @param serverUrl
	 *            URL of the server (including protocol and port number
	 * @param agencyKey
	 *            Agency key to use for all the API calls when required
	 */
	public ShuttlerServiceImpl(String serverUrl, String agencyKey) {
		this.serverUrl = serverUrl;
		this.agencyKey = agencyKey;
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
				.create();
	}

	@Override
	public Agency[] getAllAgencies() throws IOException {
		String agenciesstr = sendGetRequest(GET_ALL_AGENCIES, "");
		try {
			Agency[] agenciesobj = gson.fromJson(agenciesstr, Agency[].class);
			return agenciesobj;
		} catch (Exception e) {
			throw new IOException("Unable to get response " + e.getMessage());
		}
	}

	@Override
	public long registerUser(String nickname) throws IOException {
		boolean ok;
		String res;

		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("nickname", nickname);
		jsonParam.addProperty("devicename", "devicename");
		jsonParam.addProperty("pushtoken", "pushtoken");
		res = sendJsonPostRequest(REGISTER_USER, jsonParam.toString());

		if (res == null) {
			throw new IOException("Unable to register");
		}
		return Long.parseLong(res);
	}

	@Override
	public boolean unregisterUser(String nickname) throws IOException {
		String res;

		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("nickname", nickname);
		res = sendJsonPostRequest(UNREGISTER_USER, jsonParam.toString());

		if (res == null) {
			throw new IOException("Unable to register");
		}

		return true;
	}

	@Override
	public String[] getNearbyStopIds(double lon, double lat) throws IOException {
		String res;

		String requestStr = "?agencyKey=" + agencyKey + "&lat=" + lat + "&lon="
				+ lon + "&range=1000";
		res = sendGetRequest(GET_NEARBY_STOPS_IDS, requestStr);
		String[] ids = gson.fromJson(res, String[].class);

		return ids;
	}

	@Override
	public String[] getRouteIdsForStop(String stopId) throws IOException {
		String res;

		String requestStr = "?agencyKey=" + agencyKey + "&stopId=" + stopId;
		res = sendGetRequest(GET_STOP_ROUTES, requestStr);
		String[] stopIds = gson.fromJson(res, String[].class);

		return stopIds;
	};

	@Override
	public StopInfo getStopInfo(String stopId) throws IOException {
		String res;

		String requestStr = "?agencyKey=" + agencyKey + "&stopId=" + stopId;
		res = sendGetRequest(GET_STOP_INFO, requestStr);
		StopInfo stopIinfo = gson.fromJson(res, StopInfo.class);

		return stopIinfo;
	}

	@Override
	public boolean postComment(String nickname, String comment)
			throws IOException {
		String res;

		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("nickname", nickname);
		jsonParam.addProperty("comment", comment);
		res = sendJsonPostRequest(POST_COMMENT, jsonParam.toString());

		if (res == null) {
			throw new IOException("Unable to post comment");
		}

		if (res.equals("true"))
			return true;
		else
			return false;
	}

	@Override
	public boolean waitAtStop(String stopId, String nickname)
			throws IOException {
		String res;

		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("nickname", nickname);
		jsonParam.addProperty("stopId", stopId);
		jsonParam.addProperty("agencyKey", agencyKey);
		res = sendJsonPostRequest(WAIT_AT_STOP, jsonParam.toString());

		if (res == null) {
			throw new IOException("Unable to wait at stop");
		}

		if (res.equals("true"))
			return true;
		else
			return false;
	}

	@Override
	public boolean leaveStop(String nickname) throws IOException {
		String res;

		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("nickname", nickname);
		res = sendJsonPostRequest(LEAVE_STOP, jsonParam.toString());

		if (res == null) {
			throw new IOException("Unable to leave stop");
		}

		if (res.equals("true"))
			return true;
		else
			return false;
	}

	@Override
	public boolean enterShuttle(String nickname, String shuttleId)
			throws IOException {
		String res;

		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("nickname", nickname);
		jsonParam.addProperty("shuttleId", shuttleId);
		jsonParam.addProperty("agencyKey", agencyKey);
		res = sendJsonPostRequest(ENTER_SHUTTLE, jsonParam.toString());

		if (res == null) {
			throw new IOException("Unable to leave stop");
		}

		if (res.equals("true"))
			return true;
		else
			return false;
	}

	@Override
	public boolean leaveShuttle(String nickname) throws IOException {
		String res;

		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("nickname", nickname);
		res = sendJsonPostRequest(LEAVE_SHUTTLE, jsonParam.toString());

		if (res == null) {
			throw new IOException("Unable to leave stop");
		}

		if (res.equals("true"))
			return true;
		else
			return false;
	}

	@Override
	public ShuttleInfo getShuttleInfo(String shuttleId) throws IOException {
		String res;

		String requestStr = "?agencyKey=" + agencyKey + "&shuttleId="
				+ shuttleId;
		res = sendGetRequest(GET_SHUTTLE_INFO, requestStr);
		ShuttleInfo shuttleInfo = gson.fromJson(res, ShuttleInfo.class);

		return shuttleInfo;
	}

	@Override
	public ShuttleInfo[] getOngoingShuttles(String routeId) throws IOException {
		String res;

		String requestStr = "?agencyKey=" + agencyKey + "&routeId=" + routeId;
		res = sendGetRequest(GET_ONGOING_SHUTTLES, requestStr);
		ShuttleInfo[] shuttleInfos = gson.fromJson(res, ShuttleInfo[].class);

		return shuttleInfos;
	}

	@Override
	public boolean updatePosition(String nickname, PositionInfo position)
			throws IOException {
		String res;

		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("nickname", nickname);
		jsonParam.addProperty("position", gson.toJson(position));
		res = sendJsonPostRequest(UPDATE_POSITION, jsonParam.toString());

		if (res == null) {
			throw new IOException("Unable to leave stop");
		}

		if (res.equals("true"))
			return true;
		else
			return false;
	}

	//
	//
	//
	//
	//

	String sendGetRequest(String method, String request) {
		String requestUrl = serverUrl + method + request;
		BufferedReader rd;
		String line;
		String result = "";

		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.connect();

			int HttpResult = connection.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {

				rd = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				while ((line = rd.readLine()) != null) {
					result += line;
				}
				rd.close();
			} else {
				return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	String sendJsonPostRequest(String method, String requestContent) {
		String requestUrl = serverUrl + method;
		BufferedReader rd;
		String line;
		String result = "";

		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/json;charset=utf-8");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(requestContent.getBytes().length));
			connection.setUseCaches(false);
			connection.connect();

			// Create I/O stream
			DataOutputStream outStream = new DataOutputStream(
					connection.getOutputStream());
			// Send request
			outStream.writeBytes(requestContent);
			outStream.flush();
			outStream.close();

			int HttpResult = connection.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				// Get Response
				rd = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				while ((line = rd.readLine()) != null) {
					result += line;
				}
				rd.close();
			} else {
				return null;
			}

			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
