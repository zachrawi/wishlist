package com.zachrawi.wishlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    WishlistAdapter mWishlistAdapter;
    ArrayList<Wishlist> mWishlists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mWishlists = new ArrayList<>();
        mWishlists.add(new Wishlist("TV Android", 20000000));
        mWishlists.add(new Wishlist("TV Android", 20000000));

        mWishlistAdapter = new WishlistAdapter(this, R.layout.item_wishlist, mWishlists, new WishlistAdapter.OnTouchListener() {
            @Override
            public void onEdit(int position) {
                Wishlist wishlist = mWishlists.get(position);

                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("name", wishlist.getItemName());
                intent.putExtra("price", wishlist.getPrice());

                startActivityForResult(intent, 2);
            }

            @Override
            public void onDelete(int position) {
                mWishlists.remove(position);

                mWishlistAdapter.notifyDataSetChanged();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setAdapter(mWishlistAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            Intent intent = new Intent(this, AddActivity.class);

            startActivityForResult(intent, 1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                int price = data.getIntExtra("price", 0);

                mWishlists.add(new Wishlist(name, price));
                mWishlistAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                int price = data.getIntExtra("price", 0);
                int position = data.getIntExtra("position", 0);

                mWishlists.set(position, new Wishlist(name, price));
                mWishlistAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
