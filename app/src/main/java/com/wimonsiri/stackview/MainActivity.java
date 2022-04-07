package com.wimonsiri.stackview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.StackView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StackView stackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stackView = (StackView) findViewById(R.id.stackView);
        stackView.setInAnimation(this, android.R.animator.fade_in);
        stackView.setOutAnimation(this, android.R.animator.fade_out);
        StackViewAdapter albumsAdapter = new StackViewAdapter(
                MainActivity.this, R.layout.item, getStores());
        stackView.setAdapter(albumsAdapter);
    }

    private List<String> getStores() {
        List<String> stores = new ArrayList<String>();
        stores.add("deosai_land");
        stores.add("dudipatsar_lake");
        stores.add("rama_lake");
        stores.add("shangrila_lower_kachura_lake");
        return stores;
    }
}

class StackViewAdapter extends ArrayAdapter {
    Context context;
    private final List<String> storeList;
    private final int itemLayout;

    public StackViewAdapter(Context ctx, int resource, List<String> stores) {
        super(ctx, resource, stores);
        storeList = stores;
        context = ctx;
        itemLayout = resource;
    }

    @Override
    public int getCount() {
        return storeList.size();
    }

    @Override
    public Object getItem(int position) {
        return storeList.get(position);
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    itemLayout, parent, false);

        }
        String store = storeList.get(position);
        TextView storeName = (TextView) view.findViewById(R.id.name);
        ImageView storeImage = (ImageView) view.findViewById(R.id.image);
        storeName.setText(store);
        int resId = context.getResources().getIdentifier(store, "drawable",

                context.getPackageName());
        storeImage.setImageResource(resId);
        return view;
    }
}