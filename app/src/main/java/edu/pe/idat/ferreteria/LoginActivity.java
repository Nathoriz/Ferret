package edu.pe.idat.ferreteria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
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
import java.util.regex.Pattern;

import edu.pe.idat.ferreteria.common.Constantes;
import edu.pe.idat.ferreteria.common.SharedPreferenceManager;
import edu.pe.idat.ferreteria.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(view -> {
            if(validacion()){
                autentificacion();
                limpiar();

            }

        });

        binding.btnRcrearcuenta.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
        });
    }

    private void autentificacion() {
        RequestQueue colapeticiones = Volley.newRequestQueue(this);
        binding.btnLogin.setEnabled(false);

        Map<String,String> parametros= new HashMap<>();
        parametros.put("email",binding.etLoginEmail.getText().toString());
        parametros.put("contrasenia",binding.etLoginContrasenia.getText().toString());

        JSONObject jsonObjectParametro = new JSONObject(parametros);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Constantes.URL_API_USUARIO_LOGIN,
                jsonObjectParametro,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("Mensaje").equals("Valido")){
                                JSONObject user = response.getJSONObject("Usuario");
                                SharedPreferenceManager.setSomeIntValue(Constantes.PREF_ID,user.getInt("id"));
                                SharedPreferenceManager.setSomeStringValue(Constantes.PREF_NOMBRE,user.getString("nombre"));
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }else {
                                mensaje("Ups",response.getString("Mensaje"));
                                binding.btnLogin.setEnabled(true);
                            }
                        } catch (JSONException ex) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        colapeticiones.add(request);
    }

    public boolean validacion(){
        String msj = "";
        boolean respuesta = true;
        if(binding.etLoginEmail.getText().toString().length() == 0 &&binding.etLoginContrasenia.getText().toString().length() == 0){
            msj = "Ingrese su correo y su cantraseña";
            respuesta = false;
            mensaje("Campos vacios", msj);
        }
        else if (binding.etLoginEmail.getText().toString().length() == 0) {
            msj = "Ingrese su correo";
            respuesta = false;
            mensaje("Campo vacio", msj);
        } else if (binding.etLoginEmail.getText().toString().length() > 0) {
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            if (pattern.matcher(binding.etLoginEmail.getText().toString()).matches()) {
                respuesta = true;
            } else {
                msj = "Su correo " + binding.etLoginEmail.getText().toString() + " no es valido";
                respuesta = false;
                mensaje("Campo invalido", msj);
            }
        } else if (binding.etLoginContrasenia.getText().toString().length() == 0) {
            msj = "Ingrese su nombre";
            respuesta = false;
            mensaje("Campo vacio", msj);
        } else {
            respuesta = true;
        }
        return respuesta;
    }

    public void limpiar(){
        binding.etLoginContrasenia.setText("");
        binding.etLoginEmail.setText("");
    }

    public void mensaje(String titulo, String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje)
                .setTitle(titulo);

        AlertDialog alert =builder.create();
        alert.show();
    }


}