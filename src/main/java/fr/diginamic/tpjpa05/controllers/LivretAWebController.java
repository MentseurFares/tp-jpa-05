package fr.diginamic.tpjpa05.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.entities.LivretA;
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudClient;
import fr.diginamic.tpjpa05.repositories.CrudCompte;

@Controller
@RequestMapping("/livret_a")
public class LivretAWebController extends CompteWebAbstractController<LivretA> {
	
	@Autowired
	private CrudCompte crudCompte;
	
	@Autowired
	private CrudClient crudClient;
	
	@Override
	public String getComptes(Model model) {
		model.addAttribute("livretsA", (List<LivretA>) crudCompte.getAllLivretA());
		model.addAttribute("titre","Liste des livrets A");
		return "livrets_a/liste";
	}
	
	@Override
	public String newCompteForm(Model model) {
		model.addAttribute("compteForm", new LivretA());
		model.addAttribute("crudClient", crudClient);
		model.addAttribute("titre","Ajout d'un livret A");
		return "livrets_a/add";
	}
	
	@Override
	public String addCompte(Model model,
			                @Valid @ModelAttribute("compteForm") LivretA livretA) {
		crudCompte.save(livretA);
		return "redirect:/livret_a/comptes";
		
	}
	
	@Override
	public String updateCompteForm(@PathVariable Long id, Model model) {
		model.addAttribute("compteForm", crudCompte.findById(id));
		model.addAttribute("crudClient", crudClient);
		model.addAttribute("titre","Modification d'un livret A");
		return "livrets_a/update";
	}
	
	@Override
	public String updateCompte(Model model,
			                   @Valid @ModelAttribute("compteForm") LivretA livretA) {
		crudCompte.save(livretA);
		return "redirect:/livret_a/comptes";
	}
	
	@Override
	public String delete(@PathVariable("id") Long id) throws CompteNotFoundException {
		Optional<Compte> compte = crudCompte.findById(id);
		if(compte.isEmpty()) {
			throw(new CompteNotFoundException("Compte id :"+ id +" non trouvé !"));
		}
		crudCompte.deleteById(id);
		return "redirect:/livret_a/comptes";
	}
	
}