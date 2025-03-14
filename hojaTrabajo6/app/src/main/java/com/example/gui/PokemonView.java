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
        addPanel.add(new JLabel("Type:"));
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
    
    /**
     * Attach action listeners to the buttons in the view. These listeners delegate
     * the actions to the corresponding methods on the controller.
     */
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

    
    /**
     * Set the listener for adding a Pokemon. This listener will be notified
     * when the "Add Pokemon" button is clicked.
     * 
     * @param listener the listener to notify.
     */
    public void addAddPokemonListener(AddPokemonListener listener) {
        this.addPokemonListener = listener;
    }
/**
 * Set the listener for searching Pokemon by name. This listener will be 
 * notified when the "Search by Name" button is clicked.
 * 
 * @param listener the listener to notify.
 */

    /**
     * Set the listener for searching Pokemon by name. This listener will be 
     * notified when the "Search by Name" button is clicked.
     * 
     * @param listener the listener to notify.
     */
    public void addSearchByNameListener(SearchByNameListener listener) {
        this.searchByNameListener = listener;
    }
/**
 * Set the listener for searching Pokemon by ability. This listener will be 
 * notified when the "Search by Ability" button is clicked.
 * 
 * @param listener the listener to notify.
 */

    public void addSearchByAbilityListener(SearchByAbilityListener listener) {
        this.searchByAbilityListener = listener;
    }
    /**
     * Set the listener for refreshing the Pokemon list. This listener will be 
     * notified when the "Refresh Pokemon List" button is clicked.
     * 
     * @param listener the listener to notify.
     */
    public void addRefreshListListener(RefreshListListener listener) {
        this.refreshListListener = listener;
    }
    
    // Methods for updating UI components.


    
    /**
     * Shows a message dialog with the given message. The dialog is modal and
     * centered on the main window of the application.
     * 
     * @param message the message to be displayed in the dialog.
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(mainFrame, message);
    }
    
    /**
     * Displays the given search result in the search result area of the view.
     * 
     * @param result the search result to be displayed.
     */
    public void showSearchResult(String result) {
        resultArea.setText(result);
    }
    
    /**
     * Updates the Pokemon list in the view with the given list content.
     * 
     * @param listContent the content of the list to be displayed.
     */
    public void updatePokemonList(String listContent) {
        listArea.setText(listContent);
    }
    
    // Methods to display the initial dialogs.
    public String showFileSelection() {
        // Opens a file chooser dialog.
        return GUIUTIL.chooseFile(new JFrame("Select File"));
    }
    
    /**
     * Opens a dialog for mapping selection. The dialog presents the user with
     * three options: "Hash Map", "Tree Map", and "Linked Hash Map". The
     * selected mapping type is returned as a MappingSelectionResult object.
     * 
     * @return a MappingSelectionResult object containing the selected mapping type.
     */
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
    
    /**
     * Shows the main window of the application. This method should be called
     * once the model and controller are set up.
     */
    public void showMainWindow() {
        mainFrame.setVisible(true);
    }
    
    // Helper class to encapsulate the mapping selection.


    public static class MappingSelectionResult {
        private final String mappingType;
        
        public MappingSelectionResult(String mappingType) {
            this.mappingType = mappingType;
        }
        
        public String getMappingType() {
            return mappingType;
        }
    }
}

