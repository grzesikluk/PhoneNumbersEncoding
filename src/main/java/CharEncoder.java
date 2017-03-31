import java.util.*;

/**
 * The class is responsible for conversion of particular char to digit and reverse.
 *
 */
public class CharEncoder {

    private static Map<Character, Integer> charToIntMap;
    private static Map<Integer, List<Character>> intToCharMap;
    private static List<List<Character>> charactersSets;
    private static Set<Character> excludedCharactersSets;


    static {
        charactersSets = new LinkedList<>();
        charactersSets.add(Arrays.asList('e', 'E'));                         //0
        charactersSets.add(Arrays.asList('j', 'n', 'q', 'J', 'N', 'Q'));     //1
        charactersSets.add(Arrays.asList('r', 'w', 'x', 'R', 'W', 'X'));     //2
        charactersSets.add(Arrays.asList('d', 's', 'y', 'D', 'S', 'Y'));     //3
        charactersSets.add(Arrays.asList('f', 't', 'F', 'T'));               //4
        charactersSets.add(Arrays.asList('a', 'm', 'A', 'M'));               //5
        charactersSets.add(Arrays.asList('c', 'i', 'v', 'C', 'I', 'V'));     //6
        charactersSets.add(Arrays.asList('b', 'k', 'u', 'B', 'K', 'U'));     //7
        charactersSets.add(Arrays.asList('l', 'o', 'p', 'L', 'O', 'P'));     //8
        charactersSets.add(Arrays.asList('g', 'h', 'z', 'G', 'H', 'Z'));     //9

        excludedCharactersSets = new HashSet<>();
        excludedCharactersSets.add('"');

        charToIntMap = new HashMap<>();
        intToCharMap = new HashMap<>();

        for (int i = 0; i < charactersSets.size(); i++) {

            for (int j = 0; j < charactersSets.get(i).size(); j++) {
                charToIntMap.put(charactersSets.get(i).get(j), i);

                if (intToCharMap.containsKey(i)) {
                    intToCharMap.get(i).add(charactersSets.get(i).get(j));

                } else {
                    intToCharMap.put(i, new ArrayList<>());
                    intToCharMap.get(i).add(charactersSets.get(i).get(j));
                }
            }
        }

    }

    /**
     * Convert character to its integer value.
     *
     * @param c
     * @return integer value - decoded.
     */
    public static int charToInt(char c) {
        return charToIntMap.get(c);
    }


    public static String convertWordToDigitString(String word) {
        StringBuilder result = new StringBuilder();

        for (Character c : word.toCharArray()) {
            if (!excludedCharactersSets.contains(c))
                result.append(charToInt(c));
        }

        return result.toString();
    }

}
