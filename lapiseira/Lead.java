package lapiseira;

public class Lead {
    
    private float thickness;
    private String hardness;
    private int size;
    
    public Lead(float thickness, String hardness, int size) {
        this.thickness = thickness;
        this.hardness = hardness;
        this.size = size;
    }

    public int usagePerSheet() {
        switch (hardness) {
            case "HB" : return 1;
            case "2B" : return 2;
            case "4B" : return 4;
            case "6B" : return 6;
            default : return 0;
        }
    }

    public float getThickness() {
        return thickness;
    }
    public String getHardness() {
        return hardness;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "[" + thickness + ":" + hardness + ":" + size + "]";
    }

    

}
