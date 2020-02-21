package com.app.recycleradddelete;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Pojo> arrayList;

    public ListAdapter(Context context, ArrayList<Pojo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list,parent,false);
        return new ViewHolder(listView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.txtProdName.setText(arrayList.get(position).getProdName());
        holder.txtProdPrice.setText(arrayList.get(position).getProdPrice());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                    .setTitle("Delete Product?")
                    .setMessage("Are you sure you want to delete this Product?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            removeAt(holder.getAdapterPosition());
                        }
                    }).create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtProdName,txtProdPrice;
        private ImageView  ivDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProdName=itemView.findViewById(R.id.prodName);
            txtProdPrice=itemView.findViewById(R.id.prodPrice);
            ivDelete=itemView.findViewById(R.id.deleteCard);
        }
    }

    private void removeAt(int position){
        arrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,arrayList.size());
        Toast.makeText(context, "Product Deleted", Toast.LENGTH_SHORT).show();
    }
}
