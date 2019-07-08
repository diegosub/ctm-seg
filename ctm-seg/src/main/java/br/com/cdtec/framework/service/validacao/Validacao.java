package br.com.cdtec.framework.service.validacao;

import java.io.Serializable;

/**
 * Classe criada para controlar a validacao de erros da api
 * 
 * @author diego
 *
 */
public class Validacao implements Serializable
{
	/**
	* 
	*/
	private static final long serialVersionUID = -4473993061020324300L;

	public static final int ACAO_PESQUISAR = 1;
	public static final int ACAO_INCLUIR = 2;
	public static final int ACAO_ALTERAR = 4;
	public static final int ACAO_EXCLUIR = 8;
	public static final int ACAO_DETALHE = 16;

	private String campo;
	private String alias;
	private Validador validador;
	private int acoes;

	public Validacao(String campo, String alias, Validador validador, int acoes)
	{
		this.campo = campo;
		this.alias = alias;
		this.validador = validador;
		this.acoes = acoes;
	}

	public String getCampo()
	{
		return campo;
	}

	public void setCampo(String campoValidacao)
	{
		this.campo = campoValidacao;
	}

	public Validador getValidador()
	{
		return validador;
	}

	public void setValidador(Validador validador)
	{
		this.validador = validador;
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public int getAcoes()
	{
		return acoes;
	}

	public void setAcoes(int acoes)
	{
		this.acoes = acoes;
	}

}
