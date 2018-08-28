package ahmadqorib97.gmail.com.responsimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText nik, nama, alamat;
    Button simpan, tampil;
    RadioGroup rg;
    RadioButton jenis, laki, perempuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nik = (EditText) findViewById(R.id.t_nik);
        nama = (EditText) findViewById(R.id.t_nama);
        alamat = (EditText) findViewById(R.id.t_alamat);
        simpan = (Button) findViewById(R.id.btn_simpan);
        tampil = (Button) findViewById(R.id.btn_tampil);
        laki = (RadioButton) findViewById(R.id.rdb_laki);
        perempuan = (RadioButton) findViewById(R.id.rdb_perem);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] NIK = new byte[30];
                byte[] NAMA = new byte[30];
                byte[] JK = new byte[50];
                byte[] ALAMAT = new byte[50];

                String jkl = "";
                if(laki.isChecked()){
                    jkl ="Laki-laki";
                }else{
                    jkl ="Perempuan";
                }

                salinData(NIK, nik.getText().toString());
                salinData(NAMA, nama.getText().toString());
                salinData(ALAMAT, alamat.getText().toString());
                salinData(JK, jkl);

                try {
                    FileOutputStream dataFile = openFileOutput("penduduk.dat", MODE_APPEND);
                    DataOutputStream output = new DataOutputStream(dataFile);
                    output.write(NIK);
                    output.write(NAMA);
                    output.write(ALAMAT);
                    output.write(JK);

                    dataFile.close();

                    Toast.makeText(getBaseContext(), "Data berhasil disimpan", Toast.LENGTH_LONG).show();
                    nik.setText("");
                    nama.setText("");
                    alamat.setText("");
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tampil = new Intent(view.getContext(), ShowData.class);
                startActivity(tampil);
            }
        });
    }

    public void salinData(byte[] buffer, String data){
        for(int i = 0; i<buffer.length; i++)
            buffer[i]=0;
        for(int i=0; i<data.length(); i++)
            buffer[i]=(byte)data.charAt(i);
    }
}
