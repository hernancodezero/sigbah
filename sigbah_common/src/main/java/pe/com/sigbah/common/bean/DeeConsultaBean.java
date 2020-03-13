package pe.com.sigbah.common.bean;



/**
 * @className: PedidoCompraBean.java
 * @description: 
 * @date: 30 jul. 2017
 * @author: whr.
 */
public class DeeConsultaBean extends BaseOutputBean {

	private static final long serialVersionUID = 1L;
	
	private Integer num;
	private Integer idDee;
	
	private String numDee;
	private String nomDee;
	
	/**
	 * 
	 */
	public DeeConsultaBean() {
		super();
	}
	
	

	/**
	 * @param idDee
	 */
	public DeeConsultaBean(Integer idDee) {
		super();
		this.idDee = idDee;
	}



	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}



	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}



	/**
	 * @return the idDee
	 */
	public Integer getIdDee() {
		return idDee;
	}



	/**
	 * @param idDee the idDee to set
	 */
	public void setIdDee(Integer idDee) {
		this.idDee = idDee;
	}



	/**
	 * @return the numDee
	 */
	public String getNumDee() {
		return numDee;
	}



	/**
	 * @param numDee the numDee to set
	 */
	public void setNumDee(String numDee) {
		this.numDee = numDee;
	}



	/**
	 * @return the nomDee
	 */
	public String getNomDee() {
		return nomDee;
	}



	/**
	 * @param nomDee the nomDee to set
	 */
	public void setNomDee(String nomDee) {
		this.nomDee = nomDee;
	}


	
}
