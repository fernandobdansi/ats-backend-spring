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
public class OrdemServicoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrdemServicoItemPK id = new OrdemServicoItemPK();

	@Column(length = 100)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 2, max = 100, message = "O campo deve ter pelo menos 2 e no maximo 100 digitos")
	private float valor;

    @Builder
    public OrdemServicoItem(OrdemDeServico ordemDeServico, Servicos servicos, float valor) {
        this.id.setOrdemDeServico(ordemDeServico);
        this.id.setServicos(servicos);
        this.valor = valor;
    }

    @JsonIgnore
    public OrdemDeServico getOrdemDeServico() {
        return id.getOrdemDeServico();
    }

    public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
        id.setOrdemDeServico(ordemDeServico);
    }

    public Servicos getServicos() {
        return id.getServicos();
    }

    public void setServicos(Servicos servicos) {
        id.setServicos(servicos);
    }
	
	

}
