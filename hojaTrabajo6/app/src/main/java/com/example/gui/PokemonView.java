package com.example.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PokemonView extends JFrame {
    private final JFrame mainFrame;
    // Components for the main program window:
    private final JTextField addNameField;
    private final JTextField addAbilityField;
    private final JButton addPokemonButton;
    
    private final JTextField searchNameField;
    private final JButton searchByNameButton;
    
    private final JTextField searchAbilityField;
    private final JButton searchByAbilityButton;
    
    private final JButton refreshListButton;
    private final JTextArea resultArea;
    private final JTextArea listArea;
    
    // Interfaces for listeners.
    
    public interface AddPokemonListener {
        void addPokemon(String name, String ability);
    }
    private AddPokemonListener addPokemonListener;
    
    public interface SearchByNameListener {
        void searchByName(String name);
    }
    private SearchByNameListener searchByNameListener;
    
    public interface SearchByAbilityListener {
        void searchByAbility(String ability);
    }
    private SearchByAbilityListener searchByAbilityListener;
    
    public interface RefreshListListener {
        void refreshList();
    }
    private RefreshListListener refreshListListener;
    
    public PokemonView() {
        // Build the main program window.
        mainFrame = new JFrame("Pokemon Main Program");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new BorderLayout());
        
        // Panel for adding a Pokemon.
        JPanel addPanel = new JPanel();
        addPanel.setBorder(BorderFactory.createTitledBorder("Add New Pokemon"));
        addPanel.add(new JLabel("Name:"));
        addNameField = new JTextField(10);
        addPanel.add(addNameField);
        addPanel.add(new JLabel("Ability:"));
        addAbilityField = new JTextField(10);
        addPanel.add(addAbilityField);
        addPokemonButton = new JButton("Add Pokemon");
        addPanel.add(addPokemonButton);
        
        // Panel for searching Pokemon.
        JPanel searchPanel = new JPanel(new GridLayout(2,1));
        JPanel nameSearchPanel = new JPanel();
        nameSearchPanel.setBorder(BorderFactory.createTitledBorder("Search by Name"));
        searchNameField = new JTextField(10);
        nameSearchPanel.add(new JLabel("Name:"));
        nameSearchPanel.add(searchNameField);
        searchByNameButton = new JButton("Search");
        nameSearchPanel.add(searchByNameButton);
        
        JPanel abilitySearchPanel = new JPanel();
        abilitySearchPanel.setBorder(BorderFactory.createTitledBorder("Search by Ability"));
        searchAbilityField = new JTextField(10);
        abilitySearchPanel.add(new JLabel("Ability:"));
        abilitySearchPanel.add(searchAbilityField);
        searchByAbilityButton = new JButton("Search");
        abilitySearchPanel.add(searchByAbilityButton);
        searchPanel.add(nameSearchPanel);
        searchPanel.add(abilitySearchPanel);
        
        // Panel for displaying results and the complete list.
        JPanel outputPanel = new JPanel(new GridLayout(2, 1));
        resultArea = new JTextArea(5, 30);
        resultArea.setBorder(BorderFactory.createTitledBorder("Search Result"));
        resultArea.setEditable(false);
        listArea = new JTextArea(5, 30);
        listArea.setBorder(BorderFactory.createTitledBorder("Pokemon List"));
        listArea.setEditable(false);
        outputPanel.add(new JScrollPane(resultArea));
        outputPanel.add(new JScrollPane(listArea));
        
        refreshListButton = new JButton("Refresh Pokemon List");
        
        // Main container layout.
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(addPanel, BorderLayout.NORTH);
        centerPanel.add(searchPanel, BorderLayout.CENTER);
        centerPanel.add(outputPanel, BorderLayout.SOUTH);
        
        mainFrame.add(centerPanel, BorderLayout.CENTER);
        mainFrame.add(refreshListButton, BorderLayout.SOUTH);
        
        attachListeners();
    }
    
    private void attachListeners() {
        addPokemonButton.addActionListener(e -> {
            if (addPokemonListener != null) {
                String name = addNameField.getText().trim();
                String ability = addAbilityField.getText().trim();
                addPokemonListener.addPokemon(name, ability);
            }
        });
        
        searchByNameButton.addActionListener(e -> {
            if (searchByNameListener != null) {
                String name = searchNameField.getText().trim();
                searchByNameListener.searchByName(name);
            }
        });
        
        searchByAbilityButton.addActionListener(e -> {
            if (searchByAbilityListener != null) {
                String ability = searchAbilityField.getText().trim();
                searchByAbilityListener.searchByAbility(ability);
            }
        });
        
        refreshListButton.addActionListener(e -> {
            if (refreshListListener != null) {
                refreshListListener.refreshList();
            }
        });
    }
    
    // Methods to set listeners from the controller.
    public void addAddPokemonListener(AddPokemonListener listener) {
        this.addPokemonListener = listener;
    }
    public void addSearchByNameListener(SearchByNameListener listener) {
        this.searchByNameListener = listener;
    }
    public void addSearchByAbilityListener(SearchByAbilityListener listener) {
        this.searchByAbilityListener = listener;
    }
    public void addRefreshListListener(RefreshListListener listener) {
        this.refreshListListener = listener;
    }
    
    // Methods for updating UI components.
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(mainFrame, message);
    }
    
    public void showSearchResult(String result) {
        resultArea.setText(result);
    }
    
    public void updatePokemonList(String listContent) {
        listArea.setText(listContent);
    }
    
    // Methods to display the initial dialogs.
    public String showFileSelection() {
        // Opens a file chooser dialog.
        return GUIUTIL.chooseFile(new JFrame("Select File"));
    }
    
    public MappingSelectionResult showMappingSelection() {
        // Opens a dialog for mapping selection.
        String[] options = {"Hash Map", "Tree Map", "Linked Hash Map"};
        String selection = (String) JOptionPane.showInputDialog(null, 
            "Which type of Mapping do you want to use?",
            "Mapping Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        if (selection == null) {
            selection = "Hash Map"; // Default choice.
        }
        return new MappingSelectionResult(selection);
    }
    
    // Show the main window.
    public void showMainWindow() {
        mainFrame.setVisible(true);
    }
    
    // Helper class to encapsulate the mapping selection.
    public static class MappingSelectionResult {
        private String mappingType;
        
        public MappingSelectionResult(String mappingType) {
            this.mappingType = mappingType;
        }
        
        public String getMappingType() {
            return mappingType;
        }
    }
}

