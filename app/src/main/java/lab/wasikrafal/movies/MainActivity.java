package lab.wasikrafal.movies;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Fragment;

public class MainActivity extends AppCompatActivity implements
        RecyclerViewFragment.RecyclerFragmentActivityListener
{
    private boolean isLand = false;
    private final FragmentManager fm = getFragmentManager();
    private Fragment currentFragment = null;
    private Fragment recFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.isLand = getResources().getBoolean(R.bool.isLand);
        if (!this.isLand) {
            setOverviewFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onItemSelected(Movie m) {
        DetailFragment fragment = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.detailFragment);
        // sprawdzamy czy fragment istnieje w tej aktywności
        if (fragment != null && fragment.isInLayout()) {
            // ustawiamy teskt we fragmencie
            fragment.setMovie(m);
        }else
        {
            setDetailsFragment();

            this.fm.executePendingTransactions();
            ((DetailFragment) this.currentFragment).setMovie(m);
        }
    }

    private void setOverviewFragment() {
        FragmentTransaction ft = this.fm.beginTransaction();
        if (recFragment==null)
            this.currentFragment= new RecyclerViewFragment();
        else
            this.currentFragment = recFragment;
        ft.replace(R.id.fragment_container, this.currentFragment);
        ft.commit();
    }

    private void setDetailsFragment() {
        FragmentTransaction ft = this.fm.beginTransaction();
        recFragment = this.currentFragment;
        this.currentFragment = new DetailFragment();
        ft.replace(R.id.fragment_container, this.currentFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
