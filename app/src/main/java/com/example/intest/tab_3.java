package com.example.intest;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import java.util.List;

public class tab_3  extends Fragment {

    Button chooseSkills;
    Button chooseLanguge;
    Button next;
    TextView skillsSelected;
    TextView LangugeSelected;

    public List<Integer> mSkillsItems = new ArrayList<>();
    public List<String> skillsList =new ArrayList<>();
    String[] skillsListItems;
    AlertDialog.Builder skillBuilder ;


    public List<Integer> mLanguetems = new ArrayList<>();
    public List<String> langueList=new ArrayList<>();
    String[] langueListItems;
    AlertDialog.Builder langueBuilder ;

    Context context;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.skills, container, false);
        context=this.getActivity();
        skillBuilder = new AlertDialog.Builder(context);
        langueBuilder = new AlertDialog.Builder(context);


        chooseLanguge=(Button)root.findViewById(R.id.langue_id);
        chooseSkills=(Button)root.findViewById(R.id.skiils_id);
        next=(Button)root.findViewById(R.id.nex_skills);

        skillsSelected=(TextView) root.findViewById(R.id.skills_selected);
        LangugeSelected=(TextView) root.findViewById(R.id.langue_selected);

        skillsListItems =getResources().getStringArray(R.array.skills_item);
        langueListItems =getResources().getStringArray(R.array.languge);




        checkIfListEmpty(skillsList,skillsSelected);
        checkIfListEmpty(langueList,LangugeSelected);

        Toast.makeText(context,"skills =  "+skillsList.size(),Toast.LENGTH_SHORT).show();


        chooseSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSkillsItemes();
            }


        });

        chooseLanguge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLangugeItems();
            }


        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(langueList.isEmpty() || skillsList.isEmpty() ){
                    Toast.makeText(context,"please complet your data !",Toast.LENGTH_SHORT).show();
                }else {
                    //here you start your code
                    //uplod to firebase
                }
            }
        });

        return root;
    }










    private void showSkillsItemes(){
        final boolean[] checkedItems;

        checkedItems = new boolean[skillsListItems.length];

        skillsList= new ArrayList<>();



        skillBuilder.setTitle(R.string.dialog_title);

        skillBuilder.setMultiChoiceItems(skillsListItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {

                if(isChecked){
                    mSkillsItems.add(position);
                }else{
                    mSkillsItems.remove((Integer.valueOf(position)));
                }
            }
        });

        skillBuilder.setCancelable(false);

        skillBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String item = "";
                for (int i = 0; i < mSkillsItems.size(); i++) {
                    if(! skillsList.contains(skillsListItems[mSkillsItems.get(i)] )) {
                        item = item + skillsListItems[mSkillsItems.get(i)] + ",\n";
                        skillsList.add(skillsListItems[mSkillsItems.get(i)]);

                    }
                }
                if(item.equals("")){
                    skillsSelected.setText("no items selected !");
                    skillsSelected.setTextColor(getResources().getColor(R.color.colorAccent));
                }else {
                    skillsSelected.setText(item);
                    skillsSelected.setTextColor(getResources().getColor(R.color.colorPrimary));}
            }
        });

        skillBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        skillBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                skillsList.clear();
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;}

                mSkillsItems.clear();
                skillsSelected.setText("no items selected !");
                skillsSelected.setTextColor(getResources().getColor(R.color.colorAccent));


            }
        });

        AlertDialog mDialog = skillBuilder.create();
        mDialog.show();
    }

    public void showLangugeItems(){
        final boolean[] checkedLangItems;

        checkedLangItems = new boolean[langueListItems.length];

        langueList= new ArrayList<>();



        langueBuilder.setTitle("Choose languge");

        langueBuilder.setMultiChoiceItems(langueListItems, checkedLangItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {

                if(isChecked){
                    mLanguetems.add(position);
                }else{
                    mLanguetems.remove((Integer.valueOf(position)));
                }
            }
        });

        langueBuilder.setCancelable(false);

        langueBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String item = "";
                for (int i = 0; i < mLanguetems.size(); i++) {
                    if(! langueList.contains(langueListItems[mLanguetems.get(i)] )) {
                        item = item + langueListItems[mLanguetems.get(i)] + ",\n";
                        langueList.add(langueListItems[mLanguetems.get(i)]);

                    }
                }
                if(item.equals("")){
                    LangugeSelected.setText("no items selected !");
                    LangugeSelected.setTextColor(getResources().getColor(R.color.colorAccent));
                }else {
                    LangugeSelected.setText(item);
                    LangugeSelected.setTextColor(getResources().getColor(R.color.colorPrimary));}
            }
        });

        langueBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        langueBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                langueList.clear();
                for (int i = 0; i < checkedLangItems.length; i++) {
                    checkedLangItems[i] = false;}

                mLanguetems.clear();
                LangugeSelected.setText("no items selected !");
                LangugeSelected.setTextColor(getResources().getColor(R.color.colorAccent));


            }
        });

        AlertDialog mDialog = langueBuilder.create();
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
        }}

}
