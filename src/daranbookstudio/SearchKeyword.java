/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daranbookstudio;

import java.util.ArrayList;

/**
 *
 * @author 雅詩
 */
public class SearchKeyword {
    
    public ArrayList<Integer> schKeyword(String str, String sch) {
        ArrayList<Integer> loc = new ArrayList<Integer>();
        char[] array = str.toCharArray();
        char[] schArray = sch.toCharArray();
        char schar = schArray[0];
        int strcount = str.length();
        int schcount = sch.length();
        //int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == schar) {
                int nowKeywordEnd = i + schcount;
                if (nowKeywordEnd < strcount) {
                    String nowKeyword = str.substring(i, nowKeywordEnd);
                    //System.out.println(nowKeyword);
                    if (nowKeyword.equals(sch)) {
                        loc.add(i); //System.out.println("起始位置：" + i);
                        loc.add(nowKeywordEnd); //System.out.println("结束位置：" + nowKeywordEnd);
                        //count++;
                    }
                }
            }
        }
        return loc;
    }
    
    public ArrayList<String> schHtmValume(String str, String sch1, String sch2) {
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Integer> locs1 = schKeyword(str, sch1);
        ArrayList<Integer> locs2 = schKeyword(str, sch2);
        int nowl1 = 1;
        int nowl2 = 0;
        for (int i = 0; i < locs1.size() / 2; i++) {
            String nowKeyword = str.substring(locs1.get(nowl1), locs2.get(nowl2));
            words.add(nowKeyword);
            nowl1 += 2;
            nowl2 += 2;
        }
        return words;
    }
}