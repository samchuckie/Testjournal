package com.example.asce.logintesttwo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asce.logintesttwo.Database.EntryRom;

import java.util.List;

class Entryadapt extends RecyclerView.Adapter<Entryadapt.ViewHolder> {
    List<EntryRom> adaptjentries;
    ItemClickListener mItemClickListener;

    public Entryadapt(ItemClickListener listener) {
        mItemClickListener=listener;
    }

    @NonNull
    @Override
    public Entryadapt.ViewHolder onCreateViewHolder  (@NonNull ViewGroup parent, int viewType) {
        View textView = LayoutInflater.from(parent.getContext()).inflate(R.layout.entryadapt ,parent ,false);
        ViewHolder v =new ViewHolder(textView);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull Entryadapt.ViewHolder holder, int position) {
        EntryRom jj= adaptjentries.get(position);
        String titleer =jj.getTitle();
        String conterer =jj.getContent()
                ;
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
    public void setentries(List<EntryRom> jentries)
    {
        adaptjentries =jentries;
        notifyDataSetChanged();

    }
    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tt,cc;
        public ViewHolder(View itemView) {
            super(itemView);
            tt =itemView.findViewById(R.id.title_tv);
            cc =itemView.findViewById(R.id.content_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int elementid =adaptjentries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementid);
        }
    }
}
