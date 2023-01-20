package imageviewer.skin;

import imageviewer.architecture.Image;
import imageviewer.architecture.ImageLoader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Set;

public class FileImageLoader implements ImageLoader {

    private final File folder;
    private final File[] files;

    public FileImageLoader(File folder) {
        this.folder = folder;
        this.files = folder.listFiles(this::filterImage);
    }
    
    private final static Set<String> ImageExtensions = imageExtensions();
    private boolean filterImage(File folder, String filename) {
        return ImageExtensions.contains(extensionOf(filename));
    }

    private String extensionOf(String filename) {
        return filename.substring(filename.lastIndexOf(".")+1);
    }
    
    @Override
    public Image load() {
        return imageAt(0);
    }
    
    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String path() {
                return files[i].getAbsolutePath();
            }

            @Override
            public Image next() {
                return i == files.length - 1 ? imageAt(0) : imageAt(i+1);
            }

            @Override
            public Image prev() {
                return i == 0 ? imageAt(files.length-1) : imageAt(i-1);
            }
        };
    }
    
    private static Set<String> imageExtensions() {
        HashSet<String> set = new HashSet<String>();
        set.add("jpg");
        set.add("jpeg");
        set.add("png");
        return set;
    }

    
}
