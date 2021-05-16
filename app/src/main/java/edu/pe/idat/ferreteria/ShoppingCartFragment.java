package edu.pe.idat.ferreteria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.pe.idat.ferreteria.databinding.FragmentShoppingCartBinding;


public class ShoppingCartFragment extends Fragment {

    private FragmentShoppingCartBinding binding;

    public ShoppingCartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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