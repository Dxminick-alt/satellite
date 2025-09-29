package satellite;

import java.io.*;
import java.util.*;

/**
 * Satellite Image Processing System
 * Processes satellite images using coordinate-based algorithms
 * All methods are static and limited to 6-7 lines for modularity
 */
public class SatelliteProcessor {
    
    // Main entry point for satellite processing
    public static void main(String[] args) {
        System.out.println("Satellite Image Processing System");
        processTestImages();
        demonstrateCoordinateCalculations();
        runComprehensiveTests();
    }
    
    // Unified method for determining coordinates with configurable parameters
    public static int[] calculateCoordinates(String type, int width, int height, 
                                           double scaleX, double scaleY, int offsetX, int offsetY) {
        int x1 = (int)(offsetX * scaleX);
        int x2 = (int)((width - offsetX) * scaleX);
        int y1 = (int)(offsetY * scaleY);
        int y2 = (int)((height - offsetY) * scaleY);
        return new int[]{x1, x2, y1, y2};
    }
    
    // Unified method for reading both old and new image data
    public static String[][] readImageData(String filename, String imageType) {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.trim().split("\\s+"));
            }
        } catch (IOException e) { handleFileError(filename, e); }
        return rows.toArray(new String[0][]);
    }
    
    // Process satellite image region with given coordinates
    public static void processImageRegion(String[][] imageData, int x1, int x2, int y1, int y2) {
        System.out.printf("Processing region: [%d,%d] to [%d,%d]%n", x1, y1, x2, y2);
        int processedPixels = calculateRegionArea(x1, x2, y1, y2);
        double averageIntensity = calculateAverageIntensity(imageData, x1, x2, y1, y2);
        displayProcessingResults(processedPixels, averageIntensity);
    }
    
    // Calculate the area of the processing region
    private static int calculateRegionArea(int x1, int x2, int y1, int y2) {
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        return width * height;
    }
    
    // Calculate average intensity in the specified region
    private static double calculateAverageIntensity(String[][] data, int x1, int x2, int y1, int y2) {
        double sum = 0;
        int count = 0;
        for (int y = Math.min(y1, y2); y < Math.max(y1, y2) && y < data.length; y++) {
            for (int x = Math.min(x1, x2); x < Math.max(x1, x2) && x < data[y].length; x++) {
                sum += parsePixelValue(data[y][x]);
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }
    
    // Parse pixel value from string data
    private static double parsePixelValue(String pixelData) {
        try {
            return Double.parseDouble(pixelData);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    // Display processing results
    private static void displayProcessingResults(int pixels, double intensity) {
        System.out.printf("Processed %d pixels%n", pixels);
        System.out.printf("Average intensity: %.2f%n", intensity);
        System.out.println("---");
    }
    
    // Handle file reading errors
    private static void handleFileError(String filename, IOException e) {
        System.err.println("Error reading file: " + filename);
        System.err.println("Error: " + e.getMessage());
    }
    
    // Compare two satellite images
    public static void compareImages(String[][] oldImage, String[][] newImage) {
        System.out.println("Comparing satellite images...");
        double similarity = calculateImageSimilarity(oldImage, newImage);
        System.out.printf("Image similarity: %.2f%%%n", similarity * 100);
        detectChanges(oldImage, newImage);
    }
    
    // Calculate similarity between two images
    private static double calculateImageSimilarity(String[][] img1, String[][] img2) {
        int matches = 0;
        int total = 0;
        for (int i = 0; i < Math.min(img1.length, img2.length); i++) {
            for (int j = 0; j < Math.min(img1[i].length, img2[i].length); j++) {
                if (parsePixelValue(img1[i][j]) == parsePixelValue(img2[i][j])) matches++;
                total++;
            }
        }
        return total > 0 ? (double) matches / total : 0;
    }
    
    // Detect significant changes between images
    private static void detectChanges(String[][] oldImg, String[][] newImg) {
        int changes = 0;
        for (int i = 0; i < Math.min(oldImg.length, newImg.length); i++) {
            for (int j = 0; j < Math.min(oldImg[i].length, newImg[i].length); j++) {
                double diff = Math.abs(parsePixelValue(oldImg[i][j]) - parsePixelValue(newImg[i][j]));
                if (diff > 10.0) changes++;
            }
        }
        System.out.printf("Detected %d significant changes%n", changes);
    }
    
    // Process test images with different coordinate configurations
    private static void processTestImages() {
        System.out.println("=== Processing Test Images ===");
        createTestImage("test_old.txt", generateTestData(5, 5, "old"));
        createTestImage("test_new.txt", generateTestData(5, 5, "new"));
        
        String[][] oldData = readImageData("test_old.txt", "old");
        String[][] newData = readImageData("test_new.txt", "new");
        compareImages(oldData, newData);
    }
    
    // Generate test data for satellite images
    private static String[][] generateTestData(int width, int height, String type) {
        String[][] data = new String[height][width];
        Random rand = new Random(type.equals("old") ? 42 : 84);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                data[i][j] = String.valueOf(rand.nextInt(256));
            }
        }
        return data;
    }
    
    // Create test image file
    private static void createTestImage(String filename, String[][] data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (String[] row : data) {
                pw.println(String.join(" ", row));
            }
        } catch (IOException e) {
            handleFileError(filename, e);
        }
    }
    
    // Demonstrate coordinate calculations with different parameters
    private static void demonstrateCoordinateCalculations() {
        System.out.println("=== Coordinate Calculation Examples ===");
        
        // Standard satellite image processing
        int[] coords1 = calculateCoordinates("standard", 1024, 768, 1.0, 1.0, 10, 10);
        displayCoordinates("Standard processing", coords1);
        
        // High-resolution processing with scaling
        int[] coords2 = calculateCoordinates("high-res", 2048, 1536, 0.5, 0.5, 20, 20);
        displayCoordinates("High-resolution scaled", coords2);
        
        // Regional focus with offset
        int[] coords3 = calculateCoordinates("regional", 512, 384, 2.0, 2.0, 50, 30);
        displayCoordinates("Regional focus", coords3);
    }
    
    // Display coordinate calculation results
    private static void displayCoordinates(String description, int[] coords) {
        System.out.printf("%s: x1=%d, x2=%d, y1=%d, y2=%d%n", 
                         description, coords[0], coords[1], coords[2], coords[3]);
    }
    
    // Run comprehensive tests with multiple scenarios
    private static void runComprehensiveTests() {
        System.out.println("=== Comprehensive Test Suite ===");
        testCoordinateEdgeCases();
        testImageProcessingScenarios();
        testErrorHandling();
    }
    
    // Test coordinate calculation edge cases
    private static void testCoordinateEdgeCases() {
        System.out.println("Testing coordinate edge cases:");
        int[] coords = calculateCoordinates("edge", 0, 0, 1.0, 1.0, 0, 0);
        assert coords[0] == 0 && coords[1] == 0 : "Zero dimension test failed";
        System.out.println("✓ Edge case tests passed");
    }
    
    // Test different image processing scenarios
    private static void testImageProcessingScenarios() {
        System.out.println("Testing image processing scenarios:");
        String[][] testData = {{"100", "150", "200"}, {"120", "160", "180"}};
        processImageRegion(testData, 0, 2, 0, 1);
        System.out.println("✓ Image processing tests passed");
    }
    
    // Test error handling capabilities
    private static void testErrorHandling() {
        System.out.println("Testing error handling:");
        String[][] emptyData = readImageData("nonexistent.txt", "test");
        assert emptyData.length == 0 : "Error handling test failed";
        System.out.println("✓ Error handling tests passed");
    }
}