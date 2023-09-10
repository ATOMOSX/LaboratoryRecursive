public class Main {
    public static void main(String[] args) {
        int [] dados = {1, 1, 1};
        int valorSuperar = 6;
        tiradasMayoresValorSuperar(dados, valorSuperar, 0, 0);
        tiradasIgualesValorSuperar(dados, valorSuperar, 0, 0);
    }

    public static void tiradasMayoresValorSuperar(int[] dados, int valorSuperar, int suma, int tirada){

        if (tirada == dados.length && suma == valorSuperar){
            for (int i = 0; i < dados.length; i++){
                if (i == dados.length -1){
                    System.out.print(dados[i] + " = ");
                }else {
                    System.out.print(dados[i] + " = ");
                }
            }
            System.out.println(suma);
        } else if (tirada != dados.length) {
            for (int i = 0; i <= 6; i++){
                dados[tirada] = i;

                suma += i;
                tiradasMayoresValorSuperar(dados, valorSuperar, suma, tirada + 1);
                suma -= i;
            }
        }
    }

    public static void tiradasIgualesValorSuperar(int[] dados, int valorSuperar, int suma, int tirada){
        if (tirada == dados.length && suma == valorSuperar){
            for (int i = 0; i < dados.length; i++){
                if (i == dados.length -1){
                    System.out.println(dados[i] + " = ");
                }else {
                    System.out.println(dados[i] + " = ");
                }
            }
        } else if (tirada != dados.length) {
            for (int i = 0; i <=6; i++){
                dados[tirada] = i;
                suma += i;
                tiradasIgualesValorSuperar(dados, valorSuperar, suma, tirada);
                suma -= i;
            }
        }
    }

}