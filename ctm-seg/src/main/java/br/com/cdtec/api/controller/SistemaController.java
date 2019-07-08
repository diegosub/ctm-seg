package br.com.cdtec.api.controller;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdtec.api.model.dto.SistemaDTO;
import br.com.cdtec.api.model.entity.Sistema;
import br.com.cdtec.api.service.SistemaService;
import br.com.cdtec.framework.controller.CrudController;
import br.com.cdtec.framework.service.validacao.CatalogoValidadores;
import br.com.cdtec.framework.service.validacao.Validacao;

@RestController
@RequestMapping("/api/seguranca/sistema")
@CrossOrigin(origins = "*")
public class SistemaController extends CrudController<Sistema, BigInteger, SistemaService>
{
	private static final long serialVersionUID = 1L;

	public SistemaController()
	{
		adicionarValidacao("dsSistema", "Descrição", CatalogoValidadores.OBRIGATORIO, Validacao.ACAO_INCLUIR);
	}
	
	@Override
	protected void atualizarStatusEntidade(Sistema entity, Boolean status)
	{
		//entity.setFgAtivo(status);
		entity.setDtAlteracao(new Date());
	}

	@Override
	protected List<Object> atualizarListaResponse(List<Sistema> lista)
	{
		return lista.stream().map(sistema -> convertToDto(sistema)).collect(Collectors.toList());
	}
	
	@Override
	protected Object atualizarEntityResponse(Sistema entity)
	{		
		return this.convertToDto(entity);
	}

	private SistemaDTO convertToDto(Sistema sistema)
	{
		SistemaDTO dto = new SistemaDTO();
		modelMapper.map(sistema, dto);

		return dto;
	}

}
