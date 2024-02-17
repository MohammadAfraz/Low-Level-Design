import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseTokenParser implements TokenParser{
    int limit;
    String name;
    int start, end;
    int repeatValue;
    boolean[] range;
    final Set<String> fieldsAllowingZeroValue = new HashSet<>(List.of("Minute", "Hour"));

    public String parse(String token){
        range = new boolean[limit];
        String[] tokens = token.split(",");
        return validateTokens(tokens);
    }

    private String validateTokens(String[] tokens){
        for(String token : tokens){
            String errorMessage = validateToken(token);
            if(errorMessage != null){
                return errorMessage;
            }
        }
        return null;
    }

    private String validateToken(String token){
        if(token == null || token.length() == 0){
            return null;
        }
        if(token.contains("/")){
            String[] parts = token.split("/");
            if(parts.length != 2){
                return "Exactly 2 Expressions should be separated by '/' but got "+ parts.length +" values for field ["+name+"]";
            }
            String errorMessage = validateExpression(parts[0], true);
            if(errorMessage != null){
                return errorMessage;
            }
            errorMessage = validateRepeatValue(parts[1]);
            if(errorMessage != null){
                return errorMessage;
            }
            for(int i=start; i <= end; i = i+repeatValue){
                range[i] = true;
            }
        }
        else{
            String errorMessage = validateExpression(token, false);
            if(errorMessage != null) {
                return errorMessage;
            }
            for(int i=start; i <= end; i = i+repeatValue) {
                range[i] = true;
            }
        }
        return null;
    }
    private String validateExpression(String expr, boolean shouldRepeat){
        String[] parts = expr.split("-");
        if(parts.length == 1){
            if("*".equals(parts[0])) {
                if(fieldsAllowingZeroValue.contains(name)){
                    start = 0;
                }
                else{
                    start = 1;
                }
            }
            else{
                try{
                    start = Integer.parseInt(parts[0]);
                }
                catch(NumberFormatException nfe) {
                    return "Invalid numerical value "+ parts[0]+" for field ["+ name+"]";
                }
            }
            if(shouldRepeat || "*".equals(parts[0])){
                end = limit-1;
            }
            else{
                end = start;
            }
        }
        else if(parts.length == 2){
            try{
                start = Integer.parseInt(parts[0]);
            }
            catch(NumberFormatException nfe) {
                return "Invalid numerical value "+ parts[0]+" for field ["+ name+"]";
            }
            try{
                end = Integer.parseInt(parts[1]);
            }
            catch(NumberFormatException nfe) {
                return "Invalid numerical value "+ parts[1]+" for field ["+ name+"]";
            }
        }
        else{
            return "Exactly 2 Expressions should be separated by '-' but got "+ parts.length +" values for field ["+name+"]";
        }
        repeatValue = 1;
        String errorMessage = checkBounds(start);
        if(errorMessage != null){
            return errorMessage;
        }
        return checkBounds(end);
    }

    private String validateRepeatValue(String val){
            switch(val){
                case "*" : {
                    repeatValue = 1;
                    break;
                }
                case "0" : {
                    return "Repeat interval has to be non-zero value for field ["+ name+"]";
                }
                default : {
                    try{
                        repeatValue = Integer.parseInt(val);
                    }
                    catch(NumberFormatException nfe) {
                        return "Invalid repeat value "+ val +" for field ["+ name+"]";
                    }
                }
            }
        return checkBounds(repeatValue);
    }

    private String checkBounds(int val){
        if(fieldsAllowingZeroValue.contains(name)){
            if(val >= limit || val < 0){
                return "Value "+ val +" is out of bounds, it should be between 0 & "+ (limit-1) +" for field ["+ name+"]";
            }
        }
        else if(val >= limit || val <= 0){
            return "Value "+ val +" is out of bounds, it should be between 1 & "+ (limit-1) +" for field ["+ name+"]";
        }
        return null;
    }

    public void printOutput(){
        String paddedString = "";
        switch(name){
            case "Minute" :  {
                paddedString = "minute"; break;
            }
            case "Hour" : {
                paddedString = "hour"; break;
            }
            case "Date" : {
                paddedString = "day of month"; break;
            }
            case "Month" : {
                paddedString = "month"; break;
            }
            case "Day" : {
                paddedString = "day of week";
            }
        }
        paddedString = String.format("%1$-" + 14 + "s", paddedString);
        System.out.print(paddedString);
        for(int i=0; i<limit; i++){
            if(range[i]){
                System.out.print(i+" ");
            }
        }
        System.out.println();
    }
}
