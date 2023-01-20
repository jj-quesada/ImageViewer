package imageviewer.skin;

import imageviewer.architecture.Image;
import imageviewer.architecture.NextCommand;
import imageviewer.architecture.PrevCommand;
import imageviewer.skin.FileImageLoader;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Image image = new FileImageLoader(new File("images")).load();
        MainFrame mainFrame = new MainFrame();
        mainFrame.imageDisplay().show(image);
        mainFrame.add("prevImage", new PrevCommand(mainFrame.imageDisplay()));
        mainFrame.add("nextImage", new NextCommand(mainFrame.imageDisplay()));
        mainFrame.setVisible(true);
    }
    
}
