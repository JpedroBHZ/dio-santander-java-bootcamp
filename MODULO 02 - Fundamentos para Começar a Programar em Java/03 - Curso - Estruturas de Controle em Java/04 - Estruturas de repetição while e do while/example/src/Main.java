//Passados args em tres pontos, run with parameters

public class Main {
    static void main(String[] args) {
        var i = 0;
        //Testa condição antes
        while (args.length > i){
            System.out.println(args[i]);
            i++;
        }
        i = 0;

        System.out.println("#######################");

        //Testa condição depois de executar
        do {
            System.out.println(args[i]);
            i++;
        } while (args.length > i);
    }
}
