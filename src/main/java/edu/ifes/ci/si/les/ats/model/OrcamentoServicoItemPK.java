package edu.ifes.ci.si.les.ats.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;

@Embeddable
@Data
@EqualsAndHashCode(of = { "orcamento", "servicos" })
public class OrcamentoServicoItemPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "orcamento_id")
	private Orcamento orcamento;

	@ManyToOne
	@JoinColumn(name = "servicos_id")
	private Servicos servicos;

}
