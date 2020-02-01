package com.zachrawi.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    int position;
    String name;
    int price;

    EditText etName;
    EditText etPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        name = intent.getStringExtra("name");
        price = intent.getIntExtra("price", 0);

        etName = findViewById(R.id.etItem);
        etPrice = findViewById(R.id.etPrice);

        etName.setText(name);

        if (price > 0) {
            etPrice.setText(String.valueOf(price));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuSave) {
            Intent intent = new Intent();
            intent.putExtra("position", position);
            intent.putExtra("name", etName.getText().toString());
            intent.putExtra("price", Integer.parseInt(etPrice.getText().toString()));

            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
