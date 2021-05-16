package edu.pe.idat.ferreteria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import edu.pe.idat.ferreteria.adapter.AdapterProduct;
import edu.pe.idat.ferreteria.databinding.FragmentProductBinding;
import edu.pe.idat.ferreteria.modelo.ModelProduct;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;

    ArrayList<ModelProduct> shoppingCart;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductBinding.inflate(inflater,container,false);

        binding.rcproductos.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterProduct adapter = new AdapterProduct(getContext());

        binding.rcproductos.setAdapter(adapter);

        adapter.addProduct(listOfProducts());

        return binding.getRoot();
    }

    private ArrayList<ModelProduct> listOfProducts() {
        ArrayList<ModelProduct> product = new ArrayList<>();
        product.add(new ModelProduct(1,"JUEGO LLAVES DADOS 1/4,1/2, 42 PZS TOPTUL (54790852)", "Toptul", 342.95, R.drawable.img1));
        product.add(new ModelProduct(2,"CLAVO ALBAÑIL CON CABEZA 4x7 x 1 KG", "Prodac", 6.30, R.drawable.img2));
        product.add(new ModelProduct(3,"TARUGO PVC VERDE 1/4 x 50 UNIDADES", "SM", 3.60, R.drawable.img3));
        product.add(new ModelProduct(4,"STANLEY STHT57528-8 500GRAMS RUBBER MALLET HAMMER", "STANLEY", 70.00, R.drawable.img4));
        product.add(new ModelProduct(5,"JUEGO LLAVES DADOS 1/4,1/2, 42 PZS TOPTUL (54790852)", "toptul", 342.95, R.drawable.img5));
        product.add(new ModelProduct(6,"ALICATES PMA AI-2", "TEKTON", 40.20, R.drawable.img6));
        product.add(new ModelProduct(7,"ESCALERA ANCHA SOLDADA TORO (3 Peldaños). BTF-TJE507", "TORO", 310.00, R.drawable.img7));
        product.add(new ModelProduct(8,"TALADRO RAIDER RD-AD02", "RAIDER", 360.00, R.drawable.img8));
        product.add(new ModelProduct(9,"CABLE NH-80 6 MM INDECO", "INDECO", 120.95, R.drawable.img9));
        product.add(new ModelProduct(10,"SOCKET OVAL LUZZINI E-27 BLANCO", "LUZZINI", 4.90, R.drawable.img10));

        return product;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}