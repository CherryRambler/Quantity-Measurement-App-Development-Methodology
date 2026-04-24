public class QMA {

    public static void main(String[] args) {

        System.out.println(
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(3.0, LengthUnit.FEET))
        );

        System.out.println(
                new QuantityLength(1.0, LengthUnit.YARDS)
                        .equals(new QuantityLength(36.0, LengthUnit.INCH))
        );
        System.out.println(
                new QuantityLength(1.0, LengthUnit.CENTIMETER)
                        .equals(new QuantityLength(0.393701, LengthUnit.INCH))
        );
    }
}