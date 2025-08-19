package com.betacom.jpa.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.VeicoloDTO;
import com.betacom.jpa.exception.AcademyException;
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
import com.betacom.jpa.requests.VeicoloReq;
import com.betacom.jpa.services.services.IVeicoloService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class VeicoloImpl implements IVeicoloService {

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

	public VeicoloImpl(IVeicoloRepository veiR, IMotoRepository motoR, IMacchinaRepository carR, IBiciRepository biciR,
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(VeicoloReq vreq) throws AcademyException {
		Veicolo v = new Veicolo();

		if (tipoR.findByDescrizione(vreq.getTipoVeicolo()).isPresent()) {

			v.setTipoVeicolo(tipoR.findByDescrizione(vreq.getTipoVeicolo()).get());

			if (aliR.findByDescrizione(vreq.getAlimentazione()).isPresent()) {
				v.setAlimentazione(aliR.findByDescrizione(vreq.getAlimentazione()).get());
			} else {
				throw new AcademyException("alimentazione non valida");
			}

			if (catR.findByDescrizione(vreq.getCategoria()).isPresent()) {
				v.setCategoria(catR.findByDescrizione(vreq.getCategoria()).get());
			} else {
				throw new AcademyException("categoria non valida");
			}
		} else {
			throw new AcademyException("tipo veicolo non valido");
		}

		if (colR.findByDescrizione(vreq.getColore()).isPresent()) {
			v.setColore(colR.findByDescrizione(vreq.getColore()).get());
		} else {
			throw new AcademyException("Colore non valido");
		}

		if (marcaR.findByDescrizione(vreq.getMarca()).isPresent()) {
			v.setMarca(marcaR.findByDescrizione(vreq.getMarca()).get());
		} else {
			throw new AcademyException("Marca non valida");
		}

		v.setNumeroRuote(vreq.getNumeroRuote());
		v.setAnnoProduzione(vreq.getAnnoProduzione());
		v.setModello(vreq.getModello());

		veiR.save(v);
	}

	@Override
	public List<VeicoloDTO> listAll() throws AcademyException {
		List<VeicoloDTO> lTot = new ArrayList<VeicoloDTO>();
		MacchinaImpl mI = new MacchinaImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		lTot.addAll(mI.listAllCar());

		MotoImpl motoI = new MotoImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		lTot.addAll(motoI.listAllMoto());

		BiciImpl bI = new BiciImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		lTot.addAll(bI.listAllBici());

		return lTot;
	}

	@Override
	public void delete(VeicoloReq vReq) throws AcademyException {
		log.debug("Delete VEICOLO: " + vReq);

		Optional<Veicolo> vei = veiR.findById(vReq.getIdVeicolo());

		if (vei.isEmpty())
			throw new AcademyException("Veicolo non trovato per l'eliminazione");

		veiR.delete(vei.get());
	}

	@Override
	public void update(VeicoloReq vreq) throws AcademyException {
		log.debug("UPDATE di VEICOLO");
		Optional<Veicolo> vei = veiR.findById(vreq.getIdVeicolo());

		if (vei.isEmpty()) {
			throw new AcademyException("Veicolo non trovato con il seguente id: " + vreq.getIdVeicolo());
		}

		Veicolo v = vei.get();
		
		if (tipoR.findByDescrizione(vreq.getTipoVeicolo()).isPresent()) {

			v.setTipoVeicolo(tipoR.findByDescrizione(vreq.getTipoVeicolo()).get());

			if (aliR.findByDescrizione(vreq.getAlimentazione()).isPresent()) {
				v.setAlimentazione(aliR.findByDescrizione(vreq.getAlimentazione()).get());
			} else {
				throw new AcademyException("alimentazione non valida");
		}

			if (catR.findByDescrizione(vreq.getCategoria()).isPresent()) {
				v.setCategoria(catR.findByDescrizione(vreq.getCategoria()).get());
		} else {
				throw new AcademyException("categoria non valida");
	}
		} else {
			throw new AcademyException("tipo veicolo non valido");
	}

		if (colR.findByDescrizione(vreq.getColore()).isPresent()) {
			v.setColore(colR.findByDescrizione(vreq.getColore()).get());
		} else {
			throw new AcademyException("Colore non valido");
	}

		if (marcaR.findByDescrizione(vreq.getMarca()).isPresent()) {
			v.setMarca(marcaR.findByDescrizione(vreq.getMarca()).get());
		} else {
			throw new AcademyException("Marca non valida");
	}

		v.setNumeroRuote(vreq.getNumeroRuote());
		v.setAnnoProduzione(vreq.getAnnoProduzione());
		v.setModello(vreq.getModello());

		veiR.save(v);

	}

}
