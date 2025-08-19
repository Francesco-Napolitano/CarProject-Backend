package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.AlimentazioneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Alimentazione;
import com.betacom.jpa.repositories.IAlimentazioneRepository;
import com.betacom.jpa.requests.AlimentazioneReq;
import com.betacom.jpa.services.services.IAlimentazioneService;

@Service
public class AlimentazioneImpl implements IAlimentazioneService{
	
	private IAlimentazioneRepository aliR;
	
	public AlimentazioneImpl(IAlimentazioneRepository aliR) {
		this.aliR = aliR;
	}

	@Override
	public void create(AlimentazioneReq aliReq) throws AcademyException {
		Optional<Alimentazione> alimentazione = aliR.findByDescrizione(aliReq.getDescrizione());
		
		if(alimentazione.isPresent())
			throw new AcademyException("Categoria già presente");
		
		Alimentazione m = new Alimentazione();
		m.setDescrizione(aliReq.getDescrizione());
		aliR.save(m);
		
	}

	@Override
	public void delete(AlimentazioneReq aliReq) throws AcademyException {
		Optional<Alimentazione> alimentazione = aliR.findByDescrizione(aliReq.getDescrizione());
		
		if(alimentazione.isEmpty()) {
			throw new AcademyException("Categoria non trovata per l'eliminazione: " + aliReq.getDescrizione());
		} else {
			aliR.delete(alimentazione.get());
		}
	}

	@Override
	public List<AlimentazioneDTO> listAll() throws AcademyException {
		List<Alimentazione> aliL = aliR.findAll();
		
		return aliL.stream().map(a->
		AlimentazioneDTO.builder()
		.idAlimentazione(a.getIdAlimentazione())
		.descrizione(a.getDescrizione())
		.build()).collect(Collectors.toList());
	}

	@Override
	public void update(AlimentazioneReq aliReq) throws AcademyException {
		// TODO Auto-generated method stub
		
	}

}
