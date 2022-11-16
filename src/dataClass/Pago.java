package dataClass;
public class Pago {
	private Integer Idfactura;
	private String Tipopago;
	private Integer Numero;
	private Integer Monto;
	private Integer NumeroAutorizacion;
	public Pago(Integer idfactura, String tipopago, Integer numero, Integer monto, Integer numeroAutorizacion) {
		Idfactura = idfactura;
		Tipopago = tipopago;
		Numero = numero;
		Monto = monto;
		NumeroAutorizacion = numeroAutorizacion;
	}
	public Integer getIdfactura() {
		return Idfactura;
	}
	public void setIdfactura(Integer idfactura) {
		Idfactura = idfactura;
	}
	public String getTipopago() {
		return Tipopago;
	}
	public void setTipopago(String tipopago) {
		Tipopago = tipopago;
	}
	public Integer getNumero() {
		return Numero;
	}
	public void setNumero(Integer numero) {
		Numero = numero;
	}
	public Integer getMonto() {
		return Monto;
	}
	public void setMonto(Integer monto) {
		Monto = monto;
	}
	public Integer getNumeroAutorizacion() {
		return NumeroAutorizacion;
	}
	public void setNumeroAutorizacion(Integer numeroAutorizacion) {
		NumeroAutorizacion = numeroAutorizacion;
	}
	

}
