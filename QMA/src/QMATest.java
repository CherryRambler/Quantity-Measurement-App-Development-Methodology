import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    public void testEquality_YardToYard_SameValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARDS)
                .equals(new QuantityLength(1.0, LengthUnit.YARDS)));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARDS)
                .equals(new QuantityLength(3.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARDS)
                .equals(new QuantityLength(36.0, LengthUnit.INCH)));
    }

    @Test
    public void testEquality_CentimeterToInch_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.CENTIMETER)
                .equals(new QuantityLength(0.393701, LengthUnit.INCH)));
    }

    @Test
    public void testEquality_DifferentValues() {
        assertFalse(new QuantityLength(1.0, LengthUnit.YARDS)
                .equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_TransitiveProperty() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength c = new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void testNullComparison() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.YARDS);
        assertFalse(q.equals(null));
    }

    @Test
    public void testSameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        assertTrue(q.equals(q));
    }

    @Test
    public void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
    }
}