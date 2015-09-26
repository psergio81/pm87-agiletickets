package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	
	@Test
	public void DeveCriarApenasUmaSessaoMesmaData(){
		
		LocalDate inicio = new LocalDate(2015, 12, 01);
		LocalDate fim = new LocalDate(2015, 12, 01);
		LocalTime horario = new LocalTime(17,00);
		
		List<Sessao> sessoes = new Espetaculo().criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertNotNull("A sessao nao pode ser nula", sessoes);
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals("A hora da sessao deve ser igual a hora informada","17:00", sessoes.get(0).getHora());
		
	}
	
	@Test
	public void DeveCriarDuasSessoesDataProximoDia(){
		
		LocalDate inicio = new LocalDate(2015, 12, 01);
		LocalDate fim = new LocalDate(2015, 12, 02);
		LocalTime horario = new LocalTime(17,00);
		
		List<Sessao> sessoes = new Espetaculo().criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		Assert.assertNotNull("A sessao nao pode ser nula", sessoes);
		Assert.assertEquals(2, sessoes.size());
		Assert.assertEquals("Data da ultima sessao deve ser 02/12/15", "02/12/15" ,sessoes.get(1).getDia());
		
	}

	/* - Caso a data de inicio seja 01/01/2010, a data fim seja 31/01/2010,
    * e a periodicidade seja SEMANAL, o algoritmo cria 5 sessoes, uma
    * a cada 7 dias: 01/01, 08/01, 15/01, 22/01 e 29/01.
    */
	
	@Test
	public void DeveCriarTresSessoes(){
		
		LocalDate inicio = new LocalDate(2010, 01, 01);
		LocalDate fim = new LocalDate(2010, 01, 31);
		LocalTime horario = new LocalTime(17,00);
		
		List<Sessao> sessoes = new Espetaculo().criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		Assert.assertNotNull("A sessao nao pode ser nula", sessoes);
		Assert.assertEquals(4, sessoes.size());
//		Assert.assertEquals("Data da ultima sessao deve ser 02/12/15", "02/12/15" ,sessoes.get(1).getDia());
		
	}
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
