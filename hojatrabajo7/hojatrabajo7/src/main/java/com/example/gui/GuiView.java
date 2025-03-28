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

/**
 * Assited by ChatGPT (2025)
 * 
 */
public class GuiView extends JFrame {
    private final JFrame mainFrame;
    // Components for the main program window:
    
    private final JTextField searchNameField;
    private final JButton searchByNameButton;
    
    private final JButton refreshListButton;
    private final JTextArea resultArea;
    private final JTextArea listArea;
    
    // Interfaces for listeners.
    
    
    public interface SearchBySkuListener {
        void searchBySku(String name);
    }
    private SearchBySkuListener searchByNameListener;
    
    
    public interface RefreshListListener {
        void refreshList();
    }
    private RefreshListListener refreshListListener;
    
    public GuiView() {
        mainFrame = new JFrame("Home Appliaince Data Base");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new BorderLayout());
        
        
        // Panel for searching home appliance by sku.
        JPanel searchPanel = new JPanel(new GridLayout(2,1));
        JPanel nameSearchPanel = new JPanel();
        nameSearchPanel.setBorder(BorderFactory.createTitledBorder("Search by Name"));
        searchNameField = new JTextField(10);
        nameSearchPanel.add(new JLabel("SKU found:"));
        nameSearchPanel.add(searchNameField);
        searchByNameButton = new JButton("Search by  SKU");
        nameSearchPanel.add(searchByNameButton);
        
        // Panel for displaying results and the complete list.
        JPanel outputPanel = new JPanel(new GridLayout(2, 1));
        resultArea = new JTextArea(5, 30);
        resultArea.setBorder(BorderFactory.createTitledBorder("Search Result"));
        resultArea.setEditable(false);
        listArea = new JTextArea(5, 30);
        listArea.setBorder(BorderFactory.createTitledBorder("Home Appliance List"));
        listArea.setEditable(false);
        outputPanel.add(new JScrollPane(resultArea));
        outputPanel.add(new JScrollPane(listArea));
        
        refreshListButton = new JButton("Refresh Home Appliance List");
        
        // Main container layout.
        JPanel centerPanel = new JPanel(new BorderLayout());
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
        searchByNameButton.addActionListener(e -> {
            if (searchByNameListener != null) {
                String sku = searchNameField.getText().trim();
                searchByNameListener.searchBySku(sku);
            }
        });
        
        refreshListButton.addActionListener(e -> {
            if (refreshListListener != null) {
                refreshListListener.refreshList();
            }
        });
    }
    
    
    

    /**
     * Set the listener for searching Pokemon by name. This listener will be 
     * notified when the "Search by Name" button is clicked.
     * 
     * @param listener the listener to notify.
     */
    public void addSearchBySkuListener(SearchBySkuListener listener) {
        this.searchByNameListener = listener;
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
     * Updates the SKU list in the view with the given list content.
     * 
     * @param listContent the content of the list to be displayed.
     */
    public void updateSkuList(String listContent) {
        listArea.setText(listContent);
    }
    
    // Methods to display the initial dialogs.
    public String showFileSelection() {
        // Opens a file chooser dialog.
        return GuiUtil.chooseFile(new JFrame("Select File"));
    }
    
    
    /**
     * Shows the main window of the application. This method should be called
     * once the model and controller are set up.
     */
    public void showMainWindow() {
        mainFrame.setVisible(true);
    }
}

