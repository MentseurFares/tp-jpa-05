package fr.diginamic.tpjpa05.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.tpjpa05.entities.Banque;
import fr.diginamic.tpjpa05.entities.Client;
import fr.diginamic.tpjpa05.entities.Compte;
import fr.diginamic.tpjpa05.exceptions.ClientNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudClient;

@RestController
@RequestMapping("/api/client")
public class ClientController {

	@Autowired
	private CrudClient crudClient;
	
	// Create
    @PostMapping
    public Client addClient(@Valid @RequestBody Client client, BindingResult result) throws ClientNotFoundException {
        if(result.hasErrors()) {
            throw new ClientNotFoundException(result.toString());
        }
        return crudClient.save(client);
    }
	
	// Read ALL
	@GetMapping("/all")
	public Iterable<Client> getClients() {
		return crudClient.findAll();
	}
	
	// Read SINGLE
	@GetMapping("/{id}")
	public Optional<Client> getClient(@PathVariable Long id) throws ClientNotFoundException {
		if (crudClient.findById(id).isEmpty()) {
			throw new ClientNotFoundException("Le client avec l'id " + id + " n'existe pas");
		}
		return crudClient.findById(id);
	}
	
	// Read banque
	@GetMapping("/{id}/banque")
	public Banque getBanque(@PathVariable Long id) throws ClientNotFoundException {
		if (crudClient.findById(id).isEmpty()) {
			throw new ClientNotFoundException("Le client avec l'id " + id + " n'existe pas");
		}
		return crudClient.findById(id).get().getBanque();
	}
	
	// Read comptes
	@GetMapping("/{id}/comptes")
	public Iterable<Compte> getComptes(@PathVariable Long id) throws ClientNotFoundException {
		if (crudClient.findById(id).isEmpty()) {
			throw new ClientNotFoundException("Le client avec l'id " + id + " n'existe pas");
		}
		return crudClient.findComptesByClient(crudClient.findById(id).get());
	}
	
	// Update
	@PutMapping("/{id}")
	public Client updateClient(@PathVariable Long id, @Valid @RequestBody Client client, BindingResult result) throws ClientNotFoundException {
		if(result.hasErrors()) {
            throw new ClientNotFoundException(result.toString());
        }
		if (crudClient.findById(id).isEmpty()) {
			throw new ClientNotFoundException("Le client avec l'id " + id + " n'existe pas");
		}
		if (id != client.getId()) {
			throw new ClientNotFoundException("La variable d'URL id = " + id + " est différente de l'id du client JSON (id = " + client.getId() + ")");
		}
		return crudClient.save(client);
	}
	
	// Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable Long id) throws ClientNotFoundException {
		if (crudClient.findById(id).isEmpty()) {
			throw new ClientNotFoundException("Le client avec l'id " + id + " n'existe pas");
		}
		crudClient.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Client supprimé");
	}
	
}