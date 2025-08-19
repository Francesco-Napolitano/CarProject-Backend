package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.models.Colore;
import com.betacom.jpa.repositories.ICategoriaRepository;
import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.requests.ColoreReq;
import com.betacom.jpa.services.services.ICategoriaService;

@Service
public class CategoriaImpl implements ICategoriaService{

	private ICategoriaRepository catR;
	
	public CategoriaImpl(ICategoriaRepository catR) {
		this.catR = catR;
	}

	@Override
	public void create(CategoriaReq catReq) throws AcademyException {
		Optional<Categoria> categoria = catR.findByDescrizione(catReq.getDescrizione());
		
		if(categoria.isPresent())
			throw new AcademyException("Categoria già presente");
		
		Categoria m = new Categoria();
		m.setDescrizione(catReq.getDescrizione());
		catR.save(m);
		
	}


	@Override
	public void delete(CategoriaReq catReq) throws AcademyException {

		Optional<Categoria> categoria = catR.findByDescrizione(catReq.getDescrizione());
		
		if(categoria.isEmpty()) {
			throw new AcademyException("Categoria non trovata per l'eliminazione: " + catReq.getDescrizione());
		} else {
			catR.delete(categoria.get());
		}
	}

	@Override
	public List<CategoriaDTO> listAll() throws AcademyException {
		List<Categoria> catL = catR.findAll();
		
		return catL.stream().map(a->
		CategoriaDTO.builder()
		.idCategoria(a.getIdCategoria())
		.descrizione(a.getDescrizione())
		.build()).collect(Collectors.toList());

	}

	@Override
	public void update(CategoriaReq catReq) throws AcademyException {
		// TODO Auto-generated method stub
		
	}

}
