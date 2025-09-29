# Satellite Image Processing System

A modular Java solution for satellite image processing using static methods with strict modularity constraints.

## Quick Start

```bash
# Compile and run the main processor
mvn compile exec:java -Dexec.mainClass="satellite.SatelliteProcessor"

# Run comprehensive test suite  
mvn compile exec:java -Dexec.mainClass="satellite.SatelliteTestRunner"
```

## Key Features

- **Unified Coordinate Method**: Single method for x1,x2,y1,y2 determination with parameters
- **Unified Image Reading**: One method handles both old and new satellite images
- **Modular Design**: All methods limited to 6-7 lines maximum
- **Static Implementation**: Single class, static methods only
- **Comprehensive Testing**: Multiple test scenarios with real satellite data

## Algorithm Overview

The solution implements a coordinate-based satellite image processing algorithm that:

1. Calculates processing regions using configurable scale and offset parameters
2. Reads satellite image data from text files in matrix format
3. Processes specific regions within satellite images
4. Compares old vs new satellite images to detect changes
5. Calculates statistical measures (area, average intensity, similarity)

## Project Structure

```
src/main/java/satellite/
├── SatelliteProcessor.java      # Main processing class
└── SatelliteTestRunner.java     # Comprehensive test suite

Test Data Files:
├── satellite_old.txt            # Original satellite image data
├── satellite_new.txt            # Updated satellite image data  
├── satellite_test1.txt          # Low intensity test case
└── satellite_test2.txt          # High intensity test case
```

## Requirements Compliance

- [x] Single method for x1,x2,y1,y2 determination with added parameters
- [x] Single method for reading old and new images
- [x] Static methods only in single class
- [x] 6-7 line constraint per method for modularity
- [x] Comprehensive testing with multiple input data files
- [x] GitHub repository with satellite naming convention

## Author

GitHub: [Dxminick-alt](https://github.com/Dxminick-alt)
