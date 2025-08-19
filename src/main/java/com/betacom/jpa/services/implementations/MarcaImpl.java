package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Bici;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.repositories.IMarcaRepository;
import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.services.services.IMarcaService;

@Service
public class MarcaImpl implements IMarcaService{

	private IMarcaRepository marR;

	public MarcaImpl(IMarcaRepository marR) {
		this.marR = marR;
	}

	@Override
	public void create(MarcaReq marReq) throws AcademyException {
		Optional<Marca> marca = marR.findByDescrizione(marReq.getDescrizione());
		
		if(marca.isPresent())
			throw new AcademyException("Brand già presente");
		
		Marca m = new Marca();
		m.setDescrizione(marReq.getDescrizione());
		marR.save(m);
		
	}


	@Override
	public void delete(MarcaReq marReq) throws AcademyException {

		Optional<Marca> marca = marR.findByDescrizione(marReq.getDescrizione());
		
		if(marca.isEmpty()) {
			throw new AcademyException("Marca non trovata per l'eliminazione: " + marReq.getDescrizione());
		} else {
			marR.delete(marca.get());
		}
	}

	@Override
	public void update(MarcaReq colReq) throws AcademyException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<MarcaDTO> listAll() throws AcademyException {
		List<Marca> marca = marR.findAll();
		
		return marca.stream()
				.map(c -> MarcaDTO.builder()
						.idMarca(c.getIdMarca())
						.descrizione(c.getDescrizione())
						.build())
						.collect(Collectors.toList());
	}
	
	
	
	
}
