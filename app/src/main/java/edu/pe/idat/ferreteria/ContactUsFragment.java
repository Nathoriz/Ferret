package edu.pe.idat.ferreteria;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.regex.Pattern;
import edu.pe.idat.ferreteria.databinding.FragmentContactUsBinding;
import edu.pe.idat.ferreteria.modelo.ModelContactUs;

public class ContactUsFragment extends Fragment {

    private FragmentContactUsBinding binding;
    private ModelContactUs enviodata;

    public ContactUsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContactUsBinding.inflate(inflater,container,false);
        binding.btnenviar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (Validacion()){
                    mostrarAlerta("Registro",
                            Envio().getSexo()+" "+Envio().getNombre()+"\n"+
                                    "Su mensaje "+Envio().getAsunto()+" con el siguiente texto: \n "+
                                    Envio().getDescripcion()+" se envio correctamente.\n"+
                                    "En breve nos comunicaremos con usted mediante" +
                                    Envio().getCorreo()+" o el siguente numero " +Envio().getTelefono()+"\n"+
                                    "Gracias por contactarnos.");
                    limpiar();
                }
            }
        });
        return binding.getRoot();
    }

    public void mostrarAlerta(String titulo, String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(mensaje)
                .setTitle(titulo);

        AlertDialog alert =builder.create();
        alert.show();
    }

    public ModelContactUs Envio() {
        enviodata=new ModelContactUs();
            String asunto,nombre,correo,sexo="",descripcion;
            int telefono,empresa;

                asunto = binding.etasunto.getText().toString();
                nombre = binding.etnombre.getText().toString();
                correo = binding.etcorreo.getText().toString();
                if (binding.rdbsr.isChecked()==true) {
                    sexo=binding.rdbsr.getText().toString();

                }if(binding.rdbsra.isChecked()==true){
                    sexo=binding.rdbsra.getText().toString();
                }
                descripcion = binding.edtmensaje.getText().toString();
                telefono=Integer.parseInt(binding.ettelefono.getText().toString());
                if(binding.cbempresa.isChecked()){
                    empresa=1;
                }else {
                    empresa = 0;
                }

                enviodata.setAsunto(asunto);
                enviodata.setNombre(nombre);
                enviodata.setCorreo(correo);
                enviodata.setSexo(sexo);
                enviodata.setDescripcion(descripcion);
                enviodata.setTelefono(telefono);
                enviodata.setEmpresa(empresa);

        return enviodata;
    }

    public void limpiar(){
        binding.etasunto.setText("");
        binding.etnombre.setText("");
        binding.etcorreo.setText("");
        binding.edtmensaje.setText("");
        binding.ettelefono.setText("");
        binding.rdbsr.setSelected(false);
        binding.rdbsra.setSelected(false);
        binding.cbempresa.setSelected(false);
    }

    public boolean Validacion(){
        String msj = "";
        boolean respuesta = true;
        if (binding.etasunto.getText().toString().length() == 0) {
            msj = "Ingrese el asunto";
            respuesta = false;
            mostrarAlerta("Asunto", msj);

        } else if (binding.etnombre.getText().toString().length() == 0) {
            msj = "Ingrese su nombre";
            respuesta = false;
            mostrarAlerta("Nombre", msj);
        } else if (binding.ettelefono.getText().toString().length() == 0) {
            msj = "Ingrese su telefono";
            respuesta = false;
            mostrarAlerta("Telefono", msj);
        } else if (binding.etcorreo.getText().toString().length() == 0) {
            msj = "Ingrese su correo";
            respuesta = false;
            mostrarAlerta("Correo", msj);
        } else if (binding.etcorreo.getText().toString().length() > 0) {
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            if (pattern.matcher(binding.etcorreo.getText().toString()).matches()) {
                respuesta = true;
            } else {
                msj = "Su correo " + binding.etcorreo.getText().toString() + " no es valido";
                respuesta = false;
                mostrarAlerta("correo", msj);
            }
        } else if (!binding.rdbsr.isChecked() && !binding.rdbsra.isChecked()) {
            msj = "Elige una opcion Sr o Sra";
            respuesta = false;
            mostrarAlerta("Elige", msj);
        } else if (binding.edtmensaje.getText().toString().length() == 0) {
            msj = "Ingrese su mensaje";
            respuesta = false;
            mostrarAlerta("mensaje", msj);
        } else {
           respuesta = true;
        }
        return respuesta;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}
