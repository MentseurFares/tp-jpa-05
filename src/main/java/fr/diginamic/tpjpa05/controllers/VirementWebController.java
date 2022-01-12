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

import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.entities.Virement;
import fr.diginamic.tpjpa05.exceptions.OperationNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudCompte;
import fr.diginamic.tpjpa05.repositories.CrudOperation;

@Controller
@RequestMapping("/virement")
public class VirementWebController extends OperationWebAbstractController<Virement> {
	
	@Autowired
	private CrudOperation crudOperation;
	
	@Autowired
	private CrudCompte crudCompte;
	
	@Override
	public String getOperations(Model model) {
		model.addAttribute("virements", (List<Virement>) crudOperation.getAllVirements());
		model.addAttribute("titre","Liste des virements");
		return "virements/liste";
	}
	
	@Override
	public String newOperationForm(Model model) {
		model.addAttribute("operationForm", new Virement());
		model.addAttribute("crudCompte", crudCompte);
		model.addAttribute("titre","Ajout d'un virement");
		return "virements/add";
	}
	
	@Override
	public String addOperation(Model model,
			                @Valid @ModelAttribute("operationForm") Virement virement) {
		crudOperation.save(virement);
		return "redirect:/virement/operations";
		
	}
	
	@Override
	public String updateOperationForm(@PathVariable Long id, Model model) {
		model.addAttribute("operationForm", crudOperation.findById(id));
		model.addAttribute("crudCompte", crudCompte);
		model.addAttribute("titre","Modification d'un virement");
		return "virements/update";
	}
	
	@Override
	public String updateOperation(Model model,
			                   @Valid @ModelAttribute("operationForm") Virement virement) {
		crudOperation.save(virement);
		return "redirect:/virement/operations";
	}
	
	@Override
	public String delete(@PathVariable("id") Long id) throws OperationNotFoundException {
		Optional<Operation> operation = crudOperation.findById(id);
		if(operation.isEmpty()) {
			throw(new OperationNotFoundException("Operation id :"+ id +" non trouvé !"));
		}
		crudOperation.deleteById(id);
		return "redirect:/virement/operations";
	}
	
}