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
import fr.diginamic.tpjpa05.exceptions.OperationNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudCompte;
import fr.diginamic.tpjpa05.repositories.CrudOperation;

@Controller
@RequestMapping("/operation")
public class OperationWebController extends OperationWebAbstractController<Operation> {
	
	@Autowired
	private CrudOperation crudOperation;
	
	@Autowired
	private CrudCompte crudCompte;
	
	@Override
	public String getOperations(Model model) {
		model.addAttribute("operations", (List<Operation>) crudOperation.findAll());
		model.addAttribute("titre","Liste des operations");
		return "operations/liste";
	}
	
	@Override
	public String newOperationForm(Model model) {
		model.addAttribute("operationForm", new Operation());
		model.addAttribute("crudCompte", crudCompte);
		model.addAttribute("titre","Ajout d'une operation");
		return "operations/add";
	}
	
	@Override
	public String addOperation(Model model,
			                @Valid @ModelAttribute("operationForm") Operation operation) {
		crudOperation.save(operation);
		return "redirect:/operation/operations";
		
	}
	
	@Override
	public String updateOperationForm(@PathVariable Long id, Model model) {
		model.addAttribute("operationForm", crudOperation.findById(id));
		model.addAttribute("crudCompte", crudCompte);
		model.addAttribute("titre","Modification d'une operation");
		return "operations/update";
	}
	
	@Override
	public String updateOperation(Model model,
			                   @Valid @ModelAttribute("operationForm") Operation operation) {
		crudOperation.save(operation);
		return "redirect:/operation/operations";
	}
	
	@Override
	public String delete(@PathVariable("id") Long id) throws OperationNotFoundException {
		Optional<Operation> operation = crudOperation.findById(id);
		if(operation.isEmpty()) {
			throw(new OperationNotFoundException("Operation id :"+ id +" non trouvé !"));
		}
		crudOperation.deleteById(id);
		return "redirect:/operation/operations";
	}
	
}