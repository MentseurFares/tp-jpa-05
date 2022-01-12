package fr.diginamic.tpjpa05.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.diginamic.tpjpa05.entities.Operation;
import fr.diginamic.tpjpa05.exceptions.OperationNotFoundException;

@Controller
public abstract class OperationWebAbstractController<T extends Operation> {
	
	@GetMapping("/operations")
	public abstract String getOperations(Model model);
	
	@GetMapping("/add")
	public abstract String newOperationForm(Model model);
	
	@PostMapping("/add")
	public abstract String addOperation(Model model, @Valid @ModelAttribute("operationForm") T operation);
	
	@GetMapping("/update/{id}")
	public abstract String updateOperationForm(@PathVariable Long id, Model model);
	
	@PostMapping("/update")
	public abstract String updateOperation(Model model, @Valid @ModelAttribute("operationForm") T operation);
	
	@GetMapping("/delete/{id}")
	public abstract String delete(@PathVariable("id") Long id) throws OperationNotFoundException;
	
}