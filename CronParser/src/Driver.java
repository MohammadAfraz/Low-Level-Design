import java.util.List;
import java.util.ArrayList;

public class Driver {
    static String input1 = "1,10/10 0,2/3 3,2/5 1-10/3,2/4 1 xyz a b c";
    static String input2 = "* * * * * xyz";
    static String input3 = "*/3 */2 */5 */* * xyz";
    static String input4 = "60 1 1 1 1 xyz";
    static String input5 = "1 24 1 1 1 xyz";
    static String input6 = "1 1 0 1 1 xyz";
    static String input7 = "1 1 1 13 1 xyz";
    static String input8 = "1 1 1 1 0 xyz";
    static String input9 = "1 1 1 1 8 xyz";
    static String input10 = "1 1 1 1 1 1 xyz";
    static String input11 = "1-a 1 1 1 1 xyz";
    static String input12 = "1/1-3 1 1 1 1 xyz";
    static String input13 = "1/10a 1 1 1 1 xyz";
    static List<String> testList = new ArrayList<>(List.of(input1, input2, input3, input4, input5, input6, input7, input8, input9, input10, input11, input12,input13));

    public static void main(String args[]){
        CronParser cronParser = new CronParser();
        if(args.length >= 1) {
            String errorMessage = cronParser.parse(args[0]);
            if (errorMessage != null) {
                System.out.println(errorMessage);
            } else {
                cronParser.printOutput();
            }
        }
        else{
            runTests(cronParser);
        }
    }
    private static void runTests(CronParser cronParser){
        for(int i=0; i<testList.size(); i++){
            System.out.println("Test #"+(i+1)+": "+testList.get(i));
            String errorMessage = cronParser.parse(testList.get(i));
            if(errorMessage != null){
                System.out.println(errorMessage);
            }
            else {
                cronParser.printOutput();
            }
            System.out.println();
        }
    }
    //* * * * * xyz a b
    //command       xyz a b

    //*/15 2 1,15 * 1-5 /usr/bin/find
}
