package co.com.test.client;


import java.io.*;
import java.net.*;
import java.util.Scanner;

import co.com.test.models.Request;


public class Cliente {


    public static void main(String arg[]) throws IOException, ClassNotFoundException {

        int portNum = 11113;
        Scanner reader = new Scanner(System.in);
        Socket socket = new Socket("localhost", portNum);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Request mensaje = new Request();
        for (int i = 0; i<3; i++) {
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.print("Que desea hacer? \n \n");

            System.out.print("1. Validar Valor en su cuenta\n");
            System.out.print("2. ingresar nueva cuenta con valor\n");
            String rta=reader.next();

            if(rta.equals("1")) {
                System.out.println("Ingrese el numero de su cuenta");
                mensaje = new Request();
                mensaje.setNumCuenta(reader.nextInt());
                mensaje.setPeticion(1);

                try {
                    out.writeObject(mensaje);
                    String response = (String) in.readObject();
                    System.out.println(response);
                }catch (Exception e){
                    System.out.println("Error "+ e.getMessage());

                }

            }else if(rta.equals("2")){

                System.out.println("Ingresa el numero de cuenta");
                mensaje.setNumCuenta(reader.nextInt());
                System.out.println("Ingrese el valor");
                mensaje.setValor(reader.nextInt());
                mensaje.setPeticion(2);


                try {
                    out.writeObject(mensaje);
                    String response = (String) in.readObject();
                    System.out.println(response);
                }catch (Exception e){
                    System.out.println("Error "+ e.getMessage());

                }
            }



        }

        //socket.close();
    }
}
