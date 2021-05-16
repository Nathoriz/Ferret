package edu.pe.idat.ferreteria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.pe.idat.ferreteria.databinding.FragmentShoppingCartBinding;


public class ShoppingCartFragment extends Fragment {

    private FragmentShoppingCartBinding binding;

    private static final String ARG_PARAM1 = "dataProduct";
    private static final String ARG_PARAM2 = "amount";


    private String dataProduct;
    private String amount;

    public ShoppingCartFragment() {
    }

    public static ShoppingCartFragment newInstance(String dataProduct, String amount) {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, dataProduct);
        args.putString(ARG_PARAM2, amount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataProduct = getArguments().getString(ARG_PARAM1);
            amount = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentShoppingCartBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}