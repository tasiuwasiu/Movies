package lab.wasikrafal.movies;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ActorListFragment extends ListFragment
{
    Movie currentMovie;

    @Override
    public View onCreateView(LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    public void setMovie(Movie m)
        {
                currentMovie=m;
        }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ListAdapter adapter = new ListAdapter(getActivity(),currentMovie.getActorList());
        setListAdapter(adapter);
    }
}