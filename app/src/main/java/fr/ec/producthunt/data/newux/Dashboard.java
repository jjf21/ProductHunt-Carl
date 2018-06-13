package fr.ec.producthunt.data.newux;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import fr.ec.producthunt.R;
import fr.ec.producthunt.data.model.Post;
import fr.ec.producthunt.ui.detail.DetailActivity;
import fr.ec.producthunt.ui.detail.DetailPostFragment;
import fr.ec.producthunt.ui.home.CollectionsFragments;
import fr.ec.producthunt.ui.home.PostsFragments;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PostsFragments.Callback {

    private boolean towPane;
    DetailPostFragment detailPostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        detailPostFragment = (DetailPostFragment) DetailPostFragment.getNewInstance(null);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_frame, detailPostFragment);
        ft.commit();

        FrameLayout detailContainer = findViewById(R.id.content_frame);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (detailContainer != null) {
            towPane = true;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, DetailPostFragment.getNewInstance(null))
                    .commit();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_collections) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_frame, new CollectionsFragments());
            ft.commit();
        } else if (id == R.id.nav_publications) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_frame, new PostsFragments());
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
//        towPane = true;
        return true;

    }

    @Override
    public void onClickPost(Post post) {

        if (towPane) {
            /*DetailPostFragment detailPostFragment =
                    (DetailPostFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
*/
            /*DetailPostFragment detailPostFragment = (DetailPostFragment) DetailPostFragment.getNewInstance(null);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_frame, detailPostFragment);
            ft.commit();*/
            if (detailPostFragment != null) {
                detailPostFragment.loadUrl(post.getPostUrl());
            }
        } else {

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.POST_URL_KEY, post.getPostUrl());

            startActivity(intent);
        }

    }
}
