package br.com.cdtec.framework.service.validacao;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;

public class PeloMenosUm implements Validador
{
	private static PeloMenosUm instancia;

	private PeloMenosUm()
	{
	}

	public static PeloMenosUm getInstancia()
	{
		if (instancia == null)
		{
			instancia = new PeloMenosUm();
		}
		return instancia;
	}

	@Override
	public void validar(Object objeto, String campo, String alias) throws Exception
	{
	}

	@Override
	public String validarAction(Object objeto, String campo, String alias)
	{
		boolean existeAlgumPreenchido = false;
		boolean jaEhNulo = false;
		String erro = null;
		String[] campos = campo.split(";");
		String retorno = null;
		Object objetoAtual = null;
		if (campos != null && campos.length > 0)
		{
			for (int i = 0; i < campos.length; i++)
			{
				jaEhNulo = false;
				Object valorCampo = null;
				String nomePropriedade = "";
				objetoAtual = objeto;
				try
				{
					if (campos[i].indexOf(".") > 0)
					{
						String[] subCampos = campos[i].split("\\.");
						if (subCampos != null && subCampos.length > 1)
						{
							for (int j = 0; j < subCampos.length - 1; j++)
							{
								if (nomePropriedade.equals(""))
								{
									nomePropriedade = subCampos[j];
								}
								else
								{
									nomePropriedade = nomePropriedade + "." + subCampos[j];
								}
								valorCampo = PropertyUtils.getProperty(objetoAtual, nomePropriedade);
								if (valorCampo == null)
								{
									jaEhNulo = true;
									break;
								}
								else
								{
									if (valorCampo instanceof Collection<?>)
									{
										Collection<?> collection = (Collection<?>) valorCampo;
										if (collection.size() > 0)
										{
											objetoAtual = collection.iterator().next();
											nomePropriedade = "";
										}
										else
										{
											jaEhNulo = true;
											break;
										}
									}
								}
							}
						}

						if (nomePropriedade.equals(""))
						{
							nomePropriedade = subCampos[subCampos.length - 1];
						}
						else
						{
							nomePropriedade = nomePropriedade + "." + subCampos[subCampos.length - 1];
						}
					}
					else
					{
						nomePropriedade = campos[i];
					}

					if (!jaEhNulo)
					{
						valorCampo = PropertyUtils.getProperty(objetoAtual, nomePropriedade);

						if (valorCampo != null && !"".equals(valorCampo.toString().trim()))
						{
							existeAlgumPreenchido = true;
							break;
						}
					}
				}
				catch (InvocationTargetException e)
				{
					erro = "Problemas para acessar o atributo: " + campos[i] + " da classe: "
							+ objeto.getClass().toString();
					break;
				}
				catch (NoSuchMethodException e)
				{
					erro = "Atributo: " + campos[i] + " não encontrado na classe: " + objeto.getClass().toString();
					break;
				}
				catch (IllegalAccessException e)
				{
					erro = "Sem acesso ao atributo: " + campos[i] + " da classe: " + objeto.getClass().toString();
					break;
				}
			}
		}
		else
		{
			erro = "Deve ser passado um ou mais nomes de campos separados por ;";
		}

		if (erro != null)
		{
			retorno = erro;
		}
		else
		{
			if (!existeAlgumPreenchido)
			{
				Exception aptareException = new Exception(
						"Pelo menos um dos campos <b> " + alias + " </b> é obrigatório.");
				retorno = aptareException.getMessage();
			}
		}

		return retorno;
	}

}
