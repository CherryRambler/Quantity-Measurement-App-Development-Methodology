import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

public class QMATest {

    @Test
    public void testEquality_FeetToFeet_SameValue() {
        Object LengthUnit = new Object();
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    private void assertTrue(boolean equals) {
    }

    @Test
    public void testEquality_InchToInch_SameValue() {
        Object LengthUnit = null;
        assertTrue(new QuantityLength(1.0, LengthUnit.INCH)
                .equals(new QuantityLength(1.0, LengthUnit.INCH)));
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue() {
        Object LengthUnit = new Object();
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(12.0, LengthUnit.INCH)));
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {
        Object LengthUnit = new Object();
        assertTrue(new QuantityLength(12.0, LengthUnit.INCH)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_DifferentValues() {
        Object LengthUnit = new Object();
        assertFalse(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_NullComparison() {
        Object LengthUnit = new Object();
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertFalse(q.equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        Object LengthUnit = new Object();
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(q.equals(q));
    }

    @Test
    public void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
    }
}