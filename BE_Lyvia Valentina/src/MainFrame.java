import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {
    private CRUDDatabaseService crudService;
    private JTabbedPane tabbedPane;
    private JPanel penjualPanel, pembeliPanel, itemsPanel, transaksiPanel;
    private JTable penjualTable, pembeliTable, itemsTable, transaksiTable;
    private DefaultTableModel penjualModel, pembeliModel, itemsModel, transaksiModel;

    public MainFrame() {
        crudService = new CRUDDatabaseService();
        initComponents();
        setTitle("Jual dan Beli Barang Bekas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Mengatur Look and Feel modern
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Membuat Tabbed Pane
        tabbedPane = new JTabbedPane();
        
        // Panel Penjual
        penjualPanel = createpenjualPanel();
        tabbedPane.addTab("Penjual", penjualPanel);

        // Panel Pembeli
        pembeliPanel = createpembeliPanel();
        tabbedPane.addTab("Pembeli", pembeliPanel);

        // Panel Items
        itemsPanel = createitemsPanel();
        tabbedPane.addTab("Items", itemsPanel);
        
        // Panel Transaksi
        transaksiPanel = createtransaksiPanel();
        tabbedPane.addTab("Transaksi", transaksiPanel);

        // Menambahkan Tabbed Pane ke Frame
        add(tabbedPane);
    }

    private JPanel createpenjualPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Tabel Mahasiswa
        String[] penjualColumns = {"ID", "Username", "Password", "Email"};
        penjualModel = new DefaultTableModel(penjualColumns, 0);
        penjualTable = new JTable(penjualModel);
        
        JScrollPane scrollPane = new JScrollPane(penjualTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel Kontrol
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Tambah");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Hapus");
        JButton refreshButton = new JButton("Refresh");

        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(refreshButton);

        panel.add(controlPanel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(e -> tambahpenjual());
        editButton.addActionListener(e -> editpenjual());
        deleteButton.addActionListener(e -> hapuspenjual());
        refreshButton.addActionListener(e -> loadpenjualData());

        loadpenjualData();
        return panel;
    }

    private JPanel createpembeliPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Tabel Pembeli
        String[] pembeliColumns = {"ID", "Username", "Password", "Email"};
        pembeliModel = new DefaultTableModel(pembeliColumns, 0);
        pembeliTable = new JTable(pembeliModel);
        
        JScrollPane scrollPane = new JScrollPane(pembeliTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel Kontrol
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Tambah");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Hapus");
        JButton refreshButton = new JButton("Refresh");

        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(refreshButton);

        panel.add(controlPanel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(e -> tambahpembeli());
        editButton.addActionListener(e -> editpembeli());
        deleteButton.addActionListener(e -> hapuspembeli());
        refreshButton.addActionListener(e -> loadpembeliData());

        loadpembeliData();
        return panel;
    }

    private JPanel createitemsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Tabel items
        String[] itemsColumns = {"ID Item", "Nama", "Deskripsi", "Harga", "Status", "ID Pembeli"};
        itemsModel = new DefaultTableModel(itemsColumns, 0);
        itemsTable = new JTable(itemsModel);
        
        JScrollPane scrollPane = new JScrollPane(itemsTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel Kontrol
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Tambah");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Hapus");
        JButton refreshButton = new JButton("Refresh");

        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(refreshButton);

        panel.add(controlPanel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(e -> tambahitems());
        editButton.addActionListener(e -> edititems());
        deleteButton.addActionListener(e -> hapusitems());
        refreshButton.addActionListener(e -> loaditemsData());

        loaditemsData();
        return panel;
    }
    
    private JPanel createtransaksiPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Tabel items
        String[] transaksiColumns = {"ID Transaksi", "ID Item", "ID Pembeli", "Tanggal Transaksi", "Total Harga"};
        transaksiModel = new DefaultTableModel(transaksiColumns, 0);
        transaksiTable = new JTable(transaksiModel);
        
        JScrollPane scrollPane = new JScrollPane(transaksiTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel Kontrol
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Tambah");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Hapus");
        JButton refreshButton = new JButton("Refresh");

        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(refreshButton);

        panel.add(controlPanel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(e -> tambahtransaksi());
        editButton.addActionListener(e -> edittransaksi());
        deleteButton.addActionListener(e -> hapustransaksi());
        refreshButton.addActionListener(e -> loadtransaksiData());

        loadtransaksiData();
        return panel;
    }

    // Metode CRUD untuk Penjual
    private void loadpenjualData() {
        penjualModel.setRowCount(0);
        List<penjual> penjualList = crudService.readpenjual();
        for (penjual pjl : penjualList) {
            penjualModel.addRow(new Object[]{
                pjl.getUser_id(), 
                pjl.getUsername(), 
                pjl.getPassword(),
                pjl.getEmail()
            });
        }
    }

    private void tambahpenjual() {
        JTextField user_idField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField emailField = new JTextField();

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("ID Penjual:"));
        inputPanel.add(user_idField);
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, 
                "Tambah Penjual", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int user_id = Integer.parseInt(user_idField.getText());
                String username = usernameField.getText();
                String password = passwordField.getText();
                String email = emailField.getText();
                
                crudService.createpenjual(user_id, username, password, email);
                loadpenjualData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!");
            }
        }
    }

    private void editpenjual() {
        int selectedRow = penjualTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih penjual yang akan diedit");
            return;
        }

        int user_id = (int) penjualModel.getValueAt(selectedRow, 0);
        String username = (String) penjualModel.getValueAt(selectedRow, 1);
        String password = (String) penjualModel.getValueAt(selectedRow, 2);
        String email = (String) penjualModel.getValueAt(selectedRow, 3);

        JTextField user_idField = new JTextField(String.valueOf(user_id));
        JTextField usernameField = new JTextField(username);
        JTextField passwordField = new JTextField(password);
        JTextField emailField = new JTextField(email);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, 
                "Edit Penjual", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String newusername = usernameField.getText();
                String newpassword = passwordField.getText();
                String newemail = emailField.getText();
                
                crudService.updatepenjual(user_id, newusername, newpassword, newemail);
                loadpenjualData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!");
            }
        }
    }

    private void hapuspenjual() {
        int selectedRow = penjualTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih penjual yang akan dihapus");
            return;
        }

        int id = (int) penjualModel.getValueAt(selectedRow, 0);
        int konfirmasi = JOptionPane.showConfirmDialog(null, 
            "Anda yakin ingin menghapus penjual ini?", 
            "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == JOptionPane.YES_OPTION) {
            crudService.deletepenjual(id);
            loadpenjualData();
        }
    }
    
    // Metode CRUD untuk Pembeli
    private void loadpembeliData() {
        pembeliModel.setRowCount(0);
        List<pembeli> pembeliList = crudService.readpembeli();
        for (pembeli pmb : pembeliList) {
            pembeliModel.addRow(new Object[]{
                pmb.getUser_id(), 
                pmb.getUsername(), 
                pmb.getPassword(),
                pmb.getEmail()
            });
        }
    }

    private void tambahpembeli() {
        JTextField user_idField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField emailField = new JTextField();

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("ID Pembeli:"));
        inputPanel.add(user_idField);
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, 
                "Tambah Pembeli", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int user_id = Integer.parseInt(user_idField.getText());
                String username = usernameField.getText();
                String password = passwordField.getText();
                String email = emailField.getText();
                
                crudService.createpembeli(user_id, username, password, email);
                loadpembeliData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!");
            }
        }
    }

    private void editpembeli() {
        int selectedRow = pembeliTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih pembeli yang akan diedit");
            return;
        }

        int user_id = (int) pembeliModel.getValueAt(selectedRow, 0);
        String username = (String) pembeliModel.getValueAt(selectedRow, 1);
        String password = (String) pembeliModel.getValueAt(selectedRow, 2);
        String email = (String) pembeliModel.getValueAt(selectedRow, 3);

        JTextField user_idField = new JTextField(String.valueOf(user_id));
        JTextField usernameField = new JTextField(username);
        JTextField passwordField = new JTextField(password);
        JTextField emailField = new JTextField(email);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, 
                "Edit Pembeli", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String newusername = usernameField.getText();
                String newpassword = passwordField.getText();
                String newemail = emailField.getText();
                
                crudService.updatepembeli(user_id, newusername, newpassword, newemail);
                loadpembeliData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!");
            }
        }
    }

    private void hapuspembeli() {
        int selectedRow = pembeliTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih pembeli yang akan dihapus");
            return;
        }

        int id = (int) pembeliModel.getValueAt(selectedRow, 0);
        int konfirmasi = JOptionPane.showConfirmDialog(null, 
            "Anda yakin ingin menghapus pembeli ini?", 
            "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == JOptionPane.YES_OPTION) {
            crudService.deletepembeli(id);
            loadpembeliData();
        }
    }

    // Metode CRUD untuk Mata Kuliah
    private void loaditemsData() {
        itemsModel.setRowCount(0);
        List<items> itemsList = crudService.readitems();
        for (items it : itemsList) {
            itemsModel.addRow(new Object[]{
                it.getItem_id(), 
                it.getName(),
                it.getDescription(),
                it.getPrice(),
                it.getStatus(),
                it.getUser_id()
            });
        }
    }

    private void tambahitems() {
        JTextField item_idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField statusField = new JTextField();
        JTextField user_idField = new JTextField();

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("ID Item:"));
        inputPanel.add(item_idField);
        inputPanel.add(new JLabel("Nama:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Deskripsi:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Harga:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Status:"));
        inputPanel.add(statusField);
        inputPanel.add(new JLabel("ID penjual:"));
        inputPanel.add(user_idField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, 
                "Tambah Item", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int item_id = Integer.parseInt(item_idField.getText());
                String name = nameField.getText();
                String description = descriptionField.getText();
                int price = Integer.parseInt(priceField.getText());
                String status = statusField.getText();
                int user_id = Integer.parseInt(user_idField.getText());
                
                crudService.createitems(item_id, name, description, price, status, user_id);
                loaditemsData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!");
            }
        }
    }

    private void edititems() {
        int selectedRow = itemsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih item yang akan diedit");
            return;
        }

        int item_id = (int) itemsModel.getValueAt(selectedRow, 0);
        String name = (String) itemsModel.getValueAt(selectedRow, 1);
        String description = (String) itemsModel.getValueAt(selectedRow, 2);
        int price = (int) itemsModel.getValueAt(selectedRow, 3);
        String status = (String) itemsModel.getValueAt(selectedRow, 4);
        int user_id = (int) itemsModel.getValueAt(selectedRow, 5);
        
        JTextField nameField = new JTextField(name);
        JTextField descriptionField = new JTextField(description);
        JTextField priceField = new JTextField(price);
        JTextField statusField = new JTextField(status);
        JTextField user_idField = new JTextField(user_id);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Nama Item:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Dekripsi Item:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Harga Item:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Status Item:"));
        inputPanel.add(statusField);
        inputPanel.add(new JLabel("ID Penjual:"));
        inputPanel.add(user_idField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, 
                "Edit Item", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String newname = nameField.getText();
            String newdescription = descriptionField.getText();
            int newprice = Integer.parseInt(priceField.getText());
            String newstatus = statusField.getText();
            int newuser_id = Integer.parseInt(user_idField.getText());
            
            crudService.updateitems(item_id, newname, newdescription, newprice, newstatus, newuser_id);
            loaditemsData();
        }
    }

    private void hapusitems() {
        int selectedRow = itemsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih items yang akan dihapus");
            return;
        }

        int id = (int) itemsModel.getValueAt(selectedRow, 0);
        int konfirmasi = JOptionPane.showConfirmDialog(null, 
            "Anda yakin ingin menghapus items ini?", 
            "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == JOptionPane.YES_OPTION) {
            crudService.deleteitems(id);
            loaditemsData();
        }
    }

    // Metode CRUD untuk Transaksi
    private void loadtransaksiData() {
        transaksiModel.setRowCount(0);
        List<transaksi> transaksiList = crudService.readtransaksi();
        for (transaksi ts : transaksiList) {
            transaksiModel.addRow(new Object[]{
                ts.getTransaksi_id(), 
                ts.getItem_id(),
                ts.getUser_id(),
                ts.getTanggal_transaksi(),
                ts.getTotal_price()
            });
        }
    }

    private void tambahtransaksi() {
        JTextField transaksi_idField = new JTextField();
        JTextField Item_idField = new JTextField();
        JTextField User_idField = new JTextField();
        JTextField tanggal_transaksiField = new JTextField();
        JTextField total_priceField = new JTextField();

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("ID Transaksi:"));
        inputPanel.add(transaksi_idField);
        inputPanel.add(new JLabel("ID Item:"));
        inputPanel.add(Item_idField);
        inputPanel.add(new JLabel("ID Pembeli:"));
        inputPanel.add(User_idField);
        inputPanel.add(new JLabel("Tanggal Transaksi:"));
        inputPanel.add(tanggal_transaksiField);
        inputPanel.add(new JLabel("Total Harga:"));
        inputPanel.add(total_priceField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, 
                "Tambah Transaksi", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int transaksi_id = Integer.parseInt(transaksi_idField.getText());
                int item_id = Integer.parseInt(Item_idField.getText());
                int user_id = Integer.parseInt(User_idField.getText());
                String tanggal_transaksi = tanggal_transaksiField.getText();
                int total_price = Integer.parseInt(total_priceField.getText());
                
                crudService.createtransaksi(transaksi_id, item_id, user_id, tanggal_transaksi, total_price);
                loadtransaksiData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!");
            }
        }
    }

    private void edittransaksi() {
        int selectedRow = transaksiTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih transaksi yang akan diedit");
            return;
        }

        int transaksi_id = (int) transaksiModel.getValueAt(selectedRow, 0);
        int item_id = (int) transaksiModel.getValueAt(selectedRow, 1);
        int user_id = (int) transaksiModel.getValueAt(selectedRow, 2);
        String tanggal_transaksi = (String) transaksiModel.getValueAt(selectedRow, 3);
        int total_price = (int) transaksiModel.getValueAt(selectedRow, 4);

        JTextField item_idField = new JTextField(String.valueOf(item_id));
        JTextField user_idField = new JTextField(String.valueOf(user_id));
        JTextField tanggal_transaksiField = new JTextField(String.valueOf(tanggal_transaksi));
        JTextField total_priceField = new JTextField(String.valueOf(total_price));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Id Item:"));
        inputPanel.add(item_idField);
        inputPanel.add(new JLabel("ID Pembeli:"));
        inputPanel.add(user_idField);
        inputPanel.add(new JLabel("Tanggal Transaksi:"));
        inputPanel.add(tanggal_transaksiField);
        inputPanel.add(new JLabel("Total Harga:"));
        inputPanel.add(total_priceField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel, 
                "Edit Transaksi", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int newitem_id = Integer.parseInt(item_idField.getText());
                int newuser_id = Integer.parseInt(user_idField.getText());
                String newtanggal_transaksi = tanggal_transaksiField.getText();
                int newtotal_price = Integer.parseInt(total_priceField.getText());
                
                crudService.updatetransaksi(transaksi_id, newitem_id, newuser_id, newtanggal_transaksi, newtotal_price);
                loadtransaksiData();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!");
            }
        }
    }

    private void hapustransaksi() {
        int selectedRow = transaksiTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih transaksi yang akan dihapus");
            return;
        }

        int idtransaksi = (int) transaksiModel.getValueAt(selectedRow, 0);
        int konfirmasi = JOptionPane.showConfirmDialog(null, 
            "Anda yakin ingin menghapus transaksi ini?", 
            "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == JOptionPane.YES_OPTION) {
            crudService.deletetransaksi(idtransaksi);
            loadtransaksiData();
        }
    }

    // Metode main untuk menjalankan aplikasi
    public static void main(String[] args) {
        // Menggunakan Event Dispatch Thread untuk keamanan thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Mengatur Look and Feel modern Nimbus
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                // Jika Nimbus tidak tersedia, gunakan look and feel default
                e.printStackTrace();
            }

            // Membuat dan menampilkan frame utama
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}