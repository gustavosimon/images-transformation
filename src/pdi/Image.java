package pdi;

/**
 * Classe representando a imagem que deve ser processada.
 * 
 * @author Felipe Ramon Wozniak, Gustavo André Simon e Pedro Kassick Soares
 */
public final class Image {
    
    /** Matriz da imagem */
    private final int[][] matrix;
    /** Largura da imagem */
    private final int width;
    /** Altura da imagem */
    private final int height;

    public Image(int[][] matrix, int width, int height) {
        this.matrix = matrix;
        this.width = width;
        this.height = height;
    }

    /**
     * Retorna a matriz da imagem.
     * 
     * @return {@code matrix}
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Retorna a largura da imagem.
     * 
     * @return {@code width}
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retorna a altura da imagem.
     * 
     * @return {@code height}
     */
    public int getHeight() {
        return height;
    }

}
