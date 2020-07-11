package edu.ifes.ci.si.les.ats.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import edu.ifes.ci.si.les.ats.model.Cliente;
import edu.ifes.ci.si.les.ats.model.Dispositivo;
import edu.ifes.ci.si.les.ats.model.Marca;
import edu.ifes.ci.si.les.ats.model.Modelo;
import edu.ifes.ci.si.les.ats.model.Servicos;
import edu.ifes.ci.si.les.ats.model.StatusGarantia;
import edu.ifes.ci.si.les.ats.model.StatusOrdemDeServico;
import edu.ifes.ci.si.les.ats.model.Tecnico;
import edu.ifes.ci.si.les.ats.repositories.ClienteRepository;
import edu.ifes.ci.si.les.ats.repositories.DispositivoRepository;
import edu.ifes.ci.si.les.ats.repositories.MarcaRepository;
import edu.ifes.ci.si.les.ats.repositories.ModeloRepository;
import edu.ifes.ci.si.les.ats.repositories.ServicosRepository;
import edu.ifes.ci.si.les.ats.repositories.StatusGarantiaRepository;
import edu.ifes.ci.si.les.ats.repositories.StatusOrdemDeServicoRepository;
import edu.ifes.ci.si.les.ats.repositories.TecnicoRepository;

@Service
public class _DBService {

	@Autowired
	private MarcaRepository marcaRepository;
	@Autowired
	private ModeloRepository modeloRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private DispositivoRepository dispositivoRepository;
	@Autowired
	private ServicosRepository servicosRepository;
	@Autowired
	private StatusGarantiaRepository statusgarantiaRepository;
	@Autowired
	private StatusOrdemDeServicoRepository statusordemdeservicoRepository;

	public void instantiateTestDatabase() throws ParseException, IOException {

		// Instanciando os objetos de modelo
		Marca marca1 = new Marca(null, "Motorola");
		Marca marca2 = new Marca(null, "Apple");
		Marca marca3 = new Marca(null, "Samsung");
		Marca marca4 = new Marca(null, "Nokia");

		Modelo modelo1 = new Modelo(null, "Moto G6", marca1);
		Modelo modelo2 = new Modelo(null, "Note 10", marca3);
		Modelo modelo3 = new Modelo(null, "11 Pro", marca2);
		Modelo modelo4 = new Modelo(null, "Lumia", marca4);

		Cliente cliente1 = new Cliente(null, "Jose da Silva", "123.456.789-93", "(28) 9995-3872",
				"Rua Oriente - Jacigua - Vargem Alta");
		Cliente cliente2 = new Cliente(null, "Francisca da Silva", "123.768.789-93", "(28) 9456-3872",
				"Rua Oriente - Jacigua - Vargem Alta");
		Cliente cliente3 = new Cliente(null, "Roberto da Silva", "123.314.789-93", "(28) 9997-9872",
				"Rua Oriente - Jacigua - Vargem Alta");
		Cliente cliente4 = new Cliente(null, "Alexandre da Silva", "123.457.899-93", "(28) 9995-3872",
				"Rua Oriente - Jacigua - Vargem Alta");

		Tecnico tecnico1 = new Tecnico(null, "Alberto da Silva", "123.454.119-93", "(28) 8955-1872",
				"Rua Oriente - Jacigua - Vargem Alta","albertosilva","alb123");
		Tecnico tecnico2 = new Tecnico(null, "Rodrigo da Silva", "123.465.999-93", "(28) 9994-7872",
				"Rua Oriente - Jacigua - Vargem Alta","rodrigosilva","rod123");
		Tecnico tecnico3 = new Tecnico(null, "Fernando da Silva", "123.479.899-93", "(28) 9965-3872",
				"Rua Oriente - Jacigua - Vargem Alta","fernandosilva","fer123");
		Tecnico tecnico4 = new Tecnico(null, "Lucas da Silva", "123.457.322-93", "(28) 9991-4872",
				"Rua Oriente - Jacigua - Vargem Alta","lucassilva","luc123");

		Dispositivo dispositivo1 = new Dispositivo(null, modelo1, "123456789", "Cor Azul", cliente1);
		Dispositivo dispositivo2 = new Dispositivo(null, modelo2, "127476789", "Cor Vermelha", cliente2);
		Dispositivo dispositivo3 = new Dispositivo(null, modelo3, "193456789", "Cor Verde", cliente3);
		Dispositivo dispositivo4 = new Dispositivo(null, modelo4, "124456789", "Cor Cinza", cliente4);

		Servicos servicos1 = new Servicos(null, "Trocar Tela");
		Servicos servicos2 = new Servicos(null, "Trocar Bateria");
		Servicos servicos3 = new Servicos(null, "Trocar Antena");
		Servicos servicos4 = new Servicos(null, "Formatar SO");

		StatusGarantia statusGarantia1 = new StatusGarantia(null, "Aguardando");
		StatusGarantia statusGarantia2 = new StatusGarantia(null, "Em Análise");
		StatusGarantia statusGarantia3 = new StatusGarantia(null, "Finalizado");
		StatusGarantia statusGarantia4 = new StatusGarantia(null, "Aguardando Aprovação");

		StatusOrdemDeServico statusOrdem1 = new StatusOrdemDeServico(null, "Aguardando Inicio");
		StatusOrdemDeServico statusOrdem2 = new StatusOrdemDeServico(null, "Em Vistoria");
		StatusOrdemDeServico statusOrdem3 = new StatusOrdemDeServico(null, "Fechado");
		StatusOrdemDeServico statusOrdem4 = new StatusOrdemDeServico(null, "Aguardando Peças");

		marcaRepository.saveAll(Arrays.asList(marca1, marca2, marca3, marca4));
		modeloRepository.saveAll(Arrays.asList(modelo1, modelo2, modelo3, modelo4));
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3, cliente4));
		tecnicoRepository.saveAll(Arrays.asList(tecnico1, tecnico2, tecnico3, tecnico4));
		dispositivoRepository.saveAll(Arrays.asList(dispositivo1, dispositivo2, dispositivo3, dispositivo4));
		servicosRepository.saveAll(Arrays.asList(servicos1, servicos2, servicos3, servicos4));
		statusgarantiaRepository
				.saveAll(Arrays.asList(statusGarantia1, statusGarantia2, statusGarantia3, statusGarantia4));
		statusordemdeservicoRepository.saveAll(Arrays.asList(statusOrdem1, statusOrdem2, statusOrdem3, statusOrdem4));

	}
}
