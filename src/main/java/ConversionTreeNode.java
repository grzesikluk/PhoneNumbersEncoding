import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Lukasz on 2017-01-23.
 * <p>
 * ConversionTreeNode is base element holding tree elements. It will contain:
 * - a word which is word generated, might consist of digits
 * - phone number - which is rest of number to be converted.
 */
public class ConversionTreeNode {

    private String word;
    private String remainingPhoneNumber;
    private List<ConversionTreeNode> childElementList;

    public ConversionTreeNode(String word, String remainingPhoneNumber) {
        this.word = word;
        this.remainingPhoneNumber = remainingPhoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        List<String> subResult = toStringList();

        if (!subResult.isEmpty())
            for (String subResultString : subResult)
                result.append(remainingPhoneNumber + ": " + subResultString + "\n");

        return result.toString().trim();

    }

    public List<String> toStringList() {
        return toStringListRecursive(this).stream().map(String::trim).collect(Collectors.toList());
    }

    private static List<String> toStringListRecursive(ConversionTreeNode node) {
        List<String> result = new ArrayList<>();

        String toAdd = (node.word == null) ? "" : node.word + " ";

        if (node.getChildElementList() != null)

            for (ConversionTreeNode child : node.getChildElementList())
                for (String childWord : toStringListRecursive(child))
                    result.add(toAdd + ((childWord==null)?"":childWord));

        else
            result.add((node.getWord()==null)?"":node.getWord() + " " + node.getRemainingPhoneNumber());


        return result;
    }

    public String getWord() {
        return word;
    }

    public String getRemainingPhoneNumber() {
        return remainingPhoneNumber;
    }


    public List<ConversionTreeNode> getChildElementList() {
        return childElementList;
    }

    public void setChildElementList(List<ConversionTreeNode> childElementList) {
        this.childElementList = childElementList;
    }

}
