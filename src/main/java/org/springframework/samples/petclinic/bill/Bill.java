package org.springframework.samples.petclinic.bill;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.visit.Visit;

@Entity
@Table(name = "bill")
public class Bill{
	@Id
	@Digits(integer = 10, fraction=0)
	private Long id;
	
	@Column(name = "fecha_pago")
	@Temporal(TemporalType.DATE)
	private Date fecha_pago;
	
	@Column(name = "precio")
	@Digits(integer = 4, fraction=2)
	private Double precio;
	
	@ManyToOne
	@JoinColumn(name = "owner")
	private Owner owner;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="bill", cascade=CascadeType.ALL)
	private Visit visit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha_pago() {
		return fecha_pago;
	}

	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	
}
