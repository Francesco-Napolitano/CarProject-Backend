package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.BiciDTO;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Bici;
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
import com.betacom.jpa.requests.BiciReq;
import com.betacom.jpa.requests.VeicoloReq;
import com.betacom.jpa.services.services.IBiciService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BiciImpl implements IBiciService{
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
	
	
	public BiciImpl(IVeicoloRepository veiR, IMotoRepository motoR, IMacchinaRepository carR, IBiciRepository biciR,
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
	public void create(BiciReq biciReq, VeicoloReq veiReq) throws AcademyException {
		Bici c = new Bici();
		c.setNumeroMarce(biciReq.getNumeroMarce());
		c.setPieghevole(biciReq.getPieghevole());
		c.setSospensione(sospR.findByDescrizione(biciReq.getSospensione()).get());
		
		VeicoloImpl v = new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		v.create(veiReq);
		
		List<Veicolo> veicolo = veiR.findAll();
		Veicolo vei = veicolo.getLast();
		
		c.setVeicolo(vei);
		biciR.save(c);
		vei.setBici(c);
		veiR.save(vei);
	}
	
	@Override
	public void delete(BiciReq biciReq, VeicoloReq vReq) throws AcademyException {
		log.debug("Delete BICI");

		Optional<Bici> bici = biciR.findByIdVeicolo(biciReq.getIdVeicolo());
		log.debug( "QUAL è IL SUO IDVEICOLO: "+biciReq.getIdVeicolo());
		
		if(bici.isEmpty()) {
			throw new AcademyException("Auto non trovata per l'eliminazione: " + biciReq.getIdVeicolo());
		} else {
			biciR.delete(bici.get());
		}
			
		log.debug("Ci arrivi qui in DELETE AUTO");
		
		VeicoloImpl veiImpl = new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		veiImpl.delete(vReq);
		
	}

	
	@Override
	public List<VeicoloDTO> listAllBici() throws AcademyException{
		List<Veicolo> lV = veiR.findAllByDescrizione("bici");
		
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
						.bici(buildBiciDTO(b.getBici()))
						.macchina(null)
						.build()
						).collect(Collectors.toList());
	}
	
	private BiciDTO buildBiciDTO(Bici b)
	{
		return BiciDTO.builder()
				.numeroMarce(b.getNumeroMarce())
				.pieghevole(b.getPieghevole())
				.sospensione(b.getSospensione().getDescrizione())
				.build();
	}

	@Override
	public void update(BiciReq biciReq, VeicoloReq veiReq) throws AcademyException {
		log.debug("UPDATE di BICI");
		
		Optional<Bici> bike = biciR.findByIdVeicolo(biciReq.getIdVeicolo());

		if (bike.isEmpty()) {
			throw new AcademyException("Bici non trovata con il seguente id: " + biciReq.getIdVeicolo());
		}

		Bici bici = bike.get();
		bici.setNumeroMarce(biciReq.getNumeroMarce());
		bici.setPieghevole(biciReq.getPieghevole());
		bici.setSospensione(sospR.findByDescrizione(biciReq.getSospensione()).get());
		
		VeicoloImpl vei = new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		vei.update(veiReq);

	}

	@Override
	public VeicoloDTO getSingleBike(Integer idVeicolo) throws AcademyException {
		Optional<Veicolo> vei = veiR.findById(idVeicolo);
		
		if (vei.isEmpty()) {
			throw new AcademyException("Bici non trovata con il seguente id: " + idVeicolo);
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
				.macchina(null)
				.bici(buildBiciDTO(v.getBici()))
				.build();
	}
	
}
