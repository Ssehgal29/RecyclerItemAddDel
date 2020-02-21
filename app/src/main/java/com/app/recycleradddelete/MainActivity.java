package com.app.recycleradddelete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvProduct;
    private FloatingActionButton btnAddProd;
    private ArrayList<Pojo> arrayList;
    private ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setId();
        setListener();
    }
    public void setId(){
        rvProduct=findViewById(R.id.rvAddDelete);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));
        btnAddProd=findViewById(R.id.addProd);
        arrayList=new ArrayList<>();
        listAdapter=new ListAdapter(MainActivity.this,arrayList);
        rvProduct.setAdapter(listAdapter);

    }
    public void setListener(){
        btnAddProd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addProd:
                final Dialog addProdDialog = new Dialog(this);
                addProdDialog.setContentView(R.layout.add_product_dialog);
                addProdDialog.setCanceledOnTouchOutside(false);
                final EditText edtProdName = addProdDialog.findViewById(R.id.prodName),
                edtProdPrice = addProdDialog.findViewById(R.id.prodPrice);
                Button btnAddProd = addProdDialog.findViewById(R.id.addProd);
                btnAddProd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strProdName = edtProdName.getText().toString().trim();
                        String strProdPrice = edtProdPrice.getText().toString().trim();
                        if (strProdName.equals("")){
                            edtProdName.setError("Please Enter Product Name");
                            edtProdName.requestFocus();
                        }else if (strProdPrice.equals("")){
                            edtProdPrice.setError("Please Enter Product Price");
                            edtProdPrice.requestFocus();
                        }else {
                            arrayList.add(new Pojo(strProdName,strProdPrice));
                            listAdapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "Product Added", Toast.LENGTH_SHORT).show();
                            addProdDialog.dismiss();
                        }
                    }
                });
                addProdDialog.show();
                break;
        }
    }
}
