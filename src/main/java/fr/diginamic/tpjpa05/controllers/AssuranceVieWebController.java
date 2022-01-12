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
import fr.diginamic.tpjpa05.entities.AssuranceVie;
import fr.diginamic.tpjpa05.exceptions.CompteNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudClient;
import fr.diginamic.tpjpa05.repositories.CrudCompte;

@Controller
@RequestMapping("/assurance_vie")
public class AssuranceVieWebController extends CompteWebAbstractController<AssuranceVie> {
	
	@Autowired
	private CrudCompte crudCompte;
	
	@Autowired
	private CrudClient crudClient;
	
	@Override
	public String getComptes(Model model) {
		model.addAttribute("assurancesVie", (List<AssuranceVie>) crudCompte.getAllAssurancesVie());
		model.addAttribute("titre","Liste des assurances vie");
		return "assurances_vie/liste";
	}
	
	@Override
	public String newCompteForm(Model model) {
		model.addAttribute("compteForm", new AssuranceVie());
		model.addAttribute("crudClient", crudClient);
		model.addAttribute("titre","Ajout d'une assurance vie");
		return "assurances_vie/add";
	}
	
	@Override
	public String addCompte(Model model,
			                @Valid @ModelAttribute("compteForm") AssuranceVie assurancesVie) {
		crudCompte.save(assurancesVie);
		return "redirect:/assurance_vie/comptes";
		
	}
	
	@Override
	public String updateCompteForm(@PathVariable Long id, Model model) {
		model.addAttribute("compteForm", crudCompte.findById(id));
		model.addAttribute("crudClient", crudClient);
		model.addAttribute("titre","Modification d'une assurance vie");
		return "assurances_vie/update";
	}
	
	@Override
	public String updateCompte(Model model,
			                   @Valid @ModelAttribute("compteForm") AssuranceVie assurancesVie) {
		crudCompte.save(assurancesVie);
		return "redirect:/assurance_vie/comptes";
	}
	
	@Override
	public String delete(@PathVariable("id") Long id) throws CompteNotFoundException {
		Optional<Compte> compte = crudCompte.findById(id);
		if(compte.isEmpty()) {
			throw(new CompteNotFoundException("Compte id :"+ id +" non trouvé !"));
		}
		crudCompte.deleteById(id);
		return "redirect:/assurance_vie/comptes";
	}
	
}