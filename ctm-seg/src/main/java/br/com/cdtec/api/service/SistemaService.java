package br.com.cdtec.api.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import br.com.cdtec.api.model.entity.Sistema;
import br.com.cdtec.api.repository.SistemaRepository;
import br.com.cdtec.framework.service.CrudService;

@Service
public class SistemaService extends CrudService<Sistema, BigInteger, SistemaRepository>
{

	private static final long serialVersionUID = 1L;

	@Override
	public void validarInserir(Sistema entity) throws Exception
	{
		Integer quantidade = getRepository().quantidadePorDescricao(entity.getDsSistema());

		if (quantidade != null && quantidade > 0)
		{
			throw new Exception("Já existe um sistema cadastrado com esta descrição.");
		}
	}

	@Override
	public void validarAlterar(Sistema entity) throws Exception
	{
		Integer quantidade = getRepository().quantidadePorDescricao(entity.getDsSistema(), entity.getIdSistema());

		if (quantidade != null && quantidade > 0)
		{
			throw new Exception("Já existe um sistema cadastrado com esta descrição.");
		}
	}

}