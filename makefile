SRC_DIR := src/main/java
OUT_DIR := bin 

SOURCE := $(shell find $(SRC_DIR) -name "*.java") 

run:
	@echo "Compiling program..."
	@mkdir -p $(OUT_DIR)
	@javac -d $(OUT_DIR) $(SOURCE)
	@echo "Starting program..."
	@java -cp $(OUT_DIR) Lux 

test:
	@echo "Running tests..."
	@cd text-ui-test && ./runtest.sh
