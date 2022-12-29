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
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OrderAdapter.OnOrderListener {
    List<Order> ordersList;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OrderAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.order_options);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference orderRef = database.getReference("orders");

        ordersList = new ArrayList<>();

        recyclerView = findViewById(R.id.am_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        restaurantAdapter = new OrderAdapter(ordersList, this);
        recyclerView.setAdapter(restaurantAdapter);
        restaurantAdapter.notifyDataSetChanged();

        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot sp: snapshot.getChildren()){
                    for (DataSnapshot so: sp.getChildren()){
                        HashMap order = (HashMap)so.getValue();
                        ordersList.add(new Order(order.get("orderID").toString(), order.get("userID").toString(),
                                order.get("orderDate").toString(), order.get("orderStatus").toString(),
                                order.get("orderGate").toString(), order.get("orderTimePeriod").toString()));
                    }
//                    MainActivity.restaurantList.get(i).setDishList(dishList);
//                    Log.d("yehiaaDebug = dish", MainActivity.restaurantList.get(i).toString());
//                    dishList = new ArrayList<>();
//                    Log.d("yehiaaDebug = dish", MainActivity.restaurantList.get(i).toString());
//                    i++;
                }
                Log.d("yehiaaDebug = dish", ordersList.toString());
                restaurantAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onRestClick(int position) {
        Intent i = new Intent(this, OptionsActivity.class);
        i.putExtra("orderID", ordersList.get(position).getOrderID());
        i.putExtra("orderStatus",ordersList.get(position).getOrderStatus());
        i.putExtra("userID",ordersList.get(position).getUserID());
        startActivity(i);

    }
}