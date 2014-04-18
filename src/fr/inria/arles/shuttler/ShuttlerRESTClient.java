package fr.inria.arles.shuttler;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import android.content.Context;

import com.loopj.android.http.*;

/** The wrapper class for RESTful access to the Shuttler service */
public class ShuttlerRESTClient {

	private static final String BASE_URL = "http://arles.rocq.inria.fr:8080/Shuttler-server/webapi/";

	private static AsyncHttpClient client = new AsyncHttpClient();

	/**
	 * Perform an async GET
	 * 
	 * @param url
	 *            The relative URL to hit. Starts <emph>without</emph> a slash.
	 * @param params
	 *            Request Params
	 * @param responseHandler
	 *            an {@link AsyncHttpResponseHandler} to handle the request
	 */
	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	/**
	 * Perform an async POST
	 * 
	 * @param url
	 *            The relative URL to hit. Starts <emph>without</emph> a slash.
	 * @param params
	 *            Request Params
	 * @param responseHandler
	 *            an {@link AsyncHttpResponseHandler} to handle the request
	 */
	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	/**
	 * Posts JSON parameters.
	 * 
	 * @param context
	 *            Android context
	 * @param url
	 *            The (relative, no-leading-slash) URL to hit
	 * @param jsonObject
	 *            The JSON object to send
	 * @param responseHandler
	 *            The {@link AsyncHttpResponseHandler} to handle the response.
	 * @throws UnsupportedEncodingException
	 */
	public static void postJSON(Context context, String url,
			JSONObject jsonObject, AsyncHttpResponseHandler responseHandler)
			throws UnsupportedEncodingException {
		client.post(context, url, new StringEntity(jsonObject.toString()),
				"application/json", responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}
}