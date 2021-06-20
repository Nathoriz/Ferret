package edu.pe.idat.ferreteria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.pe.idat.ferreteria.databinding.FragmentProductDetailBinding;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductDetailBinding.inflate(inflater,container,false);
//
//        getParentFragmentManager().setFragmentResultListener("productdata", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
//                ModelProduct product = result.getParcelable("data");
//                binding.txtnameproduct.setText(product.getNombre());
//                binding.txtbrandproduct.setText(product.getMarca());
//                binding.txtpriceproduct.setText(String.valueOf(product.getPrecio()));
//                binding.ivimageproduct.setImageResource(product.getImagen());
//                priceproduct = product.getPrecio();
//            }
//        });
//
//        amount = Integer.parseInt(binding.txtamount.getText().toString());
//        binding.btnincrease.setOnClickListener(this);
//        binding.btndecrease.setOnClickListener(this);
//        binding.btnbuy.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {

//        if(v.getId() == binding.btnincrease.getId()){
//            amount = amount+1;
//            binding.txtamount.setText(String.valueOf(amount));
//            binding.txttotal.setText(String.valueOf(amount * priceproduct).trim());
//        }else if(v.getId() == binding.btndecrease.getId()){
//            if(amount <= 1){
//                mostrarAlerta("Advertencia!!!","Se requiere un producto como mínimo para realizar la compra");
//            }else{
//                amount = amount - 1;
//                binding.txtamount.setText(String.valueOf(amount));
//                binding.txttotal.setText(String.valueOf(amount * priceproduct).trim());
//            }
//        }else if(v.getId() == binding.btnbuy.getId()){
//            mostrarAlerta("Compra Exitosa",
//                    "Gracias por su compra" + "\n" +
//                    "          :)         " + "\n");
//        }
//    }
//
//    public void mostrarAlerta(String titulo, String mensaje){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setMessage(mensaje)
//                .setTitle(titulo);
//
//        AlertDialog alert =builder.create();
//        alert.show();
    }
}