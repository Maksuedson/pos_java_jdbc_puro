package model;

public class BeanUserFone {
	
	private String nome;
	private String numero;
	private String tipo;
	private String email;
	
	public BeanUserFone() {		
	}

	public BeanUserFone(String nome, String numero, String tipo, String email) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.tipo = tipo;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "BeanUserFone [nome=" + nome + ", numero=" + numero + ", tipo=" + tipo + ", email="
				+ email + "]";
	}
	
	
	
}
