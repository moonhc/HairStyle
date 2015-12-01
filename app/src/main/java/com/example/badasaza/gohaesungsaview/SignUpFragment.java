package com.example.badasaza.gohaesungsaview;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.badasaza.gohaesungsacustomer.R;
import com.example.badasaza.gohaesungsacustomer.SignUpAct;

/* ToDo: Optional: if time allows, do snackbar instead of dialog */
/**
 * Created by Badasaza on 2015-11-30.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {
    private int pageNum = -1;

    public static final String PAGE_KEY = "pgkey";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        Bundle args = getArguments();
        if(args==null)
            Log.e("SignUpFragment", "no argument");
        else{
            pageNum = args.getInt(PAGE_KEY, -1);
            if(pageNum == -1)
                Log.e("SignUpFragment", "no page number");
            TextView tv = (TextView) rootView.findViewById(R.id.signup_eval_text);
            tv.setText(getActivity().getText(R.string.signup_eval) + " ("+(pageNum+1)+"/5)");
        }

        Button b = (Button) rootView.findViewById(R.id.signup_to_next2);
        b.setText((pageNum < 4 ? getText(R.string.signup_next) : getText(R.string.signup_end)));
        b.setOnClickListener(this);
        /* ToDo: Communicate with server and get damn pictures showing! */
        ImageView img = (ImageView) rootView.findViewById(R.id.signup_sample_image);
        img.setImageResource(R.drawable.ex);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        /* ToDo: send ratings! */
        if(pageNum < 4) {
            FragmentManager fm = getFragmentManager();
            SignUpFragment suf = new SignUpFragment();
            Bundle a = new Bundle();
            a.putInt(SignUpFragment.PAGE_KEY, pageNum + 1);
            suf.setArguments(a);
            fm.beginTransaction().replace(R.id.signup_frag_container, suf).addToBackStack(null).commit();
        }else{
            SignUpAct sua = (SignUpAct) getActivity();
            sua.finisherDialog().create().show();
        }
    }
}
