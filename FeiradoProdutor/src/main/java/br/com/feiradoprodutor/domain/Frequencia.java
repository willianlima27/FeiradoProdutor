package br.com.feiradoprodutor.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Frequencia extends GenericDomain{
	
	@Column(nullable = false, unique=true)
	@Temporal(TemporalType.DATE)
	private Date data;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
