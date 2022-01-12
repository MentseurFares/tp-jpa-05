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

import fr.diginamic.tpjpa05.entities.Client;
import fr.diginamic.tpjpa05.exceptions.ClientNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudBanque;
import fr.diginamic.tpjpa05.repositories.CrudClient;

@Controller
@RequestMapping("/client")
public class ClientWebController {
	
	@Autowired
	private CrudClient crudClient;
	
	@Autowired
	private CrudBanque crudBanque;
	
	@GetMapping("/clients")
	public String getClients(Model model) {
		model.addAttribute("clients", (List<Client>) crudClient.findAll());
		model.addAttribute("titre","Liste des clients");
		return "clients/liste";
	}
	
	@GetMapping("/add")
	public String newClientForm(Model model) {
		model.addAttribute("clientForm", new Client());
		model.addAttribute("crudBanque", crudBanque);
		model.addAttribute("titre","Ajout d'un client");
		return "clients/add";
	}
	
	@PostMapping("/add")
	public String addClient(Model model,
			                @Valid @ModelAttribute("clientForm") Client client) {
		crudClient.save(client);
		return "redirect:/client/clients";
		
	}
	
	@GetMapping("/update/{id}")
	public String updateClientForm(@PathVariable Long id, Model model) {
		model.addAttribute("clientForm", crudClient.findById(id));
		model.addAttribute("crudBanque", crudBanque);
		model.addAttribute("titre","Modification d'un client");
		return "clients/update";
	}
	
	@PostMapping("/update")
	public String updateClient(Model model,
			                   @Valid @ModelAttribute("clientForm") Client client) {
		crudClient.save(client);
		return "redirect:/client/clients";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) throws ClientNotFoundException {
		Optional<Client> client = crudClient.findById(id);
		if(client.isEmpty()) {
			throw(new ClientNotFoundException("Client id :"+ id +" non trouvé !"));
		}
		crudClient.deleteById(id);
		return "redirect:/client/clients";
	}
	
}
