package com.guilcapi.crowdfoundingusuario;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
    //Elementos de product_item_layout
    public TextView txtProductName;
    public ImageView imageView;
    public ItemClickListener listner;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
            imageView = itemView.findViewById(R.id.home_product_image);
            txtProductName = itemView.findViewById(R.id.home_product_name);
    }


    public void setItemClickListener(ItemClickListener listener){
        this.listner = listner;
    }

    @Override
    public void onClick(View v) {
        listner.onClick(v, getAdapterPosition(), false);

    }
}
