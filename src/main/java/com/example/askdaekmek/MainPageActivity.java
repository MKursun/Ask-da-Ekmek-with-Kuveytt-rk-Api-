package com.example.askdaekmek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainPageActivity extends AppCompatActivity {

    String mail;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();
                    fragment.replace(R.id.frame_layout ,new HomeFragment());
                    fragment.commit();
                    break;
                case R.id.navigation_profile:

                    ProfileFragment pfragment =new ProfileFragment();
                    Bundle newBundle= new Bundle();
                    newBundle.putString("bundledData" , mail);
                    pfragment.setArguments(newBundle);

                    FragmentTransaction profileFragment = getSupportFragmentManager().beginTransaction();
                    profileFragment.replace(R.id.frame_layout ,pfragment);
                    profileFragment.commit();
                    break;
                case R.id.navigation_buying:
                    FragmentTransaction buyingFragment = getSupportFragmentManager().beginTransaction();
                    buyingFragment.replace(R.id.frame_layout ,new TransferFragment() );
                    buyingFragment.commit();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //ProfileFragment pfragment =new ProfileFragment();
        Bundle extras=getIntent().getExtras();
        mail=extras.getString("bundledEmail");
       /* Bundle newBundle= new Bundle();
        newBundle.putString("bundledData" , mail);
        pfragment.setArguments(newBundle);*/


        HomeFragment hm =new HomeFragment();
        FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();
        fragment.add(R.id.frame_layout,hm);
        fragment.commit();

    }


}
