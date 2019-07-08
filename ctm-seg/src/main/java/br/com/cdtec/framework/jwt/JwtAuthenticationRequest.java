package br.com.cdtec.framework.jwt;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String dsEmail;	
	private String dsSenha;

	public JwtAuthenticationRequest() {
		super();
	}
	
	public JwtAuthenticationRequest(String dsLogin, String dsPassword) {
		this.setDsEmail(dsEmail);
		this.setDsSenha(dsSenha);
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


}
