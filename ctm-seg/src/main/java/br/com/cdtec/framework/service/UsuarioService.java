package br.com.cdtec.framework.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cdtec.framework.model.entity.Usuario;
import br.com.cdtec.framework.repository.UsuarioRepository;


@Service
public class UsuarioService extends CrudService<Usuario, BigInteger, UsuarioRepository> {

	private static final long serialVersionUID = 1L;
	
	public static final Integer PERFIL_OPERADOR = 1;
	
//	@Autowired
//    private JavaMailSender emailSender;
//	
//	@Autowired
//    private SpringTemplateEngine templateEngine;
	
	@Value("${controlme.url}")
	private String urlConfirmaCadastro;
	
//	@Autowired
//	public TokenCadastroService tokenCadastroService;
		

	public Usuario pesquisarPorLogin(String dsLogin) {
		Usuario usuario = getRepository().pesquisarPorLogin(dsLogin);
		return usuario;
	}
	
	@Override
	public void validarInserir(Usuario usuario) throws Exception {
		
		Integer quantidade = getRepository().quantidadePorEmail(usuario.getDsEmail());
		
		if(quantidade != null
				&& quantidade > 0) {
			throw new Exception("Já existe um usuário registrado com este e-mail.");
		}
		
	}
		
	@Override
	@Transactional
	public Usuario inserir(Usuario usuario) throws Exception {
		
		validarInserir(usuario);		
		getRepository().save(usuario);
		//enviarEmailValidacaoCadastro(usuario);
		return usuario;		
		
	}
	
//	@Transactional
//	public void enviarEmailValidacaoCadastro(Usuario usuario) throws Exception {
//		
//		//GERAR TOKEN
//		String token = CriptoMD5.stringHexa(usuario.getIdUsuario()+usuario.getDsEmail()+"controlme"+usuario.getIdUsuario());
//		
//		//INSERIR TOKEN NO BANCO
//		TokenCadastro tokenCadastro = new TokenCadastro();
//		tokenCadastro.setIdUsuario(usuario.getIdUsuario());
//		tokenCadastro.setDsToken(token);
//		tokenCadastro.setFgUsado(false);
//		
//		tokenCadastroService.inserir(tokenCadastro);
//		
//		//MONTAR URL
//		String link = this.urlConfirmaCadastro + "?id=" + usuario.getIdUsuario() + "&token=" + token;
//		
//		//ENVIAR EMAIL COM LINK DE CADASTRO
//		MimeMessage message = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message,
//                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                StandardCharsets.UTF_8.name());
//
//
//        Context context = new Context();
//        context.setVariable("nome", usuario.getDsNome());
//        context.setVariable("link", link);
//        String html = templateEngine.process("templateEmailCadastro", context);
//
//        helper.setTo(usuario.getDsEmail());
//        helper.setText(html, true);
//        helper.setSubject("Cadastro - ControlMe");
//        helper.setFrom("ControlMe");
//
//        emailSender.send(message);
//	}

}