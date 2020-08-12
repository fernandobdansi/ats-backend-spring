package edu.ifes.ci.si.les.ats.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
public class OrdemDeServico implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "O Campo deve ser preenchida")
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@NotNull(message = "O Campo deve ser preenchida")
	@ManyToOne
	@JoinColumn(name = "dispositivo_id")
	private Dispositivo dispositivo;

	@NotNull(message = "O Campo deve ser preenchida")
	@OneToMany(mappedBy = "id.ordemDeServico", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<OrdemServicoItem> ordemServicosItem = new ArrayList<>();

	@NotNull(message = "O Campo deve ser preenchida")
	@Column(length = 8)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 8, max = 8, message = "O campo deve ter 8 digitos")
	private String dataEntrada;

	@NotNull(message = "O Campo deve ser preenchida")
	@Column(length = 50)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 10, max = 50, message = "O campo deve ter pelo menos 10 e no maximo 50 letras")
	private String descricaoProblema;

	@NotNull(message = "O Campo deve ser preenchida")
	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	private Tecnico tecnico;

	@Column(length = 100)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 2, max = 100, message = "O campo deve ter pelo menos 2 e no maximo 100 digitos")
	private float valorTotal;

	@NotNull(message = "O Campo deve ser preenchida")
	@Column(length = 8)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 8, max = 8, message = "O campo deve ter 8 digitos")
	private String dataSaida;

	@NotNull(message = "O Campo deve ser preenchida")
	@ManyToOne
	@JoinColumn(name = "statusordemdeservico_id")
	private StatusOrdemDeServico statusOrdemDeServico;

    @Builder
	public OrdemDeServico(Integer id, Cliente cliente, Dispositivo dispositivo, String dataEntrada,
			String descricaoProblema, Tecnico tecnico, float valorTotal, String dataSaida,
			StatusOrdemDeServico statusOrdemDeServico) {
		this.id = id;
		this.cliente = cliente;
		this.dispositivo = dispositivo;
		this.dataEntrada = dataEntrada;
		this.descricaoProblema = descricaoProblema;
		this.tecnico = tecnico;
		this.valorTotal = valorTotal;
		this.dataSaida = dataSaida;
		this.statusOrdemDeServico = statusOrdemDeServico;
	}

}
