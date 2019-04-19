package com.example.bbt_true_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leptop on 2019/3/10.
 */

public class MessageFragment extends Fragment {
    private View view;
    private RecyclerView recyclerview;
    private List<Message> messageList = new ArrayList<>();
    private MessageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.message_fragment,container,false);
        initRecyclerview();
        initMessages();//初始化数据
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initMessages()
    {
        for(int i=0;i<2;i++)
        {
            Message message_1 = new Message("用户A","你在干嘛呢","周一","1");
            messageList.add(message_1);
            Message message_2 = new Message("用户B","你在干嘛呢","周二","2");
            messageList.add(message_2);
            Message message_3 = new Message("用户C","你在干嘛呢","周三","3");
            messageList.add(message_3);
            Message message_4 = new Message("用户D","你在干嘛呢","周四","4");
            messageList.add(message_4);
            Message message_5 = new Message("用户E","你在干嘛呢","周五","5");
            messageList.add(message_5);
            Message message_6 = new Message("用户F","你在干嘛呢","周六","6");
            messageList.add(message_6);
            Message message_7 = new Message("用户G","你在干嘛呢","周日","7");
            messageList.add(message_7);
        }
    }

    private void initRecyclerview()
    {
        recyclerview = (RecyclerView)view.findViewById(R.id.recycler_view);
        adapter  = new MessageAdapter(messageList);
        recyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
    }

}
