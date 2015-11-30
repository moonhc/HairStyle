package com.example.badasaza.gohaesungsaview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.badasaza.gohaesungsacustomer.R;
import com.example.badasaza.gohaesungsacustomer.SignUpAct;

/**
 * Created by Badasaza on 2015-11-30.
 */
public class SignUpHomeFrag extends Fragment implements View.OnClickListener {

    EditText name;
    EditText tel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_sign_up_home, container, false);
        Button nextButton = (Button) rootView.findViewById(R.id.signup_to_next1);
        nextButton.setOnClickListener(this);
        name = (EditText) rootView.findViewById(R.id.signup_name_edit);
        tel = (EditText) rootView.findViewById(R.id.signup_tel_edit);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        SignUpFragment suf = new SignUpFragment();
        Bundle a = new Bundle();
        a.putInt(SignUpFragment.PAGE_KEY, 0);
        suf.setArguments(a);
        /* ToDo: gotta put animator later */
        SignUpAct sua = (SignUpAct) getActivity();
        String n = name.getText().toString();
        String t = tel.getText().toString();
        if(n == null || n.matches("") || t == null || t.matches("")){
            AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
            ab.setMessage(R.string.signup_alert_content).setTitle(R.string.signup_alert_title);
            ab.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ab.create().show();
        }else {
            sua.initTask(name.getText().toString(), tel.getText().toString());
            fm.beginTransaction().replace(R.id.signup_frag_container, suf).addToBackStack(null).commit();
        }
    }
}
