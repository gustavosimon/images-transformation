package pdi.utils;

import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.*;
import static pdi.utils.ImageConstants.*;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import pdi.Image;

/**
 * Classe utilitária com operações para manipular imagens.
 * 
 * @author Felipe Ramon Wozniak, Gustavo André Simon e Pedro Kassick Soares
 */
public class ImageUtils {
    
    /** Construtor privado para garantir que não existam instâncias dessa classe */
    private ImageUtils() {}
    
    /**
     * Escala a imagem dobrando de tamanho
     */
    public static void writeScaleUpImage(){
        Optional<Image> optOriginal = getImageFromFile();
        if (optOriginal.isEmpty()) {
            return;
        }
        Image original = optOriginal.get();
        BufferedImage processed = new BufferedImage(original.getWidth(), original.getHeight(), TYPE_INT_RGB);
        int[][] imagePixels = original.getMatrix();
        int aux = 2;
        int npx = 0;
        int npy = 0;
        for(int j=0;j< original.getHeight(); j++){
            for (int i = 0; i < original.getWidth(); i++) {
                npx = aux * i;
                npy = aux * j;
                //acredito que precise de um for aqui para popular os pixels anteriores da matriz
                processed.setRGB(npx, npy, imagePixels[i][j]);
            }
        }
    }
    
    
    /**
     * Grava a imagem de forma espelhada horizontalmente.
     */
    public static void writeHorizontallyMirrorImage() {
        Optional<Image> optOriginal = getImageFromFile();
        if (optOriginal.isEmpty()) {
            return;
        }
        Image original = optOriginal.get();
        BufferedImage processed = new BufferedImage(original.getWidth(), original.getHeight(), TYPE_INT_RGB);
        int[][] imagePixels = original.getMatrix();
        int aux;
        for (int j = 0; j < original.getHeight(); j++) {
            aux = original.getWidth() - 1;
            for (int i = 0; i < original.getWidth(); i++) {
                processed.setRGB(i, j, imagePixels[aux--][j]);
            }
        }
        writeImage(processed);
    }

    /**
     * Grava a imagem de forma espelhada verticalmente.
     */
    public static void writeVerticallyMirrorImage() {
        Optional<Image> optOriginal = getImageFromFile();
        if (optOriginal.isEmpty()) {
            return;
        }
        Image original = optOriginal.get();
        BufferedImage processed = new BufferedImage(original.getWidth(), original.getHeight(), TYPE_INT_RGB);
        int[][] imagePixels = original.getMatrix();
        for (int i = 0; i < original.getWidth(); i++) {
            int aux = original.getHeight() - 1;
            for (int j = 0; j < original.getHeight(); j++) {
                processed.setRGB(i, j, imagePixels[i][aux--]);
            }
        }
        writeImage(processed);
    }

    /**
     * Grava a imagem deslocada. 
     */
    public static void writeMovedImage() {
        Optional<Image> optOriginal = getImageFromFile();
        if (optOriginal.isEmpty()) {
            return;
        }
        Image original = optOriginal.get();
        BufferedImage processed = new BufferedImage(original.getWidth(), original.getHeight(), TYPE_INT_RGB);
        int[][] imagePixels = original.getMatrix();
        for (int i = 0; i < original.getWidth() - 20; i++) {
            for (int j = 0; j < original.getHeight() - 40; j++) {
                processed.setRGB(i + 20, j + 40, imagePixels[i][j]);
            }
        }
        writeImage(processed);
    }

    /**
     * Retorna matriz com valores RGB a partir da imagem original.
     * 
     * <p>
     * A imagem original se encontra em resources.
     * 
     * @return int[][] matriz com valores RGB
     * @throws IOException
     */
    private static Optional<Image> getImageFromFile() {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(ORIGINAL_IMAGE_PATH));
        } catch (Exception e) {
            return Optional.empty();
        }
        int width = bufferedImage.getWidth(null);
        int height = bufferedImage.getHeight(null);
        int[][] pixels = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = bufferedImage.getRGB(i, j);
            }
        }
        return Optional.of(new Image(pixels, width, height));
    }

    /**
     * Grava a imagem processada com o nome padrão.
     * 
     * @param imageToWrite imagem bufferizada para gravação
     */
    private static void writeImage(BufferedImage imageToWrite) {
        File outputfile = new File(PROCESSED_IMAGE_PATH);
        try {
            ImageIO.write(imageToWrite, IMAGE_EXTENSION, outputfile);
        } catch (IOException e) {
            System.out.println("Falha ao gravar a imagem processada" + e.toString());
        }
    }
    
}
