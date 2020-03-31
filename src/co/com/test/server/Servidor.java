package co.com.test.server;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import co.com.test.models.Conection;
import co.com.test.models.Request;

public class Servidor {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		// Port number to bind server to.
		int portNum = 11113;

		// Socket for server to listen at.
		ServerSocket listener = new ServerSocket(portNum);

		System.out.println("El servidor esta funcionando en el puerto: " + portNum);

		// Simply making Server run continously.
		while (true) {
			try {
				// Accept a client connection once Server recieves one.
				Socket clientSocket = listener.accept();

				// Creating inout and output streams. Must creat out put stream first.
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

				// Reading in Integer Object from input stream.
				Request req = (Request) in.readObject();
				String response = "";
				try {

					Connection con = Conection.getConnection();
					if (req.getPeticion() == 1) {
						// Se valida valor de una cuenta
						Statement st = (Statement) con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM Cuentas WHERE NumeroCuenta = "+req.getNumCuenta()+"");


						while(rs.next()){
							response = "El valor de tu cuenta es: "+rs.getInt(3);
						}

						out.writeObject(response);

					} else if (req.getPeticion() == 2) {

						// Se inserta cuenta con valor
						Statement st = (Statement) con.createStatement();

						st.executeUpdate("INSERT INTO socketDB.Cuentas (NumeroCuenta,ValorCuenta) " + "VALUES ("
								+ req.getNumCuenta() + "," + req.getValor() + ")");

						// Sending response back to client
						response = "Num cuenta " + req.getNumCuenta() + " Valor: " + req.getValor()
								+ " Ingresado exitosamente";
						out.writeObject(response);

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.print("Error conectando a base de datos Mensaje: "+e.getMessage());
				}


			} finally {
				// Closing Server Socket now.


			}

		}
	}
}
