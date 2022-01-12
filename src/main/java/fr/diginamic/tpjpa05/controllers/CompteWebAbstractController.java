package fr.diginamic.tpjpa05.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;

@Controller
public abstract class CompteWebAbstractController<T extends Compte> {
	
	@GetMapping("/comptes")
	public abstract String getComptes(Model model);
	
	@GetMapping("/add")
	public abstract String newCompteForm(Model model);
	
	@PostMapping("/add")
	public abstract String addCompte(Model model, @Valid @ModelAttribute("compteForm") T compte);
	
	@GetMapping("/update/{id}")
	public abstract String updateCompteForm(@PathVariable Long id, Model model);
	
	@PostMapping("/update")
	public abstract String updateCompte(Model model, @Valid @ModelAttribute("compteForm") T compte);
	
	@GetMapping("/delete/{id}")
	public abstract String delete(@PathVariable("id") Long id) throws CompteNotFoundException;
	
}