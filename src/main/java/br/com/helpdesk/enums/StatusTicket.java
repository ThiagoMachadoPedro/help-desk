package br.com.helpdesk.enums;

public enum StatusTicket {
  ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

	private Integer codigo;
	private String descricao;

	private StatusTicket(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusTicket toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}

		for(StatusTicket x : StatusTicket.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Status inválido");
	}
}
