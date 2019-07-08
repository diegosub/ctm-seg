package br.com.cdtec.framework.service.validacao;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public class Intervalo implements Validador
{
	private static Intervalo instancia;

	private Intervalo()
	{
	}

	public static Intervalo getInstancia()
	{
		if (instancia == null)
		{
			instancia = new Intervalo();
		}
		return instancia;
	}

	@Override
	public void validar(Object objeto, String campo, String alias) throws Exception
	{
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String validarAction(Object objeto, String campo, String alias)
	{
		String retorno = null;
		String erro = null;
		String[] campos = campo.split(";");
		if (campos != null)
		{
			if (campos.length != 2)
			{
				erro = "Deve ser passado dois nomes de campos separados por ;";
			}
			else
			{
				try
				{
					Object valorCampoInicial = PropertyUtils.getProperty(objeto, campos[0]);
					Object valorCampoFinal = PropertyUtils.getProperty(objeto, campos[1]);

					if (valorCampoInicial != null && valorCampoFinal != null)
					{
						if (((Comparable) valorCampoInicial).compareTo(valorCampoFinal) > 0)
						{
							Exception exception = new Exception("No campo <b>" + alias + "</b> o valor final deve ser maior ou igual ao valor inicial!");
							retorno = exception.getMessage();
						}
					}
				}
				catch (InvocationTargetException e)
				{
					erro = "Problemas para acessar um dos atributos: " + campos[0] + " ou " + campos[1] + " da classe: "
							+ objeto.getClass().toString();
				}
				catch (NoSuchMethodException e)
				{
					erro = "Atributo: " + campos[0] + " ou " + campos[1] + " não encontrado na classe: "
							+ objeto.getClass().toString();
				}
				catch (IllegalAccessException e)
				{
					erro = "Sem acesso ao atributo: " + campos[0] + " ou " + campos[1] + " da classe: "
							+ objeto.getClass().toString();
				}
			}
		}
		else
		{
			erro = "Deve ser passado dois nomes de campos separados por ;";
		}

		if (erro != null)
		{
			retorno = erro;
		}
		return retorno;
	}

}
