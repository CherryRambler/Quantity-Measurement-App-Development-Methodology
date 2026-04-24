public enum LengthUnit {
    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double baseFactor;

    LengthUnit(double baseFactor) {
        this.baseFactor = baseFactor;
    }

    public double getBaseFactor() {
        return baseFactor;
    }
}