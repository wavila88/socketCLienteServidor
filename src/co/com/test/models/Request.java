package co.com.test.models;

import java.io.Serializable;

public class Request implements Serializable{

	private Integer numCuenta;
	private Integer valor;
	private Integer peticion;

	public Request(Integer numCuenta, Integer valor,Integer peticion){
		this.numCuenta = numCuenta;
		this.valor = valor;
		this.peticion = peticion;
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

	public Integer getPeticion() {
		return peticion;
	}

	public void setPeticion(Integer peticion) {
		this.peticion = peticion;
	}



}
