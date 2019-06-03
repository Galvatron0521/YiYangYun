package com.wd.yiyangyun.mvp.home.view.activity.bianzheng;

import cn.finalteam.toolsfinal.StringUtils;

public class test {

    public static void main(String[] args) {
//        String a = "afdsfasdfas.mp4";
//        String[]  strs=a.split(".");
//        System.out.print(strs.toString());
//        System.out.print(a.substring(a.length()-3));

//        String b = "afdsf/asd/fas.mp4";
//        String[]  strs1=b.split("/");
//         for(int i=0; i< strs1.length; i++){
//
//             System.out.print(strs1[i].toString()+"-------");
//
//             if(i==strs1.length){
//                 System.out.print("----"+strs1[i].toString());
//             }
//         }

//        String str = "abcdefg";
//        System.out.println("截取最后一个字符串生成的新字符串为: " + str.substring(0,str.length()-1));//abcdef

        String newSelect = "201955";
        if(newSelect.length()==8){
            String year = newSelect.substring(0,4);
            String yue = newSelect.substring(4,6);
            String day = newSelect.substring(6,8);
            System.out.println(year+"-"+yue+"-"+day);
        }
        if(newSelect.length()==7){
            String year = newSelect.substring(0,4);
            String yue = newSelect.substring(4,5);
            String day = newSelect.substring(5,7);
            if(yue.length()==1){   //2019612
                System.out.println(year+"-0"+yue+"-"+day);
            }
            if(yue.length()==2){  // 2019122
                System.out.println(year+"-"+yue+"-0"+day);
            }
        }
        if(newSelect.length()==6){
            String year = newSelect.substring(0,4);
            String yue = newSelect.substring(4,5);
            String day = newSelect.substring(5,6);
            System.out.println(year+"-0"+yue+"-0"+day);
        }

    }
}
