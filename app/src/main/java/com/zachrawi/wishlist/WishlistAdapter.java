package com.zachrawi.wishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {
    private Context mContext;
    private int mResource;
    private ArrayList<Wishlist> mWishlists;
    private OnTouchListener mOnTouchListener;

    public WishlistAdapter(Context context, int resource, ArrayList<Wishlist> wishlists, OnTouchListener onTouchListener) {
        mContext = context;
        mResource = resource;
        mWishlists = wishlists;
        mOnTouchListener = onTouchListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(mResource, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Wishlist wishlist = mWishlists.get(position);

        holder.tvName.setText(wishlist.getItemName());
        holder.tvPrice.setText(String.valueOf(wishlist.getPrice()));

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnTouchListener.onEdit(holder.getAdapterPosition());
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnTouchListener.onDelete(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWishlists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvPrice;
        ImageView ivEdit;
        ImageView ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvItem);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    public interface OnTouchListener {
        void onEdit(int position);
        void onDelete(int position);
    }
}
