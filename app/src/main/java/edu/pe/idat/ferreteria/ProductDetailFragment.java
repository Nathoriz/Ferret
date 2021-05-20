package edu.pe.idat.ferreteria;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import edu.pe.idat.ferreteria.adapter.AdapterShoppingCart;
import edu.pe.idat.ferreteria.databinding.FragmentProductDetailBinding;
import edu.pe.idat.ferreteria.modelo.ModelProduct;

import static androidx.navigation.Navigation.findNavController;

public class ProductDetailFragment extends Fragment implements View.OnClickListener {

    private FragmentProductDetailBinding binding;
    int amount;
    double priceproduct;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductDetailBinding.inflate(inflater,container,false);

        getParentFragmentManager().setFragmentResultListener("productdata", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                ModelProduct product = result.getParcelable("data");
                binding.txtnameproduct.setText(product.getNombre());
                binding.txtbrandproduct.setText(product.getMarca());
                binding.txtpriceproduct.setText(String.valueOf(product.getPrecio()));
                binding.ivimageproduct.setImageResource(product.getImagen());
                priceproduct = product.getPrecio();

            }
        });

        amount = Integer.parseInt(binding.txtamount.getText().toString());


        binding.btnincrease.setOnClickListener(this);
        binding.btndecrease.setOnClickListener(this);
        binding.btnbuy.setOnClickListener(this);



        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == binding.btnincrease.getId()){
            amount = amount+1;
            binding.txtamount.setText(String.valueOf(amount));
            binding.txttotal.setText(String.valueOf(amount * priceproduct).trim());
        }else if(v.getId() == binding.btndecrease.getId()){
            if(amount <= 1){
                mensaje("Se requiere un minimo de un peoducto para realizar la compra");
            }else{
                amount = amount - 1;
                binding.txtamount.setText(String.valueOf(amount));
                binding.txttotal.setText(String.valueOf(amount * priceproduct).trim());
            }
        }else if(v.getId() == binding.btnbuy.getId()){
            mensaje("Compra realizada :P");
        }
    }

    private void mensaje(String mensaje){
        Toast.makeText(getActivity(),mensaje,
                Toast.LENGTH_LONG).show();
    }
}