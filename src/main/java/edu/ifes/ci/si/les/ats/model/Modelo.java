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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
public class Modelo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20)
	@NotBlank(message = "O Campo deve ser preenchido")
    @NotNull(message = "O Campo deve ser preenchido")
	@Size(min = 3, max = 20, message = "O campo deve ter pelo menos 3 e no maximo 20 letras")
	private String nomeModelo;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;

}
