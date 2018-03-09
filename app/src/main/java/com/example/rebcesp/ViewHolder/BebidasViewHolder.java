package com.example.rebcesp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rebcesp.Interface.ItemClickListener;
import com.example.rebcesp.fastdrinkfinal.R;

/**
 * Created by Rebcesp on 09/03/2018.
 */

public class BebidasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    public TextView bebidas_nombre;
    public ImageView bebidas_image;

    private ItemClickListener itemClickListener;

    public BebidasViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
    }

    public BebidasViewHolder(View itemView) {
        super(itemView);

        bebidas_nombre=itemView.findViewById(R.id.bebidas_nombre);
        bebidas_image=itemView.findViewById(R.id.bebidas_image);

        itemView.setOnClickListener(this);

    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
        itemClickListener.OnClick(view,getAdapterPosition(),false);


    }
}
