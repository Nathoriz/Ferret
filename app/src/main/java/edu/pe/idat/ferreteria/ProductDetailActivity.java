package edu.pe.idat.ferreteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import edu.pe.idat.ferreteria.databinding.ActivityProductdetailBinding;
import edu.pe.idat.ferreteria.modelo.ModelProduct;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityProductdetailBinding binding;
    int amount;
    double priceproduct;

    ArrayList<ModelProduct> dataList;
    ModelProduct data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductdetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent().hasExtra("product")) {
            ModelProduct product = getIntent().getParcelableExtra("product");
            binding.txtnameproduct.setText(product.getNombre());
            binding.txtbrandproduct.setText(product.getMarca());
            binding.txtpriceproduct.setText(String.valueOf(product.getPrecio()));
            binding.ivimageproduct.setImageResource(product.getImagen());
            priceproduct = product.getPrecio();
            // añadiendo registro de producto dentro del objeto data
            data = getIntent().getParcelableExtra("product");
        }

        amount = Integer.parseInt(binding.txtamount.getText().toString());

        binding.btnbacktoproductfrag.setOnClickListener(this);
        binding.btnincrease.setOnClickListener(this);
        binding.btndecrease.setOnClickListener(this);
        binding.btnbuy.setOnClickListener(this);
        binding.btnaddtocart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnbacktoproductfrag.getId()) {
            startActivity(new Intent(ProductDetailActivity.this, MainActivity.class));
        }else if(v.getId() == binding.btnincrease.getId()){
            amount = amount+1;
            binding.txtamount.setText(String.valueOf(amount));
        }else if(v.getId() == binding.btndecrease.getId()){
            if(amount <= 1){
                mensaje("Se requiere un minimo de un peoducto para realizar la compra");
            }else{
                amount = amount - 1;
                binding.txtamount.setText(String.valueOf(amount));
            }
        }else if(v.getId() == binding.btnbuy.getId()){
            mensaje(String.valueOf(amount * priceproduct));
        }else if(v.getId() == binding.btnaddtocart.getId()){
            //FALTA PROGRAMAR

            /*
            Bundle dataProduct = new Bundle();
            dataProduct.putSerializable("dataProduct",shoppingCartList);
            dataProduct.putString("amount", String.valueOf(amount));
            ShoppingCartFragment fragment = new ShoppingCartFragment();
            fragment.setArguments(dataProduct);
            getSupportFragmentManager().beginTransaction().add(R.id.navshoppingcartfrag,fragment);
            */
            if(binding.btnaddtocart.getText() == "AÑADIR A LA CESTA"){
                binding.btnaddtocart.setText("ELIMINAR DEL CARRITO");

                dataList.add(data);
                Bundle dataProduct = new Bundle();
                dataProduct.putInt("amount", amount);
                dataProduct.putSerializable("productList",dataList);
                ShoppingCartFragment fragment = new ShoppingCartFragment();
                fragment.setArguments(dataProduct);
                getSupportFragmentManager().beginTransaction().add(R.id.navshoppingcartfrag,fragment);
            }else if (binding.btnaddtocart.getText() == "QUITAR DEL CARRITO"){
                binding.btnaddtocart.setText("AÑADIR AL CARRITO");
                dataList.remove(data);


            }


            Bundle dataProduct = new Bundle();
            dataProduct.putInt("amount", amount);
            dataProduct.putParcelable("product",data);
            ShoppingCartFragment fragment = new ShoppingCartFragment();
            fragment.setArguments(dataProduct);
            getSupportFragmentManager().beginTransaction().add(R.id.navshoppingcartfrag,fragment);
        }
    }

    private void mensaje(String mensaje){
        Toast.makeText(ProductDetailActivity.this,mensaje,
                Toast.LENGTH_LONG).show();
    }
}