import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QMATest {

    private static final double DELTA = 0.01;


    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {
        assertTrue(new QuantityWeight(1000.0, WeightUnit.GRAM).equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        Object lengthDummy = new Object();
        assertFalse(weight.equals(lengthDummy));
    }

    @Test
    public void testEquality_NullComparison() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        QuantityWeight weight = new QuantityWeight(5.0, WeightUnit.POUND);
        assertTrue(weight.equals(weight));
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(1.0, null));
    }

    @Test
    public void testEquality_TransitiveProperty() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight c = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void testEquality_ZeroValue() {
        assertTrue(new QuantityWeight(0.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(0.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_NegativeWeight() {
        assertTrue(new QuantityWeight(-1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(-1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_LargeWeightValue() {
        assertTrue(new QuantityWeight(1000000.0, WeightUnit.GRAM).equals(new QuantityWeight(1000.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_SmallWeightValue() {
        assertTrue(new QuantityWeight(0.001, WeightUnit.KILOGRAM).equals(new QuantityWeight(1.0, WeightUnit.GRAM)));
    }


    @Test
    public void testConversion_PoundToKilogram() {
        QuantityWeight converted = new QuantityWeight(2.20462, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, converted.getValue(), DELTA);
    }

    @Test
    public void testConversion_KilogramToPound() {
        QuantityWeight converted = new QuantityWeight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND);
        assertEquals(2.20462, converted.getValue(), DELTA);
    }

    @Test
    public void testConversion_SameUnit() {
        QuantityWeight original = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight converted = original.convertTo(WeightUnit.KILOGRAM);
        assertEquals(5.0, converted.getValue(), DELTA);
    }

    @Test
    public void testConversion_ZeroValue() {
        QuantityWeight converted = new QuantityWeight(0.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertEquals(0.0, converted.getValue(), DELTA);
    }

    @Test
    public void testConversion_NegativeValue() {
        QuantityWeight converted = new QuantityWeight(-1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertEquals(-1000.0, converted.getValue(), DELTA);
    }

    @Test
    public void testConversion_RoundTrip() {
        double originalValue = 1.5;
        QuantityWeight result = new QuantityWeight(originalValue, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(originalValue, result.getValue(), DELTA);
    }


    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));
        assertEquals(3.0, result.getValue(), DELTA);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(1000.0, WeightUnit.GRAM));
        assertEquals(2.0, result.getValue(), DELTA);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {
        QuantityWeight result = new QuantityWeight(2.20462, WeightUnit.POUND).add(new QuantityWeight(1.0, WeightUnit.KILOGRAM));
        assertEquals(4.40924, result.getValue(), DELTA);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        assertEquals(2000.0, result.getValue(), DELTA);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testAddition_Commutativity() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result1 = a.add(b, WeightUnit.KILOGRAM);
        QuantityWeight result2 = b.add(a, WeightUnit.KILOGRAM);

        assertTrue(result1.equals(result2));
    }

    @Test
    public void testAddition_WithZero() {
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM).add(new QuantityWeight(0.0, WeightUnit.GRAM));
        assertEquals(5.0, result.getValue(), DELTA);
    }

    @Test
    public void testAddition_NegativeValues() {
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM).add(new QuantityWeight(-2000.0, WeightUnit.GRAM));
        assertEquals(3.0, result.getValue(), DELTA);
    }

    @Test
    public void testAddition_LargeValues() {
        QuantityWeight result = new QuantityWeight(1e6, WeightUnit.KILOGRAM).add(new QuantityWeight(1e6, WeightUnit.KILOGRAM));
        assertEquals(2e6, result.getValue(), DELTA);
    }
}