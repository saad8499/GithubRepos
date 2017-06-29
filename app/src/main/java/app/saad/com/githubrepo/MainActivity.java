package app.saad.com.githubrepo;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import app.saad.com.githubrepo.fragments.RepoListFragment;
import app.saad.com.githubrepo.utils.CircleTransform;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private View navHeader;
    private ImageView imgNavHeaderBg;
    private ImageView imgProfile;
    private TextView txtName;


    private static final String urlNavHeaderBg = "https://doyleave.000webhostapp.com/images/pic/saaddb_img.jpg";
    private static final String urlProfileImg = "https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAMuAAAAJDdmOGM4MzhhLTYzM2QtNGVjZS1iYzM5LTczY2QzMWFkM2UxYw.jpg";

    public static int navItemIndex = 0;

    private static final String TAG_LIST = "List";
    public static String CURRENT_TAG = TAG_LIST;

    private String[] activityTitles;

    private boolean shouldLoadHomeFragOnBackPress = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);



        navHeader = navigationView.getHeaderView(0);
//        ButterKnife.bind(this,navHeader);
        imgNavHeaderBg = (ImageView)navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView)navHeader.findViewById(R.id.img_profile);
        txtName = (TextView)navHeader.findViewById(R.id.name);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG =TAG_LIST;
            loadHomeFragment();
        }
    }

    private void loadNavHeader() {
        // name, website
        txtName.setText("Saad Khan");

        // loading header background image
        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

    }

    private void loadHomeFragment() {
        selectNavMenu();
        setToolbarTitle();


        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }

        Fragment fragment = getHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();

        drawer.closeDrawers();

        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {

            case 0:
                // photos
                RepoListFragment repoListFragment = new RepoListFragment();
                return repoListFragment;
            default:
                return new RepoListFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle("Github Repos");
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_movies:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_LIST;
                        break;

                    default:
                        navItemIndex = 0;
                }

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        if (shouldLoadHomeFragOnBackPress) {

            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_LIST;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return super.onOptionsItemSelected(item);

    }

}

