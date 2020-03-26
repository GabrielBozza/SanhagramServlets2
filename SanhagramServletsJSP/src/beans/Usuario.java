package beans;

import java.sql.Date;

//CLASSE QUE REPRESENTA UMA TABELA(OBJ DO BD) 
public class Usuario {

	private Integer idusuario;
	private String nome;
	private String email;
	private String senha;
	private Date datanasc;

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

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}

}
