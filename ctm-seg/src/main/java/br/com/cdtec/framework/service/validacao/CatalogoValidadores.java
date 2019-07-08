package br.com.cdtec.framework.service.validacao;


public class CatalogoValidadores
{
   public static Validador OBRIGATORIO = Obrigatorio.getInstancia(); 
   public static Validador PELO_MENOS_UM = PeloMenosUm.getInstancia(); 
   public static Validador INTERVALO = Intervalo.getInstancia(); 
}
