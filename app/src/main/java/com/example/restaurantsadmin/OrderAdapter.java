package com.example.restaurantsadmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    List<Order> ordersList;
    OnOrderListener mOnOrderListener;

    public OrderAdapter(List<Order> ordersList, OnOrderListener onOrderListener) {
        this.ordersList = ordersList;
        this.mOnOrderListener = onOrderListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_recycler_item, parent, false);
        return new OrderViewHolder(view, mOnOrderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        String orderID, orderDate, orderGate, orderTimePeriod, orderStatus;
        orderID = ordersList.get(position).getOrderID();
        orderDate = ordersList.get(position).getOrderDate();
        orderGate = ordersList.get(position).getOrderGate();
        orderTimePeriod = ordersList.get(position).getOrderTimePeriod();
        orderStatus = ordersList.get(position).getOrderStatus();

        holder.VHorderID.setText(orderID);
        holder.VHorderDate.setText(orderDate);
        holder.VHorderGate.setText(orderGate);
        holder.VHorderTimePeriod.setText(orderTimePeriod);
        holder.VHorderStatus.setText(orderStatus);
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView VHorderID, VHorderDate, VHorderGate, VHorderTimePeriod, VHorderStatus;
        OnOrderListener onOrderListener;

        public OrderViewHolder(@NonNull View itemView, OnOrderListener onRestaurantListener) {
            super(itemView);
            VHorderID = itemView.findViewById(R.id.ori_orderID_tv);
            VHorderDate = itemView.findViewById(R.id.ori_date_tv);
            VHorderGate =itemView.findViewById(R.id.ori_gate_tv);
            VHorderTimePeriod = itemView.findViewById(R.id.ori_period_tv);
            VHorderStatus = itemView.findViewById(R.id.ori_status_tv);

            this.onOrderListener = onRestaurantListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onOrderListener.onOrderClick(getAdapterPosition());
        }
    }
    public interface OnOrderListener {
        void onOrderClick(int position);
    }
}
