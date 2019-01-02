package e.taufan.sistempengaduan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import e.taufan.sistempengaduan.helper.Config;

public class AduanActivity extends AppCompatActivity{


    @BindView(R.id.ripKirimMasalah) RippleView ripKirimMasalah;
    @BindView(R.id.editMasalah) EditText editMasalah;
    @BindView(R.id.btnKirimMasalah) Button btnKirimMasalah;

    public static Integer Id_user ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aduan);
        ButterKnife.bind(this);

        ripKirimMasalah.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                btnKirimMasalah.setEnabled(false);
                addMysQL();
            }
        });
    }

    private void addMysQL(){

        String add_no_online = MainActivity.no_online;
        String add_keluhan = editMasalah.getText().toString();

        AndroidNetworking.post(Config.HOST + "insert_masalah.php")
                .addBodyParameter("no_online", add_no_online)
                .addBodyParameter("keluhan", add_keluhan)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            if(response.getString("response").equals("success")){
                                btnKirimMasalah.setEnabled(true);

                                Toast.makeText(AduanActivity.this , "Pengaduan telah tersimpan, Terima kasih. ", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(AduanActivity.this, TanggapanActivity.class));
                                finish();

                                JSONArray jsonArray = response.getJSONArray("hasil");
                                for(int i=0;i< jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Id_user = jsonObject.getInt("ID");
                                }
                            }else
                            {
                                btnKirimMasalah.setEnabled(true);
                                Toast.makeText(AduanActivity.this , response.getString("response"), Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
