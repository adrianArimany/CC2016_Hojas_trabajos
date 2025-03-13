package com.example.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PokemonGUI extends JFrame {

    public PokemonGUI() {
        setTitle("Pokemon Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create a menu bar with File and Program menus 
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu programMenu = new JMenu("Program");
        menuBar.add(fileMenu);
        menuBar.add(programMenu);
        setJMenuBar(menuBar);

        // Create a tabbed pane to hold different parts of the UI
        JTabbedPane tabbedPane = new JTabbedPane();

        // ===== Include Pokemon Panel =====
        JPanel includePanel = new JPanel(new BorderLayout());
        // Top section: mapping type dropdown
        JPanel mappingPanel = new JPanel();
        mappingPanel.add(new JLabel("Which type of Mapping do you want to use:"));
        String[] mappingTypes = {"Hash Map", "Tree Map", "Linked Hash Map"};
        JComboBox<String> mappingDropdown = new JComboBox<>(mappingTypes);
        mappingPanel.add(mappingDropdown);
        includePanel.add(mappingPanel, BorderLayout.NORTH);

        // Middle section: add Pokemon by name
        JPanel addPokemonPanel = new JPanel();
        addPokemonPanel.add(new JLabel("Add Pokemon by Name:"));
        JTextField pokemonNameField = new JTextField(15);
        addPokemonPanel.add(pokemonNameField);
        JButton addPokemonButton = new JButton("Include Pokemon");
        addPokemonPanel.add(addPokemonButton);
        includePanel.add(addPokemonPanel, BorderLayout.CENTER);

        // Bottom section: feedback label
        JLabel includeFeedbackLabel = new JLabel("");
        includePanel.add(includeFeedbackLabel, BorderLayout.SOUTH);

        // ===== Search Pokemon Panel =====
        JPanel searchPanel = new JPanel(new BorderLayout());

        // Panel for searching by name and ability
        JPanel searchInputsPanel = new JPanel(new GridLayout(2, 1));

        // Search by Name
        JPanel searchByNamePanel = new JPanel();
        searchByNamePanel.add(new JLabel("Search by Pokemon Name:"));
        JTextField searchNameField = new JTextField(15);
        searchByNamePanel.add(searchNameField);
        JButton searchByNameButton = new JButton("Search");
        searchByNamePanel.add(searchByNameButton);
        searchInputsPanel.add(searchByNamePanel);

        // Search by Ability
        JPanel searchByAbilityPanel = new JPanel();
        searchByAbilityPanel.add(new JLabel("Search by Pokemon Ability:"));
        JTextField searchAbilityField = new JTextField(15);
        searchByAbilityPanel.add(searchAbilityField);
        JButton searchByAbilityButton = new JButton("Search");
        searchByAbilityPanel.add(searchByAbilityButton);
        searchInputsPanel.add(searchByAbilityPanel);

        searchPanel.add(searchInputsPanel, BorderLayout.NORTH);

        // Results area for search output
        JTextArea searchResultsArea = new JTextArea(10, 30);
        searchResultsArea.setEditable(false);
        JScrollPane searchScrollPane = new JScrollPane(searchResultsArea);
        searchPanel.add(searchScrollPane, BorderLayout.CENTER);

        // ===== Pokemon List Panel =====
        JPanel listPanel = new JPanel(new BorderLayout());
        JTextArea pokemonListArea = new JTextArea(15, 30);
        pokemonListArea.setEditable(false);
        JScrollPane listScrollPane = new JScrollPane(pokemonListArea);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        JButton refreshListButton = new JButton("Refresh Pokemon List");
        listPanel.add(refreshListButton, BorderLayout.SOUTH);

        // Add panels as tabs
        tabbedPane.addTab("Include Pokemon", includePanel);
        tabbedPane.addTab("Search Pokemon", searchPanel);
        tabbedPane.addTab("Pokemon List", listPanel);

        add(tabbedPane);

        // ===== Event Handling (Dummy Implementations) =====

        // Include Pokemon button action
        addPokemonButton.addActionListener(e -> {
            String name = pokemonNameField.getText().trim();
            if (name.isEmpty()) {
                includeFeedbackLabel.setText("Please enter a Pokemon name.");
            } else {
                // Dummy check: assume duplicate names are not allowed
                // Replace with your logic for checking and adding the Pokemon
                includeFeedbackLabel.setText("Pokemon added: " + name);
            }
        });

        // Search by Name action
        searchByNameButton.addActionListener(e -> {
            String name = searchNameField.getText().trim();
            if (name.isEmpty()) {
                searchResultsArea.setText("Please enter a Pokemon name to search.");
            } else {
                // Replace with your search logic; currently, it only echoes the search term
                searchResultsArea.setText("Searching for Pokemon by name: " + name);
            }
        });

        // Search by Ability action
        searchByAbilityButton.addActionListener(e -> {
            String ability = searchAbilityField.getText().trim();
            if (ability.isEmpty()) {
                searchResultsArea.setText("Please enter a Pokemon ability to search.");
            } else {
                // Replace with your search logic; currently, it only echoes the search term
                searchResultsArea.setText("Searching for Pokemon by ability: " + ability);
            }
        });

        // Refresh Pokemon List action
        refreshListButton.addActionListener(e -> {
            // Replace with your actual Pokemon list retrieval logic
            pokemonListArea.setText("Bulbasaur\nCharmander\nSquirtle");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PokemonGUI().setVisible(true);
        });
    }
}
