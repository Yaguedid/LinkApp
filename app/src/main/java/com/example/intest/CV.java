package com.example.intest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.HashMap;

public class CV extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference offerSettingsRef;
    TextView FullNameView,BirthDayView,PhoneView,CityView,EmailView,LanguagesView,SkillsView,ProfileView,DiplomeView,
    DomaineView,OfferTypeView;
    ImageView imageUser;
    String candidateId="PrCQ7QHDMl";
    HashMap<String,String> user=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cv_layout);
        database = FirebaseDatabase.getInstance();
        Intent intent=getIntent();
      if(intent!=null)
        {
            candidateId=intent.getStringExtra("candidateId");
        }
        instantiateViews();
        getFromFireBase();


    }

    private void instantiateViews() {
        FullNameView=findViewById(R.id.fullNameUser);
        BirthDayView=findViewById(R.id.birthdayId);
        PhoneView=findViewById(R.id.phoneId);
        CityView=findViewById(R.id.cityId);
        EmailView=findViewById(R.id.emailId);
        LanguagesView=findViewById(R.id.languagesId);
        SkillsView=findViewById(R.id.skillsId);
        ProfileView=findViewById(R.id.profileId);
        DiplomeView=findViewById(R.id.dimplomeId);
        DomaineView=findViewById(R.id.genieId);
        OfferTypeView=findViewById(R.id.typeOfferId);
        imageUser=findViewById(R.id.imageUser);

    }
    private void getFromFireBase()
    {
        offerSettingsRef=database.getReference("Users").child(candidateId);
        offerSettingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    user.put(child.getKey(),child.getValue().toString());

                }
                setDataToViews();

            }
            @Override
            public void onCancelled(DatabaseError error) {}
        });

    }
    private void setDataToViews()
    {
        new CV.DownloadImageTask((ImageView)imageUser)
                .execute(user.get("Picture"));
        FullNameView.setText(user.get("FirstName")+" "+user.get("LastName"));
        BirthDayView.setText(user.get("Date_Of_Birth"));
        PhoneView.setText(user.get("Phone"));
        CityView.setText(user.get("City"));
        EmailView.setText(user.get("Email"));
        EmailView.setMovementMethod(new ScrollingMovementMethod());
        LanguagesView.setText(user.get("Languages"));
        LanguagesView.setMovementMethod(new ScrollingMovementMethod());
        SkillsView.setText(user.get("Skills"));
        SkillsView.setMovementMethod(new ScrollingMovementMethod());
        ProfileView.setText(user.get("Profil"));
        ProfileView.setMovementMethod(new ScrollingMovementMethod());
        PhoneView.setMovementMethod(new ScrollingMovementMethod());
        DiplomeView.setText(user.get("Diplomas"));
        DomaineView.setText(user.get("Domaine"));
        OfferTypeView.setText(user.get("Type_Shearched_Offer"));





    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public void yes(View view)
    {
        Intent intent=new Intent(CV.this, SendSms.class);
        intent.putExtra("FullNameCandidate",user.get("FirstName")+" "+user.get("LastName"));
        intent.putExtra("OfferType",user.get("Type_Shearched_Offer"));
        intent.putExtra("PhoneNumber",user.get("Phone"));
        startActivity(intent);

    }
    public void no(View view)
    {
        Toast.makeText(CV.this,"NO!",Toast.LENGTH_SHORT).show();
    }

}
