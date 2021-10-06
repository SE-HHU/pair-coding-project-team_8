package PairCode;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

/**
 * 读  用于功能2的读入模块
 *
 * 将文件中的内容
 * 1.老师出的题目  写入Formulations_For_Handle
 * 2.学生给的答案  写入Ans_For_Test_Handle
 *
 * 以备进一步的格式处理
 *
 * Read_From_File
 * 用于读入 以上的1，2内容
 *
 *
 * @author 86189
 * @date 2021/09/24
 */
public class Read {

    int cntTotal;

    /**
     * 从文件读取
     *
     * 最终结果写入storage类里
     *
     * @param Formulations_For_Handle 用于格式处理的式子
     * @param Ans_For_Test_Handle     用于test且未经过处理的答案
     */
    public void readFromFile(Vector<StringBuilder> Formulations_For_Handle,
                             Vector<StringBuilder> Ans_For_Test_Handle)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入存储式子的文档的所在地址");
        String filenameExercises = scanner.nextLine();
        System.out.println("请输入存储答案的文档的所在地址");
        String filenameAns = scanner.nextLine();

        try{
            BufferedReader in = new BufferedReader(new FileReader(filenameExercises));
            BufferedReader in_ans = new BufferedReader(new FileReader(filenameAns));
            String formulation = in.readLine();


            while(formulation != null)
            {
                StringBuilder formu = new StringBuilder(formulation);
                Formulations_For_Handle.add(formu);
                cntTotal++;
                formulation = in.readLine();

            }
            in.close();

            String answers = in_ans.readLine();
            while(answers != null)
            {
                StringBuilder ans = new StringBuilder(answers);
                System.out.println(ans);
                Ans_For_Test_Handle.add(ans);
                answers = in_ans.readLine();
            }
            in_ans.close();
        }
        catch(IOException io){
            System.out.println("Wrong");
        }
    }
}
