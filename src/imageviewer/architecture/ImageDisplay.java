package imageviewer.architecture;

public interface ImageDisplay {
    Image current();
    void show(Image image);
    
}
