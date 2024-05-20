package Pengembalian;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Sewa.Sewa;
import Transaksi.Transaksi;
import javax.swing.table.DefaultTableModel;
import KoneksiDB.KoneksiDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import Dashboard.Dashboard;
import GajiKaryawan.GajiKaryawan;
/**
 *
 * @author abiba
 */
public class Pengembalian extends javax.swing.JFrame {
    //  Dashboard Dashboard = new Dashboard();
    DefaultTableModel dtm = new DefaultTableModel();
    KoneksiDB KoneksiDB = new KoneksiDB();
    DefaultTableModel dtm2 = new DefaultTableModel();

    /**
     * Creates new form Pengembalian
     */
    public Pengembalian() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Tanggal();
        Waktu();
        AturKolom();
        AturBaris();
        generateReturnNumber();
        getItem();
        jComboTanggal();
        AturKolom2();
        AturBaris2();
    }

    public void Tanggal() {

        Thread p = new Thread() {
            public void run() {
                for (;;) {

                    Calendar kal = new GregorianCalendar();
                    int hari = kal.get(Calendar.DAY_OF_MONTH);
                    int bulan = kal.get(Calendar.MONTH) + 1;
                    int tahun = kal.get(Calendar.YEAR);
                    String tanggal = hari + "/" + bulan + "/" + tahun;
                    jTanggal2.setText(tanggal);
                    try {
                        sleep(1000);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            }
        };
        p.start();
    }
    
    public void Waktu() {
        Thread p = new Thread() {
            public void run() {
                for (;;) {
                    Calendar kal = new GregorianCalendar();
                    int jam = kal.get(Calendar.HOUR_OF_DAY);
                    int menit = kal.get(Calendar.MINUTE);
                    int detik = kal.get(Calendar.SECOND);
                    String tanggal = +jam + ":" + menit + ":" + detik;
                    jJam.setText(tanggal);
                    try {
                        sleep(1000);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                }
            }
        };
        p.start();
    }

    public void AturKolom() {
        dtm.addColumn("ID Pengembalian");
        dtm.addColumn("ID Transaksi");
        dtm.addColumn("ID Produk");
        dtm.addColumn("Tanggal");
        dtm.addColumn("Harga");
        dtm.addColumn("Status");
        jPengembalian.setModel(dtm);
    }

    public void AturBaris() {
        dtm.setRowCount(0);
        jPengembalian.setModel(dtm);
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM Pengembalian");
        try {
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("ID_Pengembalian"), rs.getString("ID_Transaksi"), rs.getString("ID_Produk"), rs.getString("Tanggal"), rs.getString("Harga"), rs.getString("Status")});
            }
            jPengembalian.setModel(dtm);
        } catch (Exception e) {
        }
    }

    public void generateReturnNumber() {
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM pengembalian ORDER BY ID_Pengembalian DESC");
        try {
            rs.next();
            String id = rs.getString("ID_Pengembalian");
            String[] arr = id.split("PNG");
            int idNum = Integer.parseInt(arr[1]);
            idNum++;
            String result = "";
            if (idNum < 9) {
                result = "000" + idNum;
            } else if (idNum < 99) {
                result = "00" + idNum;
            } else if (idNum < 999) {
                result = "" + idNum;
            }
            jIDpengembalian.setText("PNG" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getItem() {
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM Transaksi");
        jComboIDTransaksi.addItem("--Pilih Item--");
        try {
            while (rs.next()) {
                jComboIDTransaksi.addItem(rs.getString("ID_Transaksi"));
            }
        } catch (Exception e) {

        }
    }

    public void jComboTanggal() {
        LocalDate tanggalSekarang = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        for (int i = -7; i < 7; i++) {
            String tanggalTerformat = tanggalSekarang.plusDays(i).format(formatter);
            jComboTanggal1.addItem(tanggalTerformat);
        }
    }

    public void AturKolom2() {
        dtm2.addColumn("ID Produk");
        dtm2.addColumn("Nama Produk");
        dtm2.addColumn("Jenis");
        dtm2.addColumn("Jumlah Stok");
        dtm2.addColumn("Harga");
        jTableStok.setModel(dtm2);
    }

    public void AturBaris2() {
        dtm2.setRowCount(0);
        jTableStok.setModel(dtm2);
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM Stok");
        try {
            while (rs.next()) {
                dtm2.addRow(new Object[]{rs.getString("ID_Produk"), rs.getString("Nama_Produk"), rs.getString("Jenis"), rs.getString("Jumlah_Stok"), rs.getString("Harga")});
            }
            jTableStok.setModel(dtm2);
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSewa = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jJam = new javax.swing.JTextField();
        jSewa1 = new javax.swing.JButton();
        jTransaksi = new javax.swing.JButton();
        jGaji = new javax.swing.JButton();
        ID_Pengembalian = new javax.swing.JLabel();
        jIDpengembalian = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboIDTransaksi = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboStatus = new javax.swing.JComboBox<>();
        jComboTanggal1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPengembalian = new javax.swing.JTable();
        jSimpan = new javax.swing.JButton();
        jEdit = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jHarga = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTanggal2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableStok = new javax.swing.JTable();
        jIDproduk = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(1920, 1080));

        jPanel2.setBackground(new java.awt.Color(49, 170, 49));
        jPanel2.setPreferredSize(new java.awt.Dimension(174, 401));

        jSewa.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jSewa.setText("Dashboard");
        jSewa.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jSewa.setBorderPainted(false);
        jSewa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSewa.setMaximumSize(new java.awt.Dimension(88, 23));
        jSewa.setMinimumSize(new java.awt.Dimension(88, 23));
        jSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSewaMouseClicked(evt);
            }
        });
        jSewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSewaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Waktu");

        jJam.setEditable(false);
        jJam.setBackground(new java.awt.Color(49, 170, 49));
        jJam.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jJam.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jJam.setBorder(null);
        jJam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJamActionPerformed(evt);
            }
        });

        jSewa1.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jSewa1.setText("Stok");
        jSewa1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jSewa1.setBorderPainted(false);
        jSewa1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSewa1.setMaximumSize(new java.awt.Dimension(88, 23));
        jSewa1.setMinimumSize(new java.awt.Dimension(88, 23));
        jSewa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSewa1MouseClicked(evt);
            }
        });
        jSewa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSewa1ActionPerformed(evt);
            }
        });

        jTransaksi.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jTransaksi.setText("Transaksi");
        jTransaksi.setBorderPainted(false);
        jTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTransaksi.setPreferredSize(new java.awt.Dimension(103, 23));
        jTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTransaksiMouseClicked(evt);
            }
        });
        jTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransaksiActionPerformed(evt);
            }
        });

        jGaji.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jGaji.setText("Gaji");
        jGaji.setBorderPainted(false);
        jGaji.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jGaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jGajiMouseClicked(evt);
            }
        });
        jGaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGajiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSewa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jJam, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jSewa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jGaji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jJam, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jSewa1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jGaji, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(377, Short.MAX_VALUE))
        );

        ID_Pengembalian.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        ID_Pengembalian.setText("ID_Pengembalian");

        jIDpengembalian.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel3.setText("ID_Transaksi");

        jComboIDTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboIDTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboIDTransaksiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel4.setText("Tanggal ");

        jLabel5.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel5.setText("Status");

        jComboStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Status--", "Sudah", "Belum", "Terlambat" }));

        jComboTanggal1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboTanggal1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Tanggal--" }));
        jComboTanggal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboTanggal1ActionPerformed(evt);
            }
        });

        jPengembalian.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jPengembalian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Pengembalian", "ID Transaksi", "ID Produk", "Tanggal", "Harga", "Status"
            }
        ));
        jPengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPengembalianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jPengembalian);

        jSimpan.setBackground(new java.awt.Color(51, 255, 51));
        jSimpan.setText("Simpan");
        jSimpan.setPreferredSize(new java.awt.Dimension(77, 26));
        jSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSimpanActionPerformed(evt);
            }
        });

        jEdit.setBackground(new java.awt.Color(51, 51, 255));
        jEdit.setText("Edit");
        jEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditActionPerformed(evt);
            }
        });

        jHapus.setBackground(new java.awt.Color(255, 0, 0));
        jHapus.setText("Hapus");
        jHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHapusActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel7.setText("Harga");

        jHarga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(1920, 121));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProjectUAS2/sibhp3.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pengembalian Barang Sewa ");

        jTanggal2.setEditable(false);
        jTanggal2.setBackground(new java.awt.Color(0, 0, 0));
        jTanggal2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTanggal2.setForeground(new java.awt.Color(255, 255, 255));
        jTanggal2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTanggal2.setBorder(null);
        jTanggal2.setFocusable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(554, 554, 554)
                .addComponent(jTanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTableStok.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTableStok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Produk", "Nama Produk", "Jenis", "Jumlah Stok", "Harga"
            }
        ));
        jScrollPane2.setViewportView(jTableStok);

        jLabel6.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel6.setText("ID PRODUK");

        jLabel8.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel8.setText("Data Pengembalian");

        jLabel9.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel9.setText("Data Barang dan Jasa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ID_Pengembalian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jIDproduk)
                            .addComponent(jIDpengembalian)
                            .addComponent(jComboIDTransaksi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboTanggal1, 0, 243, Short.MAX_VALUE)
                            .addComponent(jComboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jHarga))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(461, 461, 461))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(554, 554, 554))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(359, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(782, 782, 782)
                        .addComponent(jLabel8)
                        .addContainerGap())))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ID_Pengembalian)
                                    .addComponent(jIDpengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jComboIDTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jIDproduk, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                        .addGap(1, 1, 1)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jComboTanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addComponent(jLabel8)
                                .addGap(15, 15, 15)))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboIDTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboIDTransaksiActionPerformed
        // TODO add your handling code here:
        if (!jComboIDTransaksi.getSelectedItem().equals("--Pilih Item--")) {
            ResultSet rs = KoneksiDB.ambilData("SELECT * FROM Transaksi WHERE ID_Transaksi = '" + jComboIDTransaksi.getSelectedItem() + "'");
            try {
                rs.next();
                int harga = Integer.parseInt(rs.getString("Harga"));
                jIDproduk.setText(rs.getString("ID_Produk"));
                jHarga.setText(String.valueOf(harga));
                jHarga.setText(String.valueOf(Integer.parseInt(jHarga.getText())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jComboIDTransaksiActionPerformed

    private void jPengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPengembalianMouseClicked
        // TODO add your handling code here:
        int n = jPengembalian.getSelectedRow();
        jIDpengembalian.setText(String.valueOf(jPengembalian.getValueAt(n, 0)));
        jComboIDTransaksi.setSelectedItem(String.valueOf(jPengembalian.getValueAt(n, 1)));
        jComboTanggal1.setSelectedItem(String.valueOf(jPengembalian.getValueAt(n, 2)));
        jHarga.setText(String.valueOf(jPengembalian.getValueAt(n, 3)));
        jComboStatus.setSelectedItem(String.valueOf(jPengembalian.getValueAt(n, 4)));
    }//GEN-LAST:event_jPengembalianMouseClicked

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        // TODO add your handling code here:
        int n = jPengembalian.getSelectedRow();
        String ID_Pengembalian = String.valueOf(jPengembalian.getValueAt(n, 0));
        KoneksiDB.aksi("Delete from Pengembalian WHERE ID_Pengembalian = '" + ID_Pengembalian + "'");
        AturBaris();
        generateReturnNumber();
    }//GEN-LAST:event_jHapusActionPerformed

    private void jComboTanggal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboTanggal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboTanggal1ActionPerformed

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        // TODO add your handling code here
        try {
            String ID_Pengembalian = (String) jIDpengembalian.getText();
            String ID_Transaksi = (String) jComboIDTransaksi.getSelectedItem();
            String ID_Produk = (String) jIDproduk.getText();
            String Tanggal = (String) jComboTanggal1.getSelectedItem();
            String Harga = (String) jHarga.getText();
            String Status = (String) jComboStatus.getSelectedItem();

            KoneksiDB.aksi("INSERT INTO Pengembalian Values('" + ID_Pengembalian + "','" + ID_Transaksi + "','" + ID_Produk + "' ,'" + Tanggal + "','" + Harga + "', '" + Status + "')");
            ResultSet rs = KoneksiDB.ambilData("SELECT * FROM Stok where ID_Produk = '" + ID_Produk + "'");
            if (rs.next()) {
                int stok = Integer.parseInt(rs.getString("Jumlah_Stok")) + 1;
                KoneksiDB.aksi("UPDATE stok SET Jumlah_Stok = " + stok + " WHERE ID_Produk = '" + rs.getString("id_Produk") + "'");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jIDpengembalian.setText("");
        jComboIDTransaksi.setSelectedItem("");
        jIDproduk.setText("");
        jComboTanggal1.setSelectedItem("");
        jHarga.setText("");
        jComboStatus.setSelectedItem("");
        AturBaris();
        AturBaris2();
        generateReturnNumber();
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        // TODO add your handling code here:
        String ID_Pengembalian = (String) jIDpengembalian.getText();
        String ID_Transaksi = (String) jComboIDTransaksi.getSelectedItem();
        String ID_Produk = (String) jIDproduk.getText();
        String Tanggal = (String) jComboTanggal1.getSelectedItem();
        String Harga = (String) jHarga.getText();
        String Status = (String) jComboStatus.getSelectedItem();

        KoneksiDB.aksi("UPDATE Pengembalian SET status = '" + String.valueOf(jComboStatus.getSelectedItem()) + "', ID_Transaksi = '" + String.valueOf(jComboIDTransaksi.getSelectedItem()) + "', ID_Produk = '" + jIDproduk.getText() + "', Tanggal = '" + String.valueOf(jComboTanggal1.getSelectedItem()) + "', Harga = '" + jHarga.getText() + "' WHERE ID_Pengembalian = '" + jIDpengembalian.getText() + "'");
        jIDpengembalian.setText("");
        jComboIDTransaksi.setSelectedItem("");
        jIDproduk.setText("");
        jComboTanggal1.setSelectedItem("");
        jHarga.setText("");
        jComboStatus.setSelectedItem("");
        AturBaris();
        generateReturnNumber();
    }//GEN-LAST:event_jEditActionPerformed

    private void jSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSewaMouseClicked
        // TODO add your handling code here:
        Dashboard Dashboard = new Dashboard("");
        Dashboard.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jSewaMouseClicked

    private void jSewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSewaActionPerformed

    private void jJamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJamActionPerformed

    private void jSewa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSewa1MouseClicked
        // TODO add your handling code here:
        Sewa Sewa = new Sewa();
        Sewa.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jSewa1MouseClicked

    private void jSewa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSewa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSewa1ActionPerformed

    private void jTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTransaksiMouseClicked
        // TODO add your handling code here:
        Transaksi Transaksi = new Transaksi();
        Transaksi.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jTransaksiMouseClicked

    private void jTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransaksiActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_jTransaksiActionPerformed

    private void jGajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jGajiMouseClicked
        // TODO add your handling code here
        GajiKaryawan GajiKaryawan = new GajiKaryawan();
        GajiKaryawan.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jGajiMouseClicked

    private void jGajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGajiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jGajiActionPerformed

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
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengembalian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengembalian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID_Pengembalian;
    private javax.swing.JComboBox<String> jComboIDTransaksi;
    private javax.swing.JComboBox<String> jComboStatus;
    private javax.swing.JComboBox<String> jComboTanggal1;
    private javax.swing.JButton jEdit;
    private javax.swing.JButton jGaji;
    private javax.swing.JButton jHapus;
    private javax.swing.JTextField jHarga;
    private javax.swing.JTextField jIDpengembalian;
    private javax.swing.JTextField jIDproduk;
    private javax.swing.JTextField jJam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTable jPengembalian;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSewa;
    private javax.swing.JButton jSewa1;
    private javax.swing.JButton jSimpan;
    private javax.swing.JTable jTableStok;
    private javax.swing.JTextField jTanggal2;
    private javax.swing.JButton jTransaksi;
    // End of variables declaration//GEN-END:variables
}
