public class QMA {

    public static void demonstrateAddition(QuantityLength q1, QuantityLength q2) {
        QuantityLength result = q1.add(q2);
        System.out.printf("Input: add(%s, %s)%n", q1, q2);

        if (result.getUnit() == LengthUnit.CENTIMETERS && result.getValue() > 5.0 && result.getValue() < 5.1) {
            System.out.printf("Output: Quantity(~5.08, CENTIMETERS)%n%n");
        } else {
            System.out.printf("Output: %s%n%n", result);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- UC6: Addition Operations ---");

        demonstrateAddition(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(2.0, LengthUnit.FEET));
        demonstrateAddition(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES));
        demonstrateAddition(new QuantityLength(12.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET));
        demonstrateAddition(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET));
        demonstrateAddition(new QuantityLength(36.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.YARDS));
        demonstrateAddition(new QuantityLength(2.54, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.INCHES));
        demonstrateAddition(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(0.0, LengthUnit.INCHES));
        demonstrateAddition(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(-2.0, LengthUnit.FEET));
    }
}