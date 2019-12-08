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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.InputStream;

public class DashbordStudent extends AppCompatActivity {
    TextView FirstNameUserView,LastNameUserView;
    private String EmailUser,FisrtnameUser,LastNameUser,IdUser,PictureUser;
    private SharedPreferences userinfo;
    ImageView userImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord_student);
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
        AdView mAdView = findViewById(R.id.adViewh);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();

        inflater.inflate(R.menu.menu_student,menu);


        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.settings:
                startActivity(new Intent(DashbordStudent.this, Settings.class));
                return true;
            case R.id.myInbox:
                startActivity(new Intent(DashbordStudent.this, InboxForStudents.class));
                return true;
            case R.id.myCv:
               Toast.makeText(DashbordStudent.this,"mycv",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.advanced_shearch:
                startActivity(new Intent(DashbordStudent.this, SharchForOffer.class));
                return true;

        }


        return super.onOptionsItemSelected(item);
    }
    private void instantiateViews() {

        FirstNameUserView=findViewById(R.id.FirstNameUser);
        LastNameUserView=findViewById(R.id.LastNameUser);
        userImage=findViewById(R.id.UserImage);
        FirstNameUserView.setText(FisrtnameUser);
        LastNameUserView.setText(LastNameUser);
        new DashbordStudent.DownloadImageTask((ImageView)userImage)
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
}
