/**
 * Created by Lukasz on 2017-01-24.
 */
public class Main {

    /*Launcher class*/
    public static void main(String[] args) {

        if(args.length != 2) {
            System.out.println("Usage: main path_to_dictonary path_to_number_file");
        }

        Dictionary dict = new Dictionary();
        dict.readFromFile(args[0]);

        PhoneNumberEncoder searcher = new PhoneNumberEncoder(dict);

        searcher.printAllConversionsForFile(args[1]);

    }
}
