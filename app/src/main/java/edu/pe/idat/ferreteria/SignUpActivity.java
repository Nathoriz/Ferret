package edu.pe.idat.ferreteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.pe.idat.ferreteria.common.Constantes;
import edu.pe.idat.ferreteria.common.SharedPreferenceManager;
import edu.pe.idat.ferreteria.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearcuenta.setOnClickListener(view -> {
            registrarUsuario(Constantes.URL_API_USUARIO_CREAR);
        });
    }

    private void registrarUsuario(String urlApiUsuarioCrear) {
        RequestQueue colapeticiones = Volley.newRequestQueue(this);
        binding.btnCrearcuenta.setEnabled(false);

        Map<String,String> parametros= new HashMap<>();
        parametros.put("apellido",binding.etSignupApellido.getText().toString());
        parametros.put("contrasenia",binding.etSignupContrasenia.getText().toString());
        parametros.put("direccion",binding.etSignupDireccion.getText().toString());
        parametros.put("email",binding.etSignupEmail.getText().toString());
        parametros.put("nombre",binding.etSignupNombre.getText().toString());


        JSONObject jsonObjectParametro = new JSONObject(parametros);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                urlApiUsuarioCrear,
                jsonObjectParametro,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        colapeticiones.add(request);
    }

    private void mensaje(String m){
        Toast.makeText(getApplicationContext(),
                m,Toast.LENGTH_LONG).show();
    }
}