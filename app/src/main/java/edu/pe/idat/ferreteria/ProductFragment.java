package edu.pe.idat.ferreteria;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.pe.idat.ferreteria.adapter.AdapterProduct;
import edu.pe.idat.ferreteria.common.Constantes;
import edu.pe.idat.ferreteria.databinding.FragmentProductBinding;
import edu.pe.idat.ferreteria.modelo.Producto;

import static androidx.navigation.Navigation.findNavController;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;
    private AdapterProduct adapter;

    public ProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductBinding.inflate(inflater,container,false);

        adapter = new AdapterProduct(getContext());

        binding.rvlistproducts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvlistproducts.setAdapter(adapter);
        obtenerProductos(Constantes.URL_API_PRODUCTO_LISTAR);
//        adapter.addProduct(listOfProducts());
//
//        adapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("data",listOfProducts().get(binding.rvlistproducts.getChildAdapterPosition(view)));
//                getParentFragmentManager().setFragmentResult("productdata", bundle);
//                findNavController(view).navigate(R.id.navproductdetailfrag);
//            }
//        });
//


        return binding.getRoot();
    }

    private void obtenerProductos(String url) {
        RequestQueue colaPeticiones = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Producto> productos = new ArrayList<>();
                        for (int i = 0; i <response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Producto producto = new Producto(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("nombre"),
                                        jsonObject.getString("marca"),
                                        jsonObject.getDouble("precio"),
                                        jsonObject.getString("urlImg"));
                                productos.add(producto);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.addProduct(productos);
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        colaPeticiones.add(jsonArrayRequest);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}