package com.example.intest;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class tab_4 extends Fragment {

  /*++++++++++++ ++++++++++++++++++++++ ++++++++++++++++++++++ ++++++++++++++++++++++++++++


   You find the data that u want to send  to FireBase in the core of confirmeData() method



   ++++++++++  +++++++++++ ++++++++++++++++ ++++++++++++++++++++++ ++++++++++++++++++++++++*/


    Button selectOffre;
    Button next;
    TextView offerSelected;

    Button selectPeriod;
    TextView periodSelected;

    EditText aboutUser;
    String AboutUser;

    public List<Integer> mOffesItems = new ArrayList<>();
    public List<String> offersList=new ArrayList<>();
    String[] offersListItems;

    public List<Integer> mPeriodItems = new ArrayList<>();
    public List<String> PeriodList=new ArrayList<>();
    String[] periodListItems;



    AlertDialog.Builder mBuilder ;
    AlertDialog.Builder mPeriodBuillder ;
    Boolean FILED_VERIFICATION=false;


    Context context;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.offretype, container, false);


        context=this.getActivity();



        mBuilder = new AlertDialog.Builder(context);
        mPeriodBuillder = new AlertDialog.Builder(context);



        next=(Button)root.findViewById(R.id.next_offre);
        selectOffre=(Button) root.findViewById(R.id.offre_looking_id);
        selectPeriod=(Button) root.findViewById(R.id.period_id);
        offerSelected=(TextView)root.findViewById(R.id.offresSelected_id);
        periodSelected=(TextView)root.findViewById(R.id.periodSelected_id);
        aboutUser =(EditText) root.findViewById(R.id.about_uer_id);

        offersListItems =getResources().getStringArray(R.array.type_item);
        periodListItems =getResources().getStringArray(R.array.period_item);



        checkIfListEmpty(PeriodList,periodSelected);
        checkIfListEmpty(offersList,offerSelected);




        selectOffre.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) { showOffreSelected();
                                           }

                                       });



        selectPeriod.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) { showPeriodSelected();
                                           }
                                       });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { confirmeData();

            }
        });

        aboutUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                aboutUser.setBackgroundColor(getResources().getColor(R.color.Verifcation_Button));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        chekIfFiledEmpty();
        checkIfListEmpty(offersList,offerSelected);
        checkIfListEmpty(PeriodList,periodSelected);

    }

    private  void checkIfListEmpty(List<String> list, TextView textView){
        if(!list.isEmpty()){

            String item="";
            for(int i=0;i<list.size();i++){

                item=item+list.get(i) +",\n";
            }
            textView.setText(item);
            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        }}



    private void chekInfo(){

        AboutUser=aboutUser.getText().toString();

        if(!offersList.isEmpty())  TabsHolder.getInstance().chekMap.put("offre_type","true");
        else TabsHolder.getInstance().chekMap.put("offre_type","false");
        if(!PeriodList.isEmpty())  TabsHolder.getInstance().chekMap.put("offre_period","true");
        else TabsHolder.getInstance().chekMap.put("offre_period","false");
        if(!AboutUser.isEmpty()) TabsHolder.getInstance().chekMap.put("offre_about","true");
        else TabsHolder.getInstance().chekMap.put("offre_about","false");
    }


    public  void confirmeData() {
        Boolean  DATA_IS_INVALISD=false;
        tab_1.getInstance().chekInfo();
        tab_2.getInstance().chekInfo();
        tab_3.getInstance().chekInfo();
        chekInfo();

        Log.d("zb44", TabsHolder.getInstance().chekMap.toString() + "");

        HashMap<String, String> resultkMap = new HashMap<String, String>(TabsHolder.getInstance().chekMap);
        List<String> keys = new ArrayList<String>();
        keys.add("Peron_city");
        keys.add("Peron_lastname");
        keys.add("Peron_birth_date");
        keys.add("Peron_email");
        keys.add("peron_name");
        keys.add("Peron_phone");
        keys.add("education_diplome");
        keys.add("education_domaine");
        keys.add("skills_langue");
        keys.add("skills");
        keys.add("offre_type");
        keys.add("offre_period");
        keys.add("offre_about");

        for (int i = 0; i < keys.size(); i++) {
            Log.d("zbbbbbb = ", "" + i + "= " + keys.get(i));
        }


        for (String boolindex : resultkMap.keySet()) {
            if (resultkMap.get(boolindex).equals("false")) {
                if (boolindex.equals(keys.get(0)) || boolindex.equals(keys.get(1)) || boolindex.equals(keys.get(2)) || boolindex.equals(keys.get(3)) || boolindex.equals(keys.get(4)) || boolindex.equals(keys.get(5)))
                    tab_1.getInstance().FILED_VERIFICATION = true;
                if (boolindex.equals(keys.get(6)) || boolindex.equals(keys.get(7)))
                    tab_2.getInstance().FILED_VERIFICATION = true;
                if (boolindex.equals(keys.get(8)) || boolindex.equals(keys.get(9)))
                    tab_3.getInstance().FILED_VERIFICATION = true;
                tab_3.getInstance().chekIfFiledEmpty();


                if (boolindex.equals(keys.get(10)) || boolindex.equals(keys.get(11)) || boolindex.equals(keys.get(12)) ) {
                    FILED_VERIFICATION = true;
                    chekIfFiledEmpty();

                }

            }
        }


/*++++++++++++ ++++++++++++++++++++++ ++++++++++++++++++++++ ++++++++++++++++++++++++++++

   You find the data that u want to send  to FireBase   Here
                 that's an exemple
 ++++++++++  +++++++++++ ++++++++++++++++ ++++++++++++++++++++++ ++++++++++++++++++++++++*/
        for(String bool : resultkMap.values()){
            if(bool.equals("false")){
                DATA_IS_INVALISD=true;
                break;
            }
        }
        Log.d("gag",DATA_IS_INVALISD.toString());
        if(DATA_IS_INVALISD==false) {
            Log.d("xnxx", tab_1.getInstance().name + "");
            Log.d("xnxx", tab_1.getInstance().lastName + "");
            Log.d("xnxx", tab_1.getInstance().phone + "");
            Log.d("xnxx", tab_1.getInstance().email + "");
            Log.d("xnxx", tab_1.getInstance().city + "");
            Log.d("xnxx", tab_1.getInstance().birthDate + "");


            Log.d("xnxx", "Domaine " + tab_2.getInstance().domainList.toString());
            Log.d("xnxx", "Diplome " + tab_2.getInstance().niveuEtude);

            Log.d("xnxx", "skills " + tab_3.getInstance().skillsList.toString() + "");
            Log.d("xnxx", "langue " + tab_3.getInstance().langueList.toString() + "");


            Log.d("xnxx", "offre chercher " + offersList.toString() + "");
            Log.d("xnxx", "period de stage " + PeriodList.toString() + "");
            Log.d("xnxx", "about " + AboutUser.toString() + "");



        }else{
            Toast.makeText(context,"please chek your data ",Toast.LENGTH_SHORT).show();
        }




    }

    public void chekIfFiledEmpty(){
        if (FILED_VERIFICATION == true) {

            if (offersList.isEmpty()) {
                offerSelected.setText("no items selected !");
                offerSelected.setTextColor(getResources().getColor(R.color.colorAccent));
                selectOffre.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }

            if (PeriodList.isEmpty()) {
                periodSelected.setText("no items selected !");
                periodSelected.setTextColor(getResources().getColor(R.color.colorAccent));
                selectPeriod.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
            if (AboutUser.isEmpty()) {
                aboutUser.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }

        }

    }


    public  void showOffreSelected(){
        final boolean[] checkedItems;

        checkedItems = new boolean[offersListItems.length];

        offersList= new ArrayList<>();



        mBuilder.setTitle("Offre you lokking for ?");

        mBuilder.setMultiChoiceItems(offersListItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {

                if(isChecked){
                    mOffesItems.add(position);
                }else{
                    mOffesItems.remove((Integer.valueOf(position)));
                }
            }
        });

        mBuilder.setCancelable(false);

        mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String item = "";
                for (int i = 0; i < mOffesItems.size(); i++) {
                    if(! offersList.contains(offersListItems[mOffesItems.get(i)] )) {
                        item = item + offersListItems[mOffesItems.get(i)] + ",\n";
                        offersList.add(offersListItems[mOffesItems.get(i)]);

                    }
                }
                if(item.equals("")){
                    offerSelected.setText("no items selected !");
                    offerSelected.setTextColor(getResources().getColor(R.color.colorAccent));
                }else {
                    offerSelected.setText(item);
                    offerSelected.setTextColor(getResources().getColor(R.color.colorPrimary));}
                selectOffre.setBackgroundColor(getResources().getColor(R.color.Verifcation_Button));

            }
        });

        mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                offersList.clear();
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;}

                mOffesItems.clear();
                offerSelected.setText("no items selected !");
                offerSelected.setTextColor(getResources().getColor(R.color.colorAccent));
                selectOffre.setBackgroundColor(getResources().getColor(R.color.Verifcation_Button));


            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();



    }
    public  void showPeriodSelected(){
        final boolean[] checkedPeriodItems;

        checkedPeriodItems = new boolean[periodListItems.length];

        PeriodList= new ArrayList<>();



        mPeriodBuillder.setTitle("selec period");

        mPeriodBuillder.setMultiChoiceItems(periodListItems, checkedPeriodItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {

                if(isChecked){
                    mPeriodItems.add(position);
                }else{
                    mPeriodItems.remove((Integer.valueOf(position)));
                }
            }
        });

        mPeriodBuillder.setCancelable(false);

        mPeriodBuillder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String item = "";
                for (int i = 0; i < mPeriodItems.size(); i++) {
                    if(! PeriodList.contains(periodListItems[mPeriodItems.get(i)] )) {
                        item = item + periodListItems[mPeriodItems.get(i)] + ",\n";
                        PeriodList.add(periodListItems[mPeriodItems.get(i)]);

                    }
                }
                if(item.equals("")){
                    periodSelected.setText("no items selected !");
                    periodSelected.setTextColor(getResources().getColor(R.color.colorAccent));
                }else {
                    periodSelected.setText(item);
                    periodSelected.setTextColor(getResources().getColor(R.color.colorPrimary));
                    selectPeriod.setBackgroundColor(getResources().getColor(R.color.Verifcation_Button));
                }
            }
        });

        mPeriodBuillder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mPeriodBuillder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                PeriodList.clear();
                for (int i = 0; i < checkedPeriodItems.length; i++) {
                    checkedPeriodItems[i] = false;}

                mPeriodItems.clear();
                periodSelected.setText("no items selected !");
                periodSelected.setTextColor(getResources().getColor(R.color.colorAccent));


            }
        });

        AlertDialog mDialog = mPeriodBuillder.create();
        mDialog.show();

    }




}
