package edu.pe.idat.ferreteria.common;

public class Constantes {
    private static String ipv="192.168.1.3";
    public static final String URL_API_METODO_PAGO_LISTAR="http://"+ipv+":8070/metodopago/listar";
    public static final String URL_API_METODO_PAGO_OBTENER="http://"+ipv+":8070/metodopago/obtener/";

    public static final String URL_API_USUARIO_CREAR="http://"+ipv+":8070/usuario/crearCuenta";
    public static final String URL_API_USUARIO_LOGIN="http://"+ipv+":8070/usuario/login";
    public static final String URL_API_USUARIO_DETALLE="http://"+ipv+":8070/usuario/";
    public static final String URL_API_USUARIO_MODIFICAR="http://"+ipv+":8070/usuario/modificar/";
    public static final String URL_API_USUARIO_PASSWORD="http://"+ipv+":8070/usuario/password/";
    public static final String URL_API_USUARIO_ELIMINAR="http://"+ipv+":8070/usuario/eliminar/";

    public static final String URL_API_PRODUCTO_LISTAR="http://"+ipv+":8070/producto/listar";
    public static final String URL_API_PRODUCTO_BUSCAR="http://"+ipv+":8070/producto/buscar";
    public static final String URL_API_PRODUCTO_DETALLE="http://"+ipv+":8070/producto/detalle/";

    public static final String URL_API_PEDIDO_LISTAR="http://"+ipv+":8070/producto/listar";
    public static final String URL_API_PEDIDO_USUARIO="http://"+ipv+":8070/producto/usuario/";
    public static final String URL_API_PEDIDO_OBTENER="http://"+ipv+":8070/producto/";
    public static final String URL_API_PEDIDO_CREAR="http://"+ipv+":8070/producto/crear";

    public static final String PREF_ID="PREF_ID";
    public static final String PREF_NOMBRE="PREF_NOMBRE";
}
