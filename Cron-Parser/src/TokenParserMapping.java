public class TokenParserMapping {
    public static TokenParser[] tokenParsers = new TokenParser[6];
    public static TokenParser[] getTokenParsers(){
        tokenParsers[0] = new MinuteParser();
        tokenParsers[1] = new HourParser();
        tokenParsers[2] = new DateParser();
        tokenParsers[3] = new MonthParser();
        tokenParsers[4] = new DayParser();
        tokenParsers[5] = new CommandParser();
        return tokenParsers;
    }
}
