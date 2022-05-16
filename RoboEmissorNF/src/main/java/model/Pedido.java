package model;

public class Pedido {
	
	private String numPedido;
	private String nomeCliente;
	private String codCliente;
	private String obs;
	private String ie;
	private String qtde;
	
	public String getQtde() {
		return qtde;
	}
	public void setQtde(String qtde) {
		this.qtde = qtde;
	}
	public String getIe() {
		return ie;
	}
	public void setIe(String ie) {
		this.ie = ie;
	}
	/**
	 * @return the numPedido
	 */
	public String getNumPedido() {
		return numPedido;
	}
	/**
	 * @param numPedido the numPedido to set
	 */
	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}
	/**
	 * @return the nomeCliente
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}
	/**
	 * @param nomeCliente the nomeCliente to set
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/**
	 * @return the codCliente
	 */
	public String getCodCliente() {
		return codCliente;
	}
	/**
	 * @param codCliente the codCliente to set
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	/**
	 * @return the obs
	 */
	public String getObs() {
		return obs;
	}
	/**
	 * @param obs the obs to set
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Pedido(String numPedido, String nomeCliente, String codCliente, String obs, String ie) {
		super();
		this.numPedido = numPedido;
		this.nomeCliente = nomeCliente;
		this.codCliente = codCliente;
		this.obs = obs;
		this.ie = ie;
	}
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

}
