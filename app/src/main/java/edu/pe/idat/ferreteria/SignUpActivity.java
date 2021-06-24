package edu.pe.idat.ferreteria;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
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
import edu.pe.idat.ferreteria.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    public SignUpActivity() {
    }


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearcuenta.setOnClickListener(view -> {
            if(validacion()){
                if(registrarUsuario(Constantes.URL_API_USUARIO_CREAR)){
                    mensaje("Registro","Usuario creado exitoso");
                    limpiar();
                }else {
                    limpiar();
                }
            }
        });
    }

    private boolean registrarUsuario(String urlApiUsuarioCrear) {
        RequestQueue colapeticiones = Volley.newRequestQueue(this);
        boolean rsp=false;
        binding.btnCrearcuenta.setEnabled(false);

        Map<String,String> parametros= new HashMap<>();
        parametros.put("apellido",binding.etSignupApellido.getText().toString());
        parametros.put("contrasenia",binding.etSignupContrasenia.getText().toString());
        parametros.put("direccion",binding.etSignupDireccion.getText().toString());
        parametros.put("email",binding.etSignupEmail.getText().toString());
        parametros.put("nombre",binding.etSignupNombre.getText().toString());

          rsp=true;
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
        binding.btnCrearcuenta.setEnabled(true);
        return rsp;
    }

    public boolean validacion(){
        String msj = "";
        boolean respuesta = true;
        if(binding.etSignupNombre.getText().toString().length() == 0 &&
                binding.etSignupApellido.getText().toString().length() == 0 &&
                binding.etSignupDireccion.getText().toString().length() == 0 &&
                binding.etSignupEmail.getText().toString().length() == 0 &&
                binding.etSignupContrasenia.getText().toString().length() == 0){
            msj = "Ingrese los campos solicitados";
            respuesta = false;
            mensaje("Campos vacios", msj);

        }else if(binding.etSignupNombre.getText().toString().length() == 0){
            msj = "Ingrese su nombre";
            respuesta = false;
            mensaje("Campos vacios", msj);
        }
        else if(binding.etSignupApellido.getText().toString().length() == 0){
            msj = "Ingrese su apellido";
            respuesta = false;
            mensaje("Campos vacios", msj);
        }else if(binding.etSignupDireccion.getText().toString().length() == 0){
            msj = "Ingrese su dirección";
            respuesta = false;
            mensaje("Campos vacios", msj);
        }
        else if (binding.etSignupEmail.getText().toString().length() == 0) {
            msj = "Ingrese su correo";
            respuesta = false;
            mensaje("Campo vacio", msj);
        } else if (binding.etSignupEmail.getText().toString().length() > 0) {
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            if (pattern.matcher(binding.etSignupEmail.getText().toString()).matches()) {
                respuesta = true;
            } else {
                msj = "Su correo " + binding.etSignupEmail.getText().toString() + " no es valido";
                respuesta = false;
                mensaje("Campo invalido", msj);
            }
        } else if (binding.etSignupContrasenia.getText().toString().length() == 0) {
            msj = "Ingrese su nombre";
            respuesta = false;
            mensaje("Campo vacio", msj);
        } else {
            respuesta = true;
        }
        return respuesta;
    }

    public void limpiar(){
        binding.etSignupNombre.setText("");
        binding.etSignupApellido.setText("");
        binding.etSignupDireccion.setText("");
        binding.etSignupContrasenia.setText("");
        binding.etSignupEmail.setText("");
    }

    private void mensaje(String titulo,String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje)
                .setTitle(titulo);

        AlertDialog alert =builder.create();
        alert.show();
    }
}

