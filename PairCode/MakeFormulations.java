package PairCode;

import java.lang.Math;

/**
 * 合成方程式
 *
 *基本逻辑：
 * 1.首先通过rand，生成了分子，分母，与符号
 * 2.再在make_string函数里面得到加入括号，实际使用的是StringBuilder
 *   善用StingBuilder里的.append();
 * 3.用Storage里的add_elem插入
 *
 * @author 86189
 * @date 2021/09/24
 */
public class MakeFormulations {

    Character[] operation = new Character[6];
    int[] number = new int[6];

    int[] fractionMom = new int[6];
    int[] fractionSon = new int[6];
    int cntOp, cntNum, cntFraction,flag ;

    /**
     * 随机生成
     *
     *用于随机生生成分子分母，符号
     *按照ReadIn中，给出的数据的上下界
     *随机生成，带分数，带括号的，小于1000的式子
     *
     * @param R r
     */
    public void Make_rand(ReadIn R)
    {
        flag = R.flag;
        cntOp = (int) (Math.random()*100)%2 + 2;
        cntNum = cntOp + 1;
        cntFraction = (int)(Math.random()*100) % cntNum;

        for(int i = 0; i<6;i++)
        {
            fractionMom[i]= (int) (Math.random()*1000) % (R.fractionMomHigh -R.fractionMomLow) +R.natureLow;
            fractionSon[i] = fractionMom[i] % 3 +1;
            number[i]=(int)(Math.random()*1000) % (R.natureHigh-R.natureLow) + R.natureLow;

            int choose =(int)( Math.random() *10) %4;
            if(choose == 0) {
                operation[i] = '+';
            }
            else if(choose == 1) {
                operation[i] = '-';
            }
            else if(choose == 2) {
                operation[i] = '*';
            }
            else {
                operation[i] = '/';
            }
        }
    }

    /**
     * 使字符串
     *
     * 将合并式子，并加入括号，生成的是能用于计算的格式
     *
     * @param store 商店
     * @return {@link StringBuilder}
     */
    public StringBuilder makeString(Storage store)
    {

        StringBuilder temp= new StringBuilder();
        StringBuilder formulation= new StringBuilder();
        int yes =0;
        int location = 0;   

        for(int i =0;i<cntNum;i++)
        {
            if(i > 0) {
                temp.append(operation[i]);
                if(operation[i] == '/') {
                    formulation.append("÷");
                }
                else if(operation[i] =='*')
                {
                    formulation.append("×");
                }
                else{
                    formulation.append(operation[i]);
                }
            }
            if(flag == 1 && yes == 0 && i<2 && (int)(Math.random()*100)%2 == 1&& i + 2 <= cntNum)
            {
                temp.append("(");
                formulation.append("(");
                yes = 1;
                location = i;
            }
            if((int)(Math.random()*10)%2==0)
            {
                temp.append(number[i]).append(",1");
                formulation.append(number[i]);
            }
            else  {
                temp.append(fractionSon[i]).append(",").append(fractionMom[i]);
                if(fractionMom[i]!=1) {
                    formulation.append(fractionSon[i]).append("/").append(fractionMom[i]);
                }
                else{
                    formulation.append(fractionSon[i]);
                }
            }
            if(flag== 1 && i> location+1  && yes==1 ) {
                yes = 0;
                temp.append(")");
                formulation.append(")");
            }
            if(flag == 1 && i == cntNum - 1 && yes == 1 ) {
                yes = 0;
                temp.append(")");
                formulation.append(")");
            }
        }
        store.add_elem(temp,formulation);
        return temp;
    }
}
