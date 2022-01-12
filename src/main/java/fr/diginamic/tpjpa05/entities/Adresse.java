package fr.diginamic.tpjpa05.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Adresse {
	
	@NotNull
	@Column(name="numero")
	private int numero;
	
	@NotNull
	@NotBlank
	@Column(name="rue")
	private String rue;
	
	@NotNull
	@Column(name="code_postal")
	private int codePostal;
	
	@NotNull
	@NotBlank
	@Column(name="ville")
	private String ville;
	
	public Adresse() {
		
	}
	
	public Adresse(int numero, String rue, int codePostal, String ville) {
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return numero + " " + rue + " " + codePostal + " " + ville;
	}

}
