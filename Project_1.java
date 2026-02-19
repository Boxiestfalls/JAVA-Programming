import java.util.*;
public class MyProgram {
    //Constant Variables
    public static final double DOOR_AREA = 21.0;
    public static final double WINDOW_AREA = 12.0;
    public static final double PAINT_COVERAGE = 350.0;
    public static final double PAINT_PRICE = 34.99;
    public static final double TAX_RATE = 0.06;
    public static final double LAMINATE_PRICE = 2.25;
    public static final double LAMINATE_WASTE = 0.07;
    public static final double HARDWOOD_PRICE = 5.50;
    public static final double HARDWOOD_WASTE = 0.10;
    public static final double TILE_PRICE = 3.90;
    public static final double TILE_WASTE = 0.08;
    public static void main(String[] args){
        Scanner scanStr = new Scanner(System.in);
        double subtotal = 0.0;
        double tax = 0.0;
        boolean running = true;
        while (running){
            System.out.print("=== Home Renovator === \n 1) Paint Room \n 2) Flooring \n 3) View Totals \n 0) Exit \n Choice:  ");
            int choice = scanStr.nextInt();
            switch (choice){
                case 1:
                    subtotal += painting(scanStr);
                    break;
                case 2:
                    subtotal += flooring(scanStr);
                    break;
                case 3:
                    viewTotals(subtotal, tax);
                    break;
                case 0:
                    System.out.println("Exiting Home Renovator... \n Thank you for using the program!");
                    running = false;
                    break;
            }
        }
    }
   public static double painting(Scanner scanStr){
        System.out.print("- - Paint Room - -");
        double length = readPositiveDouble(scanStr, "Enter room length (ft): ");
        double width = readPositiveDouble(scanStr, "Enter room width (ft): ");
        double height = readPositiveDouble(scanStr, "Enter room height (ft): ");
        double doors = readPositiveDouble(scanStr, "Enter number of doors: ");
        double windows = readPositiveDouble(scanStr, "Enter number of windows: ");
        double wallArea = ((2*length)+(2*width))*height;
        double openingsArea = (doors*DOOR_AREA) + (windows*WINDOW_AREA);
        double netPaintArea = wallArea-openingsArea;
        double gallons = Math.ceil(netPaintArea/PAINT_COVERAGE);
        double materialCost = gallons*PAINT_PRICE;
        double totalTax = materialCost*(1+TAX_RATE);
        String roundedTax = String.format("%.2f", totalTax);
        System.out.println("\nWall area: " + wallArea + " sq ft");
        System.out.println("Openings area: " + openingsArea + " sq ft");
        System.out.println("Net paint area: " + netPaintArea + " sq ft");
        System.out.println("Gallons required: " + gallons);
        System.out.println("Material cost (@ $34.99/gal): $" + materialCost);
        System.out.println("Line total (with tax): $" + roundedTax + "\n\n");
        System.out.println("[Paint module complete. Added " + roundedTax + " to running total.]");
        return materialCost;
    } 
    public static double flooring(Scanner scanStr){
        System.out.print("- - Flooring - -");
        double length = readPositiveDouble(scanStr, "Enter room length (ft): ");
        double width = readPositiveDouble(scanStr, "Enter room width (ft): ");

        System.out.print("Select flooring type: \n 1) Laminate \n 2) Hardwood \n 3) Tile \n Choice:  ");
            int choice = scanStr.nextInt();
            double cost =0;
            double waste =0;
            switch (choice){
                case 1:
                    cost = LAMINATE_PRICE;
                    waste = LAMINATE_WASTE;
                    break;
                case 2:
                    cost = HARDWOOD_PRICE;
                    waste = HARDWOOD_WASTE;
                    break;
                case 3:
                    cost = TILE_PRICE;
                    waste = TILE_WASTE;
                    break;
            }
        double area = length*width;
        double adjustedArea=area*(1+waste);
        String roundedArea = String.format("%.2f", adjustedArea);
        double materialCost = adjustedArea*cost;
        String roundedCost = String.format("%.2f", materialCost);
        double totalTax = materialCost*(1+TAX_RATE);
        String roundedTax = String.format("%.2f", totalTax);
        System.out.println("\nFloor area: " + area + " sq ft");
        System.out.println("Adjusted floor area (with waste): " + roundedArea + " sq ft");
        System.out.println("Cost per sq ft: $" + cost);
        System.out.println("Material cost: $" + roundedCost);
        System.out.println("Line total (with tax): $" + roundedTax + "\n\n");
        System.out.println("[Flooring module complete. Added $" + roundedTax + " to running total.]");
        return materialCost;
    }
    public static void viewTotals(double subtotal, double tax){
        String roundedSubtotal = String.format("%.2f", subtotal);
        tax = subtotal*TAX_RATE+.005;
        String roundedTax = String.format("%.2f", tax);
        String roundedTotal = String.format("%.2f", subtotal+tax);
        System.out.println("\n=== Totals ===");
        System.out.println("Subtotal: $" + roundedSubtotal);
        System.out.println("Tax: $" + roundedTax);
        System.out.println("Total: $" + roundedTotal + "\n\n");
    }
    public static double readPositiveDouble(Scanner scanStr, String prompt){
        while (true){
            System.out.print(prompt);
            String input = scanStr.nextLine().trim();

            try{
                double value = Double.parseDouble(input);
                if (value>0) return value;
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid input. Please enter a positive number.");
        }
    }
}
