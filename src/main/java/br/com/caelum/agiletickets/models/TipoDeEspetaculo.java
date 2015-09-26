package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public enum TipoDeEspetaculo {
	
	CINEMA {
		@Override
		public BigDecimal calcula(Sessao sessao, Integer quantidade) {
			return getValorCinemaShow(sessao);
		}

	}, SHOW {
		@Override
		public BigDecimal calcula(Sessao sessao, Integer quantidade) {
			return getValorCinemaShow(sessao);
		}
	}, TEATRO {
		@Override
		public BigDecimal calcula(Sessao sessao, Integer quantidade) {
			return sessao.getPreco();
		}
	}, BALLET {
		@Override
		public BigDecimal calcula(Sessao sessao, Integer quantidade) {
			return getValorBalletOrquestra(sessao);
		}

	}, ORQUESTRA {
		@Override
		public BigDecimal calcula(Sessao sessao, Integer quantidade) {

			BigDecimal preco;
			BigDecimal precoDaSessao = sessao.getPreco();
			Integer totalIngressos = sessao.getTotalIngressos();
			
			if((totalIngressos - sessao.getIngressosReservados()) / totalIngressos.doubleValue() <= 0.50) { 
				preco = precoDaSessao.add(precoDaSessao.multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = precoDaSessao;
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(precoDaSessao.multiply(BigDecimal.valueOf(0.10)));
			}
			
			return preco;
		}
	};

	public abstract BigDecimal calcula(Sessao sessao, Integer quantidade);
	
	private static BigDecimal getValorBalletOrquestra(Sessao sessao) {
		Integer totalIngressos = sessao.getTotalIngressos();
		Integer quantidadeIngressosReservados = sessao.getIngressosReservados();
		BigDecimal precoDaSessao = sessao.getPreco();
		BigDecimal preco;
		
		if((totalIngressos - quantidadeIngressosReservados) / totalIngressos.doubleValue() <= 0.50) { 
			preco = precoDaSessao.add(precoDaSessao.multiply(BigDecimal.valueOf(0.20)));
		} else {
			preco = sessao.getPreco();
		}
		
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = precoDaSessao.add(precoDaSessao.multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}
	
	private static BigDecimal getValorCinemaShow(Sessao sessao) {
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.05) { 
			return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return sessao.getPreco();
	}
}
