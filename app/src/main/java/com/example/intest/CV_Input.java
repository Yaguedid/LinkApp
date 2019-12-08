package com.example.intest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intest.R;
import com.example.intest.function.SendSms;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CV_Input extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference offerSettingsRef;
    private String EmailUser, FisrtnameUser, LastNameUser, IdUser, PictureUser;
    EditText FullNameView,BirthDayView,PhoneView,EmailView,ProfileView;
    ImageView imageUser;
    private SharedPreferences userinfo;
    TextView domaineItemSelected,TypeItemSelected,requiremtnItemSelected,skillsItemSelected,cityItemSelected,periodeItemSelected;
    Button domaineItemsBtn,TypeItemsBtn,RequirementsItemsBtn,skillsItemsBtn,cityItmesBtn,periodeItemsBtn;
    String[] domaineListItems,TypeListItems,requirementListItems,skillsListItems,cityListItems,periodeListItems;
    public List<String> domainList;
    public List<String> typeList;
    public List<String> reqList;
    public List<String> skillsList;
    public List<String> periodeList;
    public List<String> citiesList;
    public List<Integer> mDomainItems = new ArrayList<>();
    public List<Integer> mTypeItems = new ArrayList<>();
    public List<Integer> mReqItems = new ArrayList<>();
    public List<Integer> mSkillsrItems = new ArrayList<>();
    public List<Integer> mCitiesItems = new ArrayList<>();
    public List<Integer> mPeriodsItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv__input);
        database = FirebaseDatabase.getInstance();

        //shared pref for candidateId

        domainList=new ArrayList<>();
        reqList=new ArrayList<>();
        skillsList=new ArrayList<>();
        typeList=new ArrayList<>();
        periodeList=new ArrayList<>();
        citiesList=new ArrayList<>();
        database = FirebaseDatabase.getInstance();

        /****************************get user info ******************/
        userinfo=getSharedPreferences("userinfos", MODE_PRIVATE);

        EmailUser=userinfo.getString("email",null);
        FisrtnameUser=userinfo.getString("firstname",null);
        LastNameUser=userinfo.getString("lastname",null);
        IdUser=userinfo.getString("id",null);
        PictureUser=userinfo.getString("picture",null);
        instantiateViews();
/*********************************************/



    }

    private void instantiateViews() {

        FullNameView=findViewById(R.id.fullNameUser);
        BirthDayView=findViewById(R.id.birthdayId);
        PhoneView=findViewById(R.id.phoneId);
        cityItemSelected=findViewById(R.id.cityId);
        EmailView=findViewById(R.id.emailId);
        skillsItemSelected=findViewById(R.id.skillsId);
        ProfileView=findViewById(R.id.profileId);
        requiremtnItemSelected=findViewById(R.id.dimplomeId);
        domaineItemSelected=findViewById(R.id.genieId);
        TypeItemSelected=findViewById(R.id.typeOfferId);
        imageUser=findViewById(R.id.imageUser);
        cityItmesBtn=findViewById(R.id.cityIdButt);
        skillsItemsBtn=findViewById(R.id.skillsIdButt);
        RequirementsItemsBtn=findViewById(R.id.dimplomeIdButt);
        domaineItemsBtn=findViewById(R.id.dimplomeIdButt);
        TypeItemsBtn=findViewById(R.id.typeOfferIdButt);

        domaineListItems = getResources().getStringArray(R.array.domaine_item);
        TypeListItems = getResources().getStringArray(R.array.type_item);
        requirementListItems = getResources().getStringArray(R.array.requirement_item);
        cityListItems=getResources().getStringArray(R.array.city_item);

    }








}
