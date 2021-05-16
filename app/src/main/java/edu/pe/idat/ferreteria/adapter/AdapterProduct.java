package edu.pe.idat.ferreteria.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.pe.idat.ferreteria.ProductDetailActivity;
import edu.pe.idat.ferreteria.R;
import edu.pe.idat.ferreteria.modelo.ModelProduct;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {


     private Context context;
     private ArrayList<ModelProduct> list;

     // Nuevo adición
     private ArrayList<ModelProduct> shoppingCartList;
     // Fin de adición

    public AdapterProduct(Context context){
        this.context = context;
        list = new ArrayList<>();
        // Nuevo adición
        shoppingCartList = new ArrayList<>();
        // Fin de adición
    }


    @NonNull
    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_product,parent,false);
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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetailProduct = new Intent(context, ProductDetailActivity.class);
                intentDetailProduct.putExtra("product", item);
                context.startActivity(intentDetailProduct);
            }
        });
        */

        // Nuevo adición
        holder.showDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetailProduct = new Intent(context, ProductDetailActivity.class);
                intentDetailProduct.putExtra("product", item);
                context.startActivity(intentDetailProduct);
            }
        });

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.addToCart.getText() == "AGREGAR AL CARRITO"){
                    shoppingCartList.add(item);
                    holder.addToCart.setText("HOLA");
                }else if(holder.addToCart.getText() == "ELIMINAR DEL CARRITO"){
                    shoppingCartList.remove(item);
                    holder.addToCart.setText("AGREGAR AL CARRITO");
                }




                /*
                Intent intentDetailProduct = new Intent(context, ProductDetailActivity.class);
                intentDetailProduct.putExtra("product", item);
                context.startActivity(intentDetailProduct);*/
            }
        });
        // Fin adición
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, brand, price;
        ImageView productImage;
        CardView cardView;
        Button showDetailProduct,addToCart;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtnombreproducto);
            brand =  itemView.findViewById(R.id.txtmarcaproducto);
            price = itemView.findViewById(R.id.txtprecioproducto);
            productImage = itemView.findViewById(R.id.ivimagenproducto);
            cardView= itemView.findViewById(R.id.cv);
            // Nuevo adición
            showDetailProduct = itemView.findViewById(R.id.btnshowdetailproduct);
            addToCart = itemView.findViewById(R.id.btnaddtoshoppingcart);
            // Fin adición
        }
    }

    public void addProduct(ArrayList<ModelProduct> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
}
