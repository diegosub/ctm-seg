package br.com.cdtec.framework.service;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import br.com.cdtec.framework.repository.GenericRepository;

/**
 * Classe extenda o CrudService deve ser annotada com @Transaction apontando
 * para o transctionManager que deseja usar na classe, caso não seja annotada
 * com @Transaction, os metodos não irão abrir transação a não ser que já
 * estejam incluidos em uma transação
 */
public abstract class CrudService<Entity, IdClass extends Serializable, Repository extends GenericRepository<Entity, IdClass>>
      extends GenericService<Entity, IdClass>
{

   private static final long serialVersionUID = 1L;

   @Autowired
   private Repository repository;

   @Transactional
   public Optional<Entity> get(IdClass id) throws Exception
   {

      Optional<Entity> retorno = getRepository().findById(id);
      return retorno;
   }

   @Transactional
   public Entity inserir(Entity entity) throws Exception
   {
      validarInserir(entity);
      completarInserir(entity);
      getRepository().save(entity);
      return entity;
   }

   @Transactional
   public Entity alterar(Entity entity) throws Exception
   {
      validarAlterar(entity);
      completarAlterar(entity);
      getRepository().save(entity);
      return entity;
   }

   @Transactional
   public Page<Entity> listarTodos(int page, int count, Sort sort) throws Exception
   {
      Pageable pages = PageRequest.of(page, count, sort);
      return getRepository().findAll(pages);
   }

   @Override
   @Transactional
   public List<Entity> pesquisar(Entity entity, String[] juncoes) throws Exception
   {
	  Specification<Entity> specJuncoes = this.aplicarJuncoes(entity, new String[] {""});
	 // Specification<Entity> specFiltros = this.aplicarFiltrosDefult(entity, new String[] {""});
	  
      return null;
   }

   // SOBRESCREVER O METODO NO SERVICE PARA REALIZAR A PESQUISA
   @Transactional
   public List<Entity> implementarPesquisar(Entity entity, Sort sort) throws Exception
   {
      return null;
   }

   public void executeComando(Session session, final String cmdSql) throws Exception
   {
      session.doWork(new Work()
      {
         @Override
         public void execute(Connection conn) throws SQLException
         {
            CallableStatement cs = conn.prepareCall("{call " + cmdSql + "}");
            cs.execute();
            cs.close();
         }
      });
   }

   public void completarInserir(Entity entity) throws Exception
   {
   }

   public void completarAlterar(Entity entity) throws Exception
   {
   }

   public void validarInserir(Entity entity) throws Exception
   {
   }

   public void validarAlterar(Entity entity) throws Exception
   {
   }
   
   private Specification<Entity> aplicarJuncoes(Object entity, String[] juncoes)
   {
      Map<String, String> juncoesAplicadas = null;
      Specification<Entity> spec = null;
      
      if (juncoes != null && juncoes.length > 0)
      {
         juncoesAplicadas = new HashMap<String, String>();
         for (int i = 0; i < juncoes.length; i++)
         {
            String juncaoAtual = juncoes[i];
            if (juncaoAtual != null && juncaoAtual.trim().length() > 0)
            {
               String[] subJuncoesAtual = juncaoAtual.split("\\.");
               String subJuncaoAtual = null;
               for (int j = 0; j < subJuncoesAtual.length; j++)
               {
                  if (j == 0)
                  {
                     subJuncaoAtual = subJuncoesAtual[j];
                  }
                  else
                  {
                     subJuncaoAtual = subJuncaoAtual + "." + subJuncoesAtual[j];
                  }

                  boolean asterisco = subJuncoesAtual[j].substring(subJuncoesAtual[j].length() - 1).equals("*");
                  if (asterisco)
                  {
                     subJuncaoAtual = subJuncaoAtual.substring(0, subJuncaoAtual.length() - 1);
                  }

                  try
                  {
                     boolean embedded = false;
                     boolean oneToMany = false;

                     Field field = UtilService.getField(entity.getClass(), subJuncaoAtual);

                     Annotation[] annotations = field.getAnnotations();
                     if (annotations != null && annotations.length > 0)
                     {
                        for (int k = 0; k < annotations.length; k++)
                        {
                           if (annotations[k].annotationType().equals(Embedded.class))
                           {
                              embedded = true;
                           }
                           else if (annotations[k].annotationType().equals(OneToMany.class))
                           {
                              oneToMany = true;
                           }
                        }
                     }

                     if (!juncoesAplicadas.containsKey(subJuncaoAtual) && !embedded)
                     {
                        if (asterisco || oneToMany)
                        {
                           spec = this.leftJoin(subJuncaoAtual);
                        }
                        else
                        {
                           spec = this.innerJoin(subJuncaoAtual);
                        }
                        
                        juncoesAplicadas.put(subJuncaoAtual, subJuncaoAtual.replaceAll("\\.", ""));
                     }
                  }
                  catch (NoSuchFieldException e)
                  {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
                  catch (SecurityException e)
                  {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }

               }
            }
         }
      }

      return spec;
   }
   
   @SuppressWarnings("serial")
   private Specification<Entity> innerJoin(String juncao)
   {
	   return new Specification<Entity>()
	   {
		   public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder cb)
	       {			   
			   root.fetch(juncao, JoinType.INNER);
	           query.distinct(true);	           
	           return null;
	       } 
	   };
   }

   @SuppressWarnings("serial")
   private Specification<Entity> leftJoin(String juncao)
   {
	   return new Specification<Entity>()
	   {
		   public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder cb)
	       {			   
			   root.fetch(juncao, JoinType.LEFT);
	           query.distinct(true);	           
	           return null;
	       } 
	   };
   }

   
   public Repository getRepository()
   {
      return repository;
   }

   public void setRepository(Repository repository)
   {
      this.repository = repository;
   }
}