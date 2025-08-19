package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.MacchinaDTO;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Macchina;
import com.betacom.jpa.models.Veicolo;
import com.betacom.jpa.repositories.IAlimentazioneRepository;
import com.betacom.jpa.repositories.IBiciRepository;
import com.betacom.jpa.repositories.ICategoriaRepository;
import com.betacom.jpa.repositories.IColoreRepository;
import com.betacom.jpa.repositories.IMacchinaRepository;
import com.betacom.jpa.repositories.IMarcaRepository;
import com.betacom.jpa.repositories.IMotoRepository;
import com.betacom.jpa.repositories.ISospensioneRepository;
import com.betacom.jpa.repositories.ITipoVeicoloRepository;
import com.betacom.jpa.repositories.IVeicoloRepository;
import com.betacom.jpa.requests.MacchinaReq;
import com.betacom.jpa.requests.VeicoloReq;
import com.betacom.jpa.services.services.IMacchinaService;

import jakarta.persistence.CascadeType;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MacchinaImpl implements IMacchinaService {

	private IVeicoloRepository veiR;
	private IMotoRepository motoR;
	private IMacchinaRepository carR;
	private IBiciRepository biciR;
	private IAlimentazioneRepository aliR;
	private ICategoriaRepository catR;
	private IColoreRepository colR;
	private IMarcaRepository marcaR;
	private ITipoVeicoloRepository tipoR;
	private ISospensioneRepository sospR;
	

	public MacchinaImpl(IVeicoloRepository veiR, IMotoRepository motoR, IMacchinaRepository carR, IBiciRepository biciR,
			IAlimentazioneRepository aliR, ICategoriaRepository catR, IColoreRepository colR, IMarcaRepository marcaR,
			ITipoVeicoloRepository tipoR, ISospensioneRepository sospR) {
		this.veiR = veiR;
		this.motoR = motoR;
		this.carR = carR;
		this.biciR = biciR;
		this.aliR = aliR;
		this.catR = catR;
		this.colR = colR;
		this.marcaR = marcaR;
		this.tipoR = tipoR;
		this.sospR = sospR;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(MacchinaReq carReq,VeicoloReq veiReq) throws AcademyException {
		
		log.debug("dati carReq: "+carReq);
		Optional<Macchina> m = carR.findByTarga(carReq.getTarga());
		if(m.isPresent()) throw new AcademyException("macchina con targa: "+carReq.getTarga()+" è gia esistente ");
		
		Macchina c = new Macchina();
		c.setCilindrata(carReq.getCilindrata());
		c.setNumeroPorte(carReq.getNumeroPorte());
		c.setTarga(carReq.getTarga());
		carR.save(c).getIdMacchina();
	
		VeicoloImpl veiImpl= new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		veiImpl.create(veiReq);
		
		List<Veicolo> lv = veiR.findAll();
        Veicolo v = lv.getLast();
        c.setVeicolo(v);
        carR.save(c);
        v.setMacchina(c);
        
        veiR.save(v);
	}
	
	@Override
	public List<VeicoloDTO> listAllCar() throws AcademyException{
		log.debug("LIST ALL CAR: " );

		List<Veicolo> lV = veiR.findAllByDescrizione("macchina");
				
		return lV.stream()
				.map(b->VeicoloDTO.builder()
						.idVeicolo(b.getIdVeicolo())
						.alimentazione(b.getAlimentazione().getDescrizione())
						.categoria(b.getCategoria().getDescrizione())
						.colore(b.getColore().getDescrizione())
						.marca(b.getMarca().getDescrizione())
						.tipoVeicolo(b.getTipoVeicolo().getDescrizione())
						.numeroRuote(b.getNumeroRuote())
						.annoProduzione(b.getAnnoProduzione())
						.modello(b.getModello())
						.moto(null)
						.bici(null)
						.macchina(buildMacchinaDTO(b.getMacchina()))
						.build()
						).collect(Collectors.toList());
	}
	
	private MacchinaDTO buildMacchinaDTO(Macchina b)
	{
		return MacchinaDTO.builder()
				.cilindrata(b.getCilindrata())
				.idMacchina(b.getIdMacchina())
				.numeroPorte(b.getNumeroPorte())
				.targa(b.getTarga()).build();
	}
	
	

	@Override
	public void delete(MacchinaReq carReq, VeicoloReq vReq) throws AcademyException {
		log.debug("Delete MACCHINA");

		Optional<Macchina> car = carR.findByIdVeicolo(carReq.getIdVeicolo());
		log.debug( "QUAL è IL SUO IDVEICOLO: "+carReq.getIdVeicolo());
		
		if(car.isEmpty()) {
			throw new AcademyException("Auto non trovata per l'eliminazione: " + carReq.getIdVeicolo());
		} else {
			carR.delete(car.get());
		}
		
		log.debug("Ci arrivi qui in DELETE AUTO");
		
		VeicoloImpl veiImpl = new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		veiImpl.delete(vReq);
	}

	@Override
	public void update(MacchinaReq carReq, VeicoloReq veiReq) throws AcademyException {
		log.debug("UPDATE di MACCHINA");
		
		Optional<Macchina> car = carR.findByIdVeicolo(carReq.getIdVeicolo());

		if (car.isEmpty()) {
			throw new AcademyException("Auto non trovata con il seguente id: " + carReq.getIdVeicolo());
		}

		Macchina auto = car.get();
		auto.setCilindrata(carReq.getCilindrata());
		auto.setNumeroPorte(carReq.getNumeroPorte());
		auto.setTarga(carReq.getTarga());
		
		VeicoloImpl vei = new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		vei.update(veiReq);
	}

	@Override
	public VeicoloDTO getSingleCar(Integer idVeicolo) throws AcademyException {
		Optional<Veicolo> vei = veiR.findById(idVeicolo);
		
		if (vei.isEmpty()) {
			throw new AcademyException("Auto non trovata con il seguente id: " + idVeicolo);
		}

		Veicolo v = vei.get();
		
		return VeicoloDTO.builder()
				.idVeicolo(v.getIdVeicolo())
				.alimentazione(v.getAlimentazione().getDescrizione())
				.categoria(v.getCategoria().getDescrizione())
				.colore(v.getColore().getDescrizione())
				.marca(v.getMarca().getDescrizione())
				.tipoVeicolo(v.getTipoVeicolo().getDescrizione())
				.numeroRuote(v.getNumeroRuote())
				.annoProduzione(v.getAnnoProduzione())
				.modello(v.getModello())
				.moto(null)
				.bici(null)
				.macchina(buildMacchinaDTO(v.getMacchina()))
				.build();
	}


 }
