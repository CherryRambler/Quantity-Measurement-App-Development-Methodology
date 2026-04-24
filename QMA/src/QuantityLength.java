import java.util.Objects;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 0.01;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value cannot be NaN or Infinite");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (this.unit == targetUnit) {
            return this;
        }
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double targetValue = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(targetValue, targetUnit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        double thisBaseValue = this.unit.convertToBaseUnit(this.value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);
        double sumBaseValue = thisBaseValue + otherBaseValue;

        double targetValue = targetUnit.convertFromBaseUnit(sumBaseValue);
        return new QuantityLength(targetValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;

        double thisBaseValue = this.unit.convertToBaseUnit(this.value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBaseValue - otherBaseValue) <= EPSILON;
    }

    @Override
    public int hashCode() {
        double baseValue = this.unit.convertToBaseUnit(this.value);
        return Objects.hash(Math.round(baseValue / EPSILON));
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.1f, %s)", value, unit.name());
    }
}