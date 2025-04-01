package com.example.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Assited by ChatGPT (2025)
 * 
 */
public class GuiView extends JFrame {
    
    // Components for the main program window:
    private final JTextField searchSkuField;
    private final JButton searchBySkuButton;
    private final JButton refreshListButton;
    private final JRadioButton ascenButton;
    private final JRadioButton descenButton;
    private final JTextArea resultArea;
    private final JTextArea listArea;
    
    
    // Interfaces for listeners.
    
    
    public interface SearchBySkuListener {
        void searchBySku(String sku);
    }
    private SearchBySkuListener searchBySkuListener;
    
    

    public interface RefreshListListener {
        void refreshList();
    }
    private RefreshListListener refreshListListener;
    
    public GuiView() {
        super("Home Appliance Data Base");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        
        // Create the search panel (placed at the top).
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search by SKU"));
        searchSkuField = new JTextField(20);
        searchBySkuButton = new JButton("Search");
        searchPanel.add(new JLabel("SKU:"));
        searchPanel.add(searchSkuField);
        searchPanel.add(searchBySkuButton);
        
        // Create a radio buttom for the buttom for the mode
        ascenButton = new JRadioButton("Ascending");
        descenButton = new JRadioButton("Descending");
        ascenButton.setSelected(true);
        
        ButtonGroup group = new ButtonGroup();
        group.add(ascenButton);
        group.add(descenButton);
        
        JPanel switchModeSearchPanel = new JPanel();
        switchModeSearchPanel.setBorder(BorderFactory.createTitledBorder("BST order mode"));
        switchModeSearchPanel.add(ascenButton);
        switchModeSearchPanel.add(descenButton);
        
        // Combine the search panel and switch mode panel into one northPanel.
        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        northPanel.add(switchModeSearchPanel);
        northPanel.add(searchPanel);
        add(northPanel, BorderLayout.NORTH);
        
        // Create the output panel for results and list.
        JPanel outputPanel = new JPanel(new GridLayout(2, 1));
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createTitledBorder("Search Result"));
        listArea = new JTextArea(5, 30);
        listArea.setEditable(false);
        listArea.setBorder(BorderFactory.createTitledBorder("Home Appliance List"));
        outputPanel.add(new JScrollPane(resultArea));
        outputPanel.add(new JScrollPane(listArea));
        add(outputPanel, BorderLayout.CENTER);
        
        // Create a panel for the refresh button.
        JPanel refreshPanel = new JPanel();
        refreshListButton = new JButton("Refresh Home Appliance List");
        refreshPanel.add(refreshListButton);
        add(refreshPanel, BorderLayout.SOUTH);
        
        attachListeners();
    }
    
    /**
     * Attach action listeners to the buttons.
     */
    private void attachListeners() {
        searchBySkuButton.addActionListener(e -> {
            if (searchBySkuListener != null) {
                String sku = searchSkuField.getText().trim();
                searchBySkuListener.searchBySku(sku);
            }
        });
        
        refreshListButton.addActionListener(e -> {
            if (refreshListListener != null) {
                refreshListListener.refreshList();
            }
        });

        // Whenever the order radio buttons are changed, we trigger a refresh.
        ascenButton.addActionListener(e -> {
            if (refreshListListener != null) {
                refreshListListener.refreshList();
            }
        });
        descenButton.addActionListener(e -> {
            if (refreshListListener != null) {
                refreshListListener.refreshList();
            }
        });

    }
    
    /**
     * Set the listener for searching by SKU.
     */
    public void addSearchBySkuListener(SearchBySkuListener listener) {
        this.searchBySkuListener = listener;
    }
    
    /**
     * Set the listener for refreshing the list.
     */
    public void addRefreshListListener(RefreshListListener listener) {
        this.refreshListListener = listener;
    }
    
    /**
     * Displays the search result.
     */
    public void showSearchResult(String result) {
        resultArea.setText(result);
    }
    
    /**
     * Updates the home appliance list display.
     */
    public void updateSkuList(String listContent) {
        listArea.setText(listContent);
    }
    
    /**
     * Opens a file chooser dialog.
     */
    public String showFileSelection() {
        return GuiUtil.chooseFile(this);
    }
    
    /**
     * Shows the main window.
     */
    public void showMainWindow() {
        setVisible(true);
    }
    /**
    * Returns true if the current display order is ascending.
    */
    public boolean isAscendingOrder() {
        return ascenButton.isSelected();
    }
    
    /**
     * Shows a message dialog.
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

