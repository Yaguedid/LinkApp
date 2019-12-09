package com.example.intest;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class tab_4 extends Fragment {


    Button selectOffre;
    Button next;
    TextView itemsSelected;

    Button selectPeriod;
    TextView periodSelected;


    public List<Integer> mOffesItems = new ArrayList<>();
    public List<String> offersList=new ArrayList<>();
    String[] offersListItems;

    public List<Integer> mPeriodItems = new ArrayList<>();
    public List<String> PeriodList=new ArrayList<>();
    String[] periodListItems;



    AlertDialog.Builder mBuilder ;
    AlertDialog.Builder mPeriodBuillder ;

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
        itemsSelected=(TextView)root.findViewById(R.id.offresSelected_id);
        periodSelected=(TextView)root.findViewById(R.id.periodSelected_id);

        offersListItems =getResources().getStringArray(R.array.type_item);
        periodListItems =getResources().getStringArray(R.array.period_item);



        checkIfListEmpty(PeriodList,periodSelected);
        checkIfListEmpty(offersList,itemsSelected);



        selectOffre.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

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
                                                           itemsSelected.setText("no items selected !");
                                                           itemsSelected.setTextColor(getResources().getColor(R.color.colorAccent));
                                                       }else {
                                                           itemsSelected.setText(item);
                                                           itemsSelected.setTextColor(getResources().getColor(R.color.colorPrimary));}
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
                                                       itemsSelected.setText("no items selected !");
                                                       itemsSelected.setTextColor(getResources().getColor(R.color.colorAccent));


                                                   }
                                               });

                                               AlertDialog mDialog = mBuilder.create();
                                               mDialog.show();

                                           }

                                       }
        );



        selectPeriod.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {


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
                                                           periodSelected.setTextColor(getResources().getColor(R.color.colorPrimary));}
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
                                                       itemsSelected.setTextColor(getResources().getColor(R.color.colorAccent));


                                                   }
                                               });

                                               AlertDialog mDialog = mPeriodBuillder.create();
                                               mDialog.show();

                                           }

                                       }
        );



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmeData();

            }
        });


        return root;
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

        if(!offersList.isEmpty())  TabsHolder.getInstance().chekMap.put("offre_type","true");
        if(!PeriodList.isEmpty())  TabsHolder.getInstance().chekMap.put("offre_period","true");
    }


    public  void confirmeData(){

          tab_1.getInstance().chekInfo();
          tab_2.getInstance().chekInfo();
          tab_3.getInstance().chekInfo();
          chekInfo();

        Log.d("zb44" , TabsHolder.getInstance().chekMap.toString() +"");

         HashMap<String,String> resultkMap= new HashMap<String, String>( TabsHolder.getInstance().chekMap);

          for(String bool: resultkMap.values()){
              if(bool.equals("false")){
                  Toast.makeText(context,"complet your data ",Toast.LENGTH_SHORT).show();
              }


          }


    }
}
