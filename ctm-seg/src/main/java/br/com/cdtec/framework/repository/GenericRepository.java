package br.com.cdtec.framework.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interface utilizada para marcar os repositorios, 
 * usada para simplificar os extends das interfaces necessarias
 * para criação de um repositorio com Spring Data
 *
 * @param <Entity>
 * @param <Serializable>
 */
@Lazy(true)
public interface GenericRepository<Entity, Serializable extends java.io.Serializable> extends JpaRepository<Entity, Serializable>, JpaSpecificationExecutor<Entity> {

}