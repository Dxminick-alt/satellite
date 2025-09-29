# Satellite Image Processing Solution

## Project Overview

This Java project implements a satellite image processing system that solves the satellite picture problem using static methods with modular design. The solution follows strict modularity constraints with each method containing no more than 6-7 lines of code.

## Key Features

### 1. Unified Coordinate Determination Method
- **Method**: `calculateCoordinates(String type, int width, int height, double scaleX, double scaleY, int offsetX, int offsetY)`
- **Purpose**: Single method for determining x1, x2, y1, y2 coordinates with configurable parameters
- **Parameters**: Allows customization of scale factors and offsets for different processing scenarios

### 2. Unified Image Reading Method  
- **Method**: `readImageData(String filename, String imageType)`
- **Purpose**: Single method for reading both old and new satellite images
- **Features**: Handles different file types and graceful error handling

### 3. Modular Design
- All methods are static (no instance variables)
- Each method limited to 6-7 lines for maximum modularity
- Single class implementation as requested

## Implementation Details

### Core Processing Methods

1. **Coordinate Calculation**: 
   ```java
   public static int[] calculateCoordinates(String type, int width, int height, 
                                          double scaleX, double scaleY, int offsetX, int offsetY)
   ```

2. **Image Data Reading**:
   ```java
   public static String[][] readImageData(String filename, String imageType)
   ```

3. **Region Processing**:
   ```java
   public static void processImageRegion(String[][] imageData, int x1, int x2, int y1, int y2)
   ```

4. **Image Comparison**:
   ```java
   public static void compareImages(String[][] oldImage, String[][] newImage)
   ```

## Test Results

### Test Suite Execution

The comprehensive test suite demonstrates the following capabilities:

#### Test 1: Coordinate Calculations
```
1024x768 standard        : x1=0, x2=1024, y1=0, y2=768
2048x1536 scaled 0.5x    : x1=5, x2=1019, y1=5, y2=763
512x384 regional 2x1.5   : x1=50, x2=974, y1=22, y2=553
```

#### Test 2: Image Reading and Comparison
- Successfully reads 5x5 satellite image matrices
- Compares old vs new satellite images
- Detects changes and calculates similarity percentages

#### Test 3: Region Processing
```
Processing region: [0,0] to [3,3] - Processed 9 pixels, Average intensity: 75.00
Processing region: [1,1] to [4,4] - Processed 9 pixels, Average intensity: 89.44
Processing region: [2,2] to [5,5] - Processed 9 pixels, Average intensity: 94.33
```

#### Test 4: Different Image Configurations
- Tests various image sizes and scaling factors
- Validates coordinate calculations across different scenarios

### Verification Results
✓ Coordinate calculation method works with parameters  
✓ Unified image reading method handles different file types  
✓ Region processing calculates areas and intensities correctly  
✓ Image comparison detects changes between old and new images  
✓ All methods follow 6-7 line constraint for modularity  
✓ Static methods only - no instance variables used  

## Test Data Files

The solution includes comprehensive test data:

1. **satellite_old.txt** - Original satellite image data (5x5 matrix)
2. **satellite_new.txt** - Updated satellite image data (5x5 matrix)  
3. **satellite_test1.txt** - Additional test case with lower intensity values
4. **satellite_test2.txt** - Additional test case with higher intensity values

## Running the Solution

### Compile and Run Main Program
```bash
mvn compile exec:java -Dexec.mainClass="org.example.SatelliteProcessor"
```

### Run Comprehensive Test Suite
```bash
mvn compile exec:java -Dexec.mainClass="org.example.SatelliteTestRunner"
```

## Algorithm Design

The satellite processing algorithm follows these principles:

1. **Parameterized Coordinate System**: Single method handles all coordinate calculations with configurable scaling and offset parameters
2. **Unified Data Access**: One method reads any type of satellite image data (old/new/test)
3. **Modular Processing**: Each processing step is isolated in methods of 6-7 lines maximum
4. **Static Design**: All functionality accessible without instantiation

## Technical Specifications

- **Language**: Java
- **Build Tool**: Maven
- **Architecture**: Single class with static methods only
- **Method Constraint**: Maximum 6-7 lines per method
- **Error Handling**: Graceful handling of file I/O errors
- **Testing**: Comprehensive test suite with multiple scenarios

## GitHub Repository

Repository URL: `github.com/Dxminick-alt/satellite`

The repository contains:
- Complete source code (`SatelliteProcessor.java`)
- Test runner (`SatelliteTestRunner.java`)
- Test data files (satellite_*.txt)
- This documentation
- Maven build configuration

## Solution Verification

The solution successfully addresses all requirements:

1. ✅ Uses only one method for determining x1,x2,y1,y2 coordinates
2. ✅ Adds parameters to the coordinate method for flexibility
3. ✅ Uses one method for reading both old and new images
4. ✅ Implements only static methods in a single class
5. ✅ Maintains 6-7 line constraint per method for modularity
6. ✅ Provides comprehensive testing with multiple input data files
7. ✅ Demonstrates correct functionality through test results