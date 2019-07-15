package HMS;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hmsUserNameValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String USERNAME_PATTERN="^[a-z0-9_-]{3,15}$";

    public hmsUserNameValidator(){
        pattern=Pattern.compile(USERNAME_PATTERN);
    }
    public boolean validate(final String username){
        matcher=pattern.matcher(username);
        return matcher.matches();
    }

}
