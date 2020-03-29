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
            // Mensaje a enviar
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------");

            System.out.println("Ingresa el numero de cuenta");
            mensaje.setNumCuenta(reader.nextInt());
            System.out.println("Ingrese el valor");
            mensaje.setValor(reader.nextInt());
      


            try {
                out.writeObject(mensaje);
                String response = (String) in.readObject();
                System.out.println(response);
            }catch (Exception e){
                System.out.println("Error "+ e.getMessage());

            }
        }

        //socket.close();
	}
}
