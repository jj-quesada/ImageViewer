package imageviewer.architecture;

public interface Image {
    String path();
    Image next();
    Image prev();    
}
