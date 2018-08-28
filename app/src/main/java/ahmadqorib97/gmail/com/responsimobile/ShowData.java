package ahmadqorib97.gmail.com.responsimobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ShowData extends AppCompatActivity {
    TextView txTampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        txTampil = (TextView) findViewById(R.id.txt_show);
        try{
            FileInputStream dataFile = openFileInput("penduduk.dat");
            DataInputStream input = new DataInputStream(dataFile);

            byte[] NIK = new byte[30];
            byte[] NAMA = new byte[30];
            byte[] JK = new byte[50];
            byte[] ALAMAT = new byte[50];

            String Data = "";
            int no = 1;
            while (input.available() >0){
                input.read(NIK);
                input.read(NAMA);
                input.read(JK);
                input.read(ALAMAT);

                String dataNIK = "";
                for(int i =0; i<NIK.length; i++)
                    dataNIK = dataNIK + (char)NIK[i];

                String dataNAMA = "";
                for (int i=0; i<NAMA.length; i++)
                    dataNAMA = dataNAMA + (char)NAMA[i];

                String dataALAMAT = "";
                for (int i=0; i<ALAMAT.length; i++)
                    dataALAMAT= dataALAMAT + (char)ALAMAT[i];

                String dataJK = "";
                for (int i=0; i<JK.length; i++)
                    dataJK = dataJK + (char)JK[i];

                Data = Data + no + ". " + dataNIK + " " + dataNAMA + " " + dataJK + " " + dataALAMAT + "\n";

                no++;
            }

            txTampil.setText(Data);
            dataFile.close();
        }
        catch (IOException e){
            Toast.makeText(getBaseContext(), "error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
