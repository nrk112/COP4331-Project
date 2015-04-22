package cop4331.view;

import cop4331.Resources.ProjectConstants;
import cop4331.controller.InventoryManager;
import cop4331.model.DiscountedProduct;
import cop4331.model.Product;
import cop4331.model.RevenueReportingItem;
import cop4331.model.Seller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * A window that presents to the seller a list of their products
 */
public class SellerListView extends JDialog {

    public SellerListView(Seller user) {

        this.user = user;
        user.populateTransactions();

        //Set window properties
        setModal(true);
        setTitle("Shopazon - " + user.getFullName() + "'s items.");
        setSize(ProjectConstants.WINDOW_WIDTH + 200, ProjectConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Open the window in the center of the screen.
        setLocationRelativeTo(null);

        //Make the main JPanel to use in the Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        //Add new button
        JPanel topPanel = new JPanel();
        JButton addButton = CreateAddNewButton(user);
        topPanel.add(addButton);

        //populate jtable with products
        displayData(InventoryManager.getInstance().getProductList(), user);
        JScrollPane tableContainer = new JScrollPane(tbProducts);
        tbProducts.setFont(ProjectConstants.MEDIUM_FONT);
        tbProducts.setRowHeight(ProjectConstants.TABLE_HEIGHT);

        mainPanel.add(tableContainer, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        //Show the window
        add(mainPanel);
        setVisible(true);
    }

    /**
     * Create the button to add a new product for sale.
     * @param user The seller whos product list will be modified.
     * @return The constructed button.
     */
    private JButton CreateAddNewButton(final Seller user)
    {
        final JButton btn = new JButton("Add New Product");
        btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddProductView(user);
                displayData(InventoryManager.getInstance().getProductList(), user);
            }
        });
        return btn;
    }

    /**
     * Constructs a JTable full af a sellers products for sale.
     * @param ProductsList The list of products to populate the table with.
     * @param user The seller who's products will be displayed.
     */
    private void displayData(List<Product> ProductsList, final Seller user)
    {
        DefaultTableModel aModel = new DefaultTableModel()
        {            //setting the jtable read only
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        int TABLE_SIZE = 12;
        //setting the column name
        Object[] tableColumnNames = new Object[TABLE_SIZE];
        tableColumnNames[0] = "ID";
        tableColumnNames[1] = "Name";
        tableColumnNames[2] = "Description";
        tableColumnNames[3] = "Quantity Remaining";
        tableColumnNames[4] = "Quantity Sold";
        tableColumnNames[5] = "Cost";
        tableColumnNames[6] = "Total Cost";
        tableColumnNames[7] = "Original Price";
        tableColumnNames[8] = "Discounted By";
        tableColumnNames[9] = "Current Price";
        tableColumnNames[10] = "Total Revenue";
        tableColumnNames[11] = "Total Profit";

        aModel.setColumnIdentifiers(tableColumnNames);

        if (ProductsList == null) {
            this.tbProducts.setModel(aModel);
            return;
        }

        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);

        Object[] objects = new Object[TABLE_SIZE];

        Product currentProduct;
        double subTotalCost = 0.0;
        double subTotalRev = 0.0;
        double subTotalProfit = 0.0;

        Iterator productIter = InventoryManager.getInstance().getProductList().iterator();
        while(productIter.hasNext()) {
            currentProduct = (Product) productIter.next();
            if(currentProduct.getSellerID() == user.getID())
            {
                double discountedBy = 0.0;
                if(currentProduct instanceof DiscountedProduct)
                {
                    discountedBy = ((DiscountedProduct)currentProduct).getDiscountedBy();
                }
                RevenueReportingItem rri = user.getRevenueReportingItem(currentProduct);

                objects[0] = currentProduct.getProductID();
                objects[1] = currentProduct.getName();
                objects[2] = currentProduct.getDescription();
                objects[3] = currentProduct.getQuantity();//rri.getRemainingQuantity(currentProduct.getQuantity());
                objects[4] = rri.getTotalQuantitySold();
                objects[5] = currencyFormat.format(currentProduct.getCost());
                objects[6] = currencyFormat.format(rri.getTotalCost());
                objects[7] = currencyFormat.format(currentProduct.getPrice());
                objects[8] = (discountedBy==0)? "--" : percentageFormat.format(discountedBy/100);
                objects[9] = currencyFormat.format(currentProduct.getCurrentPrice());
                objects[10] = currencyFormat.format(rri.getTotalRevenue());
                objects[11] = currencyFormat.format(rri.getProfit());

                aModel.addRow(objects);
                subTotalCost += rri.getTotalCost();
                subTotalRev += rri.getTotalRevenue();
                subTotalProfit += rri.getProfit();

            }
        }

        if(subTotalCost>0){
            objects[0] = "Totals:";
            objects[1] = "";
            objects[2] = "";
            objects[3] = "";
            objects[4] = "";
            objects[5] = "";
            objects[6] = currencyFormat.format(subTotalCost);
            objects[7] = "";
            objects[8] = "";
            objects[9] = "";
            objects[10] = currencyFormat.format(subTotalRev);
            objects[11] = currencyFormat.format(subTotalProfit);
            aModel.addRow(objects);

            objects[0] = "Profit Margin:";
            objects[1] = "";
            objects[2] = "";
            objects[3] = "";
            objects[4] = "";
            objects[5] = "";
            objects[6] = "";
            objects[7] = "";
            objects[8] = "";
            objects[9] = "";
            objects[10] = "";
            objects[11] = percentageFormat.format((subTotalProfit/subTotalCost));
            aModel.addRow(objects);
        }
        tbProducts.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {//alternate background color for rows
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setToolTipText("Double click anywhere in this row to edit this product.");
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.white : Color.LIGHT_GRAY);
                    if(column==0){
                        c.setHorizontalAlignment(JLabel.LEFT);
                    }
                    else if(column < 4)
                    {
                        if (value instanceof Integer)
                        {
                            c.setHorizontalAlignment(JLabel.CENTER);
                        }
                        else
                        {
                            c.setHorizontalAlignment(JLabel.RIGHT);
                        }
                    }
                    else
                    {
                        c.setHorizontalAlignment(JLabel.RIGHT);
                    }
                    if(row == table.getRowCount()-1) c.setFont(c.getFont().deriveFont(Font.BOLD));
                }
                return c;
            }
        });
        //binding the jtable to the cop4331.model
        tbProducts.setModel(aModel);
        tbProducts.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2 && e.getSource() instanceof JTable) {

                    JTable jTable = (JTable) e.getSource();
                    int row = jTable.getSelectedRow();
                    int column = 0;
                    int productID;

                    try {
                        productID = (Integer) jTable.getValueAt(row, column);
                        new EditProductView(productID, user);
                        displayData(InventoryManager.getInstance().getProductList(), user);
                    }catch (ClassCastException exception) {
                        //If its not an integer that means it is the total fields and we just want to ignore it.
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


    private JTable tbProducts = new JTable();
    private Seller user;
}
