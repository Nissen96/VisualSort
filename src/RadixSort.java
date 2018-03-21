abstract class RadixSort extends VisualSort {
    RadixSort(String title) {
        super(title);
    }

    private int base;

    int getBase() {
        return base;
    }

    void setBase(int base) {
        this.base = base;
    }
}
