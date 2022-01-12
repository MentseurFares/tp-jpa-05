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
import fr.diginamic.tpjpa05.exceptions.BanqueNotFoundException;
import fr.diginamic.tpjpa05.repositories.CrudBanque;

@RestController
@RequestMapping("/api/banque")
public class BanqueController {

	@Autowired
	private CrudBanque crudBanque;
	
	// Create
	@PostMapping
	public Banque addBanque(@Valid @RequestBody Banque banque, BindingResult result) throws BanqueNotFoundException {
		if(result.hasErrors()) {
            throw new BanqueNotFoundException(result.toString());
        }
		return crudBanque.save(banque);
	}
	
	// Read ALL
	@GetMapping("/all")
	public Iterable<Banque> getBanques() {
		return crudBanque.findAll();
	}
	
	// Read SINGLE
	@GetMapping("/{id}")
	public Optional<Banque> getBanque(@PathVariable Long id) throws BanqueNotFoundException {
		if (crudBanque.findById(id).isEmpty()) {
			throw new BanqueNotFoundException("Le banque avec l'id " + id + " n'existe pas");
		}
		return crudBanque.findById(id);
	}
	
	// Read clients
	@GetMapping("/{id}/clients")
	public Iterable<Client> getClients(@PathVariable Long id) throws BanqueNotFoundException {
		if (crudBanque.findById(id).isEmpty()) {
			throw new BanqueNotFoundException("Le banque avec l'id " + id + " n'existe pas");
		}
		return crudBanque.findClientsByBanqueId(id);
	}
	
	// Update
	@PutMapping("/{id}")
	public Banque updateBanque(@PathVariable Long id,@Valid @RequestBody Banque banque, BindingResult result) throws BanqueNotFoundException {
		if(result.hasErrors()) {
            throw new BanqueNotFoundException(result.toString());
        }
		if (crudBanque.findById(id).isEmpty()) {
			throw new BanqueNotFoundException("Le banque avec l'id " + id + " n'existe pas");
		}
		if (id != banque.getId()) {
			throw new BanqueNotFoundException("La variable d'URL id = " + id + " est différente de l'id du banque JSON (id = " + banque.getId() + ")");
		}
		return crudBanque.save(banque);
	}
	
	// Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBanque(@PathVariable Long id) throws BanqueNotFoundException {
		if (crudBanque.findById(id).isEmpty()) {
			throw new BanqueNotFoundException("Le banque avec l'id " + id + " n'existe pas");
		}
		crudBanque.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Banque supprimé");
	}
	
}