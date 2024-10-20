package com.snut.material.unit;

import org.springframework.web.bind.annotation.*;
import com.snut.material.common.CommonResult;

import java.util.*;

@RestController
@RequestMapping(path = "/eryuan")
public class ZEryuangx {

    @GetMapping(path = "/qiuni/{info}")
    public CommonResult qiuni(@PathVariable("info") String info) {
        System.out.println(info);
        CommonResult commonResult = null;
        info = info.replaceAll(" ", "");// 去除所有空格
        info = info.substring(2, info.length() - 2);// 去除首尾括号
        String[] str = info.split("},\\{");
        List<Integer[]> list = new LinkedList<>();
        String res = "{{";
        for (int i = 0; i < str.length; i++) {
            res += str[i].charAt(2);
            res += str[i].charAt(1);
            res += str[i].charAt(0);
            if (i < str.length - 1) {
                res += "},{";
            }
        }
        res += "}}";
        commonResult = new CommonResult(200, res, "获取求逆关系成功");
        System.out.println(commonResult);
        return commonResult;
    }

    @GetMapping(path = "/guanxifuhe/{info}")
    public CommonResult guanxifuhe(@PathVariable("info") String info) {
        CommonResult commonResult = null;
        info = info.replaceAll(" ", "");// 去除所有空格
        info = info.substring(2, info.length() - 2);// 去除首尾括号
        String[] str = info.split("},\\{");
        List<Integer[]> list = new LinkedList<>();
        for (int i = 0; i < str.length; i++) {
            Integer[] arr = new Integer[2];
            arr[0] = Integer.valueOf(str[i].charAt(0)) - 48;
            arr[1] = Integer.valueOf(str[i].charAt(2)) - 48;
            list.add(arr);
        }
        String res = "{{";
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                int left = list.get(i)[1];
                int right = list.get(j)[0];
                if (left == right) {
                    if (flag == 0) {
                        res += list.get(i)[0] + "," + list.get(j)[1];
                        flag = 1;
                    } else {
                        res += "},{" + list.get(i)[0] + "," + list.get(j)[1];
                    }
                }
            }
        }
        res += "}}";
        commonResult = new CommonResult(200, res, "关系复合成功");

        return commonResult;
    }

    @GetMapping(path = "/guanximi/{info}/{power}")
    public CommonResult guanximi(@PathVariable("info") String info, @PathVariable("power") Integer power) {
        CommonResult commonResult = null;
        info = info.replaceAll(" ", "");// 去除所有空格
        info = info.substring(1, info.length() - 1);// 去除首尾括号
        info = info.charAt(0) + " " + info.charAt(2);
        String input = info;
        String[] pairs = input.split(",");

        Set<String> resultPairs = new HashSet<>(Arrays.asList(pairs));

        for (int i = 0; i < power - 1; i++) {
            Set<String> tempPairs = new HashSet<>(resultPairs);

            for (String pair1 : pairs) {
                String[] elements1 = pair1.trim().split(" ");
                String a = elements1[0];
                String c = elements1[1];

                for (String pair2 : resultPairs) {
                    String[] elements2 = pair2.trim().split(" ");
                    String b = elements2[0];
                    String d = elements2[1];

                    if (c.equals(b)) {
                        tempPairs.add("{" + a + "," + d + "}");
                    }
                }
            }
            resultPairs = tempPairs;
        }
        StringBuilder result = new StringBuilder();
        for (String pair : resultPairs) {
            result.append(pair).append("\n");
        }

        System.out.println(result);
        commonResult = new CommonResult(200, result, "求幂成功");
        return commonResult;
    }

    @GetMapping(path = "/guanxixingzhi/{info}")
    public CommonResult guanxixingzhi(@PathVariable("info") String info) {
        CommonResult commonResult = null;
        String input = info;
        String[] pairs = input.split(",");



        for (String pair : pairs) {
            String[] elements = pair.trim().split(" ");
            String a = elements[0];
            String b = elements[1];

        }
        StringBuilder result = new StringBuilder();
        List<String> list = new ArrayList<>();

        return commonResult;
    }


}
