/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Transaksi;

import javax.swing.table.DefaultTableModel;
import KoneksiDB.KoneksiDB;
import java.sql.ResultSet;
import javax.swing.JFrame;
import java.awt.Dimension;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import Pengembalian.Pengembalian;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author abiba
 */
public class Transaksi extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();
    KoneksiDB KoneksiDB = new KoneksiDB();
    DefaultTableModel dtm2 = new DefaultTableModel();

    /**
     * Creates new form Transaksi
     */
    public Transaksi() {
        initComponents();
        AturKolom();
        AturBaris();
        generateTransactionNumber();
        getItem();
        Waktu();
        AturKolom2();
        AturBaris2();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void Waktu() {

        Thread p = new Thread() {
            public void run() {
                for (;;) {

                    Calendar kal = new GregorianCalendar();
                    int hari = kal.get(Calendar.DAY_OF_MONTH);
                    int bulan = kal.get(Calendar.MONTH) + 1;
                    int tahun = kal.get(Calendar.YEAR);
                    String tanggal = hari + "/" + bulan + "/" + tahun;
                    jTanggal.setText(tanggal);
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

    public void jPanelTransaksi() {
        // Set the preferred size of the panel
        Dimension preferredSize = new Dimension(1920, 1080);
        this.setPreferredSize(preferredSize);
    }

    public void getItem() {
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM stok");
        jComboIDBox.addItem("--Pilih Item--");
        try {
            while (rs.next()) {
                jComboIDBox.addItem(rs.getString("ID_Produk"));
            }
        } catch (Exception e) {

        }
    }

    public void generateTransactionNumber() {
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM transaksi ORDER BY ID_Transaksi DESC");
        try {
            rs.next();
            String id = rs.getString("ID_Transaksi");
            String[] arr = id.split("TR");
            int idNum = Integer.parseInt(arr[1]);
            idNum++;
            String result = "";
            if (idNum < 9) {
                result = "00" + idNum;
            } else if (idNum < 99) {
                result = "0" + idNum;
            } else if (idNum < 999) {
                result = "" + idNum;
            }
            jIDtransaksi.setText("TR" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AturKolom() {
        dtm.addColumn("ID Transaksi");
        dtm.addColumn("ID Produk");
        dtm.addColumn("Nama Pelanggan");
        dtm.addColumn("Nama Produk");
        dtm.addColumn("Jenis");
        dtm.addColumn("Estimasi");
        dtm.addColumn("Jaminan");
        dtm.addColumn("Harga");
        dtm.addColumn("Pembayaran");
        jTransaksi.setModel(dtm);
    }

    public void AturBaris() {
        dtm.setRowCount(0);
        jTransaksi.setModel(dtm);
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM Transaksi");
        try {
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("ID_Transaksi"), rs.getString("ID_Produk"), rs.getString("Nama_Pelanggan"), rs.getString("Nama_Produk"), rs.getString("Jenis"), rs.getString("Estimasi"), rs.getString("Jaminan"), rs.getString("Harga"), rs.getString("Pembayaran")});
            }
            jTransaksi.setModel(dtm);
        } catch (Exception e) {
        }
    }

    public void AturKolom2() {
        dtm2.addColumn("ID Produk");
        dtm2.addColumn("Nama Produk");
        dtm2.addColumn("Jenis");
        dtm2.addColumn("Jumlah Stok");
        dtm2.addColumn("Harga");
        jBarang.setModel(dtm2);
    }

    public void AturBaris2() {
        dtm2.setRowCount(0);
        jBarang.setModel(dtm2);
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM Stok");
        try {
            while (rs.next()) {
                dtm2.addRow(new Object[]{rs.getString("ID_Produk"), rs.getString("Nama_Produk"), rs.getString("Jenis"), rs.getString("Jumlah_Stok"), rs.getString("Harga")});
            }
            jBarang.setModel(dtm2);
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

        jPanelTransaksi = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTransaksi = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jIDtransaksi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jNamaPelanggan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboEstimasi = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jHarga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSimpan = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        jEdit = new javax.swing.JButton();
        jComboPembayaran = new javax.swing.JComboBox<>();
        jCetak = new javax.swing.JButton();
        jJaminan1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jNamaProduk = new javax.swing.JTextField();
        jComboIDBox = new javax.swing.JComboBox<>();
        jJenis = new javax.swing.JTextField();
        jPengembalian = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTanggal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jBarang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelTransaksi.setMaximumSize(new java.awt.Dimension(1920, 1080));

        jTransaksi.setFont(new java.awt.Font("SimSun-ExtB", 0, 14)); // NOI18N
        jTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "ID Produk", "Nama Pelanggan", "Nama Produk", "Jenis ", "Estimasi", "Jaminan", "Harga", "Pembayaran"
            }
        ));
        jTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTransaksi);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("ID Transaksi");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ID Produk");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nama Pelanggan");

        jNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNamaPelangganActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Jenis");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Estimasi");

        jComboEstimasi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Hari--", "1 Hari", "2 Hari", "3 Hari" }));
        jComboEstimasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEstimasiActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Jaminan");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Harga");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Pembayaran");

        jSimpan.setBackground(new java.awt.Color(51, 255, 51));
        jSimpan.setText("Simpan");
        jSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSimpanActionPerformed(evt);
            }
        });

        jHapus.setBackground(new java.awt.Color(255, 0, 0));
        jHapus.setText("Hapus");
        jHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHapusActionPerformed(evt);
            }
        });

        jEdit.setBackground(new java.awt.Color(51, 51, 255));
        jEdit.setText("Edit");
        jEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditActionPerformed(evt);
            }
        });

        jComboPembayaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Status Pembayaran--", "Lunas", "Belum" }));
        jComboPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPembayaranActionPerformed(evt);
            }
        });

        jCetak.setBackground(new java.awt.Color(204, 204, 204));
        jCetak.setText("Cetak");
        jCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCetakActionPerformed(evt);
            }
        });

        jJaminan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJaminan1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Nama Produk");

        jComboIDBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboIDBoxActionPerformed(evt);
            }
        });

        jJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJenisActionPerformed(evt);
            }
        });

        jPengembalian.setText("Pengembalian");
        jPengembalian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPengembalianMouseClicked(evt);
            }
        });
        jPengembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPengembalianActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(0, 100, 0));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProjectUAS2/sibhp3.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Masukkan Data Penyewa");

        jTanggal.setEditable(false);
        jTanggal.setBackground(new java.awt.Color(204, 255, 255));
        jTanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTanggal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTanggal.setAutoscrolls(false);
        jTanggal.setEnabled(false);
        jTanggal.setFocusable(false);
        jTanggal.setInheritsPopupMenu(true);
        jTanggal.setRequestFocusEnabled(false);
        jTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTanggalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(419, 419, 419)
                .addComponent(jTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );

        jBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jBarang);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Data Barang dan Jasa");

        javax.swing.GroupLayout jPanelTransaksiLayout = new javax.swing.GroupLayout(jPanelTransaksi);
        jPanelTransaksi.setLayout(jPanelTransaksiLayout);
        jPanelTransaksiLayout.setHorizontalGroup(
            jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(333, 333, 333))
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(246, 246, 246)
                                .addComponent(jLabel9)
                                .addGap(33, 33, 33)
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboPembayaran, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jHarga)
                                    .addComponent(jJaminan1)))
                            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelTransaksiLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jNamaPelanggan))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelTransaksiLayout.createSequentialGroup()
                                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboEstimasi, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jIDtransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(49, 49, 49)
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(360, 360, 360))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );
        jPanelTransaksiLayout.setVerticalGroup(
            jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jIDtransaksi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jComboIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboEstimasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jJaminan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jComboPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120))
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNamaPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNamaPelangganActionPerformed

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        // TODO add your handling code here:
        int n = jTransaksi.getSelectedRow();
        String ID_Transaksi = String.valueOf(jTransaksi.getValueAt(n, 0));
        KoneksiDB.aksi("Delete from Transaksi WHERE ID_Transaksi = '" + ID_Transaksi + "'");
        AturBaris();
    }//GEN-LAST:event_jHapusActionPerformed

    private void jTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTransaksiMouseClicked
        // TODO add your handling code here:
        int n = jTransaksi.getSelectedRow();
        jIDtransaksi.setText(String.valueOf(jTransaksi.getValueAt(n, 0)));
        jComboIDBox.setSelectedItem(String.valueOf(jTransaksi.getValueAt(n, 1)));
        jNamaPelanggan.setText(String.valueOf(jTransaksi.getValueAt(n, 2)));
        jJenis.setText(String.valueOf(jTransaksi.getValueAt(n, 4)));
        jComboEstimasi.setSelectedItem(jTransaksi.getValueAt(n, 5));
        jNamaProduk.setText(String.valueOf(jTransaksi.getValueAt(n, 3)));
        jJaminan1.setText(String.valueOf(jTransaksi.getValueAt(n, 6)));
        jHarga.setText(String.valueOf(jTransaksi.getValueAt(n, 7)));
        jComboPembayaran.setSelectedItem(jTransaksi.getValueAt(n, 8));
    }//GEN-LAST:event_jTransaksiMouseClicked

    private void jComboIDBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboIDBoxActionPerformed
        // TODO add your handling code here:
        if (!jComboIDBox.getSelectedItem().equals("--Pilih Item--")) {
            ResultSet rs = KoneksiDB.ambilData("SELECT * FROM stok WHERE ID_Produk = '" + jComboIDBox.getSelectedItem() + "'");
            try {
                rs.next();
                jNamaProduk.setText(rs.getString("Nama_Produk"));
                jJenis.setText(rs.getString("Jenis"));
                jHarga.setText(rs.getString("Harga"));

            } catch (Exception e) {
//                e.printStackTrace();
            }
            String txt = String.valueOf(jComboEstimasi.getSelectedItem());
            String[] arr = txt.split(" Hari");
            jHarga.setText(String.valueOf(Integer.parseInt(jHarga.getText()) * Integer.parseInt(arr[0])));
        }
    }//GEN-LAST:event_jComboIDBoxActionPerformed

    private void jComboEstimasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEstimasiActionPerformed
        // TODO add your handling code here:

        try {
            String txt = String.valueOf(jComboEstimasi.getSelectedItem());
            String[] arr = txt.split(" Hari");
            jHarga.setText(String.valueOf(Integer.parseInt(jHarga.getText()) * Integer.parseInt(arr[0])));
        } catch (NumberFormatException e) {
            System.err.println("Masukin dulu hargannya *");
        }
    }//GEN-LAST:event_jComboEstimasiActionPerformed

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        // TODO add your handling code here:

        String ID_Transaksi = (String) jIDtransaksi.getText();
        String ID_Produk = (String) jComboIDBox.getSelectedItem();
        String Nama_Pelanggan = (String) jNamaPelanggan.getText();
        String Jenis = (String) jJenis.getText();
        String Estimasi = (String) jComboEstimasi.getSelectedItem();
        String Nama_Produk = (String) jNamaProduk.getText();
        String Jaminan = (String) jJaminan1.getText();
        String Harga = (String) jHarga.getText();
        String Pembayaran = (String) jComboPembayaran.getSelectedItem();

        KoneksiDB.aksi("INSERT INTO Transaksi Values('" + ID_Transaksi + "','" + ID_Produk + "','" + Nama_Pelanggan + "','" + Nama_Produk + "','" + Jenis + "','" + Estimasi + "','" + Jaminan + "','" + Harga + "','" + Pembayaran + "')");
        jIDtransaksi.setText("");
        jComboIDBox.setSelectedItem("");
        jNamaPelanggan.setText("");
        jJenis.setText("");
        jComboEstimasi.setSelectedItem("");
        jNamaProduk.setText("");
        jJaminan1.setText("");
        jHarga.setText("");
        jComboPembayaran.setSelectedItem("");
        AturBaris();
        generateTransactionNumber();
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        // TODO add your handling code here:
        String ID_Transaksi = (String) jIDtransaksi.getText();
        String ID_Produk = (String) jComboIDBox.getSelectedItem();
        String Nama_Pelanggan = (String) jNamaPelanggan.getText();
        String Jenis = (String) jJenis.getText();
        String Estimasi = (String) jComboEstimasi.getSelectedItem();
        String Nama_Produk = (String) jNamaProduk.getText();
        String Jaminan = (String) jJaminan1.getText();
        String Harga = (String) jHarga.getText();
        String Pembayaran = (String) jComboPembayaran.getSelectedItem();

        KoneksiDB.aksi("UPDATE Transaksi SET Jaminan = '" + jJaminan1.getText() + "', Estimasi = '" + String.valueOf(jComboEstimasi.getSelectedItem()) + "', Harga = '" + jHarga.getText() + "', ID_Transaksi = '" + jIDtransaksi.getText() + "', ID_Produk = '" + (String) jComboIDBox.getSelectedItem() + "', Nama_Pelanggan = '" + jNamaPelanggan.getText() + "', Jenis = '" + jJenis.getText() + "', Nama_Produk = '" + jNamaProduk.getText() + "', Pembayaran = '" + jComboPembayaran.getSelectedItem() + "' WHERE ID_Transaksi = '" + jIDtransaksi.getText() + "'");
        AturBaris();
        jIDtransaksi.setText("");
        jComboIDBox.setSelectedItem("");
        jNamaPelanggan.setText("");
        jJenis.setText("");
        jComboEstimasi.setSelectedItem("");
        jNamaProduk.setText("");
        jJaminan1.setText("");
        jHarga.setText("");
        jComboPembayaran.setSelectedItem("");
        generateTransactionNumber();
    }//GEN-LAST:event_jEditActionPerformed

    private void jTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTanggalActionPerformed

    private void jJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJenisActionPerformed

    private void jJaminan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJaminan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJaminan1ActionPerformed

    private void jComboPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboPembayaranActionPerformed

    private void jPengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPengembalianMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPengembalianMouseClicked

    private void jPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPengembalianActionPerformed
        // TODO add your handling code here:
        Pengembalian Pengembalian = new Pengembalian();
        Pengembalian.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jPengembalianActionPerformed

    private void jCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCetakActionPerformed
        // TODO add your handling code here:
        try {
            String file = "C:\\Users\\abiba\\Downloads\\SIBHP\\ProjectUAS2\\src\\Report\\Transaksi.jasper";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructors();
            HashMap<String, Object> param = new HashMap<>();
            param.put("namaPelanggan", jNamaPelanggan.getText());
            param.put("idTransaksi", jIDtransaksi.getText());
            param.put("idProduk", jComboIDBox.getSelectedItem());
            param.put("namaProduk", jNamaProduk.getText());
            param.put("jenis", jJenis.getText());
            param.put("estimasi", jComboEstimasi.getSelectedItem());
            param.put("jaminan", jJaminan1.getText());
            param.put("harga", jHarga.getText());
            param.put("pembayaran", jComboPembayaran.getSelectedItem());
            KoneksiDB.koneksi();
            System.out.println(getClass().getResourceAsStream(file));
            JasperPrint print = JasperFillManager.fillReport(file, param, KoneksiDB.con);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jCetakActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable jBarang;
    private javax.swing.JButton jCetak;
    private javax.swing.JComboBox<String> jComboEstimasi;
    private javax.swing.JComboBox<String> jComboIDBox;
    private javax.swing.JComboBox<String> jComboPembayaran;
    private javax.swing.JButton jEdit;
    private javax.swing.JButton jHapus;
    private javax.swing.JTextField jHarga;
    private javax.swing.JTextField jIDtransaksi;
    private javax.swing.JTextField jJaminan1;
    private javax.swing.JTextField jJenis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jNamaPelanggan;
    private javax.swing.JTextField jNamaProduk;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanelTransaksi;
    private javax.swing.JButton jPengembalian;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSimpan;
    private javax.swing.JTextField jTanggal;
    private javax.swing.JTable jTransaksi;
    // End of variables declaration//GEN-END:variables
}
