package com.okankocaturk.lotoatici;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.okankocaturk.lotoatici.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    Button btnRasgele, btnSayisal, btnSuper, btnOn, btnSans, btnYanliSuper,btnTavla,btnTombala,btnYanliCilgin;
    ArrayList<Integer> sayiArray, yanliSayiArray;
    ArrayList<Integer> rasgele;
    int secilen,secilenIndis;
    Random rnd;
    TextView txtSayilar, txtSansTopu;
    EditText edtAlt, edtUst, edtN,edtBanko;
    CheckBox chkSirali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setContentView(R.layout.layout);

        txtSayilar = (TextView) findViewById(R.id.textView1);
        txtSansTopu = (TextView) findViewById(R.id.txtSansTopu);

        btnRasgele = (Button) findViewById(R.id.btnRasgele6Sayi);
        btnSayisal = (Button) findViewById(R.id.btnSayisal);
        btnSuper = (Button) findViewById(R.id.btnSuper);
        btnOn = (Button) findViewById(R.id.btnOn);
        btnSans = (Button) findViewById(R.id.btnSans);
        btnYanliSuper = (Button) findViewById(R.id.btnYanliSuper);
        btnTavla = (Button) findViewById(R.id.btnTavla);
        btnTombala = (Button) findViewById(R.id.btnTombala);
        btnYanliCilgin = (Button) findViewById(R.id.btnYanliCilgin);

        edtAlt = (EditText) findViewById(R.id.editAlt);
        edtUst = (EditText) findViewById(R.id.editUst);
        edtN = (EditText) findViewById(R.id.editN);
        edtBanko = (EditText) findViewById(R.id.editBanko);

        chkSirali = (CheckBox) findViewById(R.id.chkSirali);

        btnRasgele.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                try {
                    if(edtAlt.getText().toString().length()>0 && edtAlt.getText().toString().length()>0 && edtAlt.getText().toString().length()>0) {
                        int alt = Integer.parseInt(edtAlt.getText().toString());
                        int ust = Integer.parseInt(edtUst.getText().toString());
                        int n = Integer.parseInt(edtN.getText().toString());

                        if (ust < alt) {
                            txtSayilar.setText("üst limit alt limitten küçük olamaz");
                            txtSansTopu.setText("");
                            return;
                        }
                        if (n < 1) {
                            txtSayilar.setText("atılacak sayı adedi az");
                            txtSansTopu.setText("");
                            return;
                        }
                        if (n > (ust - alt + 1)) {
                            txtSayilar.setText("atılacak sayı adedi fazla");
                            txtSansTopu.setText("");
                            return;
                        }

                        ArrayDoldur(alt, ust);
                        attir(n, alt, ust);
                    }else{ txtSayilar.setText("alt, üst ve adet giriniz"); }
                }catch(Exception ex){
                    //ex.printStackTrace();
                    txtSayilar.setText("hata!"+ex.getMessage());

                }
            }
        });
        btnSayisal.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                try {
                    edtAlt.setText("1");
                    edtUst.setText("90");
                    edtN.setText("6");
                    int alt = Integer.parseInt(edtAlt.getText().toString());
                    int ust = Integer.parseInt(edtUst.getText().toString());
                    int n = Integer.parseInt(edtN.getText().toString());

                    ArrayDoldur(alt, ust);
                    attir(n, alt, ust);

                } catch (Exception ex) {
                    //ex.printStackTrace();
                    txtSayilar.setText("hata!" + ex.getMessage());

                }
            }
        });
        btnSuper.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                try {
                edtAlt.setText("1");
                edtUst.setText("60");
                edtN.setText("6");
                int alt = Integer.parseInt(edtAlt.getText().toString());
                int ust = Integer.parseInt(edtUst.getText().toString());
                int n = Integer.parseInt(edtN.getText().toString());

                ArrayDoldur(alt, ust);
                attir(n, alt, ust);

            }catch(Exception ex){
                //ex.printStackTrace();
                txtSayilar.setText("hata!"+ex.getMessage());

            }
            }
        });
        btnOn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                try {
                edtAlt.setText("1");
                edtUst.setText("80");
                edtN.setText("10");
                int alt = Integer.parseInt(edtAlt.getText().toString());
                int ust = Integer.parseInt(edtUst.getText().toString());
                int n = Integer.parseInt(edtN.getText().toString());

                ArrayDoldur(alt, ust);
                attir(n, alt, ust);
                }catch(Exception ex){
                    //ex.printStackTrace();
                    txtSayilar.setText("hata!"+ex.getMessage());

                }
            }
        });
        btnSans.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                try {
                edtAlt.setText("1");
                edtUst.setText("34");
                edtN.setText("5");
                int alt = Integer.parseInt(edtAlt.getText().toString());
                int ust = Integer.parseInt(edtUst.getText().toString());
                int n = Integer.parseInt(edtN.getText().toString());

                ArrayDoldur(alt, ust);
                attir(n, alt, ust);

                edtAlt.setText("1");
                edtUst.setText("14");
                edtN.setText("1");
                alt = Integer.parseInt(edtAlt.getText().toString());
                ust = Integer.parseInt(edtUst.getText().toString());
                n = Integer.parseInt(edtN.getText().toString());

                ArrayDoldur(alt, ust);
                attirSansTopu(n, alt, ust);
                }catch(Exception ex){
                    //ex.printStackTrace();
                    txtSayilar.setText("hata!"+ex.getMessage());

                }
            }
        });
        btnYanliSuper.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                try {
                edtAlt.setText("1");
                edtUst.setText("60");
                edtN.setText("6");
                int alt = Integer.parseInt(edtAlt.getText().toString());
                int ust = Integer.parseInt(edtUst.getText().toString());
                int n = Integer.parseInt(edtN.getText().toString());



                ArrayDoldur(alt, ust);
                chkSirali.setChecked(true);

                int secilecekler = ust - alt + 1;
                yanliSayiArray = new ArrayList<Integer>(secilecekler);

                for(int sayi: sayiArray){yanliSayiArray.add(sayi);}

                YanliArrayDoldur(1	,9 -1);
                YanliArrayDoldur(2	,6 -1);
                YanliArrayDoldur(3	,6 -1);
                YanliArrayDoldur(4	,8 -1+1);
                YanliArrayDoldur(5	,10-1);
                YanliArrayDoldur(6	,4 -1);
                YanliArrayDoldur(7	,10-1);
                YanliArrayDoldur(8	,9 -1);
                YanliArrayDoldur(9	,14-1);
                YanliArrayDoldur(10	,10-1);
                YanliArrayDoldur(11	,13-1);
                YanliArrayDoldur(12	,11-1);
                YanliArrayDoldur(13	,9 -1);
                YanliArrayDoldur(14	,13-1);
                YanliArrayDoldur(15	,10-1);
                YanliArrayDoldur(16	,8 -1);
                YanliArrayDoldur(17	,6 -1);
                YanliArrayDoldur(18	,8 -1+1);
                YanliArrayDoldur(19	,7 -1);
                YanliArrayDoldur(20	,11-1);
                YanliArrayDoldur(21	,8 -1);
                YanliArrayDoldur(22	,16-1);
                YanliArrayDoldur(23	,10-1);
                YanliArrayDoldur(24	,17-1);
                YanliArrayDoldur(25	,10-1);
                YanliArrayDoldur(26	,6 -1+1);
                YanliArrayDoldur(27	,12-1+1);
                YanliArrayDoldur(28	,13-1);
                YanliArrayDoldur(29	,10-1);
                YanliArrayDoldur(30	,6 -1+1);
                YanliArrayDoldur(31	,8 -1);
                YanliArrayDoldur(32	,11-1);
                YanliArrayDoldur(33	,6 -1);
                YanliArrayDoldur(34	,3 -1);
                YanliArrayDoldur(35	,17-1);
                YanliArrayDoldur(36	,13-1);
                YanliArrayDoldur(37	,11-1);
                YanliArrayDoldur(38	,14-1);
                YanliArrayDoldur(39	,16-1);
                YanliArrayDoldur(40	,13-1);
                YanliArrayDoldur(41	,10-1);
                YanliArrayDoldur(42	,10-1);
                YanliArrayDoldur(43	,9 -1);
                YanliArrayDoldur(44	,14-1);
                YanliArrayDoldur(45	,9 -1);
                YanliArrayDoldur(46	,8 -1);
                YanliArrayDoldur(47	,9 -1);
                YanliArrayDoldur(48	,14-1);
                YanliArrayDoldur(49	,6 -1);
                YanliArrayDoldur(50	,9 -1);
                YanliArrayDoldur(51	,5 -1);
                YanliArrayDoldur(52	,8 -1);
                YanliArrayDoldur(53	,8 -1);
                YanliArrayDoldur(54	,8 -1);
                YanliArrayDoldur(55	,10-1);
                YanliArrayDoldur(56	,8 -1+1);
                YanliArrayDoldur(57	,7 -1);
                YanliArrayDoldur(58	,11-1);
                YanliArrayDoldur(59	,15-1);
                YanliArrayDoldur(60	,8 -1);

                YanliAttir(n);
                //attir(n, alt, ust);
                }catch(Exception ex){
                    ex.printStackTrace();
                    txtSayilar.setText("hata! y "+ex.getMessage());

                }
            }
        });
        btnTavla.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                try {
                edtAlt.setText("1");
                edtUst.setText("6");
                edtN.setText("1");
                int alt = Integer.parseInt(edtAlt.getText().toString());
                int ust = Integer.parseInt(edtUst.getText().toString());
                int n = Integer.parseInt(edtN.getText().toString());

                ArrayDoldur(alt, ust);
                attir(n, alt, ust);

                edtAlt.setText("1");
                edtUst.setText("6");
                edtN.setText("1");
                alt = Integer.parseInt(edtAlt.getText().toString());
                ust = Integer.parseInt(edtUst.getText().toString());
                n = Integer.parseInt(edtN.getText().toString());

                ArrayDoldur(alt, ust);
                attirSansTopu(n, alt, ust);
                }catch(Exception ex){
                    //ex.printStackTrace();
                    txtSayilar.setText("hata!"+ex.getMessage());

                }
            }
        });
        btnTombala.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                    try {

                edtAlt.setText("1");
                edtUst.setText("90");
                edtN.setText("90");
                chkSirali.setChecked(false);
                int alt = Integer.parseInt(edtAlt.getText().toString());
                int ust = Integer.parseInt(edtUst.getText().toString());
                int n = Integer.parseInt(edtN.getText().toString());

                ArrayDoldur(alt, ust);
                attir(n, alt, ust);
                    }catch(Exception ex){
                        //ex.printStackTrace();
                        txtSayilar.setText("hata!"+ex.getMessage());

                    }
            }
        });



        btnYanliCilgin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                try {
                    edtAlt.setText("1");
                    edtUst.setText("90");
                    edtN.setText("6");
                    int alt = Integer.parseInt(edtAlt.getText().toString());
                    int ust = Integer.parseInt(edtUst.getText().toString());
                    int n = Integer.parseInt(edtN.getText().toString());

                    ArrayDoldur(alt, ust);
                    chkSirali.setChecked(true);

                    int secilecekler = ust - alt + 1;
                    yanliSayiArray = new ArrayList<Integer>(secilecekler);

                    for(int sayi: sayiArray){yanliSayiArray.add(sayi);}

                    YanliArrayDoldur(1	,17-1);
                    YanliArrayDoldur(2	,7 -1);
                    YanliArrayDoldur(3	,11-1);
                    YanliArrayDoldur(4	,8 -1);
                    YanliArrayDoldur(5	,15-1);
                    YanliArrayDoldur(6	,10-1);
                    YanliArrayDoldur(7	,12-1);
                    YanliArrayDoldur(8	,15-1);
                    YanliArrayDoldur(9	,6 -1);
                    YanliArrayDoldur(10	,4 -1);
                    YanliArrayDoldur(11	,14-1);
                    YanliArrayDoldur(12	,12-1);
                    YanliArrayDoldur(13	,14-1);
                    YanliArrayDoldur(14	,9 -1);
                    YanliArrayDoldur(15	,13-1);
                    YanliArrayDoldur(16	,7 -1);
                    YanliArrayDoldur(17	,7 -1);
                    YanliArrayDoldur(18	,13-1);
                    YanliArrayDoldur(19	,11-1);
                    YanliArrayDoldur(20	,9 -1);
                    YanliArrayDoldur(21	,15-1);
                    YanliArrayDoldur(22	,13-1);
                    YanliArrayDoldur(23	,12-1);
                    YanliArrayDoldur(24	,9 -1);
                    YanliArrayDoldur(25	,7 -1);
                    YanliArrayDoldur(26	,6 -1);
                    YanliArrayDoldur(27	,11-1);
                    YanliArrayDoldur(28	,8 -1);
                    YanliArrayDoldur(29	,10-1);
                    YanliArrayDoldur(30	,9 -1);
                    YanliArrayDoldur(31	,9 -1);
                    YanliArrayDoldur(32	,12-1);
                    YanliArrayDoldur(33	,10-1);
                    YanliArrayDoldur(34	,6 -1);
                    YanliArrayDoldur(35	,7 -1);
                    YanliArrayDoldur(36	,3 -1);
                    YanliArrayDoldur(37	,9 -1);
                    YanliArrayDoldur(38	,7 -1);
                    YanliArrayDoldur(39	,12-1);
                    YanliArrayDoldur(40	,5 -1);
                    YanliArrayDoldur(41	,9 -1);
                    YanliArrayDoldur(42	,10-1);
                    YanliArrayDoldur(43	,13-1);
                    YanliArrayDoldur(44	,12-1);
                    YanliArrayDoldur(45	,13-1);
                    YanliArrayDoldur(46	,12-1);
                    YanliArrayDoldur(47	,10-1);
                    YanliArrayDoldur(48	,10-1);
                    YanliArrayDoldur(49	,7 -1);
                    YanliArrayDoldur(50	,6 -1);
                    YanliArrayDoldur(51	,6 -1);
                    YanliArrayDoldur(52	,20-1);
                    YanliArrayDoldur(53	,9 -1);
                    YanliArrayDoldur(54	,9 -1);
                    YanliArrayDoldur(55	,12-1);
                    YanliArrayDoldur(56	,11-1);
                    YanliArrayDoldur(57	,7 -1);
                    YanliArrayDoldur(58	,10-1);
                    YanliArrayDoldur(59	,11-1);
                    YanliArrayDoldur(60	,12-1);
                    YanliArrayDoldur(61	,12-1);
                    YanliArrayDoldur(62	,17-1);
                    YanliArrayDoldur(63	,10-1);
                    YanliArrayDoldur(64	,9 -1);
                    YanliArrayDoldur(65	,9 -1);
                    YanliArrayDoldur(66	,9 -1);
                    YanliArrayDoldur(67	,9 -1);
                    YanliArrayDoldur(68	,5 -1);
                    YanliArrayDoldur(69	,10-1);
                    YanliArrayDoldur(70	,11-1);
                    YanliArrayDoldur(71	,15-1);
                    YanliArrayDoldur(72	,14-1);
                    YanliArrayDoldur(73	,17-1);
                    YanliArrayDoldur(74	,16-1);
                    YanliArrayDoldur(75	,9 -1);
                    YanliArrayDoldur(76	,8 -1);
                    YanliArrayDoldur(77	,8 -1);
                    YanliArrayDoldur(78	,10-1);
                    YanliArrayDoldur(79	,10-1);
                    YanliArrayDoldur(80	,7 -1);
                    YanliArrayDoldur(81	,8 -1);
                    YanliArrayDoldur(82	,13-1);
                    YanliArrayDoldur(83	,16-1);
                    YanliArrayDoldur(84	,7 -1);
                    YanliArrayDoldur(85	,12-1);
                    YanliArrayDoldur(86	,6 -1);
                    YanliArrayDoldur(87	,18-1);
                    YanliArrayDoldur(88	,12-1);
                    YanliArrayDoldur(89	,13-1);
                    YanliArrayDoldur(90	,13-1);

                    YanliAttir(n);
                    //attir(n, alt, ust);
                }catch(Exception ex){
                    ex.printStackTrace();
                    txtSayilar.setText("hata! y "+ex.getMessage());

                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void ArrayDoldur(int alt, int ust) {
        int secilecekler = ust - alt + 1;
        // secilecekler= 55;
        sayiArray = new ArrayList<Integer>(secilecekler);

        for (int i = 0; i < secilecekler; i++) {
            sayiArray.add(i, i + alt);
        }

    }

    private void attir(int sayiAdedi, int alt, int ust) {
        // rasgele=new ArrayList<Integer>(6);

        rasgele = new ArrayList<Integer>(sayiAdedi);

        for (int j = 0; j < sayiAdedi; j++) {
            rnd = new java.util.Random();
            int elemanSayisi = ust - alt + 1;
            secilen = rnd.nextInt(elemanSayisi - j); // nextInt fonksiyonu 0 inclusive 54 exclusive seçiyormuş
            rasgele.add(sayiArray.get(secilen));
            for (int i = secilen; i < (elemanSayisi - 1 - j); i++) {
                sayiArray.set(i, sayiArray.get(i + 1));
            }
            sayiArray.remove(elemanSayisi - 1 - j);
        }
        if (chkSirali.isChecked()){
            Collections.sort(rasgele);
        }

        txtSayilar.setText(rasgele.toString());
        txtSansTopu.setText("");
        edtBanko.setText("");

    }

    private void attirSansTopu(int sayiAdedi, int alt, int ust) {
        // rasgele=new ArrayList<Integer>(6);

        rasgele = new ArrayList<Integer>(sayiAdedi);

        for (int j = 0; j < sayiAdedi; j++) {
            rnd = new java.util.Random();
            int elemanSayisi = ust - alt + 1;
            secilen = rnd.nextInt(elemanSayisi - j); // nextInt fonksiyonu 0 inclusive 54 exclusive seçiyormuş

            rasgele.add(sayiArray.get(secilen));

            for (int i = secilen; i < (elemanSayisi - 1 - j); i++) {
                sayiArray.set(i, sayiArray.get(i + 1));
            }
            sayiArray.remove(elemanSayisi - 1 - j);
        }

        Collections.sort(rasgele);
        txtSansTopu.setText(rasgele.toString());
        edtBanko.setText("");

    }

    private void YanliArrayDoldur(int numara, int adet) {
        int indis = yanliSayiArray.indexOf(numara);

        for (int i = 0; i < adet; i++) {
            yanliSayiArray.add(indis+1, numara);
        }

    }

    private void YanliAttir(int sayiAdedi) {

        int secilenSayi=-1;

        rasgele = new ArrayList<Integer>(sayiAdedi);

        //banko meselesi
        if(edtBanko.getText().toString().length()>0) {
            int banko = Integer.parseInt(edtBanko.getText().toString());
            rasgele.add(banko);
            sayiAdedi--;

            int bankonunIndisi;
            for (int indis = 0; indis < yanliSayiArray.size(); indis++) {
                if (yanliSayiArray.get(indis) == banko) {
                    bankonunIndisi = indis;

                    while (yanliSayiArray.get(bankonunIndisi) == banko) {
                        ArrayiKaydir(yanliSayiArray, bankonunIndisi);

                    }
                }
            }

        }

        for (int j = 0; j < sayiAdedi; j++) {

            rnd = new java.util.Random();
            //int elemanSayisi = ust - alt + 1;
            int elemanSayisi = yanliSayiArray.size();

            secilenIndis = rnd.nextInt(elemanSayisi); // nextInt fonksiyonu 0 inclusive 54 exclusive seçiyormuş
            secilenSayi = yanliSayiArray.get(secilenIndis);
            rasgele.add(secilenSayi);


        try{
            int bulduguIndis;
            for(int indis=0;indis<yanliSayiArray.size();indis++){
                if (yanliSayiArray.get(indis)==secilenSayi){
                      bulduguIndis=indis;

                      while(yanliSayiArray.get(bulduguIndis)==secilenSayi) {
                          ArrayiKaydir(yanliSayiArray, bulduguIndis);

                      }
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
            txtSayilar.setText("hata! y u "+ex.getMessage());

        }

        }


        if (chkSirali.isChecked()){
            Collections.sort(rasgele);
        }

        txtSayilar.setText(rasgele.toString());
        txtSansTopu.setText("");


    }

    private void ArrayiKaydir(ArrayList kaydirilacakArray, int indis){
        int elemanSayisi = yanliSayiArray.size();
        for (int i=indis;i< yanliSayiArray.size()-1;i++) {
            yanliSayiArray.set(i, yanliSayiArray.get(i + 1));
        }
        yanliSayiArray.remove(elemanSayisi-1);

    }

}
