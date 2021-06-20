package edu.pe.idat.ferreteria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.pe.idat.ferreteria.databinding.ItemProductBinding;
import edu.pe.idat.ferreteria.modelo.Producto;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>
//        implements View.OnClickListener
{

     private Context context;
     private ArrayList<Producto> list;

     private View.OnClickListener listener;

    public AdapterProduct(Context context){
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
//        view.setOnClickListener(this);
//        return new AdapterProduct.ViewHolder(view);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemProductBinding recyclerBinding = ItemProductBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(recyclerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.ViewHolder holder, int position) {
        final Producto item = list.get(position);

        holder.recyclerBinding.tvItemNombre.setText(item.getNombre());
        holder.recyclerBinding.tvItemMarca.setText(item.getMarca());
        holder.recyclerBinding.tvItemPrecio.setText(String.valueOf(item.getPrecio()));
        Glide.with(context).load(item.getImagen()).into(holder.recyclerBinding.ivItemImagen);

//        holder.recyclerBinding.cvproduct.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


//    public void setOnClickListener(View.OnClickListener listener){
//        this.listener = listener;
//    }


//    @Override
//    public void onClick(View view) {
//        if(listener!= null){
//            listener.onClick(view);
//        }
//    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductBinding recyclerBinding;
        public ViewHolder(@NonNull ItemProductBinding itemView) {
            super(itemView.getRoot());
           recyclerBinding=itemView;
        }
    }

    public void addProduct(ArrayList<Producto> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }
//
//    private void mensaje(String mensaje){
//        Toast.makeText(context,mensaje,
//                Toast.LENGTH_LONG).show();
//    }
}
