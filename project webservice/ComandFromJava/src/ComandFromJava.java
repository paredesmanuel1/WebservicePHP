import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by dev_avanza on 16/01/2017.
 */
public class ComandFromJava {
    static String salida = null;
    static ArrayList<String> tablesArrayList = new ArrayList<String>();

    public static void main(String[] args) {

        String servidor = "jdbc:mysql://localhost:3306/panamahitek_text";
        String name ="";
        String password="";
        String bd="";
        printlnPantalla("Ingrese la ruta del servidor: ");
        servidor = inputTeclado();
        printlnPantalla("Ingrese el usuario: ");
        name= inputTeclado();
        printlnPantalla("Ingrese el password: ");
        password=inputTeclado();
        printlnPantalla("Ingrese el nombre de la base de datos: ");
        bd=inputTeclado();


        MySQL db = new MySQL();
        db.MySQLConnect(servidor,name,password);

        try {


            String Query = "use "+bd+" ;";

            db.comando = db.conexion.createStatement();
            db.registro = db.comando.executeQuery(Query);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            String Query = "show tables;";

            db.comando = db.conexion.createStatement();
            db.registro = db.comando.executeQuery(Query);


            while (db.registro.next()) {

                System.out.println("Tabla: " + db.registro.getString(1));
                tablesArrayList.add(db.registro.getString(1));
                System.out.println("------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }











    }

    public static void ejecutarcomando(String comando){

        Process process = null;
        try {
            process = Runtime.getRuntime().exec(comando);
            InputStream inputstream = process.getInputStream();
            BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);

            //Si el comando tiene una salida la mostramos
            salida= String.valueOf(bufferedinputstream.read());
            if(salida != null){
                System.out.println("Comando ejecutado Correctamente");
                while (salida != null){
                    System.out.println(salida);
                }
            }else{
                System.out.println("No se a producido ninguna salida");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String inputTeclado(){
        String entrada="";

        try
        {
            InputStreamReader leer = new InputStreamReader(System.in);
            BufferedReader buff = new BufferedReader(leer);
            entrada = buff.readLine();

        }
        //leer del teclado como String
        catch(java.io.IOException ioex) {}
        return entrada;
    }

    public static void printlnPantalla(String texto){

        System.out.println(texto);

    }



}
