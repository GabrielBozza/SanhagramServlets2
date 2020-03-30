package bean;

//CLASSE QUE REPRESENTA UMA TABELA(OBJ DO BD) 
public class Usuario {

	private Integer idusuario;
	private String nome;
	private String email;
	private String senha;
	private String datanasc;

	public Integer getId() {
		return idusuario;
	}

	public void setId(Integer id) {
		this.idusuario = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(String datanasc) {
		this.datanasc = datanasc;
	}

}
