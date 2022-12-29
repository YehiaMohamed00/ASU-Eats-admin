package com.example.restaurantsadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OrderAdapter.OnOrderListener {
    Calendar calendar;
    List<Order> ordersList;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OrderAdapter orderAdapter;
    Boolean isAcceptableTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference orderRef = database.getReference("orders");

        ordersList = new ArrayList<>();

        recyclerView = findViewById(R.id.am_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        orderAdapter = new OrderAdapter(ordersList, this);
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();

        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot sp: snapshot.getChildren()){
                    for (DataSnapshot so: sp.getChildren()){
                        HashMap order = (HashMap)so.getValue();
                        ordersList.add(new Order(order.get("orderID").toString(), order.get("userID").toString(),
                                order.get("orderDate").toString(), order.get("orderStatus").toString(),
                                order.get("orderGate").toString(), order.get("orderTimePeriod").toString()));
                    }
                }
                Log.d("yehiaaDebug = dish", ordersList.toString());
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOrderClick(int position) {
        calendar = Calendar.getInstance();
        int currHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currMin = calendar.get(Calendar.MINUTE);
        if ( ordersList.get(position).getOrderTimePeriod().equals("12AM")){
            if(currHour <= 10 && currMin <= 30){
                isAcceptableTime = true;
            }
        }else if( ordersList.get(position).getOrderTimePeriod().equals("3PM")){
            if(currHour <= 13 && currMin <= 30){
                isAcceptableTime = true;
            }
        }

        if(isAcceptableTime){
            Intent i = new Intent(this, OptionsActivity.class);
            i.putExtra("orderID", ordersList.get(position).getOrderID());
            i.putExtra("userID",ordersList.get(position).getUserID());
            startActivity(i);
        }
    }
}