package com.example.restaurantsadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class OptionsActivity extends AppCompatActivity {

    Button oo_package_btn, oo_onWay_btn, oo_delivered_btn;
    String orderStatus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_options);
        String userID = getIntent().getStringExtra("userID");
        String orderID = getIntent().getStringExtra("orderID");
        ((TextView)findViewById(R.id.oo_actualID_tv)).setText(orderID);
        FirebaseDatabase.getInstance().getReference("orders").child(userID).child(orderID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderStatus = ((HashMap)snapshot.getValue()).get("orderStatus").toString();
                Log.d("yehiaDebug = status",orderStatus);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        oo_package_btn = findViewById(R.id.oo_package_btn);
        oo_onWay_btn = findViewById(R.id.oo_onWay_btn);
        oo_delivered_btn = findViewById(R.id.oo_delivered_btn);


        oo_package_btn.setOnClickListener(view -> {
            if(orderStatus.equals("packaged") || orderStatus.equals("on-way") || orderStatus.equals("delivered")){
                Toast.makeText(OptionsActivity.this, "Order is " + orderStatus, Toast.LENGTH_SHORT).show();
            } else{
                // Create a map to store the new value of the attribute
                Map<String, Object> updates = new HashMap<>();
                updates.put("orderStatus", "packaged");

                // Update the value of the attribute
                FirebaseDatabase.getInstance().getReference("orders").child(userID).child(orderID).updateChildren(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Update successful
                                Toast.makeText(OptionsActivity.this, "updated in Firebase", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Update failed
                                Toast.makeText(OptionsActivity.this, "failed to update", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        oo_onWay_btn.setOnClickListener(view -> {
            if(orderStatus.equals("placed") || orderStatus.equals("on-way") || orderStatus.equals("delivered")){
                Toast.makeText(OptionsActivity.this, "Order is " + orderStatus, Toast.LENGTH_SHORT).show();
            } else{

                // Create a map to store the new value of the attribute
                Map<String, Object> updates = new HashMap<>();
                updates.put("orderStatus", "on-way");

                // Update the value of the attribute
                FirebaseDatabase.getInstance().getReference("orders").child(userID).child(orderID).updateChildren(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Update successful
                                Toast.makeText(OptionsActivity.this, "updated in Firebase", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Update failed
                                Toast.makeText(OptionsActivity.this, "failed to update", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        oo_delivered_btn.setOnClickListener(view -> {
            if(orderStatus.equals("placed") || orderStatus.equals("packaged") || orderStatus.equals("delivered")){
                Toast.makeText(OptionsActivity.this, "Order is " + orderStatus, Toast.LENGTH_SHORT).show();
            } else{
                // Create a map to store the new value of the attribute
                Map<String, Object> updates = new HashMap<>();
                updates.put("orderStatus", "delivered");

                // Update the value of the attribute
                FirebaseDatabase.getInstance().getReference("orders").child(userID).child(orderID).updateChildren(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Update successful
                                Toast.makeText(OptionsActivity.this, "updated in Firebase", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Update failed
                                Toast.makeText(OptionsActivity.this, "failed to update", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}