public class CommandParser implements TokenParser{
    String token;
    @Override
    public String parse(String token) {
        this.token = token;
        return null;
    }

    @Override
    public void printOutput() {
        System.out.println("command       "+token);
    }
}
