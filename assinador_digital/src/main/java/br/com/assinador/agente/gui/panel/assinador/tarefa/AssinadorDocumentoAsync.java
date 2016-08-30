package br.com.assinador.agente.gui.panel.assinador.tarefa;

import java.io.File;
import java.util.Map;
import java.util.Set;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.Logger;
import br.com.assinador.agente.gui.popup.ProgressDialog;
import br.com.assinador.agente.io.FileExtension;
import br.com.assinador.agente.io.FileHandler;
import br.com.assinador.agente.multithread.ExecucaoException;
import br.com.assinador.agente.multithread.Tarefa;
import br.com.assinador.agente.multithread.TipoTarefa;
import br.com.assinador.bc.AssinaturaDigitalService;

public class AssinadorDocumentoAsync extends Tarefa<Void>{

	private String senha; 
	private Logger logger = Contexto.getLogger();
	private ProgressDialog progressDialog;
	
	public AssinadorDocumentoAsync(String senha, ProgressDialog progressDialog) {
		super(TipoTarefa.ASSINATURA_DOCUMENTO);
		this.senha = senha;
		this.progressDialog = progressDialog;
	}

	protected Void executar(Map<Object, Object> contextoTarefa) throws ExecucaoException{
		processar(senha);
		return null;
	}
	
	private void processar(String senha) throws ExecucaoException {
		try{
			Contexto.getMainWindow().setEnabled(false);
			
			FileHandler fh = new FileHandler();
			Set<File> arquivos = Contexto.getAtributo(Constantes.DOCUMENTOS_ASSINATURA_SELECIONADOS);
			
			progressDialog.setLimites(0, arquivos.size());
			AssinaturaDigitalService assinador = new AssinaturaDigitalService(senha);
			for (File f: arquivos){
				progressDialog.progredir(f.getName() + " ...", 1);
				assinar(fh, f, assinador);
			}
			
		}catch(Exception e){
			logger.log(e);
			progressDialog.dispose();
			throw new ExecucaoException("Falha na assinatura do documento \n" + e.getMessage(), e, true);
		}finally {
			Contexto.getMainWindow().setEnabled(true);
		}
	}
	
	private void assinar(FileHandler fh, File arquivo, AssinaturaDigitalService assinador) throws Exception{
		byte[] conteudoAssinado = assinador.assinar(fh.readFile(arquivo));
		assinador.verificarAssinatura(conteudoAssinado);
		
		File diretorioSelecionado = Contexto.getAtributo(Constantes.DIRETORIO_ASSINATURA_DESTINO_SELECIONADO);
		fh.writeToFile(diretorioSelecionado, arquivo.getName(), FileExtension.P7S, conteudoAssinado);
	}

}
