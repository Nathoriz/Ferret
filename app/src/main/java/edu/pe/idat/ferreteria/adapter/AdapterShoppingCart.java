package edu.pe.idat.ferreteria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import edu.pe.idat.ferreteria.R;
import edu.pe.idat.ferreteria.modelo.ModelProduct;

public class AdapterShoppingCart extends RecyclerView.Adapter<AdapterShoppingCart.ViewHolder> {
    private Context context;
    private ArrayList<ModelProduct> list;

    public AdapterShoppingCart(Context context){
        this.context = context;
        list = new ArrayList<>();
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_cartproduct,parent,false);
        return new AdapterShoppingCart.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        final ModelProduct item = list.get(position);
        holder.name.setText(item.getNombre());
        holder.brand.setText(item.getMarca());
        holder.price.setText(String.valueOf(item.getPrecio()));
        holder.productImage.setImageResource(item.getImagen());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(item);
                mensaje("elimino");
            }
        });

        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensaje("WIIIII");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, brand, price;
        ImageView productImage;
        Button show, delete;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_cart_nombreproducto);
            brand =  itemView.findViewById(R.id.txt_cart_marcaproducto);
            price = itemView.findViewById(R.id.txt_cart_precioproducto);
            productImage = itemView.findViewById(R.id.iv_cart_imagenproducto);
            show = itemView.findViewById(R.id.btndelete);
            delete =   itemView.findViewById(R.id.btndelete);
        }
    }

    public void addCartProduct(ArrayList<ModelProduct> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    private void mensaje(String mensaje){
        Toast.makeText(context,mensaje,
                Toast.LENGTH_LONG).show();
    }
}
