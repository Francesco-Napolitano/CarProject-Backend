package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.ColoreDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Colore;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.repositories.IColoreRepository;
import com.betacom.jpa.requests.ColoreReq;
import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.services.services.IColoreService;

@Service
public class ColoreImpl implements IColoreService{
	
	private IColoreRepository colR;
	
	public ColoreImpl(IColoreRepository colR) {
		this.colR = colR;
	}

	@Override
	public void create(ColoreReq colReq) throws AcademyException {
		Optional<Colore> colore = colR.findByDescrizione(colReq.getDescrizione());
		
		if(colore.isPresent())
			throw new AcademyException("Colore già presente");
		
		Colore m = new Colore();
		m.setDescrizione(colReq.getDescrizione());
		colR.save(m);
		
	}

	@Override
	public void delete(ColoreReq colReq) throws AcademyException {

		Optional<Colore> colore = colR.findByDescrizione(colReq.getDescrizione());
		
		if(colore.isEmpty()) {
			throw new AcademyException("Colore non trovato per l'eliminazione: " + colReq.getDescrizione());
		} else {
			colR.delete(colore.get());
		}
		
		
	}

	@Override
	public List<ColoreDTO> listAll() throws AcademyException {
		List<Colore> col = colR.findAll();
		
		return col.stream()
				.map(c -> ColoreDTO.builder()
						.idColore(c.getIdColore())
						.descrizione(c.getDescrizione())
						.build())
						.collect(Collectors.toList());
	}

	@Override
	public void update(ColoreReq colReq) throws AcademyException {
		// TODO Auto-generated method stub
		
	}

}
