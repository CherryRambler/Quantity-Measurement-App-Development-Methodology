public class QMA {

    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        double result = QuantityLength.convert(value, from, to);
        System.out.printf("Input: convert(%.1f, %s, %s) -> Output: %.6f%n", value, from.name(), to.name(), result);
    }

    public static void demonstrateLengthConversion(QuantityLength length, LengthUnit to) {
        QuantityLength result = length.convertTo(to);
        System.out.println("Object Conversion: " + length + " equals " + result);
    }

    public static void demonstrateLengthEquality(QuantityLength length1, QuantityLength length2) {
        boolean isEqual = length1.equals(length2);
        System.out.println("Equality Check: Is " + length1 + " equal to " + length2 + "? " + isEqual);
    }

    public static void demonstrateLengthComparison(double val1, LengthUnit unit1, double val2, LengthUnit unit2) {
        QuantityLength length1 = new QuantityLength(val1, unit1);
        QuantityLength length2 = new QuantityLength(val2, unit2);
        demonstrateLengthEquality(length1, length2);
    }

    public static void main(String[] args) {
        System.out.println("--- Unit Conversion Demonstrations ---");
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);

        System.out.println("\n--- Object API Demonstrations ---");
        QuantityLength yardLength = new QuantityLength(1.0, LengthUnit.YARDS);
        demonstrateLengthConversion(yardLength, LengthUnit.INCHES);

        System.out.println("\n--- Equality & Comparison Demonstrations ---");
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, LengthUnit.INCHES, 2.54, LengthUnit.CENTIMETERS);
    }
}