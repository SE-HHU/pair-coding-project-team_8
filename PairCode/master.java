package PairCode;

/**
 * 主
 *
 * 主程序，主干部分
 * 分为两个部分
 * 第一部分用于功能1：输入式子中各个元素的边界，随机生成式子，然后计算，将式子和答案写入文件中
 * 第二部分用于功能2：读入给定的答案和式子，然后计算结果，给出正确或错误的数量和标号
 *
 * 新增的限定：先启动ReadIn，如果为1，继续读入并且进行功能1，如果为2，开启功能2
 *
 * 本程序的限制：
 * 1.所有的负号，都是减号的意思，而不能输入负数
 * 2.必须严格按照格式输入，目前程序的鲁棒性并不强，正在努力提高程序的鲁棒性
 *
 * @author 86189
 * @date 2021/10/06
 */
public class master {

    public static void main(String[] args) {

        Storage store = new Storage();
        Calculation cal = new Calculation();
        cal.h.put('+', 1);
        cal.h.put('-', 1);
        cal.h.put('/', 2);
        cal.h.put('*', 2);

        ReadIn read = new ReadIn();

        if(read.Read()==1) {

            //开始进行功能1的进程

            MakeFormulations M_F = new MakeFormulations();

            for(int i = 1 ; i<= read.totalNumber ; i++)
            {
                M_F.Make_rand(read);
                StringBuilder temp =  M_F.makeString(store);

                cal.calculate(temp,store);
            }

            System.out.println(store.ansForWrite.size());
            for(int i =0;i< store.ansForWrite.size();i++)
            {
                System.out.print(i+1+".");
                System.out.print(store.formulationsForWrite.get(i).append("="));
                System.out.print(store.ansForWrite.get(i).a);
                if(store.ansForWrite.get(i).b != 1) {
                    System.out.println("/" + store.ansForWrite.get(i).b);
                }
                else {
                    System.out.println();
                }
            }

            Write write = new Write();
            write.writeInFile(store.formulationsForWrite, store.ansForWrite);

        }
        else
        //开始进行功能2的进程
        {
            Read readFile = new Read();
            readFile.readFromFile(store.formulationsForHandle,store.ansForTestHandle);

            Handle handleFile = new Handle();

            handleFile.handleformulations(store.formulationsForHandle,store);
            handleFile.handle_ans(store.ansForTestHandle,store);

            for(int i = 0; i< readFile.cntTotal;i++)
            {
                StringBuilder temp = store.formulationsForCalculation.get(i);
                cal.calculate(temp,store);
            }

            Judgement judge = new Judgement();
            judge.Right_or_Wrong(store);
            judge.judgeRepeat(store);

            Write write = new Write();
            write.writeJudgementResultsInFile(store);
            System.out.println("  ");
        }
    }
}
