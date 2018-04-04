# Android text to speech example

<h3>We must declare and init</h3>

```java
textToSpeech = new TextToSpeech(this,this);

...

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
```

<h3>Call the speak</h3>

```java
textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "utteranceId");
```

<h3>Stop and shut down</h3>

```java
 @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
    ```
