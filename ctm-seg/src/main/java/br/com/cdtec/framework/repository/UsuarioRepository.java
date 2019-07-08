package br.com.cdtec.framework.repository;

import java.math.BigInteger;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cdtec.framework.model.entity.Usuario;


@Lazy(true)
public interface UsuarioRepository extends GenericRepository<Usuario, BigInteger>  {
	
	@Query("SELECT u FROM Usuario u WHERE u.dsEmail = :dsEmail")
	Usuario pesquisarPorLogin(@Param("dsEmail") String dsEmail);
	
	@Query("SELECT count(u) FROM Usuario u WHERE u.dsEmail = :dsEmail")
	Integer quantidadePorEmail(@Param("dsEmail") String dsEmail);
}