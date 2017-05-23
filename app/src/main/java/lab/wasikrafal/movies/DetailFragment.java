package lab.wasikrafal.movies;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


public class DetailFragment extends Fragment implements View.OnClickListener
{
    Movie currentMovie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater
                .inflate(R.layout.fragment_detail, container, false);
        Button b = (Button) view.findViewById(R.id.buttonActors);
        b.setOnClickListener(this);

        return view;

    }

    public void setMovie(Movie m)
    {
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        currentMovie=m;
        Button b = (Button) getView().findViewById(R.id.buttonActors);
        b.setEnabled(true);
        view.setText(currentMovie.toString());
        RatingBar rb = (RatingBar) getView().findViewById(R.id.detRat);
        rb.setRating(currentMovie.getRating());
    }

    @Override
    public void onClick(View v)
    {
        Intent displayActors = new Intent(this.getActivity(), ActorsActivity.class);
        displayActors.putExtra("Movie", currentMovie);
        startActivity(displayActors);
    }
}