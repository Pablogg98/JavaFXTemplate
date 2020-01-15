package aed.hibernate.objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "residencias")
public class Residencias implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codResidencia;
	@Column(length = 30)
	private String nomResidencia;
	@ManyToOne
	@JoinColumn(name = "codUniversidad", columnDefinition = "char(6)")
	private Universidades codUniversidad;
	@Column(columnDefinition = "smallint(6)")
	private int precioMensual;
	@Column(columnDefinition = "tinyint(1)")
	private boolean comedor;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="codResidencia", orphanRemoval = true)
	private List<Estancias> estancias = new ArrayList<Estancias>();
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="codResidencia")
	public int getCodResidencia() {
		return codResidencia;
	}

	public void setCodResidencia(int codResidencia) {
		this.codResidencia = codResidencia;
	}

	public String getNomResidencia() {
		return nomResidencia;
	}

	public void setNomResidencia(String nomResidencia) {
		this.nomResidencia = nomResidencia;
	}

	public Universidades getCodUniversidad() {
		return codUniversidad;
	}

	public void setCodUniversidad(Universidades codUniversidad) {
		this.codUniversidad = codUniversidad;
	}

	public int getPrecioMensual() {
		return precioMensual;
	}

	public void setPrecioMensual(int precioMensual) {
		this.precioMensual = precioMensual;
	}

	public boolean isComedor() {
		return comedor;
	}

	public void setComedor(boolean comedor) {
		this.comedor = comedor;
	}

	public List<Estancias> getEstancias() {
		return estancias;
	}

	public void setEstancias(List<Estancias> estancias) {
		this.estancias = estancias;
	}
}