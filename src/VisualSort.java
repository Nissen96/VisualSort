import javax.swing.*;
import java.awt.*;

abstract public class VisualSort extends JFrame {
    // Each sorter should include this method
    abstract void doSort(int[] array);

    // Current state of array to draw
    private int[] currentArray;

    //private float[][] imgArray;
    //private BufferedImage img;

    // Drawing options
    private int width;
    private int height;
    private float hue;
    private float saturation;
    private int delay;
    private int currentIndex;
    private int buffer;
    private int frame;

    //private TonePlayer tonePlayer;

    /**
     * Construct the JFrame for the sorting animation.
     */
    VisualSort(String title) {
        super(title);

        // Get windows size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = screenSize.width - 100;
        this.height = screenSize.height - 100;

        // Store parameters
        this.hue = (float) Math.random();
        this.saturation = (float) Math.random();
        this.delay = 1;
        this.currentIndex = -1;
        this.buffer = 1;
        this.frame = 0;

        //this.tonePlayer = new TonePlayer();

        /*
        try {
            img = ImageIO.read(new File("img/Mona_Lisa.jpg"));
        } catch (IOException e) {e.printStackTrace();}

        int size = 64;
        this.imgArray = new float[size * size][3];
        int i = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int rgb = img.getRGB(x, y);
                float[] hsb = new float[3];
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb) & 0xFF;
                Color.RGBtoHSB(r, g, b, hsb);
                imgArray[i] = hsb;
                i++;
            }
        }
        */
        // Create window
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set window options
        setVisible(true);
        setResizable(false);
    }

    /**
     * Setter for delay
     * @param delay - New delay
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }

    /**
     * Helper method to sort out of place as default
     * @param array - Array to sort
     * @return sorted array
     */
    public int[] sort(int[] array) {
        if (array.length == 0) return array;
        return sort(array, false);
    }

    /**
     * Sort the input array with the extension's sorting algorithm
     * @param array - Array to sort
     * @param inPlace - Whether or not to solve array in place
     * @return sorted array
     */
    public int[] sort(int[] array, boolean inPlace) {
        if (array.length == 0) return array;

        // Make a copy if array should not be sorted in place
        if (!inPlace) {
            array = Util.arrayCopy(array);
        }

        // Call the sorting method of the sorter (any VisualSort extension)
        doSort(array);

        // Display that the array is sorted
        displaySorted(array);
        return array;
    }

    /**
     * Displays a black bar running through the bars from the bottom and up
     * This visualizes that the sorting algorithm has finished
     * @param array - The sorted array
     */
    private void displaySorted(int[] array) {
        // Save the currently set delay
        int curDelay = this.delay;

        // Set delay to make the visualization last for 3 seconds
        this.delay = 3000 / array.length;

        // Visualize through each array index
        for (int i = 0; i < array.length; i++) {
            visualize(array, i);
        }
        // Remove the black bar
        visualize(array);

        // Reset delay
        this.delay = curDelay;
    }

    /**
     * Draw the visualization of the array
     * Wait for an optional amount of time
     * @param array - Array to visualize
     * @param curIdx - Index of the element to display a black bar at
     */
    void visualize(int[] array, int curIdx) {
        frame++;
        if (frame % buffer == 0) {
            // Call paint() with the input options
            this.currentArray = array;
            this.currentIndex = curIdx;
            repaint();

            // Optional delay
            try {
                Thread.sleep(delay, 0);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Visualization method with curIdx set to -1
     * This means no black bar will be displayed and is default
     * @param array - Array to visualize
     */
    void visualize(int[] array) {
        visualize(array, -1);
    }

    /**
     * Draw each value in the array as a bar with its value as its height
     * This corresponds to a single frame in the visualization of the sorting steps
     */
    public void paint(Graphics g) {
        // Return if array hasn't been set yet
        if (currentArray == null) return;

        // Calculate size of each coloured square
        int numElements = currentArray.length;
        int squareWidth = (width - 100) / numElements;
        int squareHeight = (height - 100) / numElements;
        int colHeight;

        // Go to each index in the array, and fill a bar
        // with the height and brightness representing the magnitude of the value.
        for ( int i = 0; i < numElements; i++ ) {
            // Height of the current column
            colHeight = squareHeight * currentArray[i];

            // Draw the "inverse" bars above the bars in white to "erase" previous bars of different heights
            g.setColor(Color.WHITE);
            g.fillRect(100 + i * squareWidth, 0, squareWidth, height - 100 - colHeight);

            // Draw bar for value
            // If i is currentIdx, this should instead be black
            // This can be used to e.g. display an algorithm searching through the array
            // when no swaps are done (and therefore no changes to the bars)
            if (i == currentIndex) {
                g.setColor(Color.RED);
            } else {
                //g.setColor(Color.BLACK);
                g.setColor(Color.getHSBColor(hue, saturation, currentArray[i] / (float) numElements));
            }
            g.fillRect(100 + i * squareWidth, height - 100 - colHeight, squareWidth, colHeight);
        }
    }

    public void paint2(Graphics g) {
        if (currentArray == null) return;

        int numElements = currentArray.length;
        int size = (int) Math.ceil(Math.sqrt(numElements));
        int scale = (height - 200) / size;

        // Go to each index in the array, and fill a square in the
        // animation with the brightness representing the magnitude of the value.
        for (int i = 0 ; i < numElements; i++ ) {
            if (i == currentIndex) {
                g.setColor(Color.RED);
            } else {
                //float[] tile = imgArray[currentArray[i]];
                //g.setColor(Color.getHSBColor(tile[0], tile[1], tile[2]));
                g.setColor(Color.getHSBColor(hue, saturation, currentArray[i] / (float) numElements));
            }
            g.fillRect(800 + (i % size) * scale, 100 + i / size * scale, scale, scale);
        }
    }
}
