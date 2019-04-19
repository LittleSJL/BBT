package com.example.bbt_true_app;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolder>
{
    private List<Mission> mMissionList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View missionView;
        TextView renwu_gaishu;
        TextView juti_xuqiu;
        TextView number_liulan;
        public ViewHolder(View view)
        {
            super(view);
            missionView = view;
            renwu_gaishu = (TextView) view.findViewById(R.id.renwu_gaishu);
            juti_xuqiu = (TextView) view.findViewById(R.id.juti_xuqiu);
            number_liulan = (TextView) view.findViewById(R.id.number_liulan);
        }
    }

    public MissionAdapter(List<Mission> missionList)
    {
        mMissionList = missionList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mission_item,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.missionView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = holder.getAdapterPosition();
                Mission mission = mMissionList.get(position);
                Intent intent_mission = new Intent(v.getContext(),MissionActivity.class);
                v.getContext().startActivity(intent_mission);
            }
        });

        /*holder.fruitImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(),"You clicked image "+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });*/
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Mission mission = mMissionList.get(position);
        holder.renwu_gaishu.setText(mission.getGaishu());
        holder.juti_xuqiu.setText(mission.getJuti_xuqiu());
        holder.number_liulan.setText(mission.getNumber_liulan());
    }

    @Override
    public int getItemCount()
    {
        return mMissionList.size();
    }
}
