package br.com.cdtec.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

/**
 * Classe que extender o GenericService deve ser annotada com @Transaction
 * apontando para o transctionManager que deseja usar na classe,
 * caso não seja annotada com @Transaction, os metodos não irão abrir transação 
 * a não ser que já estejam incluidos em uma transação 
 */
public abstract class GenericService<Entity, IdClass> implements Serializable{
	
	private static final long serialVersionUID = 8402381475468042717L;
	
	public abstract Optional<Entity> get(IdClass id) throws Exception;
			
	public abstract Entity inserir(Entity entity) throws Exception;
	
	public abstract Entity alterar(Entity entity) throws Exception;
	
	public abstract List<Entity> pesquisar(Entity entity, String[] juncoes) throws Exception;
	
}