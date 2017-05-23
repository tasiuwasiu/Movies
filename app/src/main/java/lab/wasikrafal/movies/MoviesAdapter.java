package lab.wasikrafal.movies;

/**
 * Created by Rafał on 10.05.2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>
{

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, year, genre;
        public RatingBar rating;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            rating = (RatingBar) view.findViewById(R.id.rating);
        }
    }


    public MoviesAdapter(List<Movie> moviesList)
    {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
        holder.rating.setRating(movie.getRating());
    }

    @Override
    public int getItemCount()
    {
        return moviesList.size();
    }
}
