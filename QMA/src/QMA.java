public class QMA {
    public static void main(String[] args) {
        System.out.println("--- Equality Comparisons ---");
        System.out.println("Input: Quantity(1.0, KILOGRAM).equals(Quantity(1.0, KILOGRAM)) -> Output: " +
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
        System.out.println("Input: Quantity(1.0, KILOGRAM).equals(Quantity(1000.0, GRAM)) -> Output: " +
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
        System.out.println("Input: Quantity(1.0, KILOGRAM).equals(Quantity(~2.20462, POUND)) -> Output: " +
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new QuantityWeight(2.20462, WeightUnit.POUND)));

        System.out.println("\n--- Unit Conversions ---");
        System.out.println("Input: Quantity(1.0, KILOGRAM).convertTo(GRAM) -> Output: " +
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM));
        System.out.println("Input: Quantity(2.0, POUND).convertTo(KILOGRAM) -> Output: " +
                new QuantityWeight(2.0, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM));

        System.out.println("\n--- Addition Operations (Implicit Target Unit) ---");
        System.out.println("Input: Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM)) -> Output: " +
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(1000.0, WeightUnit.GRAM)));

        System.out.println("\n--- Addition Operations (Explicit Target Unit) ---");
        System.out.println("Input: Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM), GRAM) -> Output: " +
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM));

        System.out.println("\n--- Category Incompatibility ---");
        System.out.println("Input: Quantity(1.0, KILOGRAM).equals(Quantity(1.0, FOOT)) -> Output: " +
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(new Object()));
}