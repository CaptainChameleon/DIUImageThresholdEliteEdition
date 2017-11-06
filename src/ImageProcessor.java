
import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.core.image.ConvertImage;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;
import java.awt.image.BufferedImage;

/**
 *
 * @author Gabrilocuántico
 */
public class ImageProcessor {
    
    public static BufferedImage applyThreshold (BufferedImage image, int threshold) {
        
        //convierte la imagen en color BufferedImage en formato de la librería BoofCV
        Planar<GrayU8> imagenColor = ConvertBufferedImage.convertFromPlanar(image,
                null, true, GrayU8.class);

        // crea dos imágenes en niveles de grises
        GrayU8 imagenGris = new GrayU8(image.getWidth(), image.getHeight());
        GrayU8 imagenUmbralizada = new GrayU8(image.getWidth(), image.getHeight());

        // Convierte a niveles de gris la imagen de entrada
        ConvertImage.average(imagenColor, imagenGris);

        // umbraliza la imagen:  
        // - píxeles con nivel de gris > umbral se ponen a 1
        // - píxeles con nivel de gris <= umbra se ponen a 0
        GThresholdImageOps.threshold(imagenGris, imagenUmbralizada, threshold, false);

        image = VisualizeBinaryData.renderBinary(imagenUmbralizada, false, null);
        
        return image;
    }
    
}
