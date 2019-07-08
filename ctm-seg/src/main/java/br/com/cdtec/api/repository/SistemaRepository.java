package br.com.cdtec.api.repository;

import java.math.BigInteger;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cdtec.api.model.entity.Sistema;
import br.com.cdtec.framework.repository.GenericRepository;


@Lazy(true)
public interface SistemaRepository extends GenericRepository<Sistema, BigInteger>  {
	
	@Query("SELECT s FROM Sistema s WHERE s.dsSistema = :dsSistema")
	Sistema pesquisarPorDescricao(@Param("dsSistema") String dsSistema);
	
	@Query("SELECT count(s) FROM Sistema s WHERE s.dsSistema = :dsSistema")
	Integer quantidadePorDescricao(@Param("dsSistema") String dsSistema);
	
	@Query("SELECT count(s) FROM Sistema s WHERE s.dsSistema = :dsSistema AND s.idSistema <> :idSistema")
	Integer quantidadePorDescricao(@Param("dsSistema") String dsSistema, @Param("idSistema") BigInteger idSistema);
	
}