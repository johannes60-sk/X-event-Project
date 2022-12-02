package com.example.arroundme.modele;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.arroundme.R;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    List<ChildModelClass> childModelClasslist;
    Context context;

    public ChildAdapter(List<ChildModelClass> childModelClasslist, Context context) {
        this.childModelClasslist = childModelClasslist;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.child_rv_layout,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.ViewHolder holder, int position) {

        holder.iv_child_image.setImageResource(childModelClasslist.get(position).image);

//        Glide.with(holder.iv_childe_image).load(childModelClasslist.get(position).image).into(holder.iv_childe_image);

//        holder.tv_parent_title.setText(parentModelClassList.get(position).title);
//
//        ChildAdapter childAdapter;
////        childAdapter = new ChildAdapter(parentModelClassList.get(position).childModelClassList,context);
//        holder.rv_child.setAdapter(childAdapter);
//        childAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return childModelClasslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_child_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_child_image = itemView.findViewById(R.id.iv_child_item);
        }
    }
}
