package com.example.bbt_true_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by leptop on 2019/3/10.
 */

public class MoreFragment extends Fragment {
    private RelativeLayout about;
    private RelativeLayout help;
    private RelativeLayout xieyi;
    private RelativeLayout thanks;
    private RelativeLayout exit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState)
    {
        View view = inflater.inflate(R.layout.more_fragment,container,false);
        about = (RelativeLayout)view.findViewById(R.id.about);
        help = (RelativeLayout)view.findViewById(R.id.help);
        xieyi = (RelativeLayout) view.findViewById(R.id.xieyi);
        thanks = (RelativeLayout)view.findViewById(R.id.thanks);
        exit = (RelativeLayout) view.findViewById(R.id.exit);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"You clicked 关于",Toast.LENGTH_SHORT).show();
                Intent intent_about = new Intent(getActivity(),AboutActivity.class);
                startActivity(intent_about);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"You clicked 帮助",Toast.LENGTH_SHORT).show();
                Intent intent_help = new Intent(getActivity(),HelpActivity.class);
                startActivity(intent_help);
            }
        });

        xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"You clicked 用户协议",Toast.LENGTH_SHORT).show();
                Intent intent_agreement = new Intent(getActivity(),AgreementActivity.class);
                startActivity(intent_agreement);
            }
        });

        thanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"You clicked 特别感谢",Toast.LENGTH_SHORT).show();
                Intent intent_thanks = new Intent(getActivity(),ThanksActivity.class);
                startActivity(intent_thanks);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"You clicked 退出程序",Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });


        return view;
    }
}
