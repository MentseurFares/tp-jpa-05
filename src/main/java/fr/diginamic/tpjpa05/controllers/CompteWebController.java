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
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudClient;
import fr.diginamic.tpjpa05.repositories.CrudCompte;

@Controller
@RequestMapping("/compte")
public class CompteWebController extends CompteWebAbstractController<Compte> {
	
	@Autowired
	private CrudCompte crudCompte;
	
	@Autowired
	private CrudClient crudClient;
	
	@Override
	public String getComptes(Model model) {
		model.addAttribute("comptes", (List<Compte>) crudCompte.findAll());
		model.addAttribute("titre","Liste des comptes");
		return "comptes/liste";
	}
	
	@Override
	public String newCompteForm(Model model) {
		model.addAttribute("compteForm", new Compte());
		model.addAttribute("crudClient", crudClient);
		model.addAttribute("titre","Ajout d'un compte");
		return "comptes/add";
	}
	
	@Override
	public String addCompte(Model model,
			                @Valid @ModelAttribute("compteForm") Compte compte) {
		crudCompte.save(compte);
		return "redirect:/compte/comptes";
		
	}
	
	@Override
	public String updateCompteForm(@PathVariable Long id, Model model) {
		model.addAttribute("compteForm", crudCompte.findById(id));
		model.addAttribute("crudClient", crudClient);
		model.addAttribute("titre","Modification d'un compte");
		return "comptes/update";
	}
	
	@Override
	public String updateCompte(Model model,
			                   @Valid @ModelAttribute("compteForm") Compte compte) {
		crudCompte.save(compte);
		return "redirect:/compte/comptes";
	}
	
	@Override
	public String delete(@PathVariable("id") Long id) throws CompteNotFoundException {
		Optional<Compte> compte = crudCompte.findById(id);
		if(compte.isEmpty()) {
			throw(new CompteNotFoundException("Compte id :"+ id +" non trouvé !"));
		}
		crudCompte.deleteById(id);
		return "redirect:/compte/comptes";
	}
	
}