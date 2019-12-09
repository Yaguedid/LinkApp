package com.example.intest;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class tab_2 extends Fragment {


    CheckBox box1;
    CheckBox box2;
    CheckBox box3;
    CheckBox box4;

    String niveuEtude;

    Button select_geni;
    Button next;
    TextView itemsSelected;


    public List<Integer> mDomainItems = new ArrayList<>();
    public List<String> domainList=new ArrayList<>();;
    String[] domaineListItems;
    AlertDialog.Builder mBuilder ;


    Context context;
    public static tab_2  tab2_var;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, final ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.education, container, false);

        tab2_var=this;
        context=this.getActivity();
        mBuilder = new AlertDialog.Builder(this.getActivity());
        box1 =(CheckBox) root.findViewById(R.id.bacPlus_2);
        box2 =(CheckBox) root.findViewById(R.id.bacPlus_3);
        box3 =(CheckBox) root.findViewById(R.id.bacPlus_4);
        box4 =(CheckBox) root.findViewById(R.id.bacPlus_5);
        next=(Button)root.findViewById(R.id.next_id);
        select_geni=(Button) root.findViewById(R.id.geni_id);
        itemsSelected=(TextView)root.findViewById(R.id.geni_selected_id);
        domaineListItems =getResources().getStringArray(R.array.domaine_item);

        checkIfListEmpty(domainList,itemsSelected);

        select_geni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    showListItemes(domaineListItems,itemsSelected);
            }

        }
        );



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }



            //here where you get your data
            //uplod to firebase
        });


        return root;
    }


    private void showListItemes(final String[] itemsResTab, final TextView textView){

        final boolean[] checkedItems;

        checkedItems = new boolean[itemsResTab.length];

        domainList= new ArrayList<>();

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);

        mBuilder.setTitle(R.string.dialog_title);

        mBuilder.setMultiChoiceItems(itemsResTab, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {

                if(isChecked){
                    mDomainItems.add(position);
                }else{
                    mDomainItems.remove((Integer.valueOf(position)));
                }
            }
        });

        mBuilder.setCancelable(false);

        mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String item = "";
                for (int i = 0; i < mDomainItems.size(); i++) {
                    if(! domainList.contains(itemsResTab[mDomainItems.get(i)] )) {
                        item = item + itemsResTab[mDomainItems.get(i)] + ",\n ";
                        domainList.add(itemsResTab[mDomainItems.get(i)]);

                    }
                }
                if(item.equals("")){
                    textView.setText("no items selected !");
                    textView.setTextColor(getResources().getColor(R.color.colorAccent));
                }else {
                    textView.setText(item);
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));}
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
                domainList.clear();
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;}

                mDomainItems.clear();
                textView.setText("no items selected !");
                textView.setTextColor(getResources().getColor(R.color.colorAccent));


            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    private  void checkIfListEmpty(List<String> list, TextView textView){
        if(!list.isEmpty()){

            String item="";
            for(int i=0;i<list.size();i++){

                item=item+list.get(i) +",\n";
            }
            textView.setText(item);
            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else {
            textView.setText("no item is selected");
            textView.setTextColor(getResources().getColor(R.color.colorAccent));

        }
    }

    public void chekInfo(){

        Boolean b1 = false;
        Boolean b2 = false;
        Boolean b3 = false;
        Boolean b4 = false;
        int counter=0;

        if (box1.isChecked()) { counter=counter+1;}
        if (box2.isChecked()) { counter=counter+1;}
        if (box3.isChecked()) { counter=counter+1;}
        if (box4.isChecked()) { counter=counter+1;}

        if(counter==1){
            if (box1.isChecked()) { niveuEtude="Bac+2";}
            if (box2.isChecked()) {niveuEtude="Bac+3";}
            if (box3.isChecked()) { niveuEtude="Bac+4";}
            if (box4.isChecked()) {niveuEtude="Bac+5";}

            TabsHolder.getInstance().chekMap.put("education_diplome","true");
        }


        if(! domainList.isEmpty() )  TabsHolder.getInstance().chekMap.put("education_domaine","true");

    }

    public static tab_2 getInstance(){
        return tab2_var;
    }




}
