package edu.ifes.ci.si.les.ats.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
public class Dispositivo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "modelo_id")
	private Modelo modelo;

	@Column(length = 9)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 5, max = 9, message = "O campo deve ter pelo menos 5 e no maximo 9 digitos")
	private int numSerie;

	@Column(length = 150)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 5, max = 150, message = "O campo deve ter pelo menos 10 e no maximo 150 letras")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

}
