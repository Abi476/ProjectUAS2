/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Dashboard;
import Login.Login;
import Sewa.Sewa;
import Transaksi.Transaksi;
import Pengembalian.Pengembalian;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import KoneksiDB.KoneksiDB;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import GajiKaryawan.GajiKaryawan;
/**
 *
 * @author abiba
 */
public class Dashboard extends javax.swing.JFrame {

    Sewa Sewa = new Sewa();
    Transaksi Transaksi = new Transaksi();
    KoneksiDB KoneksiDB = new KoneksiDB();
    DefaultTableModel dtm = new DefaultTableModel();
    
    
    
    public Dashboard(String Username) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        waktu();
        Tanggal();
        generateStok();
        generatePendapatan();
        generateTotalTransaksi();
        AturKolom();
        AturBaris();
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
                    jTextField.setText(tanggal);
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

    public void generateStok() {
        ResultSet rs = KoneksiDB.ambilData("SELECT SUM(Jumlah_Stok) AS total_stock FROM Stok WHERE Jenis IN('Aksesoris', 'Kamera', 'Jasa Photoshoot', 'Jasa Editing', 'jasa Videographer')");
        try {
            if (rs.next()) {
                jTextJumlahStok.setText(rs.getString("total_stock").split(".0")[0]);
            } else {
                jTextJumlahStok.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generatePendapatan() {
        ResultSet rs = KoneksiDB.ambilData("SELECT SUM(Harga) AS total_harga FROM Pengembalian");
        try {
            if (rs.next()) {
                String totalHarga = rs.getString("total_harga");
                if (totalHarga.length() == 6) {
                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formattedTotalHarga = decimalFormat.format(Integer.parseInt(totalHarga));
                    jTextTotalPendapatan.setText(formattedTotalHarga);
                }
            } else {
                jTextTotalPendapatan.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateTotalTransaksi() {
        ResultSet rs = KoneksiDB.ambilData("SELECT COUNT(*) AS total_transaksi FROM Transaksi WHERE Jenis IN('Aksesoris', 'Kamera', 'Jasa Editing', 'Jasa Photoshoot', 'Jasa Videographer')");
        try {
            if (rs.next()) {
                int totalTransaksi = rs.getInt("total_transaksi");
                jTotalTransaksi.setText(String.valueOf(totalTransaksi));
            } else {
                jTotalTransaksi.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void AturKolom() {
        dtm.addColumn("ID Pengembalian");
        dtm.addColumn("ID Transaksi");
        dtm.addColumn("Tanggal");
        dtm.addColumn("Harga");
        dtm.addColumn("Status");
        jRiwayat.setModel(dtm);
        AturBaris();
    }

    public void AturBaris() {
        dtm.setRowCount(0);
        jRiwayat.setModel(dtm);
        ResultSet rs = KoneksiDB.ambilData("SELECT * FROM Pengembalian");
        try {
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("ID_Pengembalian"), rs.getString("ID_Transaksi"), rs.getString("Tanggal"), rs.getString("Harga"), rs.getString("Status")});
            }
            jRiwayat.setModel(dtm);
        } catch (Exception e) {
            AturBaris();
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jJam = new javax.swing.JTextField();
        jSewa = new javax.swing.JButton();
        jTransaksi = new javax.swing.JButton();
        jPengembalian = new javax.swing.JButton();
        jLogout = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jGaji = new javax.swing.JButton();
        jPengguna = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextJumlahStok = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextTotalPendapatan = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTotalTransaksi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jRiwayat = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));

        jTextField.setEditable(false);
        jTextField.setBackground(new java.awt.Color(0, 0, 0));
        jTextField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField.setForeground(new java.awt.Color(255, 255, 255));
        jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField.setBorder(null);
        jTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Poppins SemiBold", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jalan gang sanggar Asri 02 Rembangan");

        jLabel9.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kecamatan Patrang, Kabupaten Jember");

        jLabel10.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jawa Timur");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProjectUAS2/sibhp3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(712, 712, 712)
                        .addComponent(jLabel8)
                        .addGap(488, 488, 488))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jLabel10)))
                        .addGap(521, 521, 521)))
                .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel11))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addGap(18, 18, 18))
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, -1));

        jPanel1.setBackground(new java.awt.Color(49, 170, 49));
        jPanel1.setPreferredSize(new java.awt.Dimension(175, 401));

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
        jSewa.setText("Katalog Sewa");
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

        jLogout.setBackground(new java.awt.Color(0, 0, 0));
        jLogout.setFont(new java.awt.Font("Poppins ExtraBold", 0, 12)); // NOI18N
        jLogout.setForeground(new java.awt.Color(255, 255, 255));
        jLogout.setText("Log Out");
        jLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLogoutMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Waktu");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectuas2/Group 296.png"))); // NOI18N

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

        jPengguna.setText("jLabel12");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPengembalian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                .addComponent(jTransaksi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSewa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jGaji, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                            .addComponent(jJam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLogout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jJam, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPengguna)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jGaji, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
                .addComponent(jLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 170, 980));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel6.setBackground(new java.awt.Color(211, 224, 98));

        jLabel3.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Jumlah Stok");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel3)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTextJumlahStok.setEditable(false);
        jTextJumlahStok.setBackground(new java.awt.Color(255, 255, 255));
        jTextJumlahStok.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jTextJumlahStok.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextJumlahStok.setBorder(null);
        jTextJumlahStok.setFocusable(false);
        jTextJumlahStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextJumlahStokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jTextJumlahStok, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextJumlahStok, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel7.setBackground(new java.awt.Color(98, 128, 224));

        jLabel6.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total Pendapatan");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel6)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTextTotalPendapatan.setEditable(false);
        jTextTotalPendapatan.setBackground(new java.awt.Color(255, 255, 255));
        jTextTotalPendapatan.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jTextTotalPendapatan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextTotalPendapatan.setBorder(null);
        jTextTotalPendapatan.setFocusable(false);
        jTextTotalPendapatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTotalPendapatanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextTotalPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextTotalPendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 270, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel8.setBackground(new java.awt.Color(79, 163, 58));

        jLabel7.setFont(new java.awt.Font("Poppins SemiBold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Transaksi");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel7)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTotalTransaksi.setEditable(false);
        jTotalTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        jTotalTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jTotalTransaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTotalTransaksi.setBorder(null);
        jTotalTransaksi.setFocusable(false);
        jTotalTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTotalTransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTotalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTotalTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 270, -1, -1));

        jRiwayat.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Pengembalian", "ID Transaksi", "Tanggal", "Harga", "Status"
            }
        ));
        jScrollPane1.setViewportView(jRiwayat);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 540, 1201, 237));

        jLabel4.setFont(new java.awt.Font("Geometria", 3, 24)); // NOI18N
        jLabel4.setText("Daftar Riwayat Pengembalian");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 470, -1, 27));

        jLabel2.setFont(new java.awt.Font("Geometria", 3, 24)); // NOI18N
        jLabel2.setText("Ada Kami Untuk Keperluan Anda");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, -1, 30));

        jPanel9.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel9, "card3");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jSewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSewaActionPerformed

    private void jSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSewaMouseClicked
        // TODO add your handling code here:
        Sewa.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jSewaMouseClicked

    private void jLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutMouseClicked
        // TODO add your handling code here:
        Login Login = new Login();
        Login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLogoutMouseClicked

    private void jTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTransaksiMouseClicked
        // TODO add your handling code here:
        Transaksi.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jTransaksiMouseClicked

    private void jTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransaksiActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_jTransaksiActionPerformed

    private void jPengembalianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPengembalianMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jPengembalianMouseClicked

    private void jPengembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPengembalianActionPerformed
        //TODO add your handling code here:
        Pengembalian Pengembalian = new Pengembalian();
        Pengembalian.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jPengembalianActionPerformed

    private void jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldActionPerformed

    private void jTextTotalPendapatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTotalPendapatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTotalPendapatanActionPerformed

    private void jJamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jJamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jJamActionPerformed

    private void jTextJumlahStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextJumlahStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextJumlahStokActionPerformed

    private void jTotalTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTotalTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTotalTransaksiActionPerformed

    private void jGajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jGajiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jGajiMouseClicked

    private void jGajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGajiActionPerformed
        // TODO add your handling code here:
        GajiKaryawan GajiKaryawan = new GajiKaryawan();
        GajiKaryawan.setVisible(true);
        this.setVisible(false);
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jGaji;
    private javax.swing.JTextField jJam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jLogout;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jPengembalian;
    private javax.swing.JLabel jPengguna;
    private javax.swing.JTable jRiwayat;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSewa;
    private javax.swing.JTextField jTextField;
    private javax.swing.JTextField jTextJumlahStok;
    private javax.swing.JTextField jTextTotalPendapatan;
    private javax.swing.JTextField jTotalTransaksi;
    private javax.swing.JButton jTransaksi;
    // End of variables declaration//GEN-END:variables
}
