package com.codepath.example.rottentomatoes;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class BoxOfficeMovie implements Serializable {
	private static final long serialVersionUID = -8959832007991513854L;
	private String title;
	private int year;
	private String synopsis;
	private String posterUrl;
	private String largePosterUrl;
	private String criticsConsensus;
	private int audienceScore;

	private int criticsScore;
	private ArrayList<String> castList;

	// Returns a BoxOfficeMovie given the expected JSON
	// Reads `title`, `year`, `synopsis`, `posters.thumbnail`,
	// `ratings.critics_score` and the `abridged_cast`
	public static BoxOfficeMovie fromJson(JSONObject jsonObject) {
		BoxOfficeMovie b = new BoxOfficeMovie();
		try {
			// Deserialize json into object fields
			b.title = jsonObject.getString("title");
			b.year = jsonObject.getInt("year");
			b.synopsis = jsonObject.getString("synopsis");
			b.posterUrl = jsonObject.getJSONObject("posters").getString("thumbnail");
			b.largePosterUrl = jsonObject.getJSONObject("posters").getString("detailed");
			b.criticsConsensus = jsonObject.getString("critics_consensus");
			b.criticsScore = jsonObject.getJSONObject("ratings").getInt("critics_score");
			b.audienceScore = jsonObject.getJSONObject("ratings").getInt("audience_score");
			// Construct simple array of cast names
			b.castList = new ArrayList<String>();
			JSONArray abridgedCast = jsonObject.getJSONArray("abridged_cast");
			for (int i = 0; i < abridgedCast.length(); i++) {
				b.castList.add(abridgedCast.getJSONObject(i).getString("name"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		// Return new object
		return b;
	}

	// Decodes array of box office movie json results into business model objects
	public static ArrayList<BoxOfficeMovie> fromJson(JSONArray jsonArray) {
		ArrayList<BoxOfficeMovie> businesses = new ArrayList<BoxOfficeMovie>(jsonArray.length());
		// Process each result in json array, decode and convert to business
		// object
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject businessJson = null;
			try {
				businessJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			BoxOfficeMovie business = BoxOfficeMovie.fromJson(businessJson);
			if (business != null) {
				businesses.add(business);
			}
		}

		return businesses;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public int getCriticsScore() {
		return criticsScore;
	}

	public String getCastList() {
		return TextUtils.join(", ", castList);
	}
	
	public String getLargePosterUrl() {
		return largePosterUrl;
	}

	public String getCriticsConsensus() {
		return criticsConsensus;
	}
	
	public int getAudienceScore() {
		return audienceScore;
	}

}
