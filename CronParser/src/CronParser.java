import java.util.Calendar;
import java.util.Date;

public class CronParser {
    String errorMessage = "Input not given for parsing!";
    final TokenParser[]  tokenParsers;
    public CronParser(){
        tokenParsers = TokenParserMapping.getTokenParsers();
    }

    public String parse(String input){
        String tokens[] = input.split(" ");
        int spaces = 0, commandStart = 0;
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == ' '){
                spaces++;
            }
            if(spaces == 5){
                commandStart = i+1;
                break;
            }
        }
        String finalTokens[] = new String[6];
        for(int i=0; i<5; i++){
            finalTokens[i] = tokens[i];
        }
        //System.out.println("Command start "+commandStart+" token: "+input.substring(commandStart));
        finalTokens[5] = input.substring(commandStart);
        String errorMessage = tokenValidator(finalTokens);

        return errorMessage;
    }

    private String tokenValidator(String tokens[]){
        if(tokens.length != 6){
            return "Exactly 6 fields are must in CronExpression! Instead "+tokens.length+" fields are provided. Fields must be separated by single space.";
        }
        for(int i=0; i<6; i++){
            errorMessage = tokenParsers[i].parse(tokens[i]);
            if(errorMessage != null){
                return errorMessage;
            }
        }
        return null;
    }

    public void printOutput(){
        if(errorMessage != null){
            System.out.println(errorMessage);
        }
        else{
            for(int i=0; i<6; i++){
                tokenParsers[i].printOutput();
            }
        }
    }

//    public void printNext6(){
//        //Wed Sep 13 16:14
//        Date currentDate = new Date();
//        //System.out.print(currentDate.toString());
//        int nextSix = 6;
//        while(nextSix > 0){
//            checkMonth(currentDate.getMonth());
//            checkDateOfMonth();
//            checkDayOfWeek();
//            checkHour();//increment hour >
//            checkMinute();//increment minute -> nextSix--
//
//        }
//
//
//    }
//    private boolean checkMonth(){
//
//    }
}
