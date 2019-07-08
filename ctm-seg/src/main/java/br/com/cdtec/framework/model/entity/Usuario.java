package br.com.cdtec.framework.model.entity;

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

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Proxy;


@Entity
@Table(name="tb_usuario", schema="seg")
@SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO", allocationSize = 1)
@Proxy(lazy = true)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")	
	@Column(name="id_usuario")
	private BigInteger idUsuario;
	
	@Column(name="ds_nome")
	private String dsNome;
	
	@Column(name="ds_email")
	private String dsEmail;
	
	@Column(name="ds_senha")
	private String dsSenha;
	
	@Column(name="id_perfil")
	private Integer idPerfil;
	
	@Formula("(select d.ds_dominio from ngc.tb_dominio d where d.ds_campo = 'ID_PERFIL' and d.vl_dominio = cast(id_perfil as text))")
	private String dsPerfil;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_cadastro")
	private Date dtCadastro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_alteracao")
	private Date dtAlteracao;
	
	@Column(name="fg_valido")
	private Boolean fgValido;

	
	public BigInteger getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDsNome() {
		return dsNome;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getDsSenha() {
		return dsSenha;
	}

	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDsPerfil() {
		return dsPerfil;
	}

	public void setDsPerfil(String dsPerfil) {
		this.dsPerfil = dsPerfil;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public Boolean getFgValido() {
		return fgValido;
	}

	public void setFgValido(Boolean fgValido) {
		this.fgValido = fgValido;
	}
	
}