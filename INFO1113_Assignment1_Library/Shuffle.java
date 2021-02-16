import java.util.ArrayList;  
import java.util.Collections;   
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

  
  
public class Shuffle {  
      
    public static void main(String[] args) {  
        shuffle();  
    }  
      
    public static void shuffle(){  
        String[] x = {"あ","い","う","え","お",
                     "か","き","く","け","こ",
                     "さ","し","す","せ","そ",
                     "た","ち","つ","て","と",
                     "な","に","ぬ","ね","の",
                     "は","ひ","ふ","へ","ほ",
                     "ま","み","む","め","も",
                     "や","ゆ","よ",
                     "ら","り","る","れ","ろ",
                     "わ","を","きゃ","きゅ","きょ",
                     "しゃ","しゅ","しょ",
                     "ちゃ","ちゅ","ちょ",
                     "にゃ","にゅ","にょ",
                     "ひゃ","ひゅ","ひょ",
                     "みゃ","みゅ","みょ",
                     "りゃ","りゅ","りょ",
                     "が","ぎ","ぐ","げ","ご",
                     "ざ","じ","ず","ぜ","ぞ",
                     "だ","で","ど",
                     "ば","び","ぶ","べ","ぼ",
                     "ぱ","ぴ","ぷ","ぺ","ぽ",
                     "ぎゃ","ぎゅ","ぎょ",
                     "じゃ","じゅ","じょ",
                     "びゃ","びゅ","びょ",
                     "ぴゃ","ぴゅ","ぴょ"};
        String[] y = {"a","i","u","e","o",
                     "ka","ki","ku","ke","ko",
                     "sa","shi","su","se","so",
                     "ta","chi","tsu","te","to",
                     "na","ni","nu","ne","no",
                     "ha","hi","fu","he","ho",
                     "ma","mi","mu","me","mo",
                     "ya","yu","yo",
                     "ra","ri","ru","re","ro",
                     "wa","o","kya","kyu","kyo",
                     "sha","shu","sho",
                     "cha","chu","cho",
                     "nya","nyu","nyo",
                     "hya","hyu","hyo",
                     "mya","myu","myo",
                     "rya","ryu","ryo",
                     "ga","gi","gu","ge","go",
                     "za","ji","zu","ze","zo",
                     "da","de","do",
                     "ba","bi","bu","be","bo",
                     "pa","pi","pu","pe","po",
                     "gya","gyu","gyo",
                     "ja","ju","jo",
                     "bya","byu","byo",
                     "pya","pyu","pyo"};

        List<String> list = new ArrayList<>();
        HashMap<String, String> dic = new HashMap<String, String>();
        for(int i = 0; i < x.length; i++){
            dic.put(x[i], y[i]);
        }

        for(String i:x){
            list.add(i);
        }  
        Collections.shuffle(list);  
        
        Scanner scan = new Scanner(System.in);

        int count = 0;

        while(true){
            Collections.shuffle(list);
            String target = "";
            for(int i = 0; i < 20; i++){
                System.out.print(list.get(i));
                target += dic.get(list.get(i));
            }
    
            System.out.println();
    
            
            System.out.println("Enter answer:");
            String answer = scan.next();
            if(target.equals(answer)){
                System.out.println("Well done!");
            }else if (answer.equals("exit")){
                break;
            }else{
                System.out.printf("Right answer: %s\n",target);
                System.out.printf("Your  answer: %s\n",answer);
                System.out.println();
            }
            count++;
        }

        System.out.printf("You practice %d times today, good job!\n", count);


        scan.close();
    }  
}