package edu.pe.idat.ferreteria;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import static androidx.navigation.Navigation.findNavController;


public class ShoppingCartFragment extends Fragment {

    private FragmentShoppingCartBinding binding;

    public ShoppingCartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle == null) {
            // No hay datos, manejar excepción
            return;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentShoppingCartBinding.inflate(inflater,container,false);

        getParentFragmentManager().setFragmentResultListener("cartdata", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                ArrayList<ModelProduct> list = result.getParcelableArrayList("data");
                binding.rvcartproducts.setLayoutManager(new LinearLayoutManager(getContext()));
                AdapterShoppingCart adapter = new AdapterShoppingCart(getContext());
                adapter.addCartProduct(list);
                binding.rvcartproducts.setAdapter(adapter);

                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("data",list.get(binding.rvcartproducts.getChildAdapterPosition(view)));
                        getParentFragmentManager().setFragmentResult("productdata", bundle);
                        findNavController(view).navigate(R.id.navproductdetailfrag);
                    }
                });

            }
        });

        /*
        Bundle bundle = getArguments();
        if (bundle == null) {
            // No hay datos, manejar excepción
            return null;
        }
        ArrayList<ModelProduct> list = bundle.getParcelableArrayList("data");
        binding.rvcartproducts.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterShoppingCart adapter = new AdapterShoppingCart(getContext());
        adapter.addCartProduct(list);
        binding.rvcartproducts.setAdapter(adapter);
*/


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}