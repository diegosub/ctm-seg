package br.com.cdtec.framework.service.validacao;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;


public class Obrigatorio implements Validador
{
   private static Obrigatorio instancia;

   private Obrigatorio()
   {
   }

   public static Obrigatorio getInstancia()
   {
      if (instancia == null)
      {
         instancia = new Obrigatorio();
      }
      return instancia;
   }
   
   @Override
   public void validar(Object objeto, String campo, String alias) throws Exception
   {
      Object valorCampo = null;
      try
      {
         valorCampo = PropertyUtils.getProperty(objeto, campo);
      }
      catch (InvocationTargetException e)
      {
         throw new Exception("Ocorreu um erro inesperado. Campo: " + campo + " não pode ser obtido da classe: " + objeto.getClass().getCanonicalName() + " target invalido");
      }
      catch (NoSuchMethodException e)
      {
         throw new Exception("Ocorreu um erro inesperado. Campo: " + campo + " não pode ser obtido da classe: " + objeto.getClass().getCanonicalName() + " não foi encontrado");
      }
      catch (IllegalAccessException e)
      {
         throw new Exception("Ocorreu um erro inesperado. Campo: " + campo + " não pode ser obtido da classe: " + objeto.getClass().getCanonicalName() + " por acesso inválido");
      }
      
      if(valorCampo == null || "".equals(valorCampo.toString().trim()))
      {
         throw new Exception("O campo " + alias + " é obrigatório.");
      }
      else if(valorCampo instanceof Collection<?>)
      {
         if(((Collection<?>)valorCampo).size() == 0)
         {
            throw new Exception("O campo " + alias + " é obrigatório.");
         }
      }
   }

   @Override
   public String validarAction(Object objeto, String campo, String alias)
   {
      String retorno = null;
      try
      {
         validar(objeto, campo, alias);
      }
      catch (Exception ae) 
      {
         retorno = ae.getMessage();
      }
      return retorno;
   }

}
