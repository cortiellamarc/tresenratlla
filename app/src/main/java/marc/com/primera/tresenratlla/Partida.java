package marc.com.primera.tresenratlla;

import java.util.Random;

/**
 * Created by Marc on 20/2/2018.
 */

public class Partida {

    // això és el constructors
    public Partida(int dificultad){

        // quan crides la funcio s'enmagatzema a int dificultad, abaix enmagatzem el valor dins de la constant de classe dificultad
        //estableix la dificultat
        this.dificultad=dificultad;
        jugador=1;
        // el bucle aquest es una matriu per evaluar si s'ha petjat una tecla
        casillas=new int[9];
        for(int i=0;i<9;i++){
            casillas[i]=0;
        }
    }

    public boolean comprueba_casilla(int casilla){
        if (casillas[casilla]!=0){
            return false;
        }else {
            casillas[casilla]=jugador;
        }
        return true;
    }

    public void turno(){

        for (int i=0;i<COMBINACIONES.length;i++){
            for (int pos:COMBINACIONES[i]){
                System.out.println("Valor en posicion "+i+ " " + casillas[pos]);
            }
        }

        jugador++;
        if (jugador>2){
            jugador=1;
        }

    }
    public int ia(){

        int casilla;
        Random casilla_azar=new Random();
        casilla=casilla_azar.nextInt(9);
        return casilla;
    }


// dos camps de classe
public final int dificultad;

    public int jugador;

    private int [] casillas;
    private  final int[][] COMBINACIONES={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

}
