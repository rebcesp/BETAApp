package com.example.rebcesp.fastdrinkfinalv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rebcesp.Interface.ItemClickListener;
import com.example.rebcesp.ViewHolder.BebidasViewHolder;
import com.example.rebcesp.fastdrinkfinal.Model.Bebidas;
import com.example.rebcesp.fastdrinkfinal.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class BebidasLista extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference bebidaslista;

    String categoryId = "";
        FirebaseRecyclerAdapter<Bebidas,BebidasViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas_lista);

        //Firebase

        database = FirebaseDatabase.getInstance();
        bebidaslista = database.getReference("Bebidas");


        recyclerView = findViewById(R.id.recycler_bebidas);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent here


        if (getIntent() != null){
            categoryId = getIntent().getStringExtra("CategoryId");

            if (!categoryId.isEmpty() && categoryId != null){

                loadListBebidas(categoryId);
            }
        }
    }

    private void loadListBebidas(String categoryId) {

        adapter = new FirebaseRecyclerAdapter<Bebidas, BebidasViewHolder>(Bebidas.class,
                R.layout.bebidas_item,BebidasViewHolder.class, bebidaslista.orderByChild("MenuId").equalTo(categoryId)) { //Esto seria como: Select * from Bebidas where MenuId=


            @Override
            protected void populateViewHolder(BebidasViewHolder viewHolder, Bebidas model, int position) {
                viewHolder.bebidas_nombre.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.bebidas_image); //AQUI CONSEGUIREMOS EL TEXTO Y LAS IMAGENES CARGADAS DESDE EL JSON


                final Bebidas local = model;

                    viewHolder.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void OnClick(View view, int position, boolean isLongClick) {
                            Toast.makeText(BebidasLista.this, ""+local.getName(), Toast.LENGTH_SHORT).show();
                        }
                    });

            }
        };

        //Set adapter

        Log.d("TAG",""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);


    }
}
