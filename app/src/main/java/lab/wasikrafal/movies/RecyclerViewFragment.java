package lab.wasikrafal.movies;

/**
 * Created by Rafał on 10.05.2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.Activity;


public class RecyclerViewFragment extends Fragment
{

    private RecyclerFragmentActivityListener listener;
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType
    {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    private List<Movie> movieList = new ArrayList<>();
    protected RecyclerView mRecyclerView;
    protected MoviesAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.recycler_layout, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null)
        {
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }

        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mAdapter = new MoviesAdapter(movieList);
        mRecyclerView.setAdapter(mAdapter);
        initDataset();

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
                selectMovie(movie);
            }

            @Override
            public void onLongClick(View view, int position) {
                Movie movie = movieList.get(position);
                selectMovie(movie);
            }
        }));

        return rootView;
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType)
    {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    public void onAttach(Context context)
    {
        super.onAttach(context);
        Activity a = (Activity) context;

        if (a instanceof RecyclerFragmentActivityListener)
        {
            listener = (RecyclerFragmentActivityListener) a;
        }
        else
        {
            throw new ClassCastException( a.toString() + " musi implementować interfejs");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }


    private void initDataset()
    {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movie.setActorList(Arrays.asList("Mel Gibson", "Hugh Jackman", "Jason Statham", "Emma Watson", "Sean Connery"));
        movie.setImageList(Arrays.asList(R.drawable.pic1,R.drawable.pic5,R.drawable.pic2,R.drawable.pic6,R.drawable.pic3,R.drawable.pic9));
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movie.setActorList(Arrays.asList("Jason Statham", "Clint Eastwood", "Leonardo diCaprio", "Hugh Jackman", "Keira Knightley", "Mel Gibson"));
        movie.setImageList(Arrays.asList(R.drawable.pic5,R.drawable.pic1,R.drawable.pic7,R.drawable.pic6,R.drawable.pic2,R.drawable.pic3));
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movie.setActorList(Arrays.asList("Sean Connery", "Johny Deep", "Jason Statham", "Clint Eastwood", "Leonardo diCaprio"));
        movie.setImageList(Arrays.asList(R.drawable.pic4,R.drawable.pic9,R.drawable.pic2,R.drawable.pic3,R.drawable.pic6,R.drawable.pic1));
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        movie.setActorList(Arrays.asList("Hugh Jackman", "Keira Knightley", "Mel Gibson", "Emma Watson", "Sean Connery", "Johny Deep"));
        movie.setImageList(Arrays.asList(R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6));
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        movie.setActorList(Arrays.asList("Mel Gibson", "Hugh Jackman", "Jason Statham", "Emma Watson", "Sean Connery"));
        movie.setImageList(Arrays.asList(R.drawable.pic9,R.drawable.pic8,R.drawable.pic7,R.drawable.pic6,R.drawable.pic5,R.drawable.pic4));
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        movie.setActorList(Arrays.asList("Jason Statham", "Clint Eastwood", "Leonardo diCaprio", "Hugh Jackman", "Keira Knightley", "Mel Gibson"));
        movie.setImageList(Arrays.asList(R.drawable.pic1,R.drawable.pic5,R.drawable.pic2,R.drawable.pic6,R.drawable.pic3,R.drawable.pic9));
        movieList.add(movie);

        movie = new Movie("Up", "Animation", "2009");
        movie.setActorList(Arrays.asList("Sean Connery", "Johny Deep", "Jason Statham", "Clint Eastwood", "Leonardo diCaprio"));
        movie.setImageList(Arrays.asList(R.drawable.pic5,R.drawable.pic1,R.drawable.pic7,R.drawable.pic6,R.drawable.pic2,R.drawable.pic3));
        movieList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009");
        movie.setActorList(Arrays.asList("Hugh Jackman", "Keira Knightley", "Mel Gibson", "Emma Watson", "Sean Connery", "Johny Deep"));
        movie.setImageList(Arrays.asList(R.drawable.pic4,R.drawable.pic9,R.drawable.pic2,R.drawable.pic3,R.drawable.pic6,R.drawable.pic1));
        movieList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014");
        movie.setActorList(Arrays.asList("Mel Gibson", "Hugh Jackman", "Jason Statham", "Emma Watson", "Sean Connery"));
        movie.setImageList(Arrays.asList(R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6));
        movieList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        movie.setActorList(Arrays.asList("Jason Statham", "Clint Eastwood", "Leonardo diCaprio", "Hugh Jackman", "Keira Knightley", "Mel Gibson"));
        movie.setImageList(Arrays.asList(R.drawable.pic9,R.drawable.pic8,R.drawable.pic7,R.drawable.pic6,R.drawable.pic5,R.drawable.pic4));
        movieList.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986");
        movie.setActorList(Arrays.asList("Sean Connery", "Johny Deep", "Jason Statham", "Clint Eastwood", "Leonardo diCaprio"));
        movie.setImageList(Arrays.asList(R.drawable.pic1,R.drawable.pic5,R.drawable.pic2,R.drawable.pic6,R.drawable.pic3,R.drawable.pic9));
        movieList.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000");
        movie.setActorList(Arrays.asList("Hugh Jackman", "Keira Knightley", "Mel Gibson", "Emma Watson", "Sean Connery", "Johny Deep"));
        movie.setImageList(Arrays.asList(R.drawable.pic5,R.drawable.pic1,R.drawable.pic7,R.drawable.pic6,R.drawable.pic2,R.drawable.pic3));
        movieList.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        movie.setActorList(Arrays.asList("Mel Gibson", "Hugh Jackman", "Jason Statham", "Emma Watson", "Sean Connery"));
        movie.setImageList(Arrays.asList(R.drawable.pic4,R.drawable.pic9,R.drawable.pic2,R.drawable.pic3,R.drawable.pic6,R.drawable.pic1));
        movieList.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movie.setActorList(Arrays.asList("Jason Statham", "Clint Eastwood", "Leonardo diCaprio", "Hugh Jackman", "Keira Knightley", "Mel Gibson"));
        movie.setImageList(Arrays.asList(R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6));
        movieList.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        movie.setActorList(Arrays.asList("Sean Connery", "Johny Deep", "Jason Statham", "Clint Eastwood", "Leonardo diCaprio"));
        movie.setImageList(Arrays.asList(R.drawable.pic9,R.drawable.pic8,R.drawable.pic7,R.drawable.pic6,R.drawable.pic5,R.drawable.pic4));
        movieList.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movie.setActorList(Arrays.asList("Hugh Jackman", "Keira Knightley", "Mel Gibson", "Emma Watson", "Sean Connery", "Johny Deep"));
        movie.setImageList(Arrays.asList(R.drawable.pic1,R.drawable.pic5,R.drawable.pic2,R.drawable.pic6,R.drawable.pic3,R.drawable.pic9));
        movieList.add(movie);
    }

    public interface RecyclerFragmentActivityListener
    {
        public void onItemSelected(Movie m);
    }

    public void selectMovie(Movie m)
    {
        listener.onItemSelected(m);
    }
}