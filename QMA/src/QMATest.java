import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {

    private static final double EPSILON = 1e-5;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0, QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0, QuantityLength.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET), EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0, QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0, QuantityLength.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS), EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        // 2.54 cm is ~1.0 inch
        assertEquals(1.0, QuantityLength.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES), 1e-4);
    }

    @Test
    void testConversion_FeatToYard() {
        assertEquals(2.0, QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS), EPSILON);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double originalValue = 5.5;
        // Convert FEET -> YARDS -> FEET
        double inYards = QuantityLength.convert(originalValue, LengthUnit.FEET, LengthUnit.YARDS);
        double backToFeet = QuantityLength.convert(inYards, LengthUnit.YARDS, LengthUnit.FEET);

        assertEquals(originalValue, backToFeet, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0, QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0, QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        assertEquals(5.0, QuantityLength.convert(5.0, LengthUnit.FEET, LengthUnit.FEET), EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.convert(1.0, null, LengthUnit.INCHES);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.convert(1.0, LengthUnit.FEET, null);
        });
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES);
        });
    }

    @Test
    void testEquality_AcrossDifferentUnits() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }
}