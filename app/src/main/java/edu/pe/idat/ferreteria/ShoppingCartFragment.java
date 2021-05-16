package edu.pe.idat.ferreteria;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import edu.pe.idat.ferreteria.adapter.AdapterShoppingCart;
import edu.pe.idat.ferreteria.databinding.FragmentShoppingCartBinding;
import edu.pe.idat.ferreteria.modelo.ModelProduct;


public class ShoppingCartFragment extends Fragment {

    private FragmentShoppingCartBinding binding;
    //Bundle arguments = getArguments();


    public ShoppingCartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*
        //arguments.getParcelableArrayList("productList");
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                ArrayList<ModelProduct> list = result.getParcelableArrayList("data");

            }
        });
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        binding = FragmentShoppingCartBinding.inflate(inflater,container,false);

        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                ArrayList<ModelProduct> list = result.getParcelableArrayList("data");
                binding.rvcartproducts.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterShoppingCart adapter = new AdapterShoppingCart(getContext());
                adapter.addCartProduct(list);
                binding.rvcartproducts.setAdapter(adapter);
            }
        });

        binding.rvcartproducts.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterShoppingCart adapter = new AdapterShoppingCart(getContext());
        binding.rvcartproducts.setAdapter(adapter);



        /*adapter.setOnItemClickListener(new AdapterShoppingCart.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void onClick(View view) {

            }
        });
        */

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}