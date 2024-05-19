package Transaksi;
import KoneksiDB.KoneksiDB;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import Pengembalian.Pengembalian;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import Sewa.Sewa;
import GajiKaryawan.GajiKaryawan;
import Dashboard.Dashboard;
/**
 *
 * @author abiba
 */
public class Transaksi extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();
    KoneksiDB KoneksiDB = new KoneksiDB();
    DefaultTableModel dtm2 = new DefaultTableModel();
   
    public Transaksi() {
        initComponents();
        AturKolom();
        AturBaris();
        generateTransactionNumber();
        getItem();
        Tanggal();
        AturKolom2();
        AturBaris2();
        Karyawan();
        Waktu();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

    public void Karyawan() {
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM user");
        try {
            while (rs.next()) {
                String karyawan = rs.getString("Username"); // Ganti "nama_field" dengan nama kolom yang ingin Anda ambil
                jKaryawan.setText("Kahfi Adam Munajad"); // Ganti jKaryawan dengan objek yang sesuai untuk menampilkan data
            }
            rs.close(); // Tutup ResultSet setelah selesai menggunakannya
        } catch (Exception e) {
            e.printStackTrace(); // Cetak stack trace untuk membantu dalam debugging
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
        jPanel1 = new javax.swing.JPanel();
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
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTanggal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jBarang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTransaksi = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jKaryawan = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jJam = new javax.swing.JTextField();
        jSewa = new javax.swing.JButton();
        jPengembalian = new javax.swing.JButton();
        jGaji = new javax.swing.JButton();
        jSewa1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelTransaksi.setBackground(new java.awt.Color(204, 204, 255));
        jPanelTransaksi.setMaximumSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("ID Transaksi");

        jIDtransaksi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jIDtransaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("ID Produk");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Nama Pelanggan");

        jNamaPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNamaPelangganActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Jenis");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Estimasi");

        jComboEstimasi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboEstimasi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Hari--", "1 Hari", "2 Hari", "3 Hari" }));
        jComboEstimasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEstimasiActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Jaminan");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Harga");

        jHarga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHargaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Pembayaran");

        jSimpan.setBackground(new java.awt.Color(51, 255, 51));
        jSimpan.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jSimpan.setText("Simpan");
        jSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSimpanActionPerformed(evt);
            }
        });

        jHapus.setBackground(new java.awt.Color(255, 0, 0));
        jHapus.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jHapus.setText("Hapus");
        jHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHapusActionPerformed(evt);
            }
        });

        jEdit.setBackground(new java.awt.Color(51, 51, 255));
        jEdit.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jEdit.setText("Edit");
        jEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditActionPerformed(evt);
            }
        });

        jComboPembayaran.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboPembayaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Status Pembayaran--", "Lunas", "Belum" }));
        jComboPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPembayaranActionPerformed(evt);
            }
        });

        jCetak.setBackground(new java.awt.Color(204, 204, 204));
        jCetak.setText("Cetak");
        jCetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCetakActionPerformed(evt);
            }
        });

        jJaminan1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jJaminan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJaminan1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Nama Produk");

        jNamaProduk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jComboIDBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboIDBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboIDBoxActionPerformed(evt);
            }
        });

        jJenis.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jJenisActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setPreferredSize(new java.awt.Dimension(1920, 121));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProjectUAS2/sibhp3.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Masukkan Data Transaksi");

        jTanggal.setEditable(false);
        jTanggal.setBackground(new java.awt.Color(0, 0, 0));
        jTanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTanggal.setForeground(new java.awt.Color(255, 255, 255));
        jTanggal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTanggal.setAutoscrolls(false);
        jTanggal.setBorder(null);
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
                .addGap(523, 523, 523)
                .addComponent(jTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );

        jBarang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel1.setText("Data Barang dan Jasa");

        jTransaksi.setFont(new java.awt.Font("SimSun-ExtB", 0, 18)); // NOI18N
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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Karyawan");

        jKaryawan.setEditable(false);
        jKaryawan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jKaryawan.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel2.setBackground(new java.awt.Color(49, 170, 49));
        jPanel2.setPreferredSize(new java.awt.Dimension(175, 401));

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

        jSewa.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jSewa.setText("Stok");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSewa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jJam, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPengembalian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jGaji, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSewa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jJam, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jSewa1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jGaji, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        jLabel13.setText("Data Transaksi");

        javax.swing.GroupLayout jPanelTransaksiLayout = new javax.swing.GroupLayout(jPanelTransaksi);
        jPanelTransaksi.setLayout(jPanelTransaksiLayout);
        jPanelTransaksiLayout.setHorizontalGroup(
            jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 2026, Short.MAX_VALUE)
            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jIDtransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelTransaksiLayout.createSequentialGroup()
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(28, 28, 28)
                                                        .addComponent(jComboIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel2)
                                                .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createSequentialGroup()
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(46, 46, 46)
                                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboEstimasi, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jJaminan1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(125, 125, 125)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                        .addComponent(jCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1573, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addGap(1210, 1210, 1210)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                        .addGap(328, 328, 328))
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addGap(796, 796, 796)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanelTransaksiLayout.setVerticalGroup(
            jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                    .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                        .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jKaryawan)
                                    .addComponent(jLabel6)
                                    .addComponent(jComboEstimasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jIDtransaksi)
                                    .addComponent(jLabel11)
                                    .addComponent(jNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jComboIDBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jJaminan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9)
                                    .addComponent(jComboPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(242, 242, 242))
                            .addGroup(jPanelTransaksiLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(101, 101, 101))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)))
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTanggalActionPerformed

    private void jJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJenisActionPerformed

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

    private void jJaminan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJaminan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJaminan1ActionPerformed

    private void jCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCetakActionPerformed
        // TODO add your handling code here:
        try {
            String file = "D:\\netbeans document\\ProjectUAS2\\src\\Report\\Transaksi.jasper";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructors();
            HashMap<String, Object> param = new HashMap<>();
            param.put("namaPelanggan", new String(jNamaPelanggan.getText()));
            param.put("IdTransaksi", new String(jIDtransaksi.getText()));
            param.put("idProduk", jComboIDBox.getSelectedItem());
            param.put("namaProduk", jNamaProduk.getText());
            param.put("jenis", jJenis.getText());
            param.put("estimasi", jComboEstimasi.getSelectedItem());
            param.put("jaminan", jJaminan1.getText());
            param.put("harga", jHarga.getText());
            param.put("pembayaran", jComboPembayaran.getSelectedItem());
            param.put("User", jKaryawan.getText());

            KoneksiDB.koneksi();
            System.out.println(getClass().getResourceAsStream(file));
            JasperPrint print = JasperFillManager.fillReport(file, param, KoneksiDB.con);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }//GEN-LAST:event_jCetakActionPerformed

    private void jComboPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboPembayaranActionPerformed

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

        KoneksiDB.aksi("UPDATE Transaksi SET Jaminan = '" + jJaminan1.getText() + "', Estimasi = '" + String.valueOf(jComboEstimasi.getSelectedItem()) + "', Harga = '" + jHarga.getText() + "', Nama_Pelanggan = '" + jNamaPelanggan.getText() + "',ID_Transaksi = '" + jIDtransaksi.getText() + "', ID_Produk = '" + (String) jComboIDBox.getSelectedItem() + "', Jenis = '" + jJenis.getText() + "', Nama_Produk = '" + jNamaProduk.getText() + "', Pembayaran = '" + jComboPembayaran.getSelectedItem() + "' WHERE ID_Transaksi = '" + jIDtransaksi.getText() + "'");
        AturBaris();
        AturBaris2();
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

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        // TODO add your handling code here:
        int n = jTransaksi.getSelectedRow();
        String ID_Transaksi = String.valueOf(jTransaksi.getValueAt(n, 0));
        KoneksiDB.aksi("Delete from Transaksi WHERE ID_Transaksi = '" + ID_Transaksi + "'");
        AturBaris();
    }//GEN-LAST:event_jHapusActionPerformed

    private void jSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSimpanActionPerformed
        // TODO add your handling code here:
        try {
            String ID_Transaksi = (String) jIDtransaksi.getText();
            String ID_Produk = (String) jComboIDBox.getSelectedItem();
            String Nama_Pelanggan = (String) jNamaPelanggan.getText();
            String Jenis = (String) jJenis.getText();
            String Estimasi = (String) jComboEstimasi.getSelectedItem();
            String Nama_Produk = (String) jNamaProduk.getText();
            String Jaminan = (String) jJaminan1.getText();
            String Harga = (String) jHarga.getText();
            String Pembayaran = (String) jComboPembayaran.getSelectedItem();
            // Insert data ke tabel Transaksi
            KoneksiDB.aksi("INSERT INTO transaksi VALUES ('" + ID_Transaksi + "','" + ID_Produk + "','" + Nama_Pelanggan + "','" + Nama_Produk + "','" + Jenis + "','" + Estimasi + "','" + Jaminan + "','" + Harga + "','" + Pembayaran + "')");
            ResultSet rs = KoneksiDB.ambilData("SELECT * FROM Stok where ID_Produk = '"+ID_Produk+"'");
            if (rs.next()){
                int stok = Integer.parseInt(rs.getString("Jumlah_Stok")) - 1;
                KoneksiDB.aksi("UPDATE stok SET Jumlah_Stok = " + stok + " WHERE ID_Produk = '" + rs.getString("id_Produk") + "'");
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
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
        AturBaris2();
    }//GEN-LAST:event_jSimpanActionPerformed

    private void jHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHargaActionPerformed

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

    private void jNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNamaPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNamaPelangganActionPerformed

    private void jJamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJamActionPerformed

    private void jSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSewaMouseClicked
        // TODO add your handling code here:
        Sewa Sewa = new Sewa();
        Sewa.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jSewaMouseClicked

    private void jSewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSewaActionPerformed

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
    private javax.swing.JButton jGaji;
    private javax.swing.JButton jHapus;
    private javax.swing.JTextField jHarga;
    private javax.swing.JTextField jIDtransaksi;
    private javax.swing.JTextField jJam;
    private javax.swing.JTextField jJaminan1;
    private javax.swing.JTextField jJenis;
    private javax.swing.JTextField jKaryawan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelTransaksi;
    private javax.swing.JButton jPengembalian;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jSewa;
    private javax.swing.JButton jSewa1;
    private javax.swing.JButton jSimpan;
    private javax.swing.JTextField jTanggal;
    private javax.swing.JTable jTransaksi;
    // End of variables declaration//GEN-END:variables
}
