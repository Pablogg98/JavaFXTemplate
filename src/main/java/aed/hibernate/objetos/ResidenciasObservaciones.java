package aed.hibernate.objetos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name = "residenciasobservaciones")
public class ResidenciasObservaciones implements Serializable {

	@Id
	@GeneratedValue(generator = "myForeign")
	@GenericGenerator(name = "myForeign", strategy = "foreign", parameters = {
			@org.hibernate.annotations.Parameter(name = "property", value = "codResidencia") })
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codResidencia")
	private Residencias codResidencia;
	@Column(length = 300)
	private String observaciones;

	public Residencias getCodResidencia() {
		return codResidencia;
	}

	public void setCodResidencia(Residencias codResidencia) {
		this.codResidencia = codResidencia;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
