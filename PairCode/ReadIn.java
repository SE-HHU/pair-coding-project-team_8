package PairCode;

import  java.lang.NumberFormatException;
import java.util.Scanner;

/**
 * 读到
 *
 * 读入随机生成的数值的边界要求
 * 同时将这些边界条件存入ReadIn的类
 *
 * @author 86189
 * @date 2021/10/06
 */
public class ReadIn {
    int readOrRand;
    int totalNumber;
    int natureLow,natureHigh;
    int fractionLow,fractionHigh;
    int fractionMomLow, fractionMomHigh;
    int flag ;

    public int Read(){
        Scanner in = new Scanner(System.in);
        System.out.println("随机生成算式与答案，请输入1，算式判重与批改请按2");
        readOrRand = in.nextInt();

        if(readOrRand == 2) {
            return 2;
        }
        //如果是进行答案的校验功能，则以下边界情况不予读入，直接结束程序

        System.out.println("请输入你想要的式子的个数");
        totalNumber = in.nextInt();
        do{
            in.nextLine();
            try {
                System.out.println("请输入自然数的下界");
                natureLow =in.nextInt();
                System.out.println("请输入自然数的上界");
                natureHigh =in.nextInt();
            }
            catch(Exception e) {
                System.out.println("输入有误，请重新输入");
            }
        } while(natureLow >= natureHigh) ;
        do{
            in.nextLine();
            try {
                System.out.println("请输入分数的下界");
                fractionLow = in.nextInt();
                System.out.println("请输入分数的上界");
                fractionHigh = in.nextInt();
            }
            catch(Exception e) {
                System.out.println("输入有误，请重新输入");
            }
        } while(fractionLow >= fractionHigh) ;
        do{
            in.nextLine();
            try {
                System.out.println("请输入分母的下界");
                fractionMomLow= in.nextInt();
                System.out.println("请输入分母的上界");
                fractionMomHigh =in.nextInt();
            }
            catch(Exception e) {
                System.out.println("输入有误，请重新输入");
            }
        } while(fractionMomLow >= fractionMomHigh) ;
        System.out.println("请输入是否需要括号");
        flag = in.nextInt();
        return 1;
    }
}