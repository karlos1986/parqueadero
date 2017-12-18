package persistencia.entidad;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


@Entity(name = "Parqueadero")
@NamedQueries({ @NamedQuery(name = "Parqueadero.findByPlaca", query = "SELECT parqueadero FROM Parqueadero parqueadero WHERE parqueadero.vehiculo.placa = :placa"),
	@NamedQuery(name = "Parqueadero.findCeldasMoto", query = "SELECT COUNT (*) FROM Parqueadero parqueadero WHERE parqueadero.dateEgreso is null AND parqueadero.vehiculo.tipo = 2 "),
	@NamedQuery(name = "Parqueadero.findCeldasCarro", query = "SELECT COUNT (*) FROM Parqueadero parqueadero WHERE parqueadero.dateEgreso is null AND parqueadero.vehiculo.tipo = 1 "),
	@NamedQuery(name = "Parqueadero.findForVehiculo", query = "SELECT parqueadero FROM Parqueadero parqueadero WHERE parqueadero.dateEgreso is null AND parqueadero.vehiculo.placa = :placa"),
	@NamedQuery(name = "Parqueadero.findAll", query = "SELECT parqueadero FROM Parqueadero parqueadero WHERE parqueadero.dateEgreso is null")})	
public class ParqueaderoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="idVehiculo")
	private VehiculoEntity vehiculo;

	@Column(nullable = false)
	private Date dateIngreso;

	@Column(nullable = true)
	private Date dateEgreso ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDateIngreso() {
		return dateIngreso;
	}

	public void setDateIngreso(Date dateIngreso) {
		this.dateIngreso = dateIngreso;
	}
	
	public Date getDateEgreso() {
		return dateEgreso;
	}

	public void setDateEgreso(Date dateEgreso) {
		this.dateEgreso = dateEgreso;
	}
	
	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}
}
