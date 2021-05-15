package edu.pe.idat.ferreteria.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelProduct implements Parcelable {
    private int id;
    private String nombre;
    private String marca;
    private double precio;
    private int imagen;


    public ModelProduct() {

    }

    public ModelProduct(int id, String nombre, String marca, double precio, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.imagen = imagen;
    }

    protected ModelProduct(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        marca = in.readString();
        precio = in.readDouble();
        imagen = in.readInt();
    }

    public static final Creator<ModelProduct> CREATOR = new Creator<ModelProduct>() {
        @Override
        public ModelProduct createFromParcel(Parcel in) {
            return new ModelProduct(in);
        }

        @Override
        public ModelProduct[] newArray(int size) {
            return new ModelProduct[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeString(marca);
        parcel.writeDouble(precio);
        parcel.writeInt(imagen);
    }
}
