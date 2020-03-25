package com.example.blood.ecom.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.blood.ecom.Interface.ItemClickListener;
import com.example.blood.ecom.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txtpname ,txtpprice ,txtpquantity;
    private ItemClickListener itemClickListener;

    public CartViewHolder(View itemView) {
        super(itemView);
        txtpname = itemView.findViewById(R.id.cart_product_name);
        txtpprice = itemView.findViewById(R.id.cart_product_price);
        txtpquantity = itemView.findViewById(R.id.cart_product_quantity);

    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(itemView ,getAdapterPosition() ,false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
