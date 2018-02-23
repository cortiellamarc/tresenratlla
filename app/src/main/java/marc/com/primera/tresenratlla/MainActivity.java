package marc.com.primera.tresenratlla;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iniciem l'array caselles que identifica cada casella i l'enmagatzema dins l'array

        CASILLAS=new int[9];
        CASILLAS[0]=R.id.a1;
        CASILLAS[1]=R.id.a2;
        CASILLAS[2]=R.id.a3;
        CASILLAS[3]=R.id.b1;
        CASILLAS[4]=R.id.b2;
        CASILLAS[5]=R.id.b3;
        CASILLAS[6]=R.id.c1;
        CASILLAS[7]=R.id.c2;
        CASILLAS[8]=R.id.c3;



    }

    public void aJugar(View vista){

        ImageView imagen;
        //reseteixa el taulell assignant una casella en blanc a  cada valor del array
        for (int cadaCasilla:CASILLAS){
            imagen=(ImageView) findViewById((cadaCasilla));
            imagen.setImageResource(R.drawable.casilla);

        }

        //el parametre vista enmagatzema si s'ha pulsat 2 jug,
        jugadores=1;
        if (vista.getId()==R.id.dosjug){
            jugadores=2;
        }
        RadioGroup configDificultad=(RadioGroup) findViewById(R.id.configD);
        //estableix la dificultad
        int id=configDificultad.getCheckedRadioButtonId();
        int dificultad=0;

        if (id==R.id.normal) {
            dificultad = 1;
        }else if (id==R.id.imposible){
            dificultad=2;
        }
        //es crida el contructor de la classe partidad
        partida=new Partida(dificultad);
        //inhabilita botons i radio group
        ((Button) findViewById(R.id.unjug)).setEnabled(false);

        ((RadioGroup)findViewById(R.id.configD)).setAlpha(0);

        ((Button) findViewById(R.id.dosjug)).setEnabled(false);
    }

    // espera que es pitji una cella que li passa miVista i enmagatzema la cel.la a casilla
    public void toque(View miVista){
        // aquest condicional fa que no faci cap acció si no s'ha escollit jugadors i nivell de dificultat
        // o sigui, que no es pugui pulsr en el grid si no a començat el joc
        if (partida==null){
            return;
        }
        int casilla=0;
        for (int i=0;i<9;i++){
            if (CASILLAS[i]==miVista.getId()){

                casilla=i;
                break;
            }
        }
     /*   Toast toast=Toast.makeText(this, "Has petjat la casella "+casilla, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();*/

        if (partida.comprueba_casilla(casilla)==false){
            return;
        }
        marca(casilla);


        casilla=partida.ia();
        while (partida.comprueba_casilla(casilla)!=true){
            casilla=partida.ia();
        }
        partida.turno();
        marca(casilla);
        partida.turno();
    }

    private void marca(int casilla){
        ImageView imagen;
        imagen=(ImageView)findViewById(CASILLAS[casilla]);

        if (partida.jugador==1){
            imagen.setImageResource(R.drawable.circulo);
        }
        else{
            imagen.setImageResource(R.drawable.aspa);
        }
    }

    private int jugadores;
    private int[] CASILLAS;
    private Partida partida;


}
