package br.com.cdtec.framework.service;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

@SuppressWarnings("restriction")
public class UtilService
{
   public static Field getField(Class<?> classeEntidade, String propriedade) throws NoSuchFieldException, SecurityException
   {
      Field retorno = null;
      String[] subPropriedades = propriedade.split("\\.");
      if (subPropriedades != null && subPropriedades.length > 0)
      {
         Class<?> classeAtual = classeEntidade;
         for (int i = 0; i < subPropriedades.length; i++)
         {
            try
            {
               retorno = classeAtual.getDeclaredField(subPropriedades[i]);
            }
            catch (NoSuchFieldException nsf)
            {
               if (!classeAtual.getSuperclass().equals(Object.class))
               {
                  classeAtual = classeAtual.getSuperclass();
                  retorno = classeAtual.getDeclaredField(subPropriedades[i]);
               }
               else
               {
                  throw nsf;
               }
            }
            classeAtual = retorno.getType();

            Type type = retorno.getGenericType();
            if (type != null && type instanceof ParameterizedTypeImpl)
            {
               ParameterizedTypeImpl parameterizedTypeImpl = (ParameterizedTypeImpl) type;
               if (parameterizedTypeImpl.getActualTypeArguments() != null && parameterizedTypeImpl.getActualTypeArguments().length > 0)
               {
                  classeAtual = (Class<?>) parameterizedTypeImpl.getActualTypeArguments()[0];
               }
            }
         }
      }
      return retorno;
   }

   public static Field getSimpleField(Class<?> classeEntidade, String propriedade) throws SecurityException
   {
      String[] subPropriedades = propriedade.split("\\.");
      Field retorno = null;
      if (subPropriedades != null && subPropriedades.length > 0)
      {
         Class<?> classeAtual = classeEntidade;
         for (int i = 0; i < subPropriedades.length; i++)
         {
            retorno = null;
            while (!classeAtual.equals(Object.class) && retorno == null)
            {
               try
               {
                  retorno = classeAtual.getDeclaredField(subPropriedades[i]);
               }
               catch (NoSuchFieldException nsf)
               {
                  classeAtual = classeAtual.getSuperclass();
               }
            }
         }
      }
      return retorno;
   }

}
