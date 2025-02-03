package com.example.data_manager;
/**
 * Stores directory paths & settings
 */
/**
 * Stores directory paths & settings
 */
public class Config {
    /**
     * The directory path where RPN expressions are stored.
     * Each expression is stored in a separate file.
     */
    public static final String EXPRESSION_DIR = "/data/expressions/";
    /**
     * The directory path where results of the RPN expressions are stored.
     * Each result is stored in a separate file.
     */
    public static final String RESULT_DIR = "/data/results/";
    /**
     * The default file used to store RPN expressions.
     * If no file is specified, the calculator will use this file.
     */
    public static final String DEFAULT_FILE = "/data/expressions/input.txt";
}
