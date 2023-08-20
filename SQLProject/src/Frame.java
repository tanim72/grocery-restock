

import java.util.*;

import java.lang.NullPointerException;
//import java.awt.*;

import javax.swing.*;


/**
 *
 * @author tanishamehta
 */
public class Frame extends javax.swing.JFrame {
    private Supermarket storeTani; //initalize the queues and arraylists as well as the supermarket that manipulates them
    private ArrayList<Product> currentStock;
    private Queue<Product> restockList;


    /**
     * Creates new form Frame
     */
    public Frame() {
        initComponents(); //enter file name
        try {
            String textFile = JOptionPane.showInputDialog(rootPane, "Enter File Name");
            storeTani = new Supermarket(textFile);
            changeDropDowns(); //edit info in drop down
        }
        catch (Exception e) { //exception handiling
            JOptionPane.showMessageDialog(rootPane, "No Such File"); //display error handiling if file not found
            System.exit(0); //if they exit file window (textFile = null), close progrma
            
        }

    }

    public void changeDropDowns()   {  //to edit the info in drop down
        currentDay.setText("Current Day: " + storeTani.getCurrDate().getExpiryDate()); //update current day text
        currentStock = storeTani.getCurrStock(); 
        restockList = storeTani.getQueue();
        marketStock.removeAll(); //remove everything from the lists
        restockItems.removeAll();
        ArrayList<String> restock = new ArrayList<String>();
        if(restockList!=null) {
            for(Product p : restockList) {
                restock.add(p.toString()); //add to restock pile
            }
        }
        
        String[] currRestock = new String[restock.size()];
        String[] currStock = new String[currentStock.size()]; //turn arraylists into arrays
        for(int i = 0; i<currStock.length; i++) {            
            currStock[i] = currentStock.get(i).toString();
        }
        for(int i = 0; i<currRestock.length; i++) {            
            currRestock[i] = restock.get(i);
        }
        marketStock.setListData(currStock); //set new list items based on changes made after buttons were pressed
        restockItems.setListData(currRestock);


        
    }
    
    
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        //initalizing all the components

        currentDay = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        marketStock = new javax.swing.JList<>();
        restockItemsButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        nextDay = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        restockAll = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        restockItems = new javax.swing.JList<>();
        restockTitle = new javax.swing.JLabel();
        marketStockTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //set all the text for every single button and the action performed listener
        currentDay.setText("Current Day: ");

        addButton.setText("Add New Item");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        marketStock.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(marketStock);

        restockItemsButton.setText("Restock Next Item");
        restockItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockItemsButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete Selected Item");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        nextDay.setText("Next Day");
        nextDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextDayActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        restockAll.setText("Restock All Items");
        restockAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockAllActionPerformed(evt);
            }
        });

        restockItems.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(restockItems);

        restockTitle.setText("New Orders (expired or removed):");

        marketStockTitle.setText("Market Stock:");

        //organize layout of buttons and texts and scrollpanes

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(currentDay)
                .addGap(176, 176, 176))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(restockTitle)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(restockAll)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(deleteButton))
                                    .addComponent(restockItemsButton)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nextDay, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addButton)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(marketStockTitle))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentDay)
                    .addComponent(exitButton))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(deleteButton)
                        .addGap(18, 18, 18)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nextDay)
                        .addGap(24, 24, 24)
                        .addComponent(restockAll)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(marketStockTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(restockItemsButton)
                    .addComponent(restockTitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>                        

    
    private void restockItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {         //is restock items button pressed                                           
        // TODO add your handling code here:
        try {
            String expiryDate = JOptionPane.showInputDialog(rootPane, "Expiry Date in the format 'dd/mm/yy'"); //ask user for new expiry date
            storeTani.restockItem(expiryDate); //restock item 
            
            changeDropDowns(); //update frame/what you see
        }
        catch(NumberFormatException e) { //exception handling if wrong date 
            JOptionPane.showMessageDialog(rootPane, "please enter valid date in the format: dd/mm/yy");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(rootPane, "please enter valid date in the format: dd/mm/yy");
        }
        
        catch(NullPointerException e) {
            //JOptionPane.showMessageDialog(rootPane, "please enter a value");
        } 
        catch(NoSuchElementException e) { //exception handling if restock pile is empty
            JOptionPane.showMessageDialog(rootPane, "nothing to restock");
        } 
        
        

        
    }                                                  

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) { //if delete button pressed                                    
        // TODO add your handling code here:
        int index = marketStock.getSelectedIndex(); //find index of item
        if(index>=0) {
            storeTani.removeItem(currentStock.get(index)); //remove from array list and delete from market stock and add to restock
            changeDropDowns(); //update what you see
        } 
        else 
            JOptionPane.showMessageDialog(rootPane, "please select item");
        
    }                                            

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) { //if add new item                                    
        // TODO add your handling code here:
        String itemName = JOptionPane.showInputDialog(rootPane, "Product Name: "); //ask for product name
        try {
            String expiryDate = JOptionPane.showInputDialog(rootPane, "Expiry Date in the format 'dd/mm/yy'"); //ask for expiry date
            storeTani.addItem(new Product(itemName,expiryDate)); //add new product
            
            changeDropDowns(); //update frame/what you see
        }
        catch(NumberFormatException e) { //error handling if wrong date inputted
            JOptionPane.showMessageDialog(rootPane, "please enter valid date in the format: dd/mm/yy");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(rootPane, "please enter valid date in the format: dd/mm/yy");
        }
        catch(NullPointerException e) {
            //JOptionPane.showMessageDialog(rootPane, "please enter a value");
        }



            
    }                                         

    private void nextDayActionPerformed(java.awt.event.ActionEvent evt) {   //if next day is pressed                                      
        // TODO add your handling code here:
        
        storeTani.updateStack();   //update stack by removing expiry items from market stock and putting it into restock         
        changeDropDowns(); //update frame/what you see
        
        
        
         
    }                                       

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {   //if exit button pressed                                        
        // TODO add your handling code here:
        storeTani.closeConnection(); //close connection, print database information out to check
        System.exit(0); //exit program
    }                                          

    private void restockAllActionPerformed(java.awt.event.ActionEvent evt) {  //if restock all pressed                                          
        // TODO add your handling code here:
        try {
            String expiryDate = JOptionPane.showInputDialog(rootPane, "Expiry Date in the format 'dd/mm/yy'"); //prompt user for new expiry date for all items
            storeTani.restockAllItems(expiryDate); //restock all items at once
            changeDropDowns(); //update what you see
        }
        catch(NumberFormatException e) { //exception handling for invalid date input
            JOptionPane.showMessageDialog(rootPane, "please enter valid date in the format: dd/mm/yy");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(rootPane, "please enter valid date in the format: dd/mm/yy");
        }
        
        catch(NullPointerException e) {
            //JOptionPane.showMessageDialog(rootPane, "please enter a value");
        } 
        catch(NoSuchElementException e) { //exception handling once we reach the end of restock
            changeDropDowns();
        } 
    }                                              

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify  
    
    //initalize all buttons, labels, lists, and scrollpanes
    private javax.swing.JButton addButton;
    private javax.swing.JLabel currentDay;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton restockAll;
    private javax.swing.JButton exitButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> marketStock;
    private javax.swing.JLabel marketStockTitle;
    private javax.swing.JButton nextDay;
    private javax.swing.JList<String> restockItems;
    private javax.swing.JButton restockItemsButton;
    private javax.swing.JLabel restockTitle;
    // End of variables declaration                   
}