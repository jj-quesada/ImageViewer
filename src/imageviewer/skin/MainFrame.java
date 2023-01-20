package imageviewer.skin;

import imageviewer.architecture.Command;
import imageviewer.architecture.Image;
import imageviewer.architecture.ImageDisplay;
import imageviewer.architecture.Location;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private final ImagePanel imagePanel;
    private final Map<String,Command> commands;

    public MainFrame() throws IOException {
        this.commands = new HashMap<>();
        this.setTitle("Image Viewer");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(imagePanel = new ImagePanel());
        this.add(button("prevImage"), BorderLayout.WEST);
        this.add(button("nextImage"), BorderLayout.EAST);
    }

    private static final Map<String, String> icons = icons(); 
    private Component button(String name) {
        JButton button = new JButton(icons.get(name));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }
        });
        return button;
    }

    public ImageDisplay imageDisplay() {
        return imagePanel;
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }
    
    public class ImagePanel extends JPanel implements ImageDisplay {
        private Image current;
        private BufferedImage image;

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());
            Location location = Location.with(image.getWidth(), image.getHeight())
                    .calculate(getWidth(), getHeight());
            g.drawImage(image, location.x(), location.y(), location.width(), location.height(), null);
        }

        @Override
        public Image current() {
            return this.current;
        }

        @Override
        public void show(Image image) {
            this.current = image;
            this.image = load(current);
            repaint();
        }

        private BufferedImage load(Image current) {
            try {
                return ImageIO.read(new File(current.path()));
            }
            catch (IOException e) {
                return null;
            }
        }
        
    
    }
    
    
    private static Map<String, String> icons() {
        Map<String, String> result = new HashMap<>();
        result.put("prevImage", "<");
        result.put("nextImage", ">");
        return result;
    }
    
    
}
