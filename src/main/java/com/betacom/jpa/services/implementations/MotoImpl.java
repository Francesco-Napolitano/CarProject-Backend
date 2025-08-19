package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.MotoDTO;
import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Macchina;
import com.betacom.jpa.models.Moto;
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
import com.betacom.jpa.requests.MotoReq;
import com.betacom.jpa.requests.VeicoloReq;
import com.betacom.jpa.services.services.IMotoService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2

public class MotoImpl implements IMotoService {

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
	

	public MotoImpl(IVeicoloRepository veiR, IMotoRepository motoR, IMacchinaRepository carR, IBiciRepository biciR,
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
	public void create(MotoReq mReq, VeicoloReq veiReq) throws AcademyException {
		Optional<Moto> m = motoR.findByTarga(mReq.getTarga());
		if(m.isPresent()) throw new AcademyException("moto con targa :"+mReq.getTarga()+" è gia esistente ");
		
		Moto c = new Moto();
		c.setCilindrata(mReq.getCilindrata());
		c.setTarga(mReq.getTarga());
		motoR.save(c);
		
		VeicoloImpl veiImpl= new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		veiImpl.create(veiReq);
		
		List<Veicolo> lv = veiR.findAll();
        Veicolo v = lv.getLast();
        c.setVeicolo(v);
        motoR.save(c);
        v.setMoto(c);
        veiR.save(v);
}
	
	@Override
	public List<VeicoloDTO> listAllMoto() throws AcademyException{
		List<Veicolo> lV = veiR.findAllByDescrizione("moto");
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
						.moto(buildMotoDTO(b.getMoto()))
						.bici(null)
						.macchina(null)
						.build()
						).collect(Collectors.toList());
	}
	
	@Override
	public void delete(MotoReq motoReq, VeicoloReq vReq) throws AcademyException {
		log.debug("Delete MACCHINA");

		Optional<Moto> moto = motoR.findByIdVeicolo(motoReq.getIdVeicolo());
		log.debug( "QUAL è IL SUO IDVEICOLO: "+motoReq.getIdVeicolo());
		
		if(moto.isEmpty()) {
			throw new AcademyException("Auto non trovata per l'eliminazione: " + motoReq.getIdVeicolo());
		} else {
			motoR.delete(moto.get());
		}
		
		log.debug("Ci arrivi qui in DELETE AUTO");
		
		VeicoloImpl veiImpl = new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		veiImpl.delete(vReq);
	}

	
	private MotoDTO buildMotoDTO(Moto b)
	{
		return MotoDTO.builder()
				.cilindrata(b.getCilindrata())
				.idMoto(b.getIdMoto())
				.targa(b.getTarga()).build();
	}


	@Override
	public void update(MotoReq motoReq, VeicoloReq veiReq) throws AcademyException {
		log.debug("UPDATE di MOTO");
		
		Optional<Moto> moto = motoR.findByIdVeicolo(motoReq.getIdVeicolo());

		if (moto.isEmpty()) {
			throw new AcademyException("Moto non trovata con il seguente id: " + motoReq.getIdVeicolo());
		}

		Moto m = moto.get();
		m.setCilindrata(motoReq.getCilindrata());
		m.setTarga(motoReq.getTarga());
		
		VeicoloImpl vei = new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		vei.update(veiReq);

	}


	@Override
	public VeicoloDTO getSingleMoto(Integer idVeicolo) throws AcademyException {
		Optional<Veicolo> vei = veiR.findById(idVeicolo);
		
		if (vei.isEmpty()) {
			throw new AcademyException("Moto non trovata con il seguente id: " + idVeicolo);
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
				.macchina(null)
				.bici(null)
				.moto(buildMotoDTO(v.getMoto()))
				.build();
	}

}
