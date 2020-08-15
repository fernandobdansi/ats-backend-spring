package edu.ifes.ci.si.les.ats.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.ats.model.Cliente;
import edu.ifes.ci.si.les.ats.model.Dispositivo;
import edu.ifes.ci.si.les.ats.model.FeedBack;
import edu.ifes.ci.si.les.ats.model.Garantia;
import edu.ifes.ci.si.les.ats.model.Marca;
import edu.ifes.ci.si.les.ats.model.Modelo;
import edu.ifes.ci.si.les.ats.model.Orcamento;
import edu.ifes.ci.si.les.ats.model.OrcamentoServicoItem;
import edu.ifes.ci.si.les.ats.model.OrdemDeServico;
import edu.ifes.ci.si.les.ats.model.OrdemServicoItem;
import edu.ifes.ci.si.les.ats.model.Servicos;
import edu.ifes.ci.si.les.ats.model.StatusGarantia;
import edu.ifes.ci.si.les.ats.model.StatusOrdemDeServico;
import edu.ifes.ci.si.les.ats.model.Tecnico;
import edu.ifes.ci.si.les.ats.repositories.ClienteRepository;
import edu.ifes.ci.si.les.ats.repositories.DispositivoRepository;
import edu.ifes.ci.si.les.ats.repositories.FeedBackRepository;
import edu.ifes.ci.si.les.ats.repositories.GarantiaRepository;
import edu.ifes.ci.si.les.ats.repositories.MarcaRepository;
import edu.ifes.ci.si.les.ats.repositories.ModeloRepository;
import edu.ifes.ci.si.les.ats.repositories.OrcamentoRepository;
import edu.ifes.ci.si.les.ats.repositories.OrdemDeServicoRepository;
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
	@Autowired
	private OrdemDeServicoRepository ordemDeServicoRepository;
	@Autowired
	private FeedBackRepository feedBackRepository;
	@Autowired
	private GarantiaRepository garantiaRepository;
	@Autowired
	private OrcamentoRepository orcamentoRepository;

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

		Tecnico tecnico1 = new Tecnico(null, "Tec Alberto da Silva", "123.454.119-93", "(28) 8955-1872",
				"Rua Oriente - Jacigua - Vargem Alta", "albertosilva", "alb123");
		Tecnico tecnico2 = new Tecnico(null, "Tec Rodrigo da Silva", "123.465.999-93", "(28) 9994-7872",
				"Rua Oriente - Jacigua - Vargem Alta", "rodrigosilva", "rod123");
		Tecnico tecnico3 = new Tecnico(null, "Tec Fernando da Silva", "123.479.899-93", "(28) 9965-3872",
				"Rua Oriente - Jacigua - Vargem Alta", "fernandosilva", "fer123");
		Tecnico tecnico4 = new Tecnico(null, "Tec Lucas da Silva", "123.457.322-93", "(28) 9991-4872",
				"Rua Oriente - Jacigua - Vargem Alta", "lucassilva", "luc123");

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

		OrdemDeServico ordemDeServico1 = new OrdemDeServico(null, dispositivo1, "2020-08-08", "Tela Quebrada", tecnico1,
				100, "2020-08-10", statusOrdem3, "Nao");
		OrdemDeServico ordemDeServico2 = new OrdemDeServico(null, dispositivo2, "2020-08-12", "Bateria Ruim", tecnico2,
				200, "2020-08-13", statusOrdem3, "Sim");

		OrdemServicoItem ordemServicoItem = new OrdemServicoItem(ordemDeServico1, servicos1, 100);
		OrdemServicoItem ordemServicoItem2 = new OrdemServicoItem(ordemDeServico2, servicos2, 150);
		OrdemServicoItem ordemServicoItem3 = new OrdemServicoItem(ordemDeServico2, servicos4, 50);

		Orcamento orcamento1 = new Orcamento(null, dispositivo1, tecnico1, "Tela Quebrada", 100);
		Orcamento orcamento2 = new Orcamento(null, dispositivo2, tecnico2, "Bateria Ruim", 200);

		OrcamentoServicoItem orcamentoServicoItem = new OrcamentoServicoItem(orcamento1, servicos1, 100);
		OrcamentoServicoItem orcamentoServicoItem2 = new OrcamentoServicoItem(orcamento2, servicos2, 150);
		OrcamentoServicoItem orcamentoServicoItem3 = new OrcamentoServicoItem(orcamento2, servicos4, 50);

		orcamento1.setOrcamentoServicoItem(Arrays.asList(orcamentoServicoItem));
		orcamento2.setOrcamentoServicoItem(Arrays.asList(orcamentoServicoItem2, orcamentoServicoItem3));

		ordemDeServico1.setOrdemServicosItem(Arrays.asList(ordemServicoItem));
		ordemDeServico2.setOrdemServicosItem(Arrays.asList(ordemServicoItem2, ordemServicoItem3));

		FeedBack feedBack1 = new FeedBack(null, "Muito Bom", "Exelente", ordemDeServico1);
		FeedBack feedBack2 = new FeedBack(null, "Podia ser mais Barato", "Bom", ordemDeServico2);

		Garantia garantia1 = new Garantia(null, "Tela Descolou", "2020-08-15", ordemDeServico1, statusGarantia1);
		Garantia garantia2 = new Garantia(null, "Celular não carrega", "2020-08-16", ordemDeServico2, statusGarantia1);

		marcaRepository.saveAll(Arrays.asList(marca1, marca2, marca3, marca4));
		modeloRepository.saveAll(Arrays.asList(modelo1, modelo2, modelo3, modelo4));
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3, cliente4));
		tecnicoRepository.saveAll(Arrays.asList(tecnico1, tecnico2, tecnico3, tecnico4));
		dispositivoRepository.saveAll(Arrays.asList(dispositivo1, dispositivo2, dispositivo3, dispositivo4));
		servicosRepository.saveAll(Arrays.asList(servicos1, servicos2, servicos3, servicos4));
		statusgarantiaRepository.saveAll(Arrays.asList(statusGarantia1, statusGarantia2, statusGarantia3, statusGarantia4));
		statusordemdeservicoRepository.saveAll(Arrays.asList(statusOrdem1, statusOrdem2, statusOrdem3, statusOrdem4));

		ordemDeServicoRepository.saveAll(Arrays.asList(ordemDeServico1, ordemDeServico2));
		feedBackRepository.saveAll(Arrays.asList(feedBack1, feedBack2));
		garantiaRepository.saveAll(Arrays.asList(garantia1, garantia2));
		orcamentoRepository.saveAll(Arrays.asList(orcamento1, orcamento2));

	}
}
