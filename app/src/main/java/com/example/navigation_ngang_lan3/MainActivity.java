package com.example.navigation_ngang_lan3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.navigation_ngang_lan3.fragment.fragment_home;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private static final int fragment_home = 0;
    private static final int fragment_tubatquytac = 1;
    private static final int fragment_history = 2;
    private static final int fragment_profile = 3;
    private static final int fragment_logout = 4;

    private int currentfragment = fragment_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //đây là đang tạo cái 3 gạch để show cái navigation ra
        Toolbar toolbar =findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //cái này là dang khai báo navigation
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //vaào app 1 phát vào home luôn
        replaceFragment(new fragment_home());
        //sét caái checked của cái home ở navigation
        navigationView.getMenu().findItem(R.id.itemHome).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id =item.getItemId();
        if(id == R.id.itemHome){
            if(currentfragment!=fragment_home){
                replaceFragment(new fragment_home());
                currentfragment = fragment_home;
            }

        }else if(id == R.id.itemHome){

        }else if(id == R.id.itemBatquytac){

        }else if(id == R.id.itemHistory){

        }else if(id == R.id.itemProfile){

        }else if(id == R.id.itemLogout){

        }

        drawerLayout.closeDrawer(GravityCompat.START);
            return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
}