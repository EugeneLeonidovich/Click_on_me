package org.kaldi.demo;

class StringElement {
    private String check = "{\n" +
            "\"text\" : \"\"\n" +
            "}".replace("\n", "");
    boolean checkStr(String str){
        return check.equals(str.replace("\n", ""));
    }

    int getStr(String str){
        String newStr = str.replaceAll("[{}\"\\[\\]\\d.]","")
                .replace(":"," ")
                .replace(","," ")
                .replace("  ", " ")
                .replace("\n", " ");
        String[] arr = newStr.split(" ");
        char[] arrWord = arr[arr.length-1].toCharArray();
        if((arrWord[0]=='п'&&arrWord[1]=='р') || (arrWord[0]=='с'
                && arrWord[1]=='п' && arrWord[2]=='р')){
            return 2;
        }else if((arrWord[0]=='л'&&arrWord[1]=='е') || (arrWord[0]=='с'
                && arrWord[1]=='л' && arrWord[2]=='е')){
            return 1;
        }
        return 0;
    }
}
