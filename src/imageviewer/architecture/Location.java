package imageviewer.architecture;

public class Location {
    private int x;
    private int y;
    private int width;
    private int height;
    
    public static Location with(int width, int height) {
        return new Location(width, height);
    }

    private Location(int width, int height) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
    }

    public Location calculate(int width, int height) {
        if (height < this.height || width < this.width) {
            double imageRatio = (double) this.width / this.height;
            if (width < this.width) {
                this.width = width;
                this.height = (int) (width / imageRatio);
            }
            else {
                this.width = (int) (height * imageRatio);
                this.height = height;
            }
            
        }
        x = (width - this.width) / 2;
        y = (height - this.height) / 2;
        return this;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
    
    
    
    
    
}
