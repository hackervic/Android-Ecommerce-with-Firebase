package com.example.blood.ecom.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blood.ecom.Interface.ItemClickListener;
import com.example.blood.ecom.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtproductname , txtproductdescription , txtproductprice;
    public ImageView imgproduct;
    private ItemClickListener listener;

    public ProductViewHolder(View itemView) {
        super(itemView);

        imgproduct = itemView.findViewById(R.id.image_of_product);
        txtproductdescription = itemView.findViewById(R.id.description_of_product);
        txtproductname = itemView.findViewById(R.id.name_of_product);
        txtproductprice = itemView.findViewById(R.id.price_of_product);


    }

    @Override
    public void onClick(View v) {

        listener.onClick(v , getAdapterPosition(),false);

    }

    public void setItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;
    }
}
