package com.codepath.example.rottentomatoes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

public class BoxOfficeActivity extends Activity {
	private ListView lvMovies;
	private BoxOfficeMoviesAdapter adapterMovies;
	private RottenTomatoesClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_box_office);
		lvMovies = (ListView) findViewById(R.id.lvMovies);
		ArrayList<BoxOfficeMovie> aMovies = new ArrayList<BoxOfficeMovie>();
		adapterMovies = new BoxOfficeMoviesAdapter(this, aMovies);
		lvMovies.setAdapter(adapterMovies);
		// Fetch the data remotely
		fetchBoxOfficeMovies();
	}

	private void fetchBoxOfficeMovies() {
		client = new RottenTomatoesClient();
		client.getBoxOfficeMovies(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int code, JSONObject body) {
				JSONArray items = null;
				try {
					// Get the movies json array
					items = body.getJSONArray("movies");
					// Parse json array into array of model objects
					ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJson(items);
					// Load model objects into the adapter which displays them
					adapterMovies.addAll(movies);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
