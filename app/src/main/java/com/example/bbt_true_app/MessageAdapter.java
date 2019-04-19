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

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>
{
    private List<Message> mMessageList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View messageView;
        TextView current_message;
        TextView number_unread;
        public ViewHolder(View view)
        {
            super(view);
            messageView = view;
            current_message = (TextView) view.findViewById(R.id.current_message);
            number_unread = (TextView) view.findViewById(R.id.number_unread);

        }
    }

    public MessageAdapter(List<Message> messageList)
    {
        mMessageList = messageList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.messageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = holder.getAdapterPosition();
                Message message = mMessageList.get(position);
                Intent intent_msg = new Intent (v.getContext(),MessageActivity.class);
                v.getContext().startActivity(intent_msg);
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
        Message message = mMessageList.get(position);
        holder.current_message.setText(message.getCurrentmessage());
        holder.number_unread.setText(message.getNumber_unread());
    }

    @Override
    public int getItemCount()
    {
        return mMessageList.size();
    }
}
