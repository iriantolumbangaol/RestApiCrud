package restful.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restful.api.model.Sepatu;
import restful.api.repository.SepatuRepository;

@Service
public class SepatuService {
	
	@Autowired
	private SepatuRepository sepatuRepository;
	
	public List<Sepatu> readData(){
		return sepatuRepository.findAll();
	}
	
	public void create(Sepatu sepatu) {
		this.sepatuRepository.save(sepatu);	
	}
	
	public Optional<Sepatu> getUpdate(int id) {
		return sepatuRepository.findById(id);
	}
	
	public Sepatu get(int id) {
		return sepatuRepository.findById(id).get();
	}
	
	public void delete(int id) {
		this.sepatuRepository.deleteById(id);
	}
	
	public Sepatu update(Sepatu sepatu) {
		return sepatuRepository.save(sepatu);
	}

}
