package com.example.intest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.InputStream;

public class DashbordEmployer extends AppCompatActivity {
    private String EmailUser, FisrtnameUser, LastNameUser, IdUser, PictureUser;
    private SharedPreferences userinfo;
    Button PostAnOfferButton;
    TextView FirstNameUserView, LastNameUserView;
    ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord_user);
        setAdds();
        userinfo=getSharedPreferences("userinfos", MODE_PRIVATE);
        EmailUser=userinfo.getString("email",null);
        FisrtnameUser=userinfo.getString("firstname",null);
        LastNameUser=userinfo.getString("lastname",null);
        IdUser=userinfo.getString("id",null);
        PictureUser=userinfo.getString("picture",null);
        instantiateViews();
    }

    private void setAdds() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void instantiateViews() {
        PostAnOfferButton=findViewById(R.id.postOffer);
        FirstNameUserView=findViewById(R.id.FirstNameUser);
        LastNameUserView=findViewById(R.id.LastNameUser);
        userImage=findViewById(R.id.UserImage);
        FirstNameUserView.setText(FisrtnameUser);
        LastNameUserView.setText(LastNameUser);
        new DashbordEmployer.DownloadImageTask((ImageView)userImage)
                .execute(PictureUser);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();

            inflater.inflate(R.menu.our_menu,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

         switch (item.getItemId())
            {
                case R.id.myOffers:
                    Toast.makeText(DashbordEmployer.this,"item1",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.myInbox:
                        startActivity(new Intent(DashbordEmployer.this, InboxForEmployers.class));
                    return true;
                case R.id.settings:
                    startActivity(new Intent(DashbordEmployer.this, Settings.class));
                    return true;


            }


        return super.onOptionsItemSelected(item);
    }
    public void postAnOffer(View view)
    {
        startActivity(new Intent(DashbordEmployer.this, PostAnOffer.class));
    }
    public void SearchForOffers(View view)
    {
        startActivity(new Intent(DashbordEmployer.this, SharchForOffer.class));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DashbordEmployer.this,WelcomeScreen.class));
    }
}
