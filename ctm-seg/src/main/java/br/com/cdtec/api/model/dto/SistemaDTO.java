package br.com.cdtec.api.model.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class SistemaDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private BigInteger idSistema;

	private String dsSistema;

	private Boolean fgAtivo;

	private BigInteger idUsuarioCad;

	private Date dtCadastro;

	private BigInteger idUsuarioAlt;

	private Date dtAlteracao;

	public BigInteger getIdSistema()
	{
		return idSistema;
	}

	public void setIdSistema(BigInteger idSistema)
	{
		this.idSistema = idSistema;
	}

	public String getDsSistema()
	{
		return dsSistema;
	}

	public void setDsSistema(String dsSistema)
	{
		this.dsSistema = dsSistema;
	}

	public Boolean getFgAtivo()
	{
		return fgAtivo;
	}

	public void setFgAtivo(Boolean fgAtivo)
	{
		this.fgAtivo = fgAtivo;
	}

	public BigInteger getIdUsuarioCad()
	{
		return idUsuarioCad;
	}

	public void setIdUsuarioCad(BigInteger idUsuarioCad)
	{
		this.idUsuarioCad = idUsuarioCad;
	}

	public Date getDtCadastro()
	{
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro)
	{
		this.dtCadastro = dtCadastro;
	}

	public BigInteger getIdUsuarioAlt()
	{
		return idUsuarioAlt;
	}

	public void setIdUsuarioAlt(BigInteger idUsuarioAlt)
	{
		this.idUsuarioAlt = idUsuarioAlt;
	}

	public Date getDtAlteracao()
	{
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao)
	{
		this.dtAlteracao = dtAlteracao;
	}
}