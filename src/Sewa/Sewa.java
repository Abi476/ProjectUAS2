/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Sewa;

import GajiKaryawan.GajiKaryawan;
import Transaksi.Transaksi;
import javax.swing.table.DefaultTableModel;
import KoneksiDB.KoneksiDB;
import Pengembalian.Pengembalian;
import com.barcodelib.barcode.Linear;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.lowagie.text.pdf.Barcode;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Dashboard.Dashboard;
/**
 *
 * @author abiba
 */
public class Sewa extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();
    KoneksiDB KoneksiDB = new KoneksiDB();
    
    /**
     * Creates new form Sewa
     */
    public Sewa() {
        initComponents();
        AturKolom();
        AturBaris();
        generateIDproduk();
        ViewBarcode();
        waktu();
        Tanggal();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void AturKolom() {
        dtm.addColumn("ID Produk");
        dtm.addColumn("Nama Produk");
        dtm.addColumn("Jenis");
        dtm.addColumn("Jumlah Stok");
        dtm.addColumn("Harga");
        dtm.addColumn("Deskripsi");
        jStok.setModel(dtm);
    }

    public void AturBaris() {
        dtm.setRowCount(0);
        jStok.setModel(dtm);
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM Stok");
        try {
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("ID_Produk"), rs.getString("Nama_Produk"), rs.getString("Jenis"), rs.getString("Jumlah_Stok"), rs.getString("Harga"), rs.getString("Deskripsi")});
            }
            jStok.setModel(dtm);
        } catch (Exception e) {
        }
    }

    public void generateIDproduk() {
        String jenis = jComboJenis.getSelectedItem().toString();
        String query = "SELECT * FROM Stok WHERE Jenis = '" + jenis + "' ORDER BY ID_Produk DESC";
        ResultSet rs = KoneksiDB.ambilData(query);
        try {
            if (rs.next()) {
                String id = rs.getString("ID_produk");
                String categoryCode = id.substring(0, 2);
                String idNumber = id.substring(2);
                int idNum = Integer.parseInt(idNumber);
                idNum++;
                String result = categoryCode + String.format("%04d", idNum);
                jTextIDproduk.setText(result);
            } else {
                String initialID = getInitialIDByType(jenis);
                jTextIDproduk.setText(initialID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getInitialIDByType(String jenis) {
        // Menyesuaikan ID awal berdasarkan jenis
        switch (jenis) {
            case "Aksesoris":
                return "AK0001";
            case "Jasa":
                return "JA0001";
            case "Kamera":
                return "KA0001";
            default:
                return "000000"; // Nilai default jika jenis tidak ditemukan
        }
    }

    public void ViewBarcode() {
        String barcodeImg = jTextIDproduk.getText(); // ambil nilai dari TextField jIdProduk
        BufferedImage barcode = generateBarcode(barcodeImg);
        ImageIcon icon = new ImageIcon(barcode);
        jBarcodeView.setIcon(icon);
    }

    public void getBarcodeImage(int width, int height, JLabel label, String path) {
        try {
            File file = new File("D:/" + path + ".png");
            BufferedImage bi = ImageIO.read(file);
            Image i = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon ii = new ImageIcon(i);
            label.setIcon(ii);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private BufferedImage generateBarcode(String barcodeIMG) {
        int width = 261;
        int height = 81;

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    barcodeIMG,
                    BarcodeFormat.CODE_128,
                    width,
                    height
            );

            BufferedImage barcode = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    barcode.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            return barcode;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showBarcode(java.awt.event.ActionEvent evt) {
        showBarcode();
    }

    public void waktu() {

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
    
    public void Tanggal() {

        Thread p = new Thread() {
            public void run() {
                for (;;) {

                    Calendar kal = new GregorianCalendar();
                    int hari = kal.get(Calendar.DAY_OF_MONTH);
                    int bulan = kal.get(Calendar.MONTH) + 1;
                    int tahun = kal.get(Calendar.YEAR);
                    String tanggal = hari + "/" + bulan + "/" + tahun + "";
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jStok = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jNamaProduk = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jJumlahStok = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jHarga = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextDescription = new javax.swing.JTextPane();
        jSimpan = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        jEdit = new javax.swing.JButton();
        jTextIDproduk = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTanggal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboJenis = new javax.swing.JComboBox<>();
        jAddBarcode = new javax.swing.JButton();
        jBarcodeView = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jJam = new javax.swing.JTextField();
        jTransaksi = new javax.swing.JButton();
        jPengembalian = new javax.swing.JButton();
        jGaji = new javax.swing.JButton();
        jSewa1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1920, 1080));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jStok.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
        jStok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Produk", "Nama Produk", "Jenis", "Jumlah Stok", "Harga", "Deskripsi"
            }
        ));
        jStok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jStokMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jStok);

        jLabel2.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel2.setText("ID Produk");

        jLabel4.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel4.setText("Nama Produk");

        jNamaProduk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel5.setText("Jenis");

        jLabel6.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel6.setText("Jumlah Stok");

        jJumlahStok.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel7.setText("Harga");

        jHarga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel8.setText("Deskripsi");

        jTextDescription.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(jTextDescription);

        jSimpan.setBackground(new java.awt.Color(51, 255, 51));
        jSimpan.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jSimpan.setText("Simpan");
        jSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSimpanMouseClicked(evt);
            }
        });
        jSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSimpanActionPerformed(evt);
            }
        });

        jHapus.setBackground(new java.awt.Color(255, 0, 0));
        jHapus.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jHapus.setText("Hapus");
        jHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jHapusMouseClicked(evt);
            }
        });
        jHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHapusActionPerformed(evt);
            }
        });

        jEdit.setBackground(new java.awt.Color(51, 51, 255));
        jEdit.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jEdit.setText("Edit");
        jEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jEditMouseClicked(evt);
            }
        });
        jEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditActionPerformed(evt);
            }
        });

        jTextIDproduk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextIDproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextIDprodukActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(1920, 121));

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Harga Sewa Jasa dan Barang Kami");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectuas2/sibhp3.png"))); // NOI18N

        jTanggal.setEditable(false);
        jTanggal.setBackground(new java.awt.Color(0, 0, 0));
        jTanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTanggal.setForeground(new java.awt.Color(255, 255, 255));
        jTanggal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTanggal.setBorder(null);
        jTanggal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTanggalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 681, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(464, 464, 464)
                .addComponent(jTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(305, 305, 305))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(23, 23, 23))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Poppins Light", 0, 18)); // NOI18N
        jLabel9.setText("Barcode");

        jComboJenis.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih Jenis --", "Aksesoris", "Jasa", "Kamera" }));
        jComboJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboJenisActionPerformed(evt);
            }
        });

        jAddBarcode.setText("Lihat Barcode");
        jAddBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddBarcodeActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(49, 170, 49));
        jPanel4.setPreferredSize(new java.awt.Dimension(175, 401));

        jLabel10.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Waktu");

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

        jPengembalian.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jPengembalian.setText("Pengembalian");
        jPengembalian.setBorderPainted(false);
        jPengembalian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jSewa1.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jSewa1.setText("Dashboard");
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jJam, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPengembalian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jGaji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSewa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jJam, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jSewa1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jGaji, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextIDproduk, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(304, 304, 304)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)
                                            .addComponent(jJumlahStok, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)
                                            .addComponent(jHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jAddBarcode)
                                            .addComponent(jLabel9)
                                            .addComponent(jBarcodeView, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1407, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(430, 430, 430))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 2153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextIDproduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jJumlahStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)))
                            .addComponent(jBarcodeView, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAddBarcode))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(127, 127, 127))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1939, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSimpanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jSimpanMouseClicked

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        // TODO add your handling code here:
        String ID_Produk = (String) jTextIDproduk.getText();
        String Nama_Produk = (String) jNamaProduk.getText();
        String Jenis = (String) jComboJenis.getSelectedItem();
        String Jumlah_Stok = jJumlahStok.getText();
        String Harga = (String) jHarga.getText();
        String Deskripsi = (String) jTextDescription.getText();

        KoneksiDB.aksi("INSERT INTO Stok Values('" + ID_Produk + "','" + Nama_Produk + "','" + Jenis + "','" + Jumlah_Stok + "','" + Harga + "','" + Deskripsi + "')");
        AturBaris();
        jTextIDproduk.setText("");
        jNamaProduk.setText("");
        jComboJenis.setSelectedItem("");
        jJumlahStok.setText("");
        jHarga.setText("");
        jTextDescription.setText("");
        generateIDproduk();
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHapusMouseClicked
        // TODO add your handling code here:
        int n = jStok.getSelectedRow();
        String ID_Produk = String.valueOf(jStok.getValueAt(n, 0));
        KoneksiDB.aksi("Delete from Stok WHERE ID_Produk = '" + ID_Produk + "'");
        AturBaris();
        generateIDproduk();
    }//GEN-LAST:event_jHapusMouseClicked

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHapusActionPerformed

    private void jEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEditMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jEditMouseClicked

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        // TODO add your handling code here:
        String ID_Produk = (String) jTextIDproduk.getText();
        String Nama_Produk = (String) jNamaProduk.getText();
        String Jenis = (String) jComboJenis.getSelectedItem();
        String Jumlah_Stok = jJumlahStok.getText();
        String Harga = (String) jHarga.getText();
        String Deskripsi = (String) jTextDescription.getText();

        KoneksiDB.aksi("UPDATE Stok SET Nama_Produk = '" + jNamaProduk.getText() + "', Jenis = '" + jComboJenis.getSelectedItem() + "', Harga = '" + jHarga.getText() + "', Jumlah_Stok = '" + jJumlahStok.getText() + "', Harga = '" + (String) jHarga.getText() + "', Deskripsi = '" + jTextDescription.getText() + "' ID_Produk = '" + jTextIDproduk.getText() + "'");
        AturBaris();
        jTextIDproduk.setText("");
        jNamaProduk.setText("");
        jComboJenis.setSelectedItem("");
        jJumlahStok.setText("");
        jHarga.setText("");
        jTextDescription.setText("");
        generateIDproduk();
    }//GEN-LAST:event_jEditActionPerformed

    private void jTextIDprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextIDprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIDprodukActionPerformed

    private void jStokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jStokMouseClicked
        // TODO add your handling code here:
        int n = jStok.getSelectedRow();
        jTextIDproduk.setText(String.valueOf(jStok.getValueAt(n, 0)));
        jNamaProduk.setText(String.valueOf(jStok.getValueAt(n, 1)));
        jComboJenis.setSelectedItem(String.valueOf(jStok.getValueAt(n, 2)));
        jJumlahStok.setText(String.valueOf(jStok.getValueAt(n, 3)));
        jHarga.setText(String.valueOf(jStok.getValueAt(n, 4)));
        jTextDescription.setText(String.valueOf(jStok.getValueAt(n, 5)));
    }//GEN-LAST:event_jStokMouseClicked

    private void jComboJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboJenisActionPerformed
        // TODO add your handling code here:
        if (!jComboJenis.getSelectedItem().equals("--Pilih Item--")) {
            String jenis = jComboJenis.getSelectedItem().toString();
            String query = "SELECT * FROM Stok WHERE Jenis = '" + jenis + "' ORDER BY ID_Produk DESC";
            ResultSet rs = KoneksiDB.ambilData(query);
            try {
                if (rs.next()) {
                    String id = rs.getString("ID_Produk");
                    String categoryCode = id.substring(0, 2);
                    String idNumber = id.substring(2);
                    int idNum = Integer.parseInt(idNumber);
                    idNum++;
                    String result = categoryCode + String.format("%04d", idNum);
                    jTextIDproduk.setText(result);
                } else {
                    String initialID = getInitialIDByType(jenis);
                    jTextIDproduk.setText(initialID);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jComboJenisActionPerformed

    private void jAddBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddBarcodeActionPerformed
        // nambah barcode:
        try {
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(jTextIDproduk.getText());
            barcode.setI(11.0f);
            String path = jTextIDproduk.getText();
            barcode.renderBarcode("D:\\" + path + ".png");
            //int width = 261;
            //int height = 81;
            getBarcodeImage(261, 81, jBarcodeView, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jAddBarcodeActionPerformed

    private void jJamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJamActionPerformed

    private void jTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTransaksiMouseClicked
        // TODO add your handling code here:
        Transaksi Transaksi = new Transaksi();
        Transaksi.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jTransaksiMouseClicked

    private void jTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransaksiActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_jTransaksiActionPerformed

    private void jPengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPengembalianMouseClicked
        // TODO add your handling code here:
        Pengembalian Pengembalian = new Pengembalian();
        Pengembalian.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jPengembalianMouseClicked

    private void jPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPengembalianActionPerformed
        //TODO add your handling code here:
    }//GEN-LAST:event_jPengembalianActionPerformed

    private void jGajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jGajiMouseClicked
        // TODO add your handling code here
        GajiKaryawan GajiKaryawan = new GajiKaryawan();
        GajiKaryawan.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jGajiMouseClicked

    private void jGajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGajiActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jGajiActionPerformed

    private void jTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTanggalActionPerformed

    private void jSewa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSewa1MouseClicked
        // TODO add your handling code here:
        Dashboard Dashboard = new Dashboard("");
        Dashboard.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jSewa1MouseClicked

    private void jSewa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSewa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSewa1ActionPerformed

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
            java.util.logging.Logger.getLogger(Sewa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sewa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sewa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sewa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sewa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddBarcode;
    private javax.swing.JLabel jBarcodeView;
    private javax.swing.JComboBox<String> jComboJenis;
    private javax.swing.JButton jEdit;
    private javax.swing.JButton jGaji;
    private javax.swing.JButton jHapus;
    private javax.swing.JTextField jHarga;
    private javax.swing.JTextField jJam;
    private javax.swing.JTextField jJumlahStok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jNamaProduk;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jPengembalian;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSewa1;
    private javax.swing.JButton jSimpan;
    private javax.swing.JTable jStok;
    private javax.swing.JTextField jTanggal;
    private javax.swing.JTextPane jTextDescription;
    private javax.swing.JTextField jTextIDproduk;
    private javax.swing.JButton jTransaksi;
    // End of variables declaration//GEN-END:variables

    private void showBarcode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
