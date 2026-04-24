import java.util.Objects;

public record QuantityLength(double value, LengthUnit unit) {
    private static final double EPSILON = 1e-5;

    public QuantityLength {
        validateInputs(value, unit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        validateInputs(value, source);
        if (target == null) throw new IllegalArgumentException("Target unit cannot be null.");

        double valueInBaseUnit = value * source.getBaseFactor();
        return valueInBaseUnit / target.getBaseFactor();
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        double convertedValue = convert(this.value, this.unit, targetUnit);
        return new QuantityLength(convertedValue, targetUnit);
    }


    private QuantityLength addInternal(QuantityLength other, LengthUnit targetUnit) {
        if (other == null) throw new IllegalArgumentException("Second operand cannot be null.");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null.");

        double thisInBase = this.value * this.unit.getBaseFactor();
        double otherInBase = other.value * other.unit.getBaseFactor();
        double sumInBase = thisInBase + otherInBase;

        double finalValue = sumInBase / targetUnit.getBaseFactor();
        return new QuantityLength(finalValue, targetUnit);
    }

    public QuantityLength add(QuantityLength other) {
        return addInternal(other, this.unit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        return addInternal(other, targetUnit);
    }

    public static QuantityLength add(QuantityLength length1, QuantityLength length2) {
        if (length1 == null) throw new IllegalArgumentException("First operand cannot be null.");
        return length1.add(length2);
    }

    public static QuantityLength add(QuantityLength length1, QuantityLength length2, LengthUnit targetUnit) {
        if (length1 == null) throw new IllegalArgumentException("First operand cannot be null.");
        return length1.add(length2, targetUnit);
    }

    private static void validateInputs(double value, LengthUnit unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number.");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength that = (QuantityLength) obj;
        double convertedThatValue = convert(that.value, that.unit, this.unit);
        return Math.abs(this.value - convertedThatValue) <= EPSILON;
    }

    @Override
    public int hashCode() {
        double baseValue = this.value * this.unit.getBaseFactor();
        return Objects.hash(Math.round(baseValue / EPSILON));
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.1f, %s)", value, unit.name());
    }
}