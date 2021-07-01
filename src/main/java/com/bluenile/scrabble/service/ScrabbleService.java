package com.bluenile.scrabble.service;

import com.bluenile.scrabble.model.Trie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Service
@Slf4j
public class ScrabbleService {

   private Trie trie;
   private Set<String> resultSet;
   private List<String> all;
    @PostConstruct
    private void initDictionary(){
        String fileName = "dictionary.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        trie = new Trie();
        try (Stream<String> stream = Files.lines(file.toPath())) {

            stream.forEach(line -> {
                trie.insert(line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getScrabbleList(String letters){
        if(ObjectUtils.isEmpty(letters)){
            log.error("Input empty string!");
            return Collections.emptyList();
        }

        resultSet = new HashSet<>();
        all = new ArrayList<>();
        char[]  charArr = letters.toLowerCase().toCharArray();
        Character [] characterArr = new Character[charArr.length];

        for (int i = 0; i < charArr.length; i++) {
            characterArr[i] = charArr[i];
        }
        for (int i = 0; i < charArr.length; i++) {
            comb2(characterArr, i+1);
        }

        Map<String,Integer> resultMap = resultSet.stream().
                filter(str -> trie.isWord(str)).collect(toMap(str -> str, str ->  calcScore(str)));

        Map<String, Integer> sortedByValueDesc = resultMap.entrySet() .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) .
                        collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        List<String> sortedList = new ArrayList<>();
        for (Map.Entry<String, Integer> e : sortedByValueDesc.entrySet()) {
            sortedList.add(e.getKey());
        }
        return sortedList;
    }

    private void comb2(Character[] m, int n) {
        Stack<Character> buf = new Stack<>();
        List<String> result = doComb2(m,0, n, buf);

        for (String s : result) {
            resultSet.addAll(permute(s));
        }
    }

    private List<String> doComb2(Character[] m, int start, int n, Stack<Character> buf) {

        if (m.length - start + buf.size() == n) {
            StringBuffer sb = new StringBuffer();
            for (Character c : buf) {
                sb.append(c);
            }
            for (int i = start; i < m.length; ++i) {
                sb.append(m[i]);
            }
            all.add(sb.toString());
        } else if (start < m.length) {
            buf.push(m[start]);
            doComb2(m, start + 1, n, buf);
            buf.pop();
            doComb2(m, start + 1, n, buf);
        }
        return all;
    }

    private Set<String> permute(String chars) {

        Set<String> set = new HashSet<>();

        if (chars.length() == 1) {
            set.add(chars);
        } else {
            for (int i = 0; i < chars.length(); i++) {
                String pre = chars.substring(0, i);
                String post = chars.substring(i + 1);
                String remaining = pre + post;
                for (String permutation : permute(remaining)) {
                    set.add(chars.charAt(i) + permutation);
                }
            }
        }
        return set;
    }

    private Integer calcScore(String str){
        int score = 0;
        char[] charArr = str.toCharArray();
        for(char c : charArr){
            score += getCharScore(c);
        }
        return score;
    }

    private int getCharScore(char c){
        switch (c){
            case 'a' :
            case 'e' :
            case 'i' :
            case 'l' :
            case 'n' :
            case 'o' :
            case 'r' :
            case 's' :
            case 't' :
            case 'u' :
                return 1;
            case 'd' :
            case 'g' :
                return 2;
            case 'b' :
            case 'c' :
            case 'm' :
            case 'p' :
                return 3;
            case 'f' :
            case 'h' :
            case 'v' :
            case 'w' :
            case 'y' :
                return 4;
            case 'k' :
                return 5;
            case 'j' :
            case 'x' :
                return 8;
            case 'q' :
            case 'z' :
                return 8;
            default:
                return 0;

        }
    }
}
