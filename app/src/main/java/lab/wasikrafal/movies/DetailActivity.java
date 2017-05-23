package lab.wasikrafal.movies;

/**
 * Created by Rafał on 10.05.2017.
 */

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // jeżeli użytkownik będzie w orientacji landscape, należy zamknąć
        // aktywność
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        setContentView(R.layout.activity_detail);

        // pobieramy dane wysłane przez aktywność główną
       // Bundle extras = getIntent().getExtras();
       // if (extras != null) {
       //     String url = extras.getString("msg");
       //     DetailFragment detailFragment = (DetailFragment) getFragmentManager()
       //             .findFragmentById(R.id.detailFragment);

            // ustawiamy tekst fragmentu w tej aktywności
        //    detailFragment.setMovie(url);
       //}
    }
}