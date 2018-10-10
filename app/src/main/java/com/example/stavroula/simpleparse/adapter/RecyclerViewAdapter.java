package com.example.stavroula.simpleparse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stavroula.simpleparse.R;
import com.example.stavroula.simpleparse.entity.User;

import java.util.List;

import static java.lang.String.valueOf;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<User> item;
    private Context context;

    public RecyclerViewAdapter(Context context, List<User> item){
        this.item = item;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_row, parent, false);

        return new ViewHolder(view);

    }

    @Override
     public void onBindViewHolder(ViewHolder holder, int position){
        holder.id.setText(valueOf(item.get(position).getId()));
        holder.name.setText(item.get(position).getName());
        holder.lastName.setText(item.get(position).getLastName());
        holder.email.setText(item.get(position).getEmail());


    }

    public void setUserList(List<User> users){
        item = users;
        Log.d("123",valueOf(item.size()));
    }

   @Override
    public int getItemCount() {

       int a ;

       if(item != null && !item.isEmpty()) {

           a = item.size();
       }
       else {

           a = 0;

       }

       return a;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, id, lastName, email;

        public ViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
            lastName = (TextView) itemView.findViewById(R.id.lastName);
            email = (TextView) itemView.findViewById(R.id.email);
        }
    }


}
