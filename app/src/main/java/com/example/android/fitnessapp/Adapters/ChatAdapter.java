package com.example.android.fitnessapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.fitnessapp.Models.MessageModel;
import com.example.android.fitnessapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModel> messageModel;
    Context context;
    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessageModel> messageModel, Context context) {
        this.messageModel = messageModel;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender, parent, false);
            return  new SenderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_receiver, parent, false);
            return  new ReceiverViewHolder(view);
        }
        //return null;
    }

    @Override
    public int getItemViewType(int position) {

        if(messageModel.get(position).getUserId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else{
            return RECEIVER_VIEW_TYPE;
        }
        //return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageModel mModel = messageModel.get(position);

        if (holder.getClass() == SenderViewHolder.class) {
            ((SenderViewHolder)holder).senderMsg.setText(mModel.getMessage());
        } else {
            ((ReceiverViewHolder)holder).receiveMsg.setText(mModel.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageModel.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{

        TextView receiveMsg, rTime;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiveMsg = itemView.findViewById(R.id.receiverTxt);
            rTime = itemView.findViewById(R.id.receiverTime);

        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder{

        TextView senderMsg, sTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderTxt);
            sTime = itemView.findViewById(R.id.senderTime);

        }
    }
}
