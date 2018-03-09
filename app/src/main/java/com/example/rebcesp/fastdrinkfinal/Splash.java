package com.example.rebcesp.fastdrinkfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    //VAMOS!, ESTA APP Tiene que TERMINARSE!
    ImageView logo;
    //Declaramos la imagen del logo


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = (ImageView) findViewById(R.id.ImagenLogo);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition); //Creamos la animacion para la presentacion

        logo.startAnimation(myanim); //Iniciamos la imagen ANIMADA

  /*
        String fuente1 = "fonts/clothe.ttf"; //Declaramos la fuente de la letra
        this.clothe = Typeface.createFromAsset(getAssets(),fuente1); //Creamos la fuente
        txt.setTypeface(clothe); //Llamamos a la fuente
        Utilizamos esta sintaxis si queremos cambiar la fuente de la letra
*/

        final Intent i = new Intent(this, SignInActivity.class); //Luego de la transicion pasamos al segundo layout

        Thread timer = new Thread() {

            public void run() {

                try {

                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(Splash.this,SignInActivity.class);
                    startActivity(i);
                    finish();
                }
            }

        };
        timer.start();
    }

}




