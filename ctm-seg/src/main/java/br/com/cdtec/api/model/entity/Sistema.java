package br.com.cdtec.api.model.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "tb_sistema", schema = "seg")
@SequenceGenerator(name = "SQ_SISTEMA", sequenceName = "SQ_SISTEMA", allocationSize = 1)
@Proxy(lazy = true)
public class Sistema implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SISTEMA")
	@Column(name = "id_sistema")
	private BigInteger idSistema;

	@Column(name = "ds_sistema")
	private String dsSistema;

	@Column(name = "fg_ativo")
	private String fgAtivo;

	@Column(name = "id_usuario_cad")
	private BigInteger idUsuarioCad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_cadastro")
	private Date dtCadastro;

	@Column(name = "id_usuario_alt")
	private BigInteger idUsuarioAlt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_alteracao")
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

	public String getFgAtivo()
	{
		return fgAtivo;
	}

	public void setFgAtivo(String fgAtivo)
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