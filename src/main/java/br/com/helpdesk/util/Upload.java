package br.com.helpdesk.util;

public class Upload {

	// public static boolean fazerUploadImagem(MultipartFile imagem) {
	// boolean sucessoUpload = false;
	// //se a imagem for diferente de vazio
	// if (!imagem.isEmpty()) {
	// String nomeArquivo = imagem.getOriginalFilename();
	// try {
	// // Criando o diretório para armazenar o arquivo
	// String workspaceProjeto = "C:\\Users\\Admin\\Desktop\\Atual-help desk
	// back-end\\src\\main\\resources\\static\\img";
	// File dir = new File(workspaceProjeto);
	// if (!dir.exists()) {
	// dir.mkdirs();
	// }
	// // Criando o arquivo no diretório
	// File serverFile = new File(dir.getAbsolutePath() + File.separator +
	// nomeArquivo);
	// BufferedOutputStream stream = new BufferedOutputStream(new
	// FileOutputStream(serverFile));
	// stream.write(imagem.getBytes());
	// stream.close();
	// System. out .println("Arquivo armazenado em:" +
	// serverFile.getAbsolutePath());
	// System. out .println("Você fez o upload do arquivo " + nomeArquivo + " com
	// sucesso");
	// sucessoUpload = true;
	// } catch (Exception e) {
	// System. out .println("Você falhou em carregar o arquivo " + nomeArquivo + "
	// => " + e.getMessage());
	// }
	// } else {
	// System. out .println("Você falhou em carregar o arquivo porque ele está vazio
	// ");
	// }
	// return sucessoUpload;
	// }

}
