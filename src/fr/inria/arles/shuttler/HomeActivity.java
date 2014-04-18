package fr.inria.arles.shuttler;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends FragmentActivity {

	String LOG_TAG = this.getClass().getSimpleName();
	Context mContext = HomeActivity.this;

	String username = "tintin@inria.fr";
	String lineID = "1";

	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setUpMapIfNeeded();

		// setting up upload butto action
		ImageButton uploadButton = (ImageButton) findViewById(R.id.button_upload);
		uploadButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addNewRecord();
			}
		});

		// setting up refresh button action
		ImageButton refreshButton = (ImageButton) findViewById(R.id.button_refresh);
		refreshButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "Getting latest positions...",
						Toast.LENGTH_SHORT).show();
				ShuttlerRESTClient.get("busesforline/" + username + "/"
						+ lineID, null, new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject busInfo) {
						try {
							JSONArray busList = (JSONArray) busInfo
									.getJSONArray("buses");
							Log.d(LOG_TAG, busList.toString(2));
							// get first location
							if (busList.length() == 0) {
								Log.e(LOG_TAG, "No bus records found.");
								Toast.makeText(HomeActivity.this,
										"No bus records found.",
										Toast.LENGTH_SHORT).show();
							}
							JSONObject busPosition = busList.getJSONObject(0);

							double latitude = busPosition.getDouble("latitude");
							double longitude = busPosition
									.getDouble("longitude");
							LatLng mLatLng = new LatLng(latitude, longitude);
							mMap.clear();
							Date d = new Date();
							mMap.addMarker(new MarkerOptions()
									.position(mLatLng).title(
											"Shuttle Position at " + d));
							mMap.animateCamera(CameraUpdateFactory
									.newLatLngZoom(mLatLng, (float) 14.0));

						} catch (JSONException e) {
							Log.e(LOG_TAG, "error in parsing JSON response", e);
						}
					}
				});
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		if (mMap != null) {
			return;
		}

		SupportMapFragment mMapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);

		if (mMapFragment == null)
			return;

		mMap = mMapFragment.getMap();

		if (mMap == null) {
			return;
		}
		// Initialize map options. For example:
		Log.d(LOG_TAG, "Got actual map; setting options");
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.setMyLocationEnabled(true);
		mMap.getUiSettings().setAllGesturesEnabled(true);

	}

	/**
	 * Adds a new record to the Bus DB. For now, sets a hopon on line 1 at
	 * Etoile
	 */
	void addNewRecord() {
		/*
		 * { "email":"tintin@inria.fr", "password":"haddock",
		 * "latitude":"48.885944", "longitude":"2.2949", "lineid":"1" }
		 */

		JSONObject jsonParams = new JSONObject();
		try {
			jsonParams.put("email", "tintin@inria.fr");
			jsonParams.put("tintin", "haddock");
			jsonParams.put("latitude", "48.885944");
			jsonParams.put("longitude", "2.2949");
			jsonParams.put("lineid", "1");
			ShuttlerRESTClient.postJSON(mContext, "/hopon", jsonParams,
					new AsyncHttpResponseHandler() {
						@Override
						public void onSuccess(int statusCode, Header[] headers,
								byte[] body) {
							Toast.makeText(
									mContext,
									"Success in hopping on: code " + statusCode,
									Toast.LENGTH_SHORT).show();
						}

						@Override
						public void onFailure(int statusCode, Header[] headers,
								byte[] body, Throwable tr) {
							Toast.makeText(mContext,
									"ERROR in hopping on: code " + statusCode,
									Toast.LENGTH_SHORT).show();
							Log.e(LOG_TAG, "ERROR in hopping on: code "
									+ statusCode, tr);
						}
					});
		} catch (JSONException e) {
			Log.e(LOG_TAG, "JSON Exception in adding new record", e);
		} catch (UnsupportedEncodingException e) {
			Log.e(LOG_TAG, "Encoding Exception in adding new record", e);
		}
	}
}