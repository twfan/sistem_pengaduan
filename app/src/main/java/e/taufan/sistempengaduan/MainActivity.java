package e.taufan.sistempengaduan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import e.taufan.sistempengaduan.helper.Config;

public class MainActivity extends AppCompatActivity {

    private static final String[] DOCTYPE = new String[] {
            "SIUP", "TDP", "SIMULTAN"
    };

    @BindView(R.id.SpinnerDocType) BetterSpinner SpinnerDocType;
    @BindView(R.id.editTextNoOnline) EditText editTextNoOnline;
    @BindView(R.id.btnLogin) Button btnLogin;
    @BindView(R.id.ripLogin) RippleView ripLogin;



    public static String no_online;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);




//        ====================SPINNER=============
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DOCTYPE);
        final BetterSpinner textView = (BetterSpinner) findViewById(R.id.SpinnerDocType);
        textView.setAdapter(adapter);
//        with declaration array in top
//    ======================END OF SPINNER===========


        ripLogin.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                btnLogin.setEnabled(false);
                _loginMYSQL();
//                startActivity(new Intent(MainActivity.this, AduanActivity.class ));
//                finish();
            }
        });
    }



    private void _loginMYSQL(){
        final String varNologin = editTextNoOnline.getText().toString() ;
        String varPerizinan = SpinnerDocType.getText().toString();

        MainActivity.no_online = varNologin;

        AndroidNetworking.post(Config.HOST + "login.php")
                .addBodyParameter("no_online", varNologin)
                .addBodyParameter("jenis_perizinan", varPerizinan)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try{
                            if(response.getString("response").equals("success")){
//                                Toast.makeText(LoginActivity.this , "Anda Berhasil login ", Toast.LENGTH_LONG).show();
                                MainActivity.no_online = varNologin;
                                startActivity(new Intent(MainActivity.this, AduanActivity.class ));
                                finish();

                            }else
                            {
                                Toast.makeText(MainActivity.this , "Kombinasi nomor online dan jenis perizinan tidak terdaftar. ", Toast.LENGTH_LONG).show();
                                btnLogin.setEnabled(true);
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
