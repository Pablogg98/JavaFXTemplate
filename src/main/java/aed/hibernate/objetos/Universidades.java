package aed.hibernate.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "universidades")
public class Universidades {

	@Id
	@Column(columnDefinition = "char(6)")
	private String codUniversidad;
	@Column(length = 30)
	private String nomUniversidad;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="codUniversidad")
	private List<Residencias> residencias = new ArrayList<Residencias>();
	
	public String getCodUniversidad() {
		return codUniversidad;
	}
	public void setCodUniversidad(String codUniversidad) {
		this.codUniversidad = codUniversidad;
	}
	public String getNomUniversidad() {
		return nomUniversidad;
	}
	public void setNomUniversidad(String nomUniversidad) {
		this.nomUniversidad = nomUniversidad;
	}
	
}
