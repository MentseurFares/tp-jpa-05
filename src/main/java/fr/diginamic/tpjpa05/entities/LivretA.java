package fr.diginamic.tpjpa05.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="livret_a")
public class LivretA extends Compte {

	@NotNull
	@Column(name="taux")
	private double taux;
	
	public LivretA() {
		
	}
	
	public LivretA(String numero, double solde, double taux) {
		super(numero, solde);
		this.taux = taux;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
	
}
