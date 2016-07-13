package br.ufc.Util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class JornalFileUtil {

	public static void salvarImagem(String path, MultipartFile imagem){
		
		File file = new File(path);
		try {
			FileUtils.writeByteArrayToFile(file, imagem.getBytes());
			System.out.println("SALVO EM: "+file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void removerArquivos(String path) {
		File f = new File(path);
		try {
			f.delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
