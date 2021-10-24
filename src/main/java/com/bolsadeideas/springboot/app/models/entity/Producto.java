package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name="productos")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;

	@NotEmpty
	private String nombre;
	
	@NotNull
	private Double precio;
	
	@NotNull
	private Integer stock;



	@Temporal(TemporalType.DATE)
	@Column(name="create_at")
	private Date createAt;
	
	private String foto;
	
	@NotEmpty
	private String laboratorio;

	@PrePersist
	public void prePersist() {
		createAt=new Date();
	}
	
	@PostPersist
	public void postPersistCodigo() {
		double valor = (double)this.getId();
		codigo=this.transformaracodigo(valor);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String transformaracodigo(double valor){
		DecimalFormat f = new DecimalFormat("PDT0000");
		String salida=f.format(valor);
		System.out.println(salida);
		return salida;
	}
	
	
	private static final long serialVersionUID = 1L;

}
