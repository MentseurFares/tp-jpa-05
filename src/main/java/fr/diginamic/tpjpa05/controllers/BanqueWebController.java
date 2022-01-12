package fr.diginamic.tpjpa05.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.tpjpa05.entities.Banque;
import fr.diginamic.tpjpa05.exceptions.BanqueNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudBanque;

@Controller
@RequestMapping("/banque")
public class BanqueWebController {
	
	@Autowired
	private CrudBanque crudBanque;
	
	@GetMapping("/banques")
	public String getBanques(Model model) {
		model.addAttribute("banques", (List<Banque>) crudBanque.findAll());
		model.addAttribute("crudBanque", crudBanque);
		model.addAttribute("titre","Liste des banques");
		return "banques/liste";
	}
	
	@GetMapping("/add")
	public String newBanqueForm(Model model) {
		model.addAttribute("banqueForm", new Banque());
		model.addAttribute("titre","Ajout d'une banque");
		return "banques/add";
	}
	
	@PostMapping("/add")
	public String addBanque(Model model,
			                @Valid @ModelAttribute("banqueForm") Banque banque) {
		crudBanque.save(banque);
		return "redirect:/banque/banques";
		
	}
	
	@GetMapping("/update/{id}")
	public String updateBanqueForm(@PathVariable Long id, Model model) {
		model.addAttribute("banqueForm", crudBanque.findById(id));
		model.addAttribute("titre","Modification d'un banque");
		return "banques/update";
	}
	
	@PostMapping("/update")
	public String updateBanque(Model model,
			                   @Valid @ModelAttribute("banqueForm") Banque banque) {
		crudBanque.save(banque);
		return "redirect:/banque/banques";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) throws BanqueNotFoundException {
		Optional<Banque> banque = crudBanque.findById(id);
		if(banque.isEmpty()) {
			throw(new BanqueNotFoundException("Banque id :"+ id +" non trouvée !"));
		}
		crudBanque.deleteById(id);
		return "redirect:/banque/banques";
	}
	
}
