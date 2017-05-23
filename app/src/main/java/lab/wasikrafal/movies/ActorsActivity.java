package lab.wasikrafal.movies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActorsActivity extends AppCompatActivity
{

    Movie currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors);
        Intent currentI = getIntent();
        currentMovie = (Movie)currentI.getSerializableExtra("Movie");
        setImages();
        ActorListFragment fr = (ActorListFragment) getFragmentManager().findFragmentById(R.id.actorFragment);
        fr.setMovie(currentMovie);
    }


    private void setImages()
    {
        final ImageView iv1 = (ImageView) findViewById(R.id.iv1);
        iv1.setImageResource(currentMovie.getImage(0));

        final ImageView iv2 = (ImageView) findViewById(R.id.iv2);
        iv2.setImageResource(currentMovie.getImage(1));

        final ImageView iv3 = (ImageView) findViewById(R.id.iv3);
        iv3.setImageResource(currentMovie.getImage(2));

        final ImageView iv4= (ImageView) findViewById(R.id.iv4);
        iv4.setImageResource(currentMovie.getImage(3));

        final ImageView iv5 = (ImageView) findViewById(R.id.iv5);
        iv5.setImageResource(currentMovie.getImage(4));

        final ImageView iv6 = (ImageView) findViewById(R.id.iv6);
        iv6.setImageResource(currentMovie.getImage(5));

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        setImages();
    }
}
