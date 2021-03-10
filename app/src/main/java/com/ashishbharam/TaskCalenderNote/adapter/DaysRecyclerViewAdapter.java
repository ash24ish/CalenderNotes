package com.ashishbharam.TaskCalenderNote.adapter;
/* 
Created by Ashish Bharam on 22-Feb-21 at 03:30 PM.
Copyright (c) 2021 Ashish Bharam. All rights reserved.
*/

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashishbharam.TaskCalenderNote.R;

import java.util.List;

public class DaysRecyclerViewAdapter extends RecyclerView.Adapter<DaysRecyclerViewAdapter.DaysViewHolder> {

    private OnDayClickListener onDayClickListener;
    private List<String> daysList;

    public DaysRecyclerViewAdapter(OnDayClickListener onDayClickListener, List<String> daysList) {
        this.onDayClickListener = onDayClickListener;
        this.daysList = daysList;
    }

    public interface OnDayClickListener {
        void onDayClick(int position);
    }

    @NonNull
    @Override
    public DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new DaysViewHolder(view, onDayClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysViewHolder holder, int position) {
        position++;
        holder.days.setText(""+position);

    }

    @Override
    public int getItemCount() {
        return daysList.size();
    }

    public static class DaysViewHolder extends RecyclerView.ViewHolder{
        TextView days;
        OnDayClickListener dayClickListener;
        public DaysViewHolder(@NonNull View itemView, OnDayClickListener onDayClickListener) {
            super(itemView);

            days = itemView.findViewById(R.id.rv_day);
            dayClickListener = onDayClickListener;

            itemView.setOnClickListener(v -> {
                if (dayClickListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        dayClickListener.onDayClick(position);
                    }
                }
            });
        }
    }
}
