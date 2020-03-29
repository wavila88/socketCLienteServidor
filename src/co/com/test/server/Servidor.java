package co.com.test.server;

import java.io.*;
import java.net.*;
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

		System.out.println("Server is now running at port: " + portNum);

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

				// Se inserta en BD
				Connection con = Conection.getConnection();
				try {
					Statement st = (Statement) con.createStatement();
					st.executeUpdate("INSERT INTO socketDB.Cuentas (NumeroCuenta,ValorCuenta) " + "VALUES ("
							+ req.getNumCuenta() + "," + req.getValor() + ")");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.print("Error conectando a base de datos");
				}

				// Sending response back to client
				String response = "Num cuenta " + req.getNumCuenta() + " Valor: " + req.getValor()
						+ " Ingresado exitosamente";
				out.writeObject(response);
				System.out.println("OK");
				out.close();
				in.close();
				clientSocket.close();

			} finally {
				// Closing Server Socket now.
				listener.close();

			}

		}
	}
}
