package ayds.dictionary.charlie.fulllogic.view;

public class BoldText {

    public static String textToHtml(String result, String searchedWord){
        StringBuilder builder = new StringBuilder();
        String textWithBold = result.replaceAll("(?i)" + searchedWord, "<b>" + searchedWord + "</b>");
        builder.append(textWithBold);
        return builder.toString();
    }
}
