package edu.pe.idat.ferreteria.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelContactUs implements Parcelable {

    private String asunto;
    private String nombre;
    private int telefono;
    private String correo;
    private String sexo;
    private String descripcion;
    private int empresa;

    public ModelContactUs() {

    }

    public ModelContactUs(String asunto, String nombre, int telefono, String correo, String sexo, String descripcion, int empresa) {

        this.asunto = asunto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.sexo = sexo;
        this.descripcion = descripcion;
        this.empresa = empresa;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    protected ModelContactUs(Parcel in) {
        asunto = in.readString();
        nombre = in.readString();
        telefono = in.readInt();
        correo = in.readString();
        sexo = in.readString();
        descripcion = in.readString();
        empresa = in.readInt();
    }

    public static final Creator<ModelContactUs> CREATOR = new Creator<ModelContactUs>() {
        @Override
        public ModelContactUs createFromParcel(Parcel in) {
            return new ModelContactUs(in);
        }

        @Override
        public ModelContactUs[] newArray(int size) {
            return new ModelContactUs[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(asunto);
        parcel.writeString(nombre);
        parcel.writeInt(telefono);
        parcel.writeString(correo);
        parcel.writeString(sexo);
        parcel.writeString(descripcion);
        parcel.writeInt(empresa);
    }
}

