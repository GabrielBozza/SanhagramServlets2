package bean;

import java.util.Date;

public class Mensagem {

	private Integer idmensagens;
	private String remetente;
	private String destinatario;
	private String texto_mensagem;
	private Date data_envio;

	public Integer getIdmensagem() {
		return idmensagens;
	}

	public void setIdmensagem(Integer idmensagens) {
		this.idmensagens = idmensagens;
	}
	
	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	
	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	public String getTexto_mensagem() {
		return texto_mensagem;
	}

	public void setTexto_mensagem(String texto_mensagem) {
		this.texto_mensagem = texto_mensagem;
	}
	
	public Date getData_envio() {
		return data_envio;
	}

	public void setData_envio(Date data_envio) {
		this.data_envio = data_envio;
	}

}
