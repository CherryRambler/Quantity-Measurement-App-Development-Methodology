import java.util.Objects;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    private double getBaseValue() {
        return this.value * this.unit.getConversionFactor();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityLength that = (QuantityLength) obj;

        return Math.abs(this.getBaseValue() - that.getBaseValue()) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(getBaseValue() * 10000.0) / 10000.0);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
    }
}