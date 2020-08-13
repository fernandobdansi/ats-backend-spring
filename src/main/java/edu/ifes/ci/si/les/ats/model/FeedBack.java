package edu.ifes.ci.si.les.ats.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
public class FeedBack implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 5, max = 100, message = "O campo deve ter pelo menos 5 e no maximo 100 letras")
	private String comentario;

	@Column(length = 50)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 50, message = "O campo deve ter pelo menos 3 e no maximo 50 letras")
	private String satisfacao;

	@OneToOne
	@JoinColumn(name = "ordemdeservico_id")
	private OrdemDeServico ordemDeServico;

}
