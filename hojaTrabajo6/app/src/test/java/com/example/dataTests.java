package com.example;
import java.io.File;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.data.DataHandler;


public class dataTests {

    private static final String TEST_DIR = "test_dir";
    private static final String TEST_DIR_PATH = System.getProperty("user.dir") + "/" + TEST_DIR;

    @BeforeEach
    public void setUp() {
        // Delete test directory if it already exists
        File testDir = new File(TEST_DIR_PATH);
        if (testDir.exists()) {
            testDir.delete();
        }
    }

    @AfterEach
    public void tearDown() {
        // Delete test directory after each test
        File testDir = new File(TEST_DIR_PATH);
        if (testDir.exists()) {
            testDir.delete();
        }
    }

    @Test
    public void testCreateDirectory_DirectoryDoesNotExist() {
        // Act
        DataHandler.createDicectory(TEST_DIR_PATH);

        // Assert
        File testDir = new File(TEST_DIR_PATH);
        assertTrue(testDir.exists());
    }

    @Test
    public void testCreateDirectory_DirectoryAlreadyExists() {
        // Arrange
        File testDir = new File(TEST_DIR_PATH);
        testDir.mkdirs();

        // Act
        DataHandler.createDicectory(TEST_DIR_PATH);

        // Assert
        assertTrue(testDir.exists());
    }

    @Test
    public void testCreateDirectory_NullPath() {
        // Act and Assert
        try {
            DataHandler.createDicectory(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }
}