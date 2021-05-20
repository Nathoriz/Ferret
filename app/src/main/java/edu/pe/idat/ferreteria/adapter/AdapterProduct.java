package edu.pe.idat.ferreteria.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import edu.pe.idat.ferreteria.R;
import edu.pe.idat.ferreteria.databinding.ActivityProductdetailBinding;
import edu.pe.idat.ferreteria.modelo.ModelProduct;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> implements View.OnClickListener {
     private Context context;
     private ArrayList<ModelProduct> list;
     private ArrayList<ModelProduct> shoppingCartList;

     private View.OnClickListener listener;

    public AdapterProduct(Context context){
        this.context = context;
        list = new ArrayList<>();
        shoppingCartList = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_product,parent,false);
        view.setOnClickListener(this);
        return new AdapterProduct.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.ViewHolder holder, int position) {
        final ModelProduct item = list.get(position);

        holder.name.setText(item.getNombre());
        holder.brand.setText(item.getMarca());
        holder.price.setText(String.valueOf(item.getPrecio()));
        holder.productImage.setImageResource(item.getImagen());
/*
        holder.showDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intentDetailProduct = new Intent(context, ActivityProductdetailBinding.class);
                //intentDetailProduct.putExtra("product", item);

                //Bundle bundle = new Bundle();
                //bundle.putParcelable("data",item);


                //context.startActivity(intentDetailProduct);
            }
        });
*/
        holder.addToCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder.addToCart.isChecked() == true){
                    shoppingCartList.add(item);
                    mensaje("añadio");

                }else if(holder.addToCart.isChecked() == false){
                    shoppingCartList.remove(item);
                    mensaje("elimino");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        if(listener!= null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, brand, price;
        ImageView productImage;
        //Button showDetailProduct;
        CheckBox addToCart;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_cart_nombreproducto);
            brand =  itemView.findViewById(R.id.txt_cart_marcaproducto);
            price = itemView.findViewById(R.id.txt_cart_precioproducto);
            productImage = itemView.findViewById(R.id.iv_cart_imagenproducto);
            //showDetailProduct = itemView.findViewById(R.id.btnshowdetailproduct);
            addToCart = itemView.findViewById(R.id.cbaddtocart);
        }
    }

    public void addProduct(ArrayList<ModelProduct> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    public ArrayList<ModelProduct> getCartProduct() {
        return shoppingCartList;
    }

    private void mensaje(String mensaje){
        Toast.makeText(context,mensaje,
                Toast.LENGTH_LONG).show();
    }
}
