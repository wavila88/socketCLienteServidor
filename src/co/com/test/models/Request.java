package co.com.test.models;

import java.io.Serializable;

public class Request implements Serializable{

	private Integer numCuenta;
	private Integer valor;
	
	public Request(Integer numCuenta, Integer valor){
		this.numCuenta = numCuenta;
		this.valor = valor;
	}

	public Request(){

	}

	public Integer getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(Integer numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	
	
}
