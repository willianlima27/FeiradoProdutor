package br.com.feiradoprodutor.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@MappedSuperclass
public class GenericDomain implements Serializable{

	// define a variavel como chave primária
	@Id
	// define que a variável é auto incremento, caso deseje personalizar,
	// utilizar SEQUENCE.
	@SequenceGenerator(name = "pk_sequence", sequenceName = "entity_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pk_sequence")
	
	
	
	private long codigo;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomain other = (GenericDomain) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	
}
