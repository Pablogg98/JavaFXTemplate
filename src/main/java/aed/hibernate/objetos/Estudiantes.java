package aed.hibernate.objetos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estudiantes")
public class Estudiantes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codEstudiante;
	@Column(length = 9)
	private String dni;
	@Column(length = 50)
	private String nomEstudiante;
	@Column(length = 9)
	private String telefonoEstudiante;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="codEstudiante")
	private List<Estancias> estancias = new ArrayList<Estancias>();
	
	public int getCodEstudiante() {
		return codEstudiante;
	}
	public void setCodEstudiante(int codEstudiante) {
		this.codEstudiante = codEstudiante;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNomEstudiante() {
		return nomEstudiante;
	}
	public void setNomEstudiante(String nomEstudiante) {
		this.nomEstudiante = nomEstudiante;
	}
	public String getTelefonoEstudiante() {
		return telefonoEstudiante;
	}
	public void setTelefonoEstudiante(String telefonoEstudiante) {
		this.telefonoEstudiante = telefonoEstudiante;
	}

}
