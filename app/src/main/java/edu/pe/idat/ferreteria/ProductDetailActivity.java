package edu.pe.idat.ferreteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import edu.pe.idat.ferreteria.databinding.ActivityProductdetailBinding;
import edu.pe.idat.ferreteria.modelo.ModelProduct;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityProductdetailBinding binding;
    int amount;
    double priceproduct;

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

        }
    }

    private void mensaje(String mensaje){
        Toast.makeText(ProductDetailActivity.this,mensaje,
                Toast.LENGTH_LONG).show();
    }
}