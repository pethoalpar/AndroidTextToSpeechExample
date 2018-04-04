package pethoalpar.com.texttospeechexample;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private EditText editText;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) this.findViewById(R.id.editText);
        textToSpeech = new TextToSpeech(this,this);

        this.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "utteranceId");
            }
        });
    }

    @Override
    public void onInit(int i) {
        if(i == TextToSpeech.SUCCESS){
            int result = textToSpeech.setLanguage(Locale.ENGLISH);
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "This language is not supported", Toast.LENGTH_SHORT).show();
            }else{
                textToSpeech.setPitch((float) 0.98);
            }
        } else{
            Toast.makeText(this, "Init failed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
