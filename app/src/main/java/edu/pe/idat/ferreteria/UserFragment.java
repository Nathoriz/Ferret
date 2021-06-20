package edu.pe.idat.ferreteria;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.pe.idat.ferreteria.adapter.AdapterProduct;
import edu.pe.idat.ferreteria.common.Constantes;
import edu.pe.idat.ferreteria.common.SharedPreferenceManager;
import edu.pe.idat.ferreteria.databinding.FragmentUserBinding;
import edu.pe.idat.ferreteria.modelo.Producto;

import static androidx.navigation.Navigation.findNavController;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;


    public UserFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater,container,false);
        cargarDatos(Constantes.URL_API_USUARIO_DETALLE+SharedPreferenceManager.getSomeIntValue(Constantes.PREF_ID));
     return binding.getRoot();
    }

    private void cargarDatos(String url) {
        RequestQueue colaPeticiones = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("Mensaje").equals("Valido")){
                                JSONObject user = response.getJSONObject("Usuario");
                                String nombre = user.getString("nombre");
                                binding.tvFirstletter.setText(String.valueOf(nombre.charAt(0)));
                                binding.tvPerfilNombre.setText(nombre);
                                binding.tvPerfilApellido.setText(user.getString("apellido"));
                                binding.tvPerfilDireccion.setText(user.getString("direccion"));
                            }else {
                                mensaje(response.getString("Mensaje"));
                            }
                        } catch (JSONException ex) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        colaPeticiones.add(request);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void mensaje(String m){
        Toast.makeText(getContext(),
                m,Toast.LENGTH_LONG).show();
    }
}