package az.blogoot.utility;

/**
 * EmailUtilit
 */
public class EmailUtilit {
    public static String body(String name, String lastname, String url) {
        return String.format("Hello %s %s!\n" 
        + "Activation url: %s", name, lastname, url);
    }

    public static String subject (){
        return "Activation email";
    }

}