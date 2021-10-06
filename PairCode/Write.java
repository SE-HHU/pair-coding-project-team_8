package PairCode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * 写
 *
 * 将内容写入文件模块
 *
 * writeInFile函数
 * 用于将Formulations_For_Write和Ans_For_Write内的内容写入文件中
 * (功能1和功能2中都能使用)
 *
 * writeJudgementResultsInFile
 * 将判断过后的结果，按照要求格式，写入"Grade.txt"文件
 * 
 * @author 86189
 * @date 2021/10/06
 */
public class Write{

    //写入函数，将文件写入"Exercises.txt"和"Answers.txt"这两个文件

    public void writeInFile(Vector<StringBuilder> formulationsForWrite,
                            Vector<Fraction> ansForWrite)
    {
        String filename = "Exercises.txt";
        String filenameForAns = "Answers.txt";
        try {
            FileWriter write = new FileWriter(filename);
            FileWriter writeAns = new FileWriter(filenameForAns);

            for(int i = 0; i<formulationsForWrite.size(); i++)
            {
                write.write(i+1 + "." + formulationsForWrite.get(i));
                write.write("\r\n");
                if(ansForWrite.get(i).b == 1){
                    writeAns.write(i+1+"."+ansForWrite.get(i).a);
                }
                else {
                    writeAns.write(i + 1 + "." + ansForWrite.get(i).a + "/" + ansForWrite.get(i).b);
                }
                writeAns.write("\r\n");
            }
            write.close();
            writeAns.close();
        }
        catch (IOException iox){
            System.out.println("Problem Writing");
        }
    }

    //将功能2中的最后判断结果写入"Grade.txt"

    public void writeJudgementResultsInFile(Storage Store)
    {
        String filename = "Grade.txt";
        try{
            FileWriter writeGrade = new FileWriter(filename);
            writeGrade.write("Correct: "+Store.correct_num+" {");
            for(int i= 0; i< Store.Correct_array.size();i++)
            {
                writeGrade.write(Store.Correct_array.get(i)+"");
                if(i != Store.Correct_array.size()-1 )
                {
                    writeGrade.write(",");
                }
            }
            writeGrade.write("}");
            writeGrade.write("\r\n");

            writeGrade.write("Wrong: "+Store.wrong_num+" {");
            for(int i = 0 ;i<Store.Wrong_array.size();i++)
            {
                writeGrade.write(Store.Wrong_array.get(i)+"");
                if(i != Store.Wrong_array.size()-1)
                {
                    writeGrade.write(",");
                }
            }
            writeGrade.write("}");
            writeGrade.write("\r\n"+"Repeat:"+ Store.real_repeat.size()+"\r\n");

            writeGrade.write("RepeatDetail:"+"\r\n");
            for(int i = 0; i<Store.real_repeat.size(); i++)
            {
                writeGrade.write("("+(i+1) + ")");
                int j = 0;

                int one_index= Store.real_repeat.get(i).get(j);

                writeGrade.write(Store.formulationsForHandle.get(one_index-1).toString());
                for(int k = j+1;k <Store.real_repeat.get(i).size();k++)
                {
                    int two_index = Store.real_repeat.get(i).get(k);
                    writeGrade.write(" Repeat " );
                    writeGrade.write(Store.formulationsForHandle.get(two_index-1).toString());

                }
                   writeGrade.write("\r\n");
            }
            writeGrade.close();
        }

        catch (IOException io){
            System.out.println("Wrong");
        }
    }
}
