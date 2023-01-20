package imageviewer.architecture;

public class NextCommand implements Command {
    private final ImageDisplay display;

    public NextCommand(ImageDisplay display) {
        this.display = display;
    }
    
    @Override
    public void execute() {
        display.show(display.current().next());
    }
    
}
