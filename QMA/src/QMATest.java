import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QMATest {

    private static final double DELTA = 0.01;


    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), DELTA, "FEET conversion factor should be 1.0");
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), DELTA, "INCHES conversion factor should be ~0.0833");
    }

    @Test
    public void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), DELTA, "YARDS conversion factor should be 3.0");
    }

    @Test
    public void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), DELTA, "CENTIMETERS conversion factor should be ~0.0328");
    }


    @Test
    public void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), DELTA);
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), DELTA);
    }

    @Test
    public void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), DELTA);
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), DELTA);
    }


    @Test
    public void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), DELTA);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), DELTA);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), DELTA);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), DELTA);
    }


    @Test
    public void testQuantityLengthRefactored_Equality() {
        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInches = new QuantityLength(12.0, LengthUnit.INCHES);
        assertTrue(oneFoot.equals(twelveInches), "1 Foot should equal 12 Inches");
    }

    @Test
    public void testQuantityLengthRefactored_ConvertTo() {
        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength converted = oneFoot.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, converted.getValue(), DELTA);
        assertEquals(LengthUnit.INCHES, converted.getUnit());
    }

    @Test
    public void testQuantityLengthRefactored_Add() {
        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInches = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength result = oneFoot.add(twelveInches, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {
        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInches = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength result = oneFoot.add(twelveInches, LengthUnit.YARDS);

        assertEquals(2.0 / 3.0, result.getValue(), DELTA); // 2 feet is ~0.667 yards
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }


    @Test
    public void testQuantityLengthRefactored_NullUnit() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
        assertEquals("Unit cannot be null", exception.getMessage());
    }

    @Test
    public void testQuantityLengthRefactored_InvalidValue() {
        IllegalArgumentException exceptionNaN = assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(Double.NaN, LengthUnit.FEET);
        });
        assertEquals("Value cannot be NaN or Infinite", exceptionNaN.getMessage());

        IllegalArgumentException exceptionInfinite = assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(Double.POSITIVE_INFINITY, LengthUnit.FEET);
        });
        assertEquals("Value cannot be NaN or Infinite", exceptionInfinite.getMessage());
    }


    @Test
    public void testRoundTripConversion_RefactoredDesign() {
        double originalValue = 10.0;
        double baseValue = LengthUnit.YARDS.convertToBaseUnit(originalValue);
        double centiValue = LengthUnit.CENTIMETERS.convertFromBaseUnit(baseValue);

        double backToBase = LengthUnit.CENTIMETERS.convertToBaseUnit(centiValue);
        double finalYards = LengthUnit.YARDS.convertFromBaseUnit(backToBase);

        assertEquals(originalValue, finalYards, DELTA, "Round trip conversion should retain mathematical precision");
    }

    @Test
    public void testUnitImmutability() {

        double initialFactor = LengthUnit.FEET.getConversionFactor();
        double subsequentFactor = LengthUnit.FEET.getConversionFactor();
        assertEquals(initialFactor, subsequentFactor, "Enum conversion factor should be immutable");
    }
}