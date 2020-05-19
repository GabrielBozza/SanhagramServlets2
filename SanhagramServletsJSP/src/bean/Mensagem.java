package bean;

public class Mensagem {

	private Integer idmensagens;
	private Integer flag_grupo;
	private String remetente;
	private String destinatario;
	private String texto_mensagem;
	private String data_envio;

	public Integer getIdmensagem() {
		return idmensagens;
	}

	public void setIdmensagem(Integer idmensagens) {
		this.idmensagens = idmensagens;
	}
	
	public Integer getFlag_grupo() {
		return flag_grupo;
	}

	public void setFlag_grupo(Integer flag_grupo) {
		this.flag_grupo = flag_grupo;
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
	
	public String getData_envio() {
		return data_envio;
	}

	public void setData_envio(String data_envio) {
		this.data_envio = data_envio;
	}

}
