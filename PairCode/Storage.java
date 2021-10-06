package PairCode;

import java.util.Vector;

/**
 * 存储
 * 核心数据的存储所在的模块
 * 本程序以Vector为主
 *
 * @author 86189
 * @date 2021/10/06
 */
public class Storage {
    Vector<StringBuilder> formulationsForCalculation= new Vector<>();
    // 此向量中是用于计算的式子  形式: 1,1+2,3+5,1*6,1 ( 1+2/3+5*6 )

    Vector<StringBuilder>  formulationsForWrite = new Vector<>();

    //用于写在文件中的式子  1,1+2,3+5,1*6,1 -> 1.1+2/3+5*6=

    Vector<Fraction> ansForWrite = new Vector<>();
    //用于写在文件中的答案  1.a

    Vector<Fraction> ansForTest = new Vector<>();
    //从文件中读出的且已经经过格式处理后的答案，用于检验对错

    Vector<StringBuilder> ansForTestHandle = new Vector<>();
    //从文件中读取的答案且还未经过格式处理

    Vector<StringBuilder> ansForTestHandleNew = new Vector<>();

    Vector<StringBuilder> formulationsForHandle = new Vector<>();
    // 将formulationsForHandle 处理一下后，存进formulationsForCalculation

    Vector<StringBuilder> formulationsForHandleNew = new Vector<>();

    Vector<Integer> Correct_array = new Vector<>();
    Vector<Integer> Wrong_array = new Vector<>();

    //存储正确答案，错误答案的序号

    Vector<Vector<Integer>> potential_repeat = new Vector<>();
    //答案相同的式子序号，还需进一步确认是否是重复

    Vector<Vector<Integer>> real_repeat = new Vector<>();
    //重复已经是实锤了的式子序号



    int correct_num,wrong_num;
    //计算正确的答案数量，计算错误的答案数量

    //以下三个为add_elem的重载，用于将数据书写进storage类
    public void add_elem(StringBuilder temp, StringBuilder formulation)
    {
        formulationsForCalculation.add(temp);
        formulationsForWrite.add(formulation);
    }
    public void add_elem(Fraction Ans)
    {
        ansForWrite.add(Ans);
    }
    public void add_elem(StringBuilder formulationsForHandle)
    {
        formulationsForCalculation.add(formulationsForHandle);
    }

    //将读入的学生的用于测试正确与否的答案
    public void add_elem_to_test_ans(Fraction ansForTestHandle)
    {
        ansForTest.add(ansForTestHandle);
    }
}
