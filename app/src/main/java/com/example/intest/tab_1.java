package com.example.intest;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class tab_1 extends Fragment   {

    ImageView userImage;
    EditText userName;
    EditText userLastName;
    EditText userEmail;
    EditText userPhone;
    Spinner Daybirth;
    Spinner Monthbirth;
    Spinner Yearbirth;
    Spinner userCity;

    String name;
    String lastName;
    String email;
    String phone;
    String birthDate;
    String city="";

    Button chooseImage;
    Button next;

    int PICK_IMAGE=3;
    Uri imgUri;

    String[] citysTable ;
    String[] daysTable ;
    String[] yearsTable ;
    String[] monthsTable ;


    Context context;

    public static tab_1  tab1_var;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pesonal_information, container, false);

        tab1_var=this;
        context=this.getActivity();
        userImage=(ImageView) root.findViewById(R.id.user_ImageId);
        userName=(EditText) root.findViewById(R.id.user_name);
        userLastName=(EditText) root.findViewById(R.id.user_lastName);
        userEmail=(EditText) root.findViewById(R.id.user_email);
        userPhone=(EditText) root.findViewById(R.id.user_phone);
        Daybirth=(Spinner) root.findViewById(R.id.day_birth_id);
        Monthbirth=(Spinner) root.findViewById(R.id.month_birth_id);
        Yearbirth=(Spinner) root.findViewById(R.id.year_birth_id);
        userCity=(Spinner) root.findViewById(R.id.user_city);

        citysTable =getResources().getStringArray(R.array.city);
        daysTable =getResources().getStringArray(R.array.day);
        yearsTable =getResources().getStringArray(R.array.years);
        monthsTable =getResources().getStringArray(R.array.months);


        ArrayAdapter arr_city = new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,citysTable);
        arr_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userCity.setAdapter(arr_city);

        ArrayAdapter arr_day = new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,daysTable);
        arr_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Daybirth.setAdapter(arr_day);

        ArrayAdapter arr_month = new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,monthsTable);
        arr_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Monthbirth.setAdapter(arr_month);

        ArrayAdapter arr_year = new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,yearsTable);
        arr_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Yearbirth.setAdapter(arr_year);

        initUserInfo();
        chooseImage =(Button)root.findViewById(R.id.chooseImg_id);
        next=(Button)root.findViewById(R.id.personel_info_next) ;


        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get image from gallery
                /*Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);*/
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("frag","im posed");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("frag","im reusmed");
    }

    private void initUserInfo() {

        Picasso.with(getActivity()).load("https://media.licdn.com/dms/image/C4D03AQGVFKFu7quDXw/profile-displayphoto-shrink_100_100/0?e=1581552000&v=beta&t=bsjTy5dFDcBk6mkloiAAPyL_ktPTKwYaex7MiaZtncM").into(userImage);
        userName.setText("redouan");
        userLastName.setText("eddafali");
        userEmail.setText("xxx@gmail.com");

    }

        public  void chekInfo(){

            name =userName.getText().toString();
            lastName =userLastName.getText().toString();
            email =userEmail.getText().toString();
            phone =userPhone.getText().toString();

            city = userCity.getSelectedItem().toString();
            birthDate=Daybirth.getSelectedItem().toString()+"-"+ Monthbirth.getSelectedItem().toString()+"-"+Yearbirth.getSelectedItem().toString();

            if(!name.isEmpty())TabsHolder.getInstance().chekMap.put("peron_name","true");
            if(!lastName.isEmpty())TabsHolder.getInstance().chekMap.put("Peron_lastname","true");
            if(!email.isEmpty())TabsHolder.getInstance().chekMap.put("Peron_email","true");
            if(!phone.isEmpty())TabsHolder.getInstance().chekMap.put("Peron_phone","true");
            if(!birthDate.isEmpty())TabsHolder.getInstance().chekMap.put("Peron_birth_date","true");
            if(!city.isEmpty())TabsHolder.getInstance().chekMap.put("Peron_city","true");


        }

        public static tab_1 getInstance(){
        return tab1_var;
        }


}






/* -------------- ------------------------------- --------------------------- --------------------------- */


/* load image from phone

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_IMAGE && resultCode == RESULT_OK){

          if(data!=null){
            imgUri=data.getData();
            Picasso.with(getActivity()).load(imgUri).into(userImage);}
        }



    }*/


