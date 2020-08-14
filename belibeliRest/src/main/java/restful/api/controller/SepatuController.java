package restful.api.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restful.api.model.Sepatu;
import restful.api.service.SepatuService;

@RestController
@RequestMapping("/sepatu")
public class SepatuController {
	
	@Autowired
	private SepatuService sepatuService;
	
	@GetMapping("/")
	public List<Sepatu> readData(){
		return sepatuService.readData();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Sepatu> get(@PathVariable int id) {
		try {
			Sepatu sepatu = sepatuService.get(id);
			return new ResponseEntity<Sepatu>(sepatu, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Sepatu>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/create")
	public void add(@RequestBody Sepatu sepatu) {
		sepatuService.create(sepatu);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Sepatu> update(@PathVariable int id, @RequestBody Sepatu sepatu){
		Optional<Sepatu> cariSepatu = sepatuService.getUpdate(id);
		if (cariSepatu.isPresent()) {
			Sepatu _sepatu = cariSepatu.get();
			_sepatu.setDescription(sepatu.getDescription());
			_sepatu.setMerk(sepatu.getMerk());
			_sepatu.setPrice(sepatu.getPrice());
			_sepatu.setQuantity(sepatu.getQuantity());
			_sepatu.setDate(sepatu.getDate());
			
			return new ResponseEntity<Sepatu>(sepatuService.update(_sepatu),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Sepatu> delete(@PathVariable int id) {
		try {
			sepatuService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Sepatu>(HttpStatus.NOT_FOUND);
		}
	}

}
