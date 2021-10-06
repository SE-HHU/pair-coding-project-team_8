package PairCode;

import java.util.HashMap;
import java.util.Stack;

/**
 * 计算
 *
 * 本程序的核心部分
 * 作用：计算带括号同时支持分数的四则运算
 *
 * 从formulationsForCalculations里读入Vector<StringBuffer>
 *
 * 1.先将StringBuffer转化为String
 *
 * 2.给四则运算予以优先级+ 和 -为1，* 和 / 为2，后者拥有更高的优先级
 *   用hashmap来进行存储
 *
 * 3.用树来存储
 *   将符号存储在根节点，将数字存储在叶节点
 *
 * 4.用栈来模拟这种树，以优化内存空间，一个栈存储符号，一个栈用于存储数字
 *
 * 5.基本逻辑；
 *   1.如果遇到数字或者分数，将其存入Stack<Fraction>
 *   2.如果遇到了'('，那么直接将其插入到Stack<char>
 *   3.如果遇到了')'，那么出栈，直至遇到'('，并将其范围内的数字计算
 *   4.如果遇到加减乘除，则比较两者与Stack<char>中栈顶的符号的优先级
 *   5.优先级若小于等于前者，则进行运算
 *   6.优先级若大于前者，则直接将该符号插入符号栈
 *   7.若在符号栈中还剩下了符号，那么进行运算，知道符号已经清空
 *
 * 6.将最后计算得到的结果，进行上下约分后，插入Stack<Fraction>
 *
 * 7.最后Stack<Fraction>中，剩下的唯一一个数字就是答案
 *
 * @author 86189
 * @date 2021/10/06
 */
public class Calculation {
    public Fraction plus(Fraction a, Fraction b)
    {
        return new Fraction(a.a*b.b + a.b*b.a, a.b*b.b);
    }
    //分数的加

    public Fraction minus(Fraction a, Fraction b)
    {
        return new Fraction(a.a*b.b - a.b*b.a,a.b*b.b);
    }
    //分数的减

    public Fraction multiply(Fraction a, Fraction b)
    {
        return new Fraction(a.a*b.a, a.b*b.b);
    }
    //分数的乘

    public Fraction divide( Fraction a,Fraction b)
    {
        return new Fraction(a.a*b.b, a.b*b.a);
    }
    //分数的除

    HashMap<Character, Integer> h;
    //h用于存储符号和其优先级

    Stack<Fraction> num ;
    //num用于存储计算过程中用的数字

    Stack<Character> op ;
    //符号栈，用于存储计算过程中所用的符号

    Character charPlus = '+';
    Character charMinus = '-';
    Character charMultiply = '*';
    Character charDivide = '/';
    Character lefter = '(';
    Character righter = ')';

    /**
     * 计算
     * 进行最基本的初始化
     */
    public Calculation(){
         h = new HashMap<>();
         num = new Stack<>();
         op = new Stack<>();
    }

    /**
     * gcd
     *
     * 进行真分数的约分
     * @return int
     *///
    public int gcd(int m, int n)
    {
        int r = 1;
        if(m < n)
        {
            int temp =m; m = n; n = temp;
        }
        while( n >0)
        {
            r = m % n; m = n; n = r;
        }
        return m;
    }

    /**
     * 计算的具体实施过程
     * eval
     */
    public void eval()
    {
        Fraction second= num.peek();num.pop();
        //由于栈的顺序，第二个数字是先出来的

        Fraction first = num.peek(); num.pop();
        //第一个数字后出来

        Character o = op.peek(); op.pop();

        Fraction res = new Fraction();

        if(o.equals(charPlus)) {
            res = plus(first,second);
        }
        if(o.equals(charMinus)){
            res = minus(first,second);
        }
        if(o.equals(charMultiply)) {
            res = multiply(first,second);
        }
        if(o.equals(charDivide)){
            res = divide(first,second);
        }

        int gcd_num =gcd(res.a,res.b);
        res.a /= gcd_num;
        res.b /= gcd_num;

        num.push(res);
    }

    /**
     * 计算
     *
     * 具体的计算逻辑
     */
    public  void calculate(StringBuilder sb,Storage store)
    {

        String str = sb.toString();
        //1.先将StringBuffer转化为String

        for(int i = 0 ;i< str.length() ; i++)
        //此循环用于读入数字
        {
            Fraction fraction = new Fraction();
            int temp_a,temp_b ;

            char c = str.charAt(i);
            if(Character.isDigit(c))
            {
                int j = i,x = 0;
                while(j< str.length() && Character.isDigit(str.charAt(j)))
                {
                    x = x * 10 + (str.charAt(j++) - '0');
                }
                temp_a = x;
                j ++;
                x = 0;
                while(j< str.length() && Character.isDigit(str.charAt(j)))
                {
                    x = x * 10 +(str.charAt(j++) - '0');
                }
                i = j -1;
                temp_b = x;
                fraction.create(temp_a, temp_b);
                num.push(fraction);
            }
            else if(c == lefter) {
                op.push(c);
            }
                
            //遇到'('直接插入

            else if(c == righter)
            {
                while(!op.peek().equals(lefter)){
                    eval();
                }
                op.pop();
            }
            //遇到了')'后，直接运算，知道遇到'('

            else
            //遇到了加减乘除符号后的逻辑
            {
                while(op.size()>0 && !op.peek().equals(righter)
                        && !op.peek().equals(lefter) && h.get(c)<= h.get(op.peek())) {
                    eval();
                }
                {
                    op.push(c);
                }
            }
        }
        while(op.size()>0) {
            eval();
        }
        //Formulations已经扫描完成，但op栈里面还有剩下的符号

        Fraction ans = num.peek();
        num.pop();
        store.add_elem(ans);
    }
}
