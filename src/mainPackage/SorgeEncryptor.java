package mainPackage;

import java.util.*;

public class SorgeEncryptor {
    private static final Map<Character, Integer> table;
    static {
        Hashtable<Character, Integer> temp = new Hashtable<Character, Integer>();
        temp.put('E', 70);
        temp.put('A', 71);
        temp.put('H', 72);
        temp.put('S', 73);
        temp.put('Y', 74);
        temp.put('M', 75);
        temp.put('B', 76);
        temp.put('J', 78);
        temp.put('T', 79);
        temp.put('Z', 80);
        temp.put('P', 81);
        temp.put('C', 82);
        temp.put('K', 83);
        temp.put('U', 84);
        temp.put('L', 85);
        temp.put('D', 86);
        temp.put('N', 87);
        temp.put('V', 89);
        temp.put('O', 90);
        temp.put('F', 91);
        temp.put('Q', 92);
        temp.put('W', 93);
        temp.put('I', 94);
        temp.put('G', 95);
        temp.put('R', 96);
        temp.put('X', 97);

        temp.put('e', 40);
        temp.put('a', 41);
        temp.put('h', 42);
        temp.put('s', 43);
        temp.put('y', 45);
        temp.put('m', 46);
        temp.put('b', 47);
        temp.put('j', 48);
        temp.put('t', 49);
        temp.put('z', 50);
        temp.put('p', 51);
        temp.put('c', 52);
        temp.put('k', 53);
        temp.put('u', 54);
        temp.put('l', 56);
        temp.put('d', 57);
        temp.put('n', 58);
        temp.put('v', 59);
        temp.put('o', 60);
        temp.put('f', 61);
        temp.put('q', 62);
        temp.put('w', 63);
        temp.put('i', 64);
        temp.put('g', 65);
        temp.put('r', 67);
        temp.put('x', 68);

        temp.put('.',23);
        temp.put('@',24);
        temp.put('_',25);
        temp.put('-',26);
        table = Collections.unmodifiableMap(temp);
    }

    public static String encrypt(String message) {
        char [] arr = message.toCharArray();
        StringBuilder builder = new StringBuilder();
        for(Character c: arr) {
            if(Character.isDigit(c)) {
                builder.append(c).append(c);
            }
            else builder.append(table.get(c));
        }
        return builder.toString();
    }

    public static String decrypt(String message){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<message.length()-1;i=i+2){
            if(table.containsValue(Integer.parseInt(message.substring(i,i+2)))){
                builder.append(getKey(table,Integer.parseInt(message.substring(i,i+2))));
            }
            else{
                builder.append(message.substring(i,i+1));
            }
        }
        return builder.toString();
    }

    public static Character getKey(Map<Character, Integer> map, Integer value) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
