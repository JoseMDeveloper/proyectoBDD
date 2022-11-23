package dataClass;

public class tipoPago {
	
	private String tipPago;
	private Float monto=0F;
	private Long numero=0L;
	private String nombre;
	private Integer ano;
	private Integer mes;

	public tipoPago(Float monto) {
		super();
		this.tipPago="Efectivo";
		this.monto = monto;
	}
	public tipoPago(Float monto,Long numero) {
		super();
		this.tipPago="Bono";
		this.monto=monto;
		this.numero=numero;
	}
	public tipoPago(Float monto,Long numero,String nombre,Integer ano,Integer mes) {
		super();
		this.tipPago="Tarjeta Credito";
		this.monto = monto;
		this.numero=numero;
		this.nombre=nombre;
		this.ano=ano;
		this.mes=mes;
	}
	
	@Override
	public String toString() {
		if(tipPago.equals("Efectivo"))
		{
			return  "Tipo="+tipPago + ", monto=" + monto ;
		}
		else if(tipPago.equals("Bono"))
		{
			return  "Tipo="+tipPago + ", monto=" + monto + ", numero=" + numero ;
			
		}
		else if(tipPago.equals("Tarjeta Credito"))
		{
			return  "Tipo="+tipPago + ", monto=" + monto + ", numero=" + numero +", nombre="+nombre+"ano="+ano+", mes="+mes;
			
		}
				
		return nombre;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public String getTipPago() {
		return tipPago;
	}
	public Long getNumero() {
		return numero;
	}
	public String getNombre() {
		return nombre;
	}
	public Integer getAno() {
		return ano;
	}
	public Integer getMes() {
		return mes;
	}
	
}
