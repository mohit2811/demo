package com.tech.arora.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Main2Activity {
    EditText edittotal;
    RecyclerView mRecyclerView;
    int totll = 0;
    private ArrayList<String> mItems;
    private int count = 0;
    private Main2Activity obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittotal = findViewById(R.id.edit_totallll);
        mItems = new ArrayList<String>();
        mItems.add(0, "0");
        obj = this;
        initRecyclerView();
    }


    @Override
    public void total(ArrayList<String> total) {
        totll = 0;
        for (int j = 0; j < total.size(); j++) {
            totll += Integer.parseInt(total.get(j));
        }

        edittotal.setText("total" + totll);
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.textView1);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mRecentLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mRecentLayoutManager);


        RecyclerView.Adapter<CustomViewHolder> mAdapter = new RecyclerView.Adapter<CustomViewHolder>() {
            @Override
            public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell, viewGroup, false);
                return new CustomViewHolder(view);
            }

            @Override
            public void onBindViewHolder(final CustomViewHolder viewHolder, final int i) {
                viewHolder.editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (editable.length() >= 1) {
                            mItems.set(i, editable.toString());
                            obj.total(mItems);
                        } else {
                            mItems.set(i, "0");
                            obj.total(mItems);
                        }
                        if (editable.length() == 3) {
                            count+=1;
                            mItems.add(count, "0");
                        }
                    }
                });
            }

            @Override
            public int getItemCount() {
                return mItems.size();
            }

        };
        mRecyclerView.setAdapter((RecyclerView.Adapter) mAdapter);
        mRecyclerView.setHasFixedSize(true);

    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {

        private EditText editText;

        public CustomViewHolder(View itemView) {
            super(itemView);

            editText = itemView.findViewById(R.id.cellEdit);
        }
    }
}