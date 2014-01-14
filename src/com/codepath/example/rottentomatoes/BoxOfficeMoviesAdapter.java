package com.codepath.example.rottentomatoes;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BoxOfficeMoviesAdapter extends ArrayAdapter<BoxOfficeMovie> {
	public BoxOfficeMoviesAdapter(Context context, ArrayList<BoxOfficeMovie> aMovies) {
		super(context, 0, aMovies);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		BoxOfficeMovie movie = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.adapter_item_box_office_movie, null);
		}
		// Lookup view for data population
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
		TextView tvCriticsScore = (TextView) convertView.findViewById(R.id.tvCriticsScore);
		TextView tvCast = (TextView) convertView.findViewById(R.id.tvCast);
		ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);
		// Populate the data into the template view using the data object
		tvTitle.setText(movie.getTitle());
		tvCriticsScore.setText("Score: " + movie.getCriticsScore() + "%");
		tvCast.setText(movie.getCastList());
		Picasso.with(getContext()).load(movie.getPosterUrl()).into(ivPosterImage);
		// Return the completed view to render on screen
		return convertView;
	}
}
