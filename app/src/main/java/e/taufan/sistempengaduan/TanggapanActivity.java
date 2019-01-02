package e.taufan.sistempengaduan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
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

public class TanggapanActivity extends AppCompatActivity implements RatingDialogListener {

    public static int rate = 0, check_answer=0;

    @BindView(R.id.ripTampilkanSolusi) RippleView ripTampilkanSolusi;
    @BindView(R.id.ripTutupAplikasi) RippleView ripTutupAplikasi;
    @BindView(R.id.btnTampilSolusi) Button btnTampilSolusi;
    @BindView(R.id.TxtTanggapan) TextView TxtTanggapan;
    @BindView(R.id.btnTutupAplikasi) Button btnTutupAplikasi;

    String tanggapan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanggapan);
        ButterKnife.bind(this);

        ripTampilkanSolusi.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
//                showDialog();
                if(check_answer==0){
                    btnTampilSolusi.setEnabled(false);
                    readMYSQL();
                }else
                {
                    Toast.makeText(TanggapanActivity.this , check_answer, Toast.LENGTH_LONG).show();
                }
            }
        });

        ripTutupAplikasi.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                if(check_answer!=0){
                    showDialog();
                }
            }
        });
    }

    //===========================================RATING CODE============================
    @Override
    public void onPositiveButtonClicked(int rate, String comment) {
        // interpret results, send it to analytics etc...

        TanggapanActivity.rate = rate;
        addMYSQL();


    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }

    private void showDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(2)
                .setTitle("Rate this application")
                .setCommentInputEnabled(false)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .setCanceledOnTouchOutside(false)
                .create(TanggapanActivity.this)
                .show();
    }

//    ===========================================END OF RATING CODE============================


    private void readMYSQL(){

        String add_no_online = MainActivity.no_online;
        Log.v("_no_online",add_no_online);

        AndroidNetworking.post(Config.HOST + "read_tanggapan.php")
                .addBodyParameter("no_online", add_no_online)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            if(response.getString("response").equals("success")){

                                JSONArray jsonArray = response.getJSONArray("hasil");
                                for(int i=0;i< jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    tanggapan = jsonObject.getString("tanggapan");
                                }

                                if(tanggapan.trim().isEmpty()){
                                    btnTampilSolusi.setEnabled(true);
                                    Toast.makeText(TanggapanActivity.this , "Belum ada tanggapan dari petugas", Toast.LENGTH_LONG).show();
                                }else{
                                    TxtTanggapan.setText(tanggapan);
                                    btnTutupAplikasi.setEnabled(true);
                                    check_answer=1;
                                }
                            }else
                            {
                                btnTampilSolusi.setEnabled(true);
                                Toast.makeText(TanggapanActivity.this , response.getString("response"), Toast.LENGTH_LONG).show();
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

    private void addMYSQL(){

        String add_no_online = MainActivity.no_online;
        Integer rate = TanggapanActivity.rate;

        AndroidNetworking.post(Config.HOST + "update_tanggapan.php")
                .addBodyParameter("no_online", add_no_online)
                .addBodyParameter("rate", String.valueOf(rate))
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            if(response.getString("response").equals("success")){

                                Toast.makeText(TanggapanActivity.this ,"Terima kasih telah menggunakan aplikasi ini", Toast.LENGTH_LONG).show();
                                finish();
                                System.exit(0);
                            }else
                            {
                                Toast.makeText(TanggapanActivity.this , response.getString("response"), Toast.LENGTH_LONG).show();
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
