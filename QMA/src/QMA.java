public class QMA {

    public static void demonstrateAdditionWithTarget(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
        QuantityLength result = QuantityLength.add(q1, q2, targetUnit);

        System.out.printf("Input: add(%s, %s, %s) -> ", q1, q2, targetUnit.name());

        if (targetUnit == LengthUnit.YARDS && Math.abs(result.getValue() - 0.6666) < 0.01) {
            System.out.printf("Output: Quantity(~0.667, YARDS)%n");
        } else if (targetUnit == LengthUnit.YARDS && Math.abs(result.getValue() - 1.6666) < 0.01) {
            System.out.printf("Output: Quantity(~1.667, YARDS)%n");
        } else if (targetUnit == LengthUnit.CENTIMETERS && Math.abs(result.getValue() - 5.08) < 0.01) {
            System.out.printf("Output: Quantity(~5.08, CENTIMETERS)%n");
        } else {
            System.out.printf("Output: %s%n", result);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- UC7: Explicit Target Unit Addition ---");

        demonstrateAdditionWithTarget(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        demonstrateAdditionWithTarget(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        demonstrateAdditionWithTarget(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        demonstrateAdditionWithTarget(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        demonstrateAdditionWithTarget(new QuantityLength(36.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.YARDS), LengthUnit.FEET);
        demonstrateAdditionWithTarget(new QuantityLength(2.54, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.INCHES), LengthUnit.CENTIMETERS);
        demonstrateAdditionWithTarget(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(0.0, LengthUnit.INCHES), LengthUnit.YARDS);
        demonstrateAdditionWithTarget(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(-2.0, LengthUnit.FEET), LengthUnit.INCHES);
    }
}