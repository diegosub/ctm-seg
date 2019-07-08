package br.com.cdtec.framework.service.validacao;

public interface Validador
{
   public void validar(Object objeto, String campo, String alias) throws Exception;
   
   public String validarAction(Object objeto, String campo, String alias);
}
