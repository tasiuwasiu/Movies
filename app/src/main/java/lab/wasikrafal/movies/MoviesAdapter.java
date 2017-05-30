package lab.wasikrafal.movies;

/**
 * Created by Rafał on 10.05.2017.
 */

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>
{

    private List<Movie> moviesList;
    private List<Movie> itemsPendingRemoval;

    private static final int PENDING_REMOVAL_TIMEOUT = 3000;
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<Movie, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a removal if need be


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, year, genre;
        public RatingBar rating;
        public RelativeLayout regularLayout;
        public LinearLayout swipeLayout;
        public TextView listItem;
        public TextView undo;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            rating = (RatingBar) view.findViewById(R.id.rating);
            regularLayout = (RelativeLayout) view.findViewById(R.id.regularLayout);
            listItem = (TextView) view.findViewById(R.id.list_item);
            swipeLayout = (LinearLayout) view.findViewById(R.id.swipeLayout);
            undo = (TextView) view.findViewById(R.id.undo);
        }
    }


    public MoviesAdapter(List<Movie> moviesList)
    {
        this.moviesList = moviesList;
        itemsPendingRemoval = new ArrayList<>();
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
        final Movie movie = moviesList.get(position);


        if (itemsPendingRemoval.contains(movie)) {
            /** {show swipe layout} and {hide regular layout} */
            holder.regularLayout.setVisibility(View.GONE);
            holder.swipeLayout.setVisibility(View.VISIBLE);
            holder.undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    undoOpt(movie);
                }
            });
        } else {
            /** {show regular layout} and {hide swipe layout} */
            holder.regularLayout.setVisibility(View.VISIBLE);
            holder.swipeLayout.setVisibility(View.GONE);
            holder.title.setText(movie.getTitle());
            holder.genre.setText(movie.getGenre());
            holder.year.setText(movie.getYear());
            holder.rating.setRating(movie.getRating());
        }
    }

    private void undoOpt(Movie customer) {
        Runnable pendingRemovalRunnable = pendingRunnables.get(customer);
        pendingRunnables.remove(customer);
        if (pendingRemovalRunnable != null)
            handler.removeCallbacks(pendingRemovalRunnable);
        itemsPendingRemoval.remove(customer);
        // this will rebind the row in "normal" state
        notifyItemChanged(moviesList.indexOf(customer));
    }


    public void pendingRemoval(int position) {

        final Movie data = moviesList.get(position);
        if (!itemsPendingRemoval.contains(data)) {
            itemsPendingRemoval.add(data);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the data
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(moviesList.indexOf(data));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(data, pendingRemovalRunnable);
        }
    }

    public void remove(int position) {
        Movie data = moviesList.get(position);
        if (itemsPendingRemoval.contains(data)) {
            itemsPendingRemoval.remove(data);
        }
        if (moviesList.contains(data)) {
            moviesList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isPendingRemoval(int position) {
        Movie data = moviesList.get(position);
        return itemsPendingRemoval.contains(data);
    }

    @Override
    public int getItemCount()
    {
        return moviesList.size();
    }
}
