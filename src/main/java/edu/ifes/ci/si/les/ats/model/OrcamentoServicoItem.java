package edu.ifes.ci.si.les.ats.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class OrcamentoServicoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrcamentoServicoItemPK id = new OrcamentoServicoItemPK();

	@Column(length = 100)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 2, max = 100, message = "O campo deve ter pelo menos 2 e no maximo 100 digitos")
	private float valor;

    @Builder
    public OrcamentoServicoItem(Orcamento orcamento, Servicos servicos, float valor) {
        this.id.setOrcamento(orcamento);
        this.id.setServicos(servicos);
        this.valor = valor;
    }

    @JsonIgnore
    public Orcamento getOrcamento() {
        return id.getOrcamento();
    }

    public void setOrcamento(Orcamento orcamento) {
        id.setOrcamento(orcamento);
    }

    public Servicos getServicos() {
        return id.getServicos();
    }

    public void setServicos(Servicos servicos) {
        id.setServicos(servicos);
    }
	
	

}
