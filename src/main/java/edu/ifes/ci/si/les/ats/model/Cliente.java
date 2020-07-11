package edu.ifes.ci.si.les.ats.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 10, max = 200, message = "O campo deve ter pelo menos 10 e no maximo 200 letras")
	private String nome;

	@Column(length = 11)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 11, max = 11, message = "O campo deve ter 11 digitos")
	private String cpf;

	@Column(length = 13)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 10, max = 13, message = "O campo deve ter pelo menos 10 digitos")
	private String telefone;

	@Column(length = 200)
	@NotBlank(message = "O Campo deve ser preenchido")
	@Size(min = 10, max = 200, message = "O campo deve ter pelo menos 10 e no maximo 200 letras")
	private String endereco;

}
