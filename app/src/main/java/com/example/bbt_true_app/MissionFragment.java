package com.example.bbt_true_app;

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

public class MissionFragment extends Fragment {
    private View view;
    private RecyclerView recyclerview;
    private List<Mission> missionList = new ArrayList<>();
    private MissionAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.mission_fragment,container,false);
        initRecyclerview();
        initMissions();//初始化数据
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initMissions()
    {
        for(int i=0;i<2;i++)
        {
            Mission mission_1 = new Mission("在上面代码的空白处点右键","或者在Person类名上点右键 —> Source –> ",".","0");
            missionList.add(mission_1);
            Mission mission_2 = new Mission("在上面代码的空白","或者在Person类名上点右键 —> Source –> Generate Getters and Setters",".","1");
            missionList.add(mission_2);
            Mission mission_3 = new Mission("在上面代码的空白处点右键","或者在Person类名上点右键 —> ",".","2");
            missionList.add(mission_3);
            Mission mission_4 = new Mission("在上面代码的","或者在Person类名上点右键 —> Source –> Generate Getters and Setters",".","3");
            missionList.add(mission_4);
            Mission mission_5 = new Mission("在上面代码的空白处点右键测试","或者在Person类名上点右键 ",".","4");//14个字
            missionList.add(mission_5);
            Mission mission_6 = new Mission("在上面代码的空白处点右键","或者在Person类名上点右键 —> Source –> Generate ",".","5");
            missionList.add(mission_6);
            Mission mission_7 = new Mission("在上面代码的空白处点右键","或者在Person类名上点右键 ",".","6");
            missionList.add(mission_7);
            Mission mission_8 = new Mission("在上面代码的空白处点右键","或者在Person类名上点右键 —> Source –> Ge",".","7");
            missionList.add(mission_8);
        }
    }

    private void initRecyclerview()
    {
        recyclerview = (RecyclerView)view.findViewById(R.id.recycler_view);
        adapter  = new MissionAdapter(missionList);
        recyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
    }

}
