package com.example.asce.logintesttwo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class Entryadapt extends RecyclerView.Adapter<Entryadapt.ViewHolder> {
    List<jentry> adaptjentries;
    @NonNull
    @Override
    public Entryadapt.ViewHolder onCreateViewHolder  (@NonNull ViewGroup parent, int viewType) {
        View textView = LayoutInflater.from(parent.getContext()).inflate(R.layout.entryadapt ,parent ,false);
        ViewHolder v =new ViewHolder(textView);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull Entryadapt.ViewHolder holder, int position) {
        jentry jj= adaptjentries.get(position);
        String titleer =jj.aaa();
        String conterer =jj.bbb();
        holder.tt.setText(titleer);
        holder.cc.setText(conterer);

    }

    @Override
    public int getItemCount() {
        if (adaptjentries == null) {
            return 0;
        }
        return adaptjentries.size();
    }
    public void setentries(List<jentry> jentries)
    {
        adaptjentries =jentries;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tt,cc;
        public ViewHolder(View itemView) {
            super(itemView);
            tt =itemView.findViewById(R.id.title_tv);
            cc =itemView.findViewById(R.id.content_tv);
        }
    }
}
