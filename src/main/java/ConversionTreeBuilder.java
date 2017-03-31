import java.util.ArrayList;

/**
 * Builds tree with conversions of number.
 *
 */
public class ConversionTreeBuilder {

    /**
     * Return root node of conversion tree for phoneNumber.
     *
     * @param phoneNumber - string with number, which might contain unnecessary signs
     * @param dict  - dictionary
     * @return
     */
    public static ConversionTreeNode convertPhoneNumberToConversionTree(String phoneNumber, Dictionary dict) {

        String convertedNumber = PhoneNumberConverter.removeUnnecessaryCharsFromPhoneNumber(phoneNumber);

        ConversionTreeNode root = new ConversionTreeNode(null, convertedNumber);
        createConversionTreeRecursion(root, convertedNumber, dict);

        return root;
    }


    /**
     * This is the recursive procedure which properly builds conversion tree.
     *
     * @param node - root node, when recursive it might be done for nodes in lower hierarchy
     * @param phoneNumber - part of number to be converted
     * @param dict - dictionary
     */
    private static void createConversionTreeRecursion(ConversionTreeNode node, String phoneNumber, Dictionary dict) {

        boolean foundWords = false;
        int startingIndex = 0;
        String prefixDigit = "";

        /* There is no sense to convert 1 letter words*/
        if (phoneNumber.length() < 2)
            return;

        /* Create child array list */
        node.setChildElementList(new ArrayList<>());

        while (startingIndex < 2 && !foundWords) {

            for (int endIndex = startingIndex + 2; endIndex <= phoneNumber.length(); endIndex++) {
            /*For all digits longer than 2 digits */

                String replacedPartOfNumber = phoneNumber.substring(startingIndex, endIndex);
                String remainingPartOfNumber = phoneNumber.substring(endIndex, phoneNumber.length());

                if (dict.getWordsForNumber(replacedPartOfNumber) != null) {
                    /* Found words that replace this part of digit*/

                    for (String wordsReplacingPartOfNumber : dict.getWordsForNumber(replacedPartOfNumber)) {
                    /*Create subnode*/
                        ConversionTreeNode newNode = new ConversionTreeNode(prefixDigit + wordsReplacingPartOfNumber, remainingPartOfNumber);
                        node.getChildElementList().add(newNode);

                    /*And launch recursion*/
                        createConversionTreeRecursion(newNode, remainingPartOfNumber, dict);
                    }
                    foundWords = true;
                }

            }

            /* If no word found move one one char forward and create prefix digit*/
            startingIndex++;
            prefixDigit = new Character(phoneNumber.charAt(0)).toString()+" ";

        }


    }

}
