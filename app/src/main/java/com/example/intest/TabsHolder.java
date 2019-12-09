package com.example.intest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.intest.main.CustomViewPager;
import com.example.intest.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;


public class TabsHolder  extends AppCompatActivity {
    public static ViewPager viewPager;
    public static TabsHolder mVar;
    public static HashMap<String,String> chekMap =new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_holder);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        //viewPager.setPagingEnabled(true);
        viewPager.setAdapter(sectionsPagerAdapter);

        mVar=this;


        chekMap.put("peron_name","false");
        chekMap.put("Peron_lastname","false");
        chekMap.put("Peron_email","false");
        chekMap.put("Peron_phone","false");
        chekMap.put("Peron_birth_date","false");
        chekMap.put("Peron_city","false");

        chekMap.put("education_domaine","false");
        chekMap.put("education_diplome","false");

        chekMap.put("skills_langue","false");
        chekMap.put("skills","false");

        chekMap.put("offre_type","false");
        chekMap.put("offre_period","false");







        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
        public void setState(Boolean b){

          //  viewPager.setPagingEnabled(b);
        }

        public static TabsHolder getInstance(){
            return mVar;
        }



}
