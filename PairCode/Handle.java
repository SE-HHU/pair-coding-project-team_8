package PairCode;

import java.util.Vector;

/**
 * 处理
 *
 * 对读入的式子和答案进行格式上的处理
 *
 * @author 陈伽宇
 * @date 2021/09/24
 */
public class Handle {

    /**
     * 处理配方
     *
     *式子处理：
     * 1.将最初的序号及'.'给去掉
     * 2.去掉式子的'='
     * 3.统一将'➗'更改为格式统一且容易读入的'/'
     * 4.将分数a/b,表示为a,b
     * 5.将自然数a,表示为a,1
     *
     * @param formulationsForHandle 配方的处理
     * @param store                   商店
     */
    public void handleformulations(Vector<StringBuilder> formulationsForHandle ,Storage store)
    {


        for(int i = 0;i< formulationsForHandle.size();i++)
        {
            int first_char = formulationsForHandle.get(i).indexOf(".");

            StringBuilder new_Ans = new StringBuilder(
                    formulationsForHandle.get(i).substring(first_char+1));
            store.formulationsForHandleNew.add(new_Ans);
        }

        for (StringBuilder stringBuilder : store.formulationsForHandleNew)
        {
            StringBuilder temp = new StringBuilder();
            for (int p = 0; p < stringBuilder.length(); p++)
            {

                if (Character.isDigit(stringBuilder.charAt(p)))
                {
                    int j = p, x = 0;
                    while (j < stringBuilder.length()
                            && Character.isDigit(stringBuilder.charAt(j)))
                    {
                        x = x * 10 + (stringBuilder.charAt(j++) - '0');
                    }
                    if (stringBuilder.charAt(j) == '/')
                    {
                        temp.append(x).append(",");
                        x = 0;
                        j++;
                        while (j < stringBuilder.length()
                                && Character.isDigit(stringBuilder.charAt(j)))
                        {
                            x = x * 10 + (stringBuilder.charAt(j++) - '0');
                        }
                        temp.append(x);
                    }
                    else {
                        temp.append(x).append(",").append(1);
                    }
                    p = j - 1;

                }
                else
                {
                    if (stringBuilder.charAt(p) != '=') {
                        if (stringBuilder.charAt(p) == '÷') {
                            temp.append("/");
                        }  else if(stringBuilder.charAt(p) == '×') {
                            temp.append("*");
                        }
                        else{
                            temp.append(stringBuilder.charAt(p));
                        }
                    }
                }
            }
            store.add_elem(temp);
        }
    }

    //

    /**
     * 处理答
     *
     * 对读入的答案进行格式上的处理
     * 答案处理：
     * 1.将最初的序号及'.'给去掉
     *
     * @param ansForTestHandle ans为测试处理
     * @param store               商店
     */
    public void handle_ans (Vector<StringBuilder> ansForTestHandle, Storage store)
    {

        for(int i = 0;i<ansForTestHandle.size();i++)
        {
            int first_char = ansForTestHandle.get(i).indexOf(".");

            StringBuilder new_Ans = new StringBuilder(
                    ansForTestHandle.get(i).substring(first_char+1));
            store.ansForTestHandleNew.add(new_Ans);
        }

        for(int i = 0; i< store.ansForTestHandleNew.size(); i++)
        {
            int flag = 0;
            Fraction fraction = new Fraction();
            int temp_a,temp_b;

            int flagAns =  0;
            for(int p = 0; p <store.ansForTestHandleNew.get(i).length();p++)
            {
                if(store.ansForTestHandleNew.get(i).charAt(p)=='-')
                {
                    flagAns = 1; continue;
                }
                if(Character.isDigit(store.ansForTestHandleNew.get(i).charAt(p)))
                {

                    int  j= p,x = 0;
                    while(j < store.ansForTestHandleNew.get(i).length() &&
                            Character.isDigit(store.ansForTestHandleNew.get(i).charAt(j)))
                    {
                        x = x*10 + store.ansForTestHandleNew.get(i).charAt(j) - '0';
                        if(j + 1 >= store.ansForTestHandleNew.get(i).length())
                        {
                            flag= 1; break;
                        }
                        else {
                            j++;
                        }
                    }
                    if(flag == 1)
                    {
                        if(flagAns == 0) {
                            fraction.a = x;
                        }
                        else {
                            fraction.a = x * -1;
                        }
                        fraction.b = 1;
                        break;
                    }
                    else
                    {
                        if(flagAns==0) {
                            temp_a = x;
                        }
                        else {
                            temp_a = x * -1;
                        }
                        j++;
                        x = 0;
                        while(j < store.ansForTestHandleNew.get(i).length() &&
                                Character.isDigit(store.ansForTestHandleNew.get(i).charAt(j)))
                        {
                            x = x*10 + store.ansForTestHandleNew.get(i).charAt(j) - '0';
                            if(j + 1 > store.ansForTestHandleNew.get(i).length())
                            {
                                flag= 1; break;
                            }
                            else {
                                j++;
                            }
                        }
                        p =j -1;
                        temp_b = x;
                        fraction.create(temp_a,temp_b);
                        break;
                    }
                }
            }
            store.add_elem_to_test_ans(fraction);
        }
    }
}